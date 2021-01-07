import abc
import math
import semver
from datetime import datetime
from typing import List, Iterable, Union, Tuple, Optional, Callable, Any

from aoe_rms_spoon.ILeafRMSStatement import ILeafRMSStatement
from aoe_rms_spoon.INonTerminalRMSStatement import INonTerminalRMSStatement
from aoe_rms_spoon.exceptions import RMSSemanticErrorException, RMSSyntaxErrorException, RMSSemanticWarningException
from aoe_rms_spoon import IRMSStatement
from aoe_rms_spoon.models import CodeGenerationOutput, SemanticCheckOutput
from aoe_rms_spoon.with_statements import WithDefine, WithComment, WithConst, WithInclude, WithIncludeDrs


class Expression(IRMSStatement, abc.ABC):

    def __init__(self):
        super().__init__()
        self.children: List["Expression"] = []

    def is_section_declaration(self) -> bool:
        return False

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        if self.has_direct_child_different_than_typenames(
                "DefineLiteral", "ConstLiteral", "DictExpression",
                "IntegerLiteral", "FloatLiteral", "BooleanLiteral"):
            raise RMSSyntaxErrorException(f"Expected expression, got somethign different!")
        return self.default_semantic_validate(semantic_input)

    def get_children(self) -> List["Expression"]:
        return self.children

    @abc.abstractmethod
    def get_type(self) -> type:
        """
        Type of the expression
        :return:
        """
        pass

    @abc.abstractmethod
    def _can_be_seen_as_bool(self, **kwargs) -> bool:
        """
        :return: True if we can see this expression as a bool
        """
        pass

    @abc.abstractmethod
    def _can_be_seen_as_int(self, **kwargs) -> int:
        """
        :return: True if we can see this expression as a int
        :return:
        """
        pass

    @abc.abstractmethod
    def _can_be_seen_as_float(self, **kwargs) -> float:
        """
        :return: True if we can see this expression as a float
        :return:
        """
        pass

    def is_boolean(self, **kwargs) -> bool:
        """

        :return: true if we know for sure that this is a boolean value
        """
        if len(self.children) > 1:
            raise TypeError(f"Cannot compute type of expression!")
        elif len(self.children) == 0:
            raise TypeError(f"Cannot compute type of expression! No elements!")
        else:
            self.children[0]._can_be_seen_as_bool(**kwargs)
            return True

    def is_int(self, **kwargs) -> bool:
        """

        :return: true if we know for sure that this is a int value
        """
        if len(self.children) > 1:
            raise TypeError(f"Cannot compute type of expression! kwargs={kwargs}")
        elif len(self.children) == 0:
            raise TypeError(f"Cannot compute type of expression! No elements! kwargs={kwargs}")
        else:
            self.children[0]._can_be_seen_as_int(**kwargs)
            return True

    def is_float(self, **kwargs) -> bool:
        """

        :return: true if we know for sure that this is a float value
        """
        if len(self.children) > 1:
            raise TypeError(f"Cannot compute type of expression!")
        elif len(self.children) == 0:
            raise TypeError(f"Cannot compute type of expression! No elements!")
        else:
            self.children[0]._can_be_seen_as_float(**kwargs)
            return True

# ##################################################
# ABSTARCT CLASSES
# ##################################################


class AbstractSectionDeclarationStmt(INonTerminalRMSStatement, WithDefine, WithComment, WithConst, WithInclude, WithIncludeDrs, abc.ABC):
    """
    Define a RMS section (e.g., OBJECT_GENERATION)
    """

    def __init__(self):
        super().__init__()

    @abc.abstractmethod
    def get_rms_name(self) -> str:
        """
        The name fo the seciton that needs to be put in the RMS file
        :return:
        """
        pass

    def is_section_declaration(self) -> bool:
        return True

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        result = list()
        result.append(f"/* {'#' * 30} */")
        result.append(f"<{self.get_rms_name()}>")
        result.append(f"/* {'#' * 30} */")
        output = self.default_code_generation(context)
        result.extend(output.rms)
        if output.newline:
            result.append("")
        return CodeGenerationOutput(result, True, context)


class AbstractCommandStmt(INonTerminalRMSStatement, abc.ABC):
    """
    A generic RMS command, like ai_info_map_type or create_player_lands.
    Each childre is an argument of the command
    """

    def __init__(self):
        super().__init__()

    def get_name(self) -> str:
        return self.get_command_name()

    def is_section_declaration(self) -> bool:
        return False

    def min_arity(self) -> int:
        """
        Minimum number of arguments the command can accept
        :return:
        """
        return len(list(filter(lambda x: x[1], self.get_arguments())))

    def max_arity(self) -> int:
        """
        Maximum number of arguments the command can accept
        :return:
        """

        return len(self.get_arguments())

    def set_argument(self, index: int, argument: Expression):
        """
        Set the command argument
        :param index: index of the argument
        :param argument: argument value
        :return:
        """
        self.add_or_replace_child(index, argument)

    @abc.abstractmethod
    def get_command_name(self) -> str:
        """
        The name of the command
        :return:
        """
        pass

    @abc.abstractmethod
    def get_command_help(self) -> str:
        """
        Documentation for this command
        :return:
        """
        pass

    def get_actual_arguments(self) -> Iterable["IRMSStatement"]:
        """
        Get the actual arguments of the command. Defaults to its children
        :return:
        """
        return self.get_children()

    @abc.abstractmethod
    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        """

        :return: ordered list of the argument of the command. Each tuple rpersents an argument.
        The frist value in the pair is the paraemter formal type whiel the seocnd is true if it is optional.
        The third is the description of what the argument does
        """
        pass

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        signature = self.get_arguments()
        # we assume each children is a argument
        actual_parameters_number = len(list(self.get_actual_arguments()))

        min_arity = self.min_arity()
        max_arity = self.max_arity()
        if actual_parameters_number not in range(min_arity, max_arity + 1):
            raise RMSSemanticErrorException(f"command {self.get_command_name()} required at least {min_arity} arguments and at most {max_arity}! You input {actual_parameters_number}!")

        detected_as_optional = False
        for i, (formal_type, is_optional, comment) in enumerate(signature):
            # we first assume you put all the required arguments
            if i >= len(signature):
                # now we assume they are all optional arguments!
                detected_as_optional = True

            if detected_as_optional and not is_optional:
                raise RMSSemanticErrorException(f"We have consumed all the arguments, but the signature {self.get_command_name()} required us to input another required argument!")
            elif not detected_as_optional and not is_optional:
                # we need to consume a required argument: check if the type is correct

                self.ensure_ith_child_has_type(i, formal_type)
        return None

    def code_generation(self, context: "CodeGenerationInput") -> CodeGenerationOutput:
        result = list()

        result.append(self.get_command_name())
        for i, child in enumerate(self.get_actual_arguments()):
            output = child.code_generation(context)
            result.extend(output.rms)
            if output.newline:
                result.append("")

        return CodeGenerationOutput(result, True, context)


class AbstractNoArgumentCommand(AbstractCommandStmt, abc.ABC):
    """
    A command which requires no arguments. Convenience class
    """

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return []


class AbstractSingleArgumentCommand(AbstractCommandStmt, abc.ABC):
    """
    A command which requires a single argument. Convenience method
    """

    def __init__(self, expr: "Expression"):
        super().__init__()
        self.set_argument(0, expr)

    @abc.abstractmethod
    def get_argument_type(self) -> type:
        """
        type of the only argument for the command
        :return:
        """
        pass

    @abc.abstractmethod
    def is_optional(self) -> bool:
        """
        the first argument is optional?
        :return:
        """
        pass

    @abc.abstractmethod
    def get_argument_comment(self) -> str:
        """
        comment of the only argument
        :return:
        """
        pass

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return [(
            self.get_argument_type(),
            self.is_optional(),
            self.get_argument_comment()
        )]


class AbstractSingleIntCommandStmt(AbstractSingleArgumentCommand, abc.ABC):
    """
    A command specifying a single int argument
    """

    def get_argument_type(self) -> type:
        return int


class AbstractSingleBoolCommand(AbstractSingleArgumentCommand, abc.ABC):
    """
    A command specifying a single bool argument
    """

    @abc.abstractmethod
    def get_argument_type(self) -> type:
        return bool


class AbstractSingleFloatCommand(AbstractSingleArgumentCommand, abc.ABC):
    """
    A command specifying a single float argument
    """

    @abc.abstractmethod
    def get_argument_type(self) -> type:
        return float


class AbstractSingleDictCommand(AbstractSingleArgumentCommand, abc.ABC):
    """
    A command specifying a single dict argument
    """

    @abc.abstractmethod
    def get_argument_type(self) -> type:
        return dict

# ###############################################
# LITERALS
# ###############################################


class IntegerLiteral(Expression):
    """
    A plain number
    """

    def __init__(self, val: int):
        super().__init__()
        self.__val = val

    def get_name(self) -> str:
        return f"integer {self.__val}"

    def _can_be_seen_as_bool(self, **kwargs) -> bool:
        return self.__val == 0 or self.__val == 1

    def _can_be_seen_as_int(self, **kwargs) -> bool:
        return True

    def _can_be_seen_as_float(self) -> bool:
        return True

    def is_boolean(self, **kwargs) -> bool:
        return False

    def is_int(self, **kwargs) -> bool:
        return True

    def is_float(self, **kwargs) -> bool:
        return False

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        return None

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        return CodeGenerationOutput([f"{self.__val}"], False, context)

    def get_type(self) -> type:
        return int


class FloatLiteral(Expression):
    """
    A plain number
    """

    def __init__(self, val: float):
        super().__init__()
        self.__val = val

    def get_name(self) -> str:
        return f"float {self.__val}"

    def _can_be_seen_as_bool(self, **kwargs) -> bool:
        return False

    def _can_be_seen_as_int(self, **kwargs) -> bool:
        return math.modf(self.__val)[1] == 0.

    def _can_be_seen_as_float(self, **kwargs) -> bool:
        return True

    def is_boolean(self, **kwargs) -> bool:
        return False

    def is_int(self, **kwargs) -> bool:
        return False

    def is_float(self, **kwargs) -> bool:
        return True

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        return None

    def code_generation(self, context: "CodeGenerationInput") -> CodeGenerationOutput:
        return CodeGenerationOutput([f"{self.__val}"], False, context)

    def get_type(self) -> type:
        return float


class BooleanLiteral(Expression):
    """
    A plain boolean
    """

    def __init__(self, val: bool):
        super().__init__()
        self.__val = val

    def get_name(self) -> str:
        return f"boolean {self.__val}"

    def _can_be_seen_as_bool(self, **kwargs) -> bool:
        return True

    def _can_be_seen_as_int(self, **kwargs) -> bool:
        return True

    def _can_be_seen_as_float(self, **kwargs) -> float:
        return False

    def is_boolean(self, **kwargs) -> bool:
        return True

    def is_int(self, **kwargs) -> bool:
        return False

    def is_float(self, **kwargs) -> bool:
        return False

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        return None

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        return CodeGenerationOutput([f"{1 if self.__val else 0}"], False, context)

    def get_type(self) -> type:
        return bool


class DefineLiteral(Expression):
    """
    A single identifier represeint ga condition that may or may not be satisfied
    """

    def __init__(self, name: str):
        super().__init__()
        self.__name = name

    def get_name(self) -> str:
        return f"define {self.__name}"

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        # we may have defined or not the symbol. Regardless the semantic should be respected
        return None

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        return CodeGenerationOutput([f"{self.__name}"], False, context)

    def get_type(self) -> type:
        return bool

    def _can_be_seen_as_bool(self, **kwargs) -> bool:
        # define is an implicit boolean
        return True

    def _can_be_seen_as_int(self, **kwargs) -> int:
        return False

    def _can_be_seen_as_float(self, **kwargs) -> float:
        return False

    def is_boolean(self, **kwargs) -> bool:
        return True

    def is_int(self, **kwargs) -> bool:
        return False

    def is_float(self, **kwargs) -> bool:
        return False


class ConstLiteral(Expression):
    """
    A single identifier representing a #const
    """

    def get_name(self) -> str:
        return f"const {self.__name}"

    def __init__(self, name: str):
        super().__init__()
        self.__name = name

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        semantic_input.raise_if_not_identifier(self.__name)
        semantic_input.raise_if_not_const(self.__name, semantic_input)
        return None

    def code_generation(self, context: "CodeGenerationInput") -> CodeGenerationOutput:
        return CodeGenerationOutput([f"{self.__name}"], False, context)

    def get_type(self) -> type:
        return int

    def _can_be_seen_as_bool(self) -> bool:
        return True

    def _can_be_seen_as_int(self) -> int:
        return True

    def _can_be_seen_as_float(self) -> float:
        return True

    def is_boolean(self, **kwargs) -> bool:
        return False

    def is_int(self, **kwargs) -> bool:
        return True

    def is_float(self, **kwargs) -> bool:
        return False


class DictExpression(Expression):
    """
    A dictionary as argument of a command (e.g., create_players_lands)
    """

    def __init__(self, *arguments):
        """

        :param arguments: Commands to put in the dictionary of commands
        """
        super().__init__()
        for arg in arguments:
            self.add_command(arg)

    def get_name(self) -> str:
        return f"listern dict #{len(self.get_children())}"

    def get_type(self) -> type:
        return dict

    def _can_be_seen_as_bool(self, **kwargs) -> bool:
        return False

    def _can_be_seen_as_int(self, **kwargs) -> int:
        return False

    def _can_be_seen_as_float(self, **kwargs) -> float:
        return False

    def is_boolean(self, **kwargs) -> bool:
        return False

    def is_int(self, **kwargs) -> bool:
        return False

    def is_float(self, **kwargs) -> bool:
        return False

    def add_command(self, stmt: "IRMSStatement") -> "DictExpression":
        self.add_statement(stmt)
        return self

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        result = list()
        result.append("{")

        for s in self.get_children():
            output = s.code_generation(context)
            result.extend(output.rms)
            if output.newline:
                result.append("")

        result.append("}")
        return CodeGenerationOutput(result, False, context)

    @classmethod
    def new(cls) -> "DictExpression":
        return DictExpression()


# ###############################################
# FACTORY METHODS
# ###############################################


def make_integer(val: int) -> "Expression":
    return IntegerLiteral(val)


def make_float(val: float) -> "Expression":
    return FloatLiteral(val)


def make_boolean(val: bool) -> "Expression":
    return BooleanLiteral(val)


def make_const(val: str) -> "ConstLiteral":
    return ConstLiteral(val)


def define(val: str) -> "DefineLiteral":
    return DefineLiteral(val)

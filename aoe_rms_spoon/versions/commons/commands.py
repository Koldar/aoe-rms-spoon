from typing import List, Tuple, Union, Optional

from aoe_rms_spoon import rms_tools
from aoe_rms_spoon.ILeafRMSStatement import ILeafRMSStatement
from aoe_rms_spoon.exceptions import RMSSemanticErrorException, RMSSemanticWarningException, RMSSyntaxErrorException
from aoe_rms_spoon.models import CodeGenerationOutput, SemanticCheckOutput
from aoe_rms_spoon.nodes import AbstractCommandStmt, AbstractSingleIntCommandStmt, AbstractNoArgumentCommand


# ###############################################
# COMMONS COMMANDS
# ###############################################


class Comment(ILeafRMSStatement):
    """
    A Block comment
    """

    def get_name(self) -> str:
        return "comment"

    def __init__(self, comment: Union[str, List[str]]):
        super().__init__()
        self.comment = comment

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        result = list()
        result.append("/*")
        if isinstance(self.comment, str):
            result.append(self.comment)
        elif isinstance(self.comment, list):
            result.extend(list(self.comment))
        else:
            raise TypeError(f"invalid comment type ({type(self.comment)}")

        result.append("*/")
        return CodeGenerationOutput(result, True, context)

    def is_section_declaration(self) -> bool:
        return False

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        return SemanticCheckOutput.ok(semantic_input)


class IncludeDrs(ILeafRMSStatement):
    """
    Include Dsr statement
    """

    def get_name(self) -> str:
        return "include_drs"

    def __init__(self, inc_file: str):
        """
        build an include.
        :param inc_file: needs to include inc extensiont
        """
        super().__init__()
        self.__inc_file = inc_file

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        return CodeGenerationOutput([f"#include_drs {self.__inc_file}"], True, context)

    def is_section_declaration(self) -> bool:
        return False

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        if not self.__inc_file.endswith(".inc"):
            semantic_input.warnings.append(RMSSemanticWarningException(f"""{self.get_path()}: {self.__inc_file} does not end with .inc extension"""))
        return SemanticCheckOutput.ok(semantic_input)


class Include(ILeafRMSStatement):
    """
    Include statement
    """

    def get_name(self) -> str:
        return "include"

    def __init__(self, inc_file: str):
        """
        build an include.
        :param inc_file: needs to include inc extensiont
        """
        super().__init__()
        self.__inc_file = inc_file

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        return CodeGenerationOutput([f"#include {self.__inc_file}"], True, context)

    def is_section_declaration(self) -> bool:
        return False

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        if not self.__inc_file.endswith(".inc"):
            semantic_input.warnings.append(RMSSemanticWarningException(f"""{self.get_path()}: {self.__inc_file} does not end with .inc extension"""))
        return SemanticCheckOutput.ok(semantic_input)


class Define(ILeafRMSStatement):
    """
    Defin3e statement
    """

    def get_name(self) -> str:
        return "define"

    def __init__(self, condition: str):
        """
        build an include.
        :param condition: identifier to check if it is defined or not
        """
        super().__init__()
        self.__condition: str = condition

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        return CodeGenerationOutput([f"#define {self.__condition}"], True, context)

    def is_section_declaration(self) -> bool:
        return False

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        if not rms_tools.is_identifier(self.__condition):
            raise RMSSyntaxErrorException(f"{self.get_path()}: {self.__condition} is not a valid RMS identifier")
        return SemanticCheckOutput.ok(semantic_input)


class Const(ILeafRMSStatement):
    """
    #const statement
    """

    def get_name(self) -> str:
        return "const"

    def __init__(self, name: str, value: int):
        super().__init__()
        self.__name = name
        self.__value = value

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        if semantic_input.has_const(self.__name):
            raise RMSSemanticErrorException(f"You are trying to set const {self.__name} twice! Invalid operation")
        semantic_input.defined_const[self.__name] = self.__value
        return SemanticCheckOutput.ok(semantic_input)

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        return CodeGenerationOutput([f"#const {self.__name} {self.__value}"], True, context)


class StandardRandomPlacementStmt(AbstractCommandStmt):
    """
    random_placement statement
    """

    def get_command_name(self) -> str:
        return "random_placement"

    def get_command_help(self) -> str:
        return """Randomly place the players"""

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return []

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> "Optional[SemanticCheckOutput]":
        if self.has_sibling_among_types(StandardGroupedByTeamStmt, StandardRandomPlacementStmt):
            raise RMSSemanticErrorException(f"random_placement mututally exclusive with grouped_by_team!")
        return None


class StandardGroupedByTeamStmt(AbstractCommandStmt):
    """
    grouped_by_team statement
    """

    def __init__(self):
        super().__init__()

    def get_command_name(self) -> str:
        return "grouped_by_team"

    def get_command_help(self) -> str:
        return """Place the team members near eachothers"""

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return []

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> "Optional[SemanticCheckOutput]":
        if self.has_sibling_among_types(StandardGroupedByTeamStmt, StandardRandomPlacementStmt):
            raise RMSSemanticErrorException(f"random_placement mututally exclusive with grouped_by_team!")
        return None


class StandardAiInfoMapTypeStmt(AbstractCommandStmt):

    def get_command_name(self) -> str:
        return "ai_info_map_type"

    def get_command_help(self) -> str:
        return """
            Set the AI type that will be used in this map
        """

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return [
            (int, False, "map"),
            (bool, False, "isNomad"),
            (bool, False, "isMichi"),
            (bool, False, "ShowType")
        ]

    def set_map(self, expr: "aoe_rms_spoon.nodes.Expression"):
        """
        Set map of IA
        :param expr:
        :return:
        """
        self.set_argument(0, expr)

    def set_is_nomad(self, expr: "aoe_rms_spoon.nodes.Expression"):
        self.set_argument(1, expr)

    def set_is_michi(self, expr: "aoe_rms_spoon.nodes.Expression"):
        self.set_argument(2, expr)

    def set_show_type(self, expr: "aoe_rms_spoon.nodes.Expression"):
        self.set_argument(3, expr)


class StandardBaseTerrainStmt(AbstractCommandStmt):

    def get_command_name(self) -> str:
        return "base_terrain"

    def get_command_help(self) -> str:
        return """
        Fuill the entire map with the terrain given
        """

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return [
            (int, False, "The terrain that will be sued to fill the entire map")
        ]

    def set_terrain(self, terrain: "aoe_rms_spoon.nodes.Expression"):
        self.set_argument(0, terrain)


class StandardCreatePlayerLandsStmt(AbstractCommandStmt):

    def get_command_name(self) -> str:
        return "create_player_lands"

    def get_command_help(self) -> str:
        return """
            Create for every player a terrain which is owned by the specific player
        """

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return [
            (dict, False, "Options containing the specification of the land to generate")
        ]

    def set_terrain_specifics(self, d: "DictExpression"):
        self.set_argument(0, d)
        return self

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> "Optional[SemanticCheckOutput]":
        from aoe_rms_spoon.versions.commons.sections import StandardPlayerSetupSection
        self.ensure_parent_is_of_type(StandardPlayerSetupSection)
        return self.default_semantic_validate(semantic_input)


class StandardTerrainType(AbstractSingleIntCommandStmt):

    def is_optional(self) -> bool:
        return False

    def get_argument_comment(self) -> str:
        return "terrain to use"

    def get_command_name(self) -> str:
        return "terrain_type"

    def get_command_help(self) -> str:
        return """
            The terrain to use for this command
        """


class StandardBaseSize(AbstractSingleIntCommandStmt):

    def is_optional(self) -> bool:
        return False

    def get_argument_comment(self) -> str:
        return ""

    def get_command_name(self) -> str:
        return "base_size"

    def get_command_help(self) -> str:
        return """

        """


class StandardLandPercent(AbstractSingleIntCommandStmt):

    def is_optional(self) -> bool:
        return False

    def get_argument_comment(self) -> str:
        return ""

    def get_command_name(self) -> str:
        return "land_percent"

    def get_command_help(self) -> str:
        return """

        """

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return [(int, False, "")]


class StandardClumpingFactor(AbstractSingleIntCommandStmt):

    def is_optional(self) -> bool:
        return False

    def get_argument_comment(self) -> str:
        return ""

    def get_command_name(self) -> str:
        return "clumping_factor"

    def get_command_help(self) -> str:
        return """

        """

    def get_arguments(self) -> List[Tuple[type, bool, str]]:
        return [(int, False, "")]


class StandardBorderFuzziness(AbstractSingleIntCommandStmt):
    def is_optional(self) -> bool:
        return False

    def get_argument_comment(self) -> str:
        return ""

    def get_command_name(self) -> str:
        return "border_fuzziness"

    def get_command_help(self) -> str:
        return ""


class StandardSetZoneByTeam(AbstractNoArgumentCommand):
    def get_command_name(self) -> str:
        return "set_zone_by_team"

    def get_command_help(self) -> str:
        return ""


class StandardOtherZoneAvoidanceDistance(AbstractSingleIntCommandStmt):
    def is_optional(self) -> bool:
        return False

    def get_argument_comment(self) -> str:
        return ""

    def get_command_name(self) -> str:
        return "other_zone_avoidance_distance"

    def get_command_help(self) -> str:
        return ""


class StandardCirclePlacement(AbstractNoArgumentCommand):
    def get_command_name(self) -> str:
        return "circle_placement"

    def get_command_help(self) -> str:
        return ""


class StandardCircleRadius(AbstractSingleIntCommandStmt):
    def is_optional(self) -> bool:
        return False

    def get_argument_comment(self) -> str:
        return ""

    def get_command_name(self) -> str:
        return "circle_radius"

    def get_command_help(self) -> str:
        return ""

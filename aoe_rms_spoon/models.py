import abc
from typing import Optional, Dict, List

from aoe_rms_spoon import rms_tools
from aoe_rms_spoon.exceptions import RMSSyntaxErrorException, RMSSemanticWarningException
from aoe_rms_spoon.rms_models import Condition, Terrain


class CodeGenerationInput:

    def __init__(self):
        pass


class CodeGenerationOutput:
    """
    Output of generate of IRMSStatement class
    """

    def __init__(self, rms: List[str], newline: bool, context: CodeGenerationInput):
        self.rms = rms
        self.newline = newline
        self.context = CodeGenerationInput


class SemanticCheckInput:

    def __init__(self, aoe_version: "AbstractAoeVersion"):
        self.defined_conditions: Dict[str, Condition] = {}
        """
        All the condition defined up until now
        """
        self.defined_const: Dict[str, int] = {}
        """
        All the #cosntdefined up until now
        """
        self.aoe_version = aoe_version
        """
        The version you are currently targeting in order to generate the RMS file
        """
        self.warnings: List[RMSSemanticWarningException] = []
        """
        List of warning detected during generation
        """

    def has_condition(self, name: str) -> bool:
        """
        Check if the symbol table cotnains the specified condition
        :param name:
        :return:
        """
        return name in self.defined_conditions

    def raise_if_not_condition(self, name: str, semantic_input: "SemanticCheckInput"):
        if not self.has_condition(name):
            semantic_input.warnings.append(RMSSemanticWarningException(f"condition '{name}' is not defined!"))

    def raise_if_not_const(self, name: str, semantic_input: "SemanticCheckInput"):
        if not self.has_const(name):
            semantic_input.warnings.append(RMSSemanticWarningException(f"#const '{name}' is not defined!"))

    def raise_if_not_identifier(self, name: str):
        if not rms_tools.is_identifier(name):
            raise RMSSyntaxErrorException(f"Expected idenfier. Got '{name}'!")


    def has_const(self, name: str) -> bool:
        """
        Check if the symbol table cotnains the specified const
        :param name:
        :return:
        """
        return name in self.defined_const


class SemanticCheckOutput:

    def __init__(self, semantic_input: SemanticCheckInput, exception: Optional[Exception]):
        self.semantic_input = semantic_input
        self.exception: Optional[Exception] = exception

    @classmethod
    def ok(cls, semantic_input: SemanticCheckInput):
        return SemanticCheckOutput(semantic_input, None)

    @classmethod
    def error(cls, semantic_input: SemanticCheckInput, exception: Exception):
        return SemanticCheckOutput(semantic_input, exception)

    def is_correct(self) -> bool:
        return self.exception is None

    def is_error(self) -> bool:
        return self.exception is not None


class TerrainContainer(abc.ABC):

    def __init__(self):
        self.terrains: List[Terrain] = []

    def get_terrain_by_name(self, name: str) -> Terrain:
        for terrain in self.terrains:
            if name in terrain.names:
                return terrain
        else:
            raise KeyError(f"Cannot find terrain with name {name}!")
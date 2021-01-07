import abc
from typing import Optional

from aoe_rms_spoon.INonTerminalRMSStatement import INonTerminalRMSStatement
from aoe_rms_spoon.with_statements import WithDefine, WithComment, WithAddFileInfo, WithIncludeDrs, WithInclude, \
    WithConst, WithPlayerSetup


class AbstractRootRMSStatement(INonTerminalRMSStatement, WithDefine, WithComment, WithAddFileInfo, WithIncludeDrs, WithInclude, WithConst, WithPlayerSetup, abc.ABC):
    """
    Root of the RMS syntax tree we are building
    """

    def __init__(self, aoe_version: "AbstractAoeVersion"):
        super().__init__()
        self.aoe_version: "AbstractAoeVersion" = aoe_version

    def get_name(self) -> str:
        return "root"

    def semantic_validate(self, semantic_input: "SemanticCheckInput") -> Optional["SemanticCheckOutput"]:
        return self.default_semantic_validate(semantic_input)

    def code_generation(self, context: "CodeGenerationInput") -> "CodeGenerationOutput":
        return self.default_code_generation(context)

    def is_section_declaration(self) -> bool:
        return False

    def get_aoe_version(self) -> "AbstractAoeVersion":
        return self.aoe_version

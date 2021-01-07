from typing import Optional, TypeVar, Generic

from aoe_rms_spoon.models import SemanticCheckInput, SemanticCheckOutput
from aoe_rms_spoon.nodes import AbstractSectionDeclarationStmt
from aoe_rms_spoon.versions.commons import StandardCreatePlayerLandsStmt

TCREATE_PLAYER_LANDS = TypeVar('TCREATE_PLAYER_LANDS', bound=StandardCreatePlayerLandsStmt)


class StandardPlayerSetupSection(AbstractSectionDeclarationStmt, Generic[TCREATE_PLAYER_LANDS]):

    def get_name(self) -> str:
        return "player section"

    def __init__(self):
        super().__init__()

    def get_rms_name(self) -> str:
        return "PLAYER_SETUP"

    def semantic_validate(self, semantic_input: SemanticCheckInput) -> Optional[SemanticCheckOutput]:
        return self.default_semantic_validate(semantic_input)

    def create_player_lands(self) -> "TCREATE_PLAYER_LANDS":
        result = self.get_aoe_version().create_player_lands()

        self.add_statement(result)

        return result


class StandardLandGenerationSection(AbstractSectionDeclarationStmt):

    def get_name(self) -> str:
        return "land generation"

    def __init__(self):
        super().__init__()

    def get_rms_name(self) -> str:
        return "LAND_GENERATION"

    def semantic_validate(self, semantic_input: SemanticCheckInput) -> Optional[SemanticCheckOutput]:
        return self.default_semantic_validate(semantic_input)


class StandardElevationGenerationSection(AbstractSectionDeclarationStmt):

    def get_name(self) -> str:
        return "elevation generation"

    def __init__(self):
        super().__init__()

    def get_rms_name(self) -> str:
        return "ELEVATION_GENERATION"

    def semantic_validate(self, semantic_input: SemanticCheckInput) -> Optional[SemanticCheckOutput]:
        return self.default_semantic_validate(semantic_input)


class StandardCliffGenerationSection(AbstractSectionDeclarationStmt):

    def get_name(self) -> str:
        return "cliff generation"

    def __init__(self):
        super().__init__()

    def get_rms_name(self) -> str:
        return "CLIFF_GENERATION"

    def semantic_validate(self, semantic_input: SemanticCheckInput) -> Optional[SemanticCheckOutput]:
        return self.default_semantic_validate(semantic_input)


class StandardTerrainGenerationSection(AbstractSectionDeclarationStmt):

    def get_name(self) -> str:
        return "terrain generation"

    def __init__(self):
        super().__init__()

    def get_rms_name(self) -> str:
        return "TERRAIN_GENERATION"

    def semantic_validate(self, semantic_input: SemanticCheckInput) -> Optional[SemanticCheckOutput]:
        return self.default_semantic_validate(semantic_input)


class StandardConnectionGenerationSection(AbstractSectionDeclarationStmt):

    def get_name(self) -> str:
        return "connection generation"

    def __init__(self):
        super().__init__()

    def get_rms_name(self) -> str:
        return "CONNECTION_GENERATION"

    def semantic_validate(self, semantic_input: SemanticCheckInput) -> Optional[SemanticCheckOutput]:
        return self.default_semantic_validate(semantic_input)


class StandardObjectsGenerationSection(AbstractSectionDeclarationStmt):

    def get_name(self) -> str:
        return "objects generation"

    def __init__(self):
        super().__init__()

    def get_rms_name(self) -> str:
        return "OBJECTS_GENERATION"

    def semantic_validate(self, semantic_input: SemanticCheckInput) -> Optional[SemanticCheckOutput]:
        return self.default_semantic_validate(semantic_input)

from typing import Set

from aoe_rms_spoon.AbstractAoeVersion import AbstractAoeVersion, TROOT, TPLAYER_SETUP, TCLIFF_GENERATION, TELEVATION_GENERATION, TLAND_GENERATION, TOBJECTS_GENERATION, TTERRAIN_GENERATION, TCONNECTION_GENERATION
from aoe_rms_spoon.models import TerrainContainer
from aoe_rms_spoon.versions.commons import sections
from aoe_rms_spoon.versions.aoe_2_de.nodes import AOE2DERootRMSStatement


class AoEDEIIVersion(AbstractAoeVersion):

    def __init__(self):
        super().__init__()

    def create_root(self) -> "TROOT":
        return AOE2DERootRMSStatement(aoe_version=self)

    def available_functions(self) -> Set[type]:
        return set()

    def name(self) -> str:
        return f"Age of Empires II: Definitive Edition"

    def terrains(self) -> TerrainContainer:
        result = TerrainContainer()

        return result

    def available_commands(self) -> Set[type]:
        return set()

    def player_setup(self) -> "TPLAYER_SETUP":
        return sections.StandardPlayerSetupSection()

    def land_generation(self) -> "TLAND_GENERATION":
        return sections.StandardLandGenerationSection()

    def elevation_generation(self) -> "TELEVATION_GENERATION":
        return sections.StandardElevationGenerationSection()

    def cliff_generation(self) -> "TCLIFF_GENERATION":
        return sections.StandardCliffGenerationSection()

    def terrain_generation(self) -> "TTERRAIN_GENERATION":
        return sections.StandardTerrainGenerationSection()

    def connection_generation(self) -> "TCONNECTION_GENERATION":
        return sections.StandardConnectionGenerationSection()

    def objects_generation(self) -> "TOBJECTS_GENERATION":
        return sections.StandardObjectsGenerationSection()




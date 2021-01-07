import abc
from datetime import datetime
from typing import Iterable, Set, TypeVar, Generic, Tuple, List

import semver

from aoe_rms_spoon.AbstractRootRMSStatement import AbstractRootRMSStatement
from aoe_rms_spoon.models import TerrainContainer
import aoe_rms_spoon.versions.commons.sections
from aoe_rms_spoon.versions.commons import commands, sections

from aoe_rms_spoon import version as spoon_version


TROOT = TypeVar('TROOT', bound=AbstractRootRMSStatement)
TPLAYER_SETUP = TypeVar('TPLAYER_SETUP', bound=aoe_rms_spoon.versions.commons.sections.StandardPlayerSetupSection)
TLAND_GENERATION = TypeVar('TLAND_GENERATION', bound=aoe_rms_spoon.versions.commons.sections.StandardLandGenerationSection)
TELEVATION_GENERATION = TypeVar('TELEVATION_GENERATION', bound=aoe_rms_spoon.versions.commons.sections.StandardElevationGenerationSection)
TCLIFF_GENERATION = TypeVar('TCLIFF_GENERATION', bound=aoe_rms_spoon.versions.commons.sections.StandardCliffGenerationSection)
TTERRAIN_GENERATION = TypeVar('TTERRAIN_GENERATION', bound=aoe_rms_spoon.versions.commons.sections.StandardTerrainGenerationSection)
TCONNECTION_GENERATION = TypeVar('TCONNECTION_GENERATION', bound=aoe_rms_spoon.versions.commons.sections.StandardConnectionGenerationSection)
TOBJECTS_GENERATION = TypeVar('TOBJECTS_GENERATION', bound=aoe_rms_spoon.versions.commons.sections.StandardObjectsGenerationSection)


class AbstractAoeVersion(abc.ABC, Generic[TROOT]):

    @abc.abstractmethod
    def name(self) -> str:
        """
        name of the version
        :return:
        """
        pass

    @abc.abstractmethod
    def create_root(self) -> "TROOT":
        pass

    @abc.abstractmethod
    def terrains(self) -> "TerrainContainer":
        """
        List of default terrains you can use in your RMS script.
        :return:
        """
        pass

    def available_sections(self) -> Set[type]:
        """
        List of sections you can use in your RMS script.
        :return:
        """
        return {
            sections.StandardPlayerSetupSection
        }

    @abc.abstractmethod
    def available_commands(self) -> Set[type]:
        """
        List of nodes that you can use in the script. Any node outside this set will generate a semantic error!
        """
        pass

    @abc.abstractmethod
    def available_functions(self) -> Set[type]:
        """
        List of functions that you can use in the script. Any node outside this set will generate a semantic error!
        :return:
        """
        pass

    # section delcaration

    @abc.abstractmethod
    def player_setup(self) -> "TPLAYER_SETUP":
        pass

    @abc.abstractmethod
    def land_generation(self) -> "TLAND_GENERATION":
        pass

    @abc.abstractmethod
    def elevation_generation(self) -> "TELEVATION_GENERATION":
        pass

    @abc.abstractmethod
    def cliff_generation(self) -> "TCLIFF_GENERATION":
        pass

    @abc.abstractmethod
    def terrain_generation(self) -> "TTERRAIN_GENERATION":
        pass

    @abc.abstractmethod
    def connection_generation(self) -> "TCONNECTION_GENERATION":
        pass

    @abc.abstractmethod
    def objects_generation(self) -> "TOBJECTS_GENERATION":
        pass

    # directives

    def comment(self, message: str) -> "TCOMMENT":
        return commands.Comment(message)

    def include(self, file: str) -> "TINCLUDE":
        return commands.Include(file)

    def include_drs(self, file: str) -> "TINCLUDEDRS":
        return commands.IncludeDrs(file)

    def const(self, name: str, val: int) -> "TCONST":
        return commands.Const(name, val)

    def define(self, name: str) -> "TDEFINE":
        return commands.Define(name)



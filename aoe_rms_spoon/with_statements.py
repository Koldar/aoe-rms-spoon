# ###########################################################
# CLASSES WITH FEATRES
# ###########################################################

# COMMONS
import abc
from datetime import datetime
from typing import Union, Iterable, Tuple, List

import semver
from aoe_rms_spoon import version as spoon_version

from aoe_rms_spoon import IRMSStatement
from aoe_rms_spoon.procedures import convert_primitive


class WithConst(IRMSStatement, abc.ABC):
    def const(self, name: str, value: int) -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().const(name=name, value=value))
        return self


class WithComment(IRMSStatement, abc.ABC):

    def add_comment(self, comment: Union[str, Iterable[str]]) -> "IRMSStatement":
        """
        Add comment in the statement
        :param comment:
        :return:
        """
        self.add_statement(self.get_aoe_version().comment(comment))
        return self


class WithPlayerSetup(IRMSStatement, abc.ABC):
    def player_setup(self) -> "PlayerSetupSection":
        result = self.get_aoe_version().player_setup()
        self.add_statement(result)
        return result


class WithLandGeneration(IRMSStatement, abc.ABC):
    def land_generation(self) -> "LandGenerationSection":
        result = self.get_aoe_version().land_generation()
        self.add_statement(result)
        return result



class WithElevationGeneration(IRMSStatement, abc.ABC):
    def elevation_generation(self) -> "ElevationGenerationSection":
        result = self.get_aoe_version().elevation_generation()
        self.add_statement(result)
        return result

    def end_elevation_generation(self) -> "INonTerminalRMSStatement":
        return self.parent


class WithCliffGeneration(IRMSStatement, abc.ABC):
    def cliff_generation(self) -> "CliffGenerationSection":
        result = self.get_aoe_version().cliff_generation()
        self.add_statement(result)
        return result


class WithTerrainGeneration(IRMSStatement, abc.ABC):
    def terrain_generation(self) -> "TerrainGenerationSection":
        result = self.get_aoe_version().terrain_generation()
        self.add_statement(result)
        return result


class WithConnectionGeneration(IRMSStatement, abc.ABC):
    def connection_generation(self) -> "ConnectionGenerationSection":
        result = self.get_aoe_version().connection_generation()
        self.add_statement(result)
        return result


class WithObjectsGeneration(IRMSStatement, abc.ABC):
    def objects_generation(self) -> "ObjectsGenerationSection":
        result = self.get_aoe_version().objects_generation()
        self.add_statement(result)
        return result


class WithAddFileInfo(IRMSStatement, abc.ABC):

    def add_file_info(self, author: str, version: semver.VersionInfo, description: str,
                        changelogs: List[Tuple[semver.VersionInfo, str]]) -> "IRMSStatement":
        """
                        Add a comment containing information about this file
                        :param author: author of the file
                        :param version: version of the file
                        :param description: description of the file
                        :param changelogs: list of changes applied to the file
                        :return:
                        """
        result = [
            description,
            f"",
            f"This file has been generated in {datetime.utcnow()} via aoe-rms-spoon tool (version {spoon_version.VERSION})",
            f"AUTHOR: {author}",
            f"VERSION: {version}",
            f"",
            f"### CHANGELOGS ###",
            f"",
        ]

        for i, (v, changelog_text) in changelogs:
            result.append(f" {i}. VERSION {v}: {changelog_text}")

        return self.get_aoe_version().comment(result)



class WithDefine(IRMSStatement, abc.ABC):
    def define_condition(self, condition: str) -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().define(condition))
        return self


class WithIncludeDrs(IRMSStatement, abc.ABC):

    def include_drs(self, inc_file: str) -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().include_drs(inc_file))
        return self


class WithInclude(IRMSStatement, abc.ABC):

    def include(self, inc_file: str) -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().include(inc_file))
        return self


# CREATE_PLAYER_LANDS


class WithTerrainType(IRMSStatement, abc.ABC):

    def terrain_type(self, terrain_type: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().terrain_type(convert_primitive(terrain_type)))
        return self


class WithLandPercent(IRMSStatement, abc.ABC):

    def land_percent(self, percentage: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().land_percent(convert_primitive(percentage)))
        return self


class WithNumberOfTiles(IRMSStatement, abc.ABC):

    def number_of_tiles(self, amount: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().number_of_tiles(convert_primitive(amount)))
        return self


class WithBaseSize(IRMSStatement, abc.ABC):

    def base_size(self, radius: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().base_size(convert_primitive(radius)))
        return self


class WithLandPosition(IRMSStatement, abc.ABC):

    def land_position(self, x: "Expression", y: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().land_position(convert_primitive(x), convert_primitive(y)))
        return self


class WithCircleRadius(IRMSStatement, abc.ABC):

    def circle_radius(self, radius: "Expression", variance: "Expression" = None) -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().circle_radius(convert_primitive(radius), convert_primitive(variance)))
        return self


class WithLeftBorder(IRMSStatement, abc.ABC):

    def left_border(self, radius: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().left_border(convert_primitive(radius)))
        return self


class WithRightBorder(IRMSStatement, abc.ABC):

    def right_border(self, radius: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().right_border(convert_primitive(radius)))
        return self


class WithTopBorder(IRMSStatement, abc.ABC):

    def top_border(self, radius: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().top_border(convert_primitive(radius)))
        return self


class WithBottomBorder(IRMSStatement, abc.ABC):

    def bottom_border(self, radius: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().bottom_border(convert_primitive(radius)))
        return self


class WithBorderFuzziness(IRMSStatement, abc.ABC):

    def border_fuzziness(self, border_adherence: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().border_fuzziness(convert_primitive(border_adherence)))
        return self


class WithClumpingFactor(abc.ABC):

    def clumping_factor(self, obj: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().clumping_factor(convert_primitive(obj)))
        return self


class WithBaseElevation(abc.ABC):

    def base_elevation(self, height: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().base_elevation(convert_primitive(height)))
        return self


class WithAssignToPlayer(abc.ABC):

    def assign_to_player(self, player_number: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().border_fuzziness(convert_primitive(player_number)))
        return self


class WithAssignTo(IRMSStatement, abc.ABC):

    def assign_to(self, assign_target: "Expression", n: "Expression", mode: "Expression", flags: "Expression") -> "IRMSStatement":
        self.add_statement(WithAssignTo(convert_primitive(assign_target), convert_primitive(n), convert_primitive(mode), convert_primitive(flags)))
        return self


class WithZone(IRMSStatement, abc.ABC):

    def zone(self, zone_id: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().zone(convert_primitive(zone_id)))
        return self


class WithSetZoneByTeam(IRMSStatement, abc.ABC):

    def set_zone_by_team(self) -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().set_zone_by_team())
        return self


class WithSetZoneRandomly(IRMSStatement, abc.ABC):

    def set_zone_randomly(self) -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().set_zone_randomly())
        return self


class WithOtherZoneAvoidanceDistance(IRMSStatement, abc.ABC):

    def other_zone_avoidance_distance(self, tiles: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().other_zone_avoidance_distance(convert_primitive(tiles)))
        return self


class WithMinPlacementDistance(IRMSStatement, abc.ABC):

    def min_placement_distance(self, tiles: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().min_placement(convert_primitive(tiles)))
        return self


class WithLandId(IRMSStatement, abc.ABC):

    def land_id(self, identifier: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().land_id(convert_primitive(identifier)))
        return self


class WithCirclePlacement(IRMSStatement, abc.ABC):

    def circle_placement(self, obj: "Expression") -> "IRMSStatement":
        self.add_statement(self.get_aoe_version().circle_placement(convert_primitive(obj)))
        return self

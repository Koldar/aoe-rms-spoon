from typing import Tuple

import semver

# version of AoE involved
from aoe_rms_spoon.versions import aoe_2_de as age_version
from aoe_rms_spoon import AbstractRMSScriptGenerator, AbstractAoeVersion
from aoe_rms_spoon.AbstractRootRMSStatement import AbstractRootRMSStatement


class Script(AbstractRMSScriptGenerator):

    def _create_script(self) -> Tuple["AbstractRootRMSStatement", "AbstractAoeVersion"]:

        # fetch the version
        manager = age_version.create_age_version()

        with manager.create_root() as r:
            r.add_file_info(
                author="Koldar",
                version=semver.VersionInfo(1, 0, 0),
                description="A simple empty file used to generate towncenters",
                changelogs=[]
            )

            r.define_condition("PH_AUTUMN")
            r.include_drs("F_season.inc")

            with r.player_setup() as ps:
                with ps.create_player_lands() as cpl:
                    cpl.terrain_type("DESERT")
                    cpl.base_size(19)
                    cpl.land_percent(5)
                    cpl.clumping_factor(5)
            return r, manager


if __name__ == "__main__":
    Script().create_script(file_to_generate=f"{__file__}.rms")

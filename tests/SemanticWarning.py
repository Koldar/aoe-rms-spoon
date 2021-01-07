import semver

from aoe_rms_spoon.AbstractRMSScriptGenerator import AbstractRMSScriptGenerator
from aoe_rms_spoon.nodes import AbstractRootRMSStatement
from aoe_rms_spoon.versions.aoe_2_de.AoEDEIIVersion import AoEDEIIVersion


class Script(AbstractRMSScriptGenerator):
    def _create_script(self, root: "AbstractRootRMSStatement") -> "AbstractRootRMSStatement":
        root.add_file_info(
            author="Koldar",
            version=semver.VersionInfo(1, 0, 0),
            description="A simple empty file used to generate towncenters",
            changelogs=[]
        )

        root.define_condition("PH_AUTUMN")
        root.include_drs("F_season.def")

        root.player_setup()


if __name__ == "__main__":
    Script().create_script(aoe_version=AoEDEIIVersion(), file_to_generate=f"{__file__}.rms")

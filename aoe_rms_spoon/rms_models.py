from typing import List


class Condition(object):

    def __init__(self, name: str, defined_in: str, line: int):
        self.name = name
        """
        Name of the condition. Uniquely rerpesenting the condition
        """
        self.defined_in = defined_in
        """
        File where the condition has been defined while readin the RMS file
        """
        self.line = line
        """
        line number where such a condtion has bee read
        """


class Terrain(object):
    """
    A terrain i nthe game. Terrain depends on the version of aoe
    """

    def __init__(self, id: int, names: List[str], scenario_editor_names: List[str], texture_files: List[str], comment: str, dockable: bool, navigable: bool, walkable: bool, buildable: bool):
        self.id = id
        self.names = names
        self.texture_files = texture_files
        self.scenario_editor_names = scenario_editor_names
        self.dockable = dockable
        self.navigable = navigable
        self.walkable = walkable
        self.buildable = buildable
        self.comment = comment



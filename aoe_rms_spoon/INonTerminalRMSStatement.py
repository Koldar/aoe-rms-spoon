import abc
from typing import List

from aoe_rms_spoon import IRMSStatement


class INonTerminalRMSStatement(IRMSStatement, abc.ABC):
    """
    A non terminal node in the AST RMS tree. Normally this node just relay the generation to its children
    """

    def __init__(self, children = None):
        super().__init__()
        if children is not None:
            self.children = children
        else:
            self.children = []

    def get_children(self) -> List["IRMSStatement"]:
        return self.children

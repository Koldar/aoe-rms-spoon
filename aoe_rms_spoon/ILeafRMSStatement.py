import abc
from typing import List

from aoe_rms_spoon import IRMSStatement


class ILeafRMSStatement(IRMSStatement, abc.ABC):

    def __init__(self):
        super().__init__()

    def is_section_declaration(self) -> bool:
        return False

    def get_children(self) -> List["IRMSStatement"]:
        return []

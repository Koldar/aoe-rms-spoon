import re
from typing import Any


def is_identifier(string: str) -> bool:
    """
    Check if the given string is a correct RMS identifier
    :param string:
    :return:
    """
    return re.match(r"^[a-zA-Z_][a-zA-Z_0-9]*$", string) is not None


def is_number(string: str) -> bool:
    try:
        v = int(string)
        return True
    except ValueError:
        return False
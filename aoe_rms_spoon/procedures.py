from typing import Any


def convert_primitive(obj: Any) -> "Expression":
    """
    Convert an object into a literal
    :param obj:
    :return: an expression representing the object
    """
    # Used to avoid cicrular dependency
    from aoe_rms_spoon.nodes import Expression, IntegerLiteral, BooleanLiteral, FloatLiteral, ConstLiteral
    if isinstance(obj, Expression):
        return obj
    if isinstance(obj, int):
        return IntegerLiteral(obj)
    elif isinstance(obj, bool):
        return BooleanLiteral(obj)
    elif isinstance(obj, float):
        return FloatLiteral(obj)
    elif isinstance(obj, str):
        return ConstLiteral(obj)
    else:
        raise TypeError(f"Invalid converasion from {type(obj)} to Expression! Only int, bool, float, str are accepted")
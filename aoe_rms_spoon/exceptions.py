
class RMSException(BaseException):
    """
    A generic exception in the RMS file"""
    pass


class RMSSyntaxErrorException(RMSException):
    """
    A generic syntax error in the RMS file"""
    pass


class RMSSyntaxWarningException(RMSException):
    """
    A genric syntax warning in the RMS file
    """


class RMSSemanticErrorException(RMSException):
    """
    An exception representing a semantic error
    """
    pass


class RMSSemanticWarningException(RMSException):
    """
    An exceptio nrepresenting a semantic wearning in the file
    """
    pass

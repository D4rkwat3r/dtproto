package protocol.error

import annotation.ProtocolException

@ProtocolException
class TokenMismatchException : Exception("The token received when parsing the packet does not match the expected one.")

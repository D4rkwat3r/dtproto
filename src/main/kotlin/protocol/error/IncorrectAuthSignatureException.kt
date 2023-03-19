package protocol.error

import annotation.ProtocolException

@ProtocolException
class IncorrectAuthSignatureException : Exception("Signature of the packet auth header is incorrect.")

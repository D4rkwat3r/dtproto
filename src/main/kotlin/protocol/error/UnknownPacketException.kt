package protocol.error

import annotation.ProtocolException

@ProtocolException
class UnknownPacketException : Exception("Packet ID is unknown.")

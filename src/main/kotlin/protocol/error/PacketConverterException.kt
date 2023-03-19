package protocol.error

import annotation.ProtocolException

@ProtocolException
class PacketConverterException : Exception("Failed to convert packet to object.")

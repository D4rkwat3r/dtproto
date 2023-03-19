package protocol.error

import annotation.ProtocolException

@ProtocolException
class MalformedPacketException : Exception("Unable to read the packet.")

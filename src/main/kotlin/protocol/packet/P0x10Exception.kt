package protocol.packet

import protocol.DTPacket
import protocol.exception.DTException

class P0x10Exception(val code: Short, val message: String): DTPacket(code, message) {
    override val id: PacketID = PacketID.EXCEPTION

    constructor(exception: DTException): this(exception.code, exception.message!!)
}

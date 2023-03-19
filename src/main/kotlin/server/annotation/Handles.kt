package server.annotation

import protocol.DTPacket

annotation class Handles(vararg val ids: DTPacket.PacketID)

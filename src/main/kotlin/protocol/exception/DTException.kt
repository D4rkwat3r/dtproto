package protocol.exception

open class DTException(val code: Short, message: String) : Exception(message)

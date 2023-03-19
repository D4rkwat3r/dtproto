package protocol.util

import annotation.PacketData
import protocol.Auth
import protocol.DTPacket
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor

object PacketConverterUtil {
    private fun transformDataObject(constructor: KCallable<*>, dataObject: List<Any?>): Map<KParameter, Any?>? {
        val argumentMapping = mutableMapOf<KParameter, Any?>()
        for ((parameter, dataField) in constructor.parameters.zip(dataObject)) {
            if (dataField == null) {
                if (parameter.type.isMarkedNullable) {
                    argumentMapping[parameter] = null
                    continue
                } else {
                    println("[PacketConverterUtil] Can't create instance for packet, ${parameter.name} is not nullable but value is null")
                    return null
                }
            }
            // поддержка Set не имеет смысла
            if (
                dataField is ArrayList<*>
                && (parameter.type.arguments.getOrNull(0)?.type?.classifier as? KClass<*>)?.hasAnnotation<PacketData>() == true
                && parameter.type.classifier in arrayOf(List::class, Array::class)
            ) {
                val parameterTypeClass = (parameter.type.arguments[0].type!!.classifier as KClass<*>)
                val objectList = dataField.map { dataFields ->
                    parameterTypeClass.primaryConstructor?.call(*(dataFields as List<*>).toTypedArray())
                }
                when (parameter.type.classifier) {
                    Array::class -> argumentMapping[parameter] = objectList.toTypedArray()
                    List::class -> argumentMapping[parameter] = objectList
                }
                continue
            }
            if (
                dataField::class == parameter.type.classifier
                || dataField::class.isSubclassOf(parameter.type.classifier as KClass<*>)
            ) {
                argumentMapping[parameter] = dataField
                continue
            }
            if (dataField is List<*>) {
                val objectConstructor = (parameter.type.classifier as KClass<*>).primaryConstructor ?: return null
                val nestedObjectArgs = transformDataObject(objectConstructor, dataField) ?: return null
                argumentMapping[parameter] = objectConstructor.callBy(nestedObjectArgs)
                continue
            }
            println("[PacketConverterUtil] Can't convert packet to object because of ${parameter.name} with type ${parameter.type}")
            return null
        }
        return argumentMapping
    }

    fun createObjectFor(
        packetID: DTPacket.PacketID,
        requestId: Int,
        flags: List<DTPacket.PacketFlag>,
        auth: Auth? = null,
        dataObject: List<Any?> = listOf()
    ): DTPacket? {
        val constructor = packetID.clazz.primaryConstructor ?: return null
        val args = transformDataObject(constructor, dataObject) ?: return null
        return (constructor.callBy(args) as? DTPacket)?.apply {
            setRequestId(requestId)
            setFlags(flags)
            if (auth != null) setAuth(auth.toRawHeader())
        }
    }
}

package net.yakuraion.mangakko.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

/**
 * Gson адаптер, позволяющий сериализовывать/десериализовывать абстрактные классы.
 *
 * При десериализации восстанавливается объект конкретного (не абстрактного) класса.
 */
class AbstractTypeGsonAdapter<T> : JsonSerializer<T>, JsonDeserializer<T>
        where T : Any {

    override fun serialize(src: T, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return JsonObject().apply {
            addProperty("type", src::class.java.name)
            add("data", context.serialize(src))
        }
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): T {
        val member = json as JsonObject
        val typeString = get(member, "type")
        val data = get(member, "data")
        val actualType = typeForName(typeString)
        return context.deserialize(data, actualType)
    }

    private fun typeForName(typeElem: JsonElement): Type {
        return try {
            Class.forName(typeElem.asString)
        } catch (e: ClassNotFoundException) {
            throw JsonParseException(e)
        }
    }

    private fun get(wrapper: JsonObject, memberName: String): JsonElement {
        return wrapper[memberName]
            ?: throw JsonParseException("no '$memberName' member found in json file.")
    }
}

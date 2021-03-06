package com.liuzq.httplibrary.gson

import com.google.gson.*
import java.lang.reflect.Type

/**
 * 定义为int类型,如果后台返回""或者null,则返回0
 */
class IntegerDefault0Adapter : JsonSerializer<Int>, JsonDeserializer<Int> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Int {
        try {
            if (json?.asString.isNullOrBlank() || json?.asString == "null") {
                return 0
            }
        } catch (ignore: Exception) {
        }
        try {
            return json?.asInt!!
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(src: Int?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src)
    }

}
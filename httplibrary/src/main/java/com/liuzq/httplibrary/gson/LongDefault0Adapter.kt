package com.liuzq.httplibrary.gson

import com.google.gson.*
import java.lang.reflect.Type

/**
 * 定义为long类型,如果后台返回""或者null,则返回0
 */
class LongDefault0Adapter : JsonSerializer<Long>, JsonDeserializer<Long> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Long {
        try {
            if (json?.asString.isNullOrBlank() || json?.asString == "null") {
                return 0L
            }
        } catch (ignore: Exception) {
        }

        try {
            return json?.asLong!!
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(src: Long?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src)
    }


}
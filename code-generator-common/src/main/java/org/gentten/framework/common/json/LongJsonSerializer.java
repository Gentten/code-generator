package org.gentten.framework.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * long 转字符串
 *
 * @author : duanzhiqiang
 * @date : 2019-04-23 13:06
 */
public class LongJsonSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String text = (value == null ? null : String.valueOf(value));
        if (text != null) {
            gen.writeString(text);
        }
    }
}

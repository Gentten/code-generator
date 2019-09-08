package org.gentten.framework.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 字符串转long  js long类型有效位只有53位 不做转化会出现精度丢失
 *
 * @author : duanzhiqiang
 * @date : 2019-04-23 13:08
 */
public class LongJsonDeserializer extends JsonDeserializer<Long> {
    private static final Logger logger = LoggerFactory.getLogger(LongJsonDeserializer.class);

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();

        try {

            return value == null ? null : Long.parseLong(value);

        } catch (NumberFormatException e) {

            logger.error("解析长整形错误", e);

            return null;

        }
    }
}

package org.gentten.framework.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gentten.framework.common.exception.SysException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * jackson 版的json 工具类
 *
 * @author : duanzhiqiang
 * @date : 2019-07-04 19:05
 */
@Slf4j
public class JacksonUtils {
    /**
     * 定义jackson对象
     */
    private static final ObjectMapper objectMapper;

    static {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper = mapper;
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return 指定的java对象
     */
    public static <T> T fromJson(String jsonData, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonData, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T fromJson(String jsonData, TypeReference typeReference) {
        try {
            return objectMapper.readValue(jsonData, typeReference);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象
     *
     * @param in    InputStream
     * @param clazz 指定的java对象
     * @return 指定的java对象
     */
    public static <T> T fromJson(InputStream in, Class<T> clazz) throws IOException {
        return objectMapper.readValue(in, clazz);
    }

    /**
     * 功能描述：把java对象转换成JSON数据
     *
     * @param object java对象
     * @return JSON数据
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return List<T>
     */
    public static <T> List<T> fromJsonArray(String jsonData, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            List<T> list = objectMapper.readValue(jsonData, javaType);
            return list;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * json字符串转化为 JavaBean 还可以直接JsonUtils.getInstance().readValue(String content,Class valueType)用这种方式
     *
     * @param <T>
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T toJavaBean(String content, Class<T> valueType) {
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            log.error("转换为json字符串失败", e);
        }
        return null;
    }

    /**
     * json字符串转化为list 还可以 直接使用 JacksonUtils.getInstance().readValue(String content, new TypeReference<List<T>>(){})方式
     *
     * @param <T>
     * @param content
     * @param typeReference
     * @return
     * @throws IOException
     */
    public static <T> List<T> toJavaBeanList(String content, TypeReference<List<T>> typeReference) throws IOException {
        try {
            return objectMapper.readValue(content, typeReference);
        } catch (JsonParseException e) {
            log.error("json字符串转化为 list失败", e);
            throw new SysException("json字符串转化为 list失败");
        } catch (IOException e) {
            log.error("json字符串转化为 list失败", e);
            throw new IOException("json字符串转化为 list失败");
        }
    }

    /**
     * json字符串转化为list 还可以 直接使用 JacksonUtils.getInstance().readValue(String content, new TypeReference<List<T>>(){})方式
     *
     * @param <T>
     * @param content
     * @param typeReference
     * @return
     * @throws IOException
     */
    public static <T> Map<String, T> toJavaBeanMap(String content,
                                                   TypeReference<Map<String, T>> typeReference) throws IOException {
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.readValue(content, typeReference);
        } catch (JsonParseException e) {
            log.error("json字符串转化为 map失败", e);
            throw new SysException("json字符串转化为 map失败");
        } catch (IOException e) {
            log.error("json字符串转化为 map失败", e);
            throw new IOException("json字符串转化为 map失败");
        }
    }

    /**
     * 实体类转Map
     *
     * @param <T>
     * @param object
     * @param typeReference
     * @return
     * @throws IOException
     */
    public static <T> Map<String, T> javaBeanToMap(Object object,
                                                   TypeReference<Map<String, T>> typeReference) throws IOException {
        try {
            String json = objectMapper.writeValueAsString(object);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            log.error("json字符串转化为 map失败", e);
            throw new SysException("json字符串转化为 map失败");
        } catch (IOException e) {
            log.error("json字符串转化为 map失败", e);
            throw new IOException("json字符串转化为 map失败");
        }
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的List<Map<String, Object>>
     * @return List<Map < String, Object>>
     */

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        Test test = Test.builder().id(1).v(V.builder().s("ss").m("mm").build()).build();
        String json = toJson(test);
        System.out.println(json);
        Test t1 = fromJson(json, Test.class);
        System.out.println(t1.toString());
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class Test {
        private Integer id;

        private V v;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class V {
        private String s;
        private String m;
    }

}

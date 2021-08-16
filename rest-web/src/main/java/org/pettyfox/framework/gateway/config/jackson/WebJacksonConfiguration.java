package org.pettyfox.framework.gateway.config.jackson;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.pagehelper.PageInfo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author pettyfox
 */
@Configuration
public class WebJacksonConfiguration {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return builder -> {
            JavaTimeModule javaTimeModule = new JavaTimeModule();

            //LocalDateTime 配置
//            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
            javaTimeModule.addDeserializer(Date.class, new DateDeserializer());
            builder.modulesToInstall(javaTimeModule);
            //PageInfo实体序列化配置
            builder.serializerByType(PageInfo.class, new PageInfoSerializer());
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            builder.serializerByType(Long.class, ToStringSerializer.instance);
        };
    }


    public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ofPattern(pattern)));
        }
    }

    public static class DateDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser p, DeserializationContext deserializationContext)
                throws IOException {
            Date date = null;
            String t = p.getValueAsString();
            try {
                date = DateTime.parse(t).toDate();

            } catch (IllegalArgumentException e) {
                date = DateUtil.parseDateTime(t);
            }
            return date;

        }
    }
}

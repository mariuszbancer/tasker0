package com.mbancer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class JacksonConfiguration {

    public static final DateTimeFormatter ISO_FIXED_FORMAT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    public static final DateTimeFormatter ISO_DATE_OPTIONAL_TIME =
         new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .optionalStart()
            .appendLiteral('T')
            .append(DateTimeFormatter.ISO_TIME)
            .toFormatter();

    @Autowired
    private Jackson2ObjectMapperBuilder builder;

    @PostConstruct
    public void postConstruct() {
        this.builder.serializers(new ZonedDateTimeSerializer(ISO_FIXED_FORMAT));
        this.builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(ISO_DATE_OPTIONAL_TIME));
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        return this.builder.createXmlMapper(false).build();
    }

}

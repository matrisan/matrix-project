package com.matrixboot.hub.manager.infrastructure.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrixboot.hub.manager.domain.value.Capacity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * <p>
 * create in 2021/9/17 10:42 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Converter
public class CapacityConverter implements AttributeConverter<Capacity, String> {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public String convertToDatabaseColumn(Capacity attribute) {
        return objectMapper.writeValueAsString(attribute);
    }

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public Capacity convertToEntityAttribute(String dbData) {
        if (StringUtils.hasText(dbData)) {
            return objectMapper.readValue(dbData, Capacity.class);
        }
        return null;
    }
}

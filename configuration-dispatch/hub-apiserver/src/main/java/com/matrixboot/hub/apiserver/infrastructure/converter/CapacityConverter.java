package com.matrixboot.hub.apiserver.infrastructure.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrixboot.hub.apiserver.domain.value.CapacityValue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
@Converter(autoApply = true)
public class CapacityConverter implements AttributeConverter<CapacityValue, String> {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public String convertToDatabaseColumn(CapacityValue attribute) {
        return objectMapper.writeValueAsString(attribute);
    }

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public CapacityValue convertToEntityAttribute(String dbData) {
        return objectMapper.readValue(dbData, CapacityValue.class);
    }
}

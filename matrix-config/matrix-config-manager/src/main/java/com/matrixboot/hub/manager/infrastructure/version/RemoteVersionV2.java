package com.matrixboot.hub.manager.infrastructure.version;

import cn.hutool.core.net.Ipv4Util;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>
 * create in 2021/9/22 1:36 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component("RemoteVersionV2")
public class RemoteVersionV2 implements IRemoteVersion<VersionV2> {

    @Resource
    private IFeignInput feignInput;

    @Override
    public VersionV2 convertor(@NotNull MatrixConfigEntity entity) {
        return VersionV2.builder()
                .hostname(entity.getDomain())
                .ip(entity.getSource())
                .status(entity.getStatus())
                .selector(entity.getSelector())
                .build();
    }

    @Override
    public void doTransfer(BaseVersion data) {
        log.info("doTransfer - {}", data);
        feignInput.stream(data);
    }

    @FeignClient(name = "client", url = "http://127.0.0.1:8788")
    interface IFeignInput {

        /**
         * 推送数据
         *
         * @param versioned 版本
         */
        @PostMapping("/test_key")
        void stream(@RequestBody BaseVersion versioned);
    }
}

@Slf4j
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class VersionV2 implements BaseVersion {

    private static final long serialVersionUID = -2502509127815698037L;

    /**
     * @see MatrixConfigEntity#getDomain()
     */
    String hostname;

    /**
     * @see MatrixConfigEntity#getSource()
     */
    @JsonSerialize(using = IpSerializer.class)
    String ip;

    Integer status;

    String selector;
}

class IpSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, @NotNull JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(Ipv4Util.ipv4ToLong(s));
    }
}




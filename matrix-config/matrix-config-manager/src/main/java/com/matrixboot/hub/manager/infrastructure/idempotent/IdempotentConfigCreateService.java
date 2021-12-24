package com.matrixboot.hub.manager.infrastructure.idempotent;

import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * create in 2021/12/24 10:43 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class IdempotentConfigCreateService {

    public String convert(List<@Valid ConfigCreateCommand> command) {
        log.info("IdempotentService:{}", command);
        return command.stream().map(ConfigCreateCommand::getDomain).collect(Collectors.joining());
    }

}

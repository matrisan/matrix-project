package com.matrixboot.manager.server.application;


import com.matrixboot.manager.server.domain.entity.WebsiteInfoEntity;
import com.matrixboot.manager.server.interfaces.dto.WebsiteAddCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/8/11 2:50 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebsiteFactory {

    public static WebsiteInfoEntity from(WebsiteAddCommand command) {
        return new WebsiteInfoEntity();
    }

}

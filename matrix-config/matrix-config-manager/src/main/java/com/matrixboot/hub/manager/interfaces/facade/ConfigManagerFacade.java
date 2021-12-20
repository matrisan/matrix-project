package com.matrixboot.hub.manager.interfaces.facade;

import com.matrixboot.excel.ExcelRequestBody;
import com.matrixboot.excel.ExcelResponseBody;
import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import com.matrixboot.hub.manager.application.ConfigDeleteCommand;
import com.matrixboot.hub.manager.application.ConfigUpdateCommand;
import com.matrixboot.hub.manager.application.service.ConfigManagerService;
import com.matrixboot.hub.manager.domain.IConfigView;
import com.matrixboot.hub.manager.interfaces.vo.ReturnVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * create in 2021/10/9 10:39 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RestController
@AllArgsConstructor
public class ConfigManagerFacade {

    private final ConfigManagerService service;

    /**
     * 分页查找所有的配置信息
     *
     * @param pageable 分页信息
     * @return Page
     */
    @GetMapping("configs")
    public Page<IConfigView> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    /**
     * 创建单个配置
     *
     * @param command 创建配置命令
     * @return ReturnVO
     */
    @PostMapping("config")
    public ReturnVO<String> configCreate(@RequestBody ConfigCreateCommand command) {
        service.configCreate(command);
        return ReturnVO.success();
    }

    /**
     * 全量更新配置
     *
     * @param command 更新配置命令
     * @return ReturnVO
     */
    @PutMapping("config")
    public ReturnVO<String> configUpdate(@RequestBody ConfigUpdateCommand command) {
        service.configUpdate(command);
        return ReturnVO.success();
    }

    /**
     * 批量创建配置,主要是从 excel 上传配置
     *
     * @param command 创建配置命令
     * @return ReturnVO
     */
    @PostMapping("config/excel")
    public ReturnVO<String> configCreate(@ExcelRequestBody List<ConfigCreateCommand> command) {
        service.configCreate(command);
        return ReturnVO.success();
    }

    /**
     * 删除单个网站配置
     *
     * @param command 删除配置命令
     * @return ReturnVO
     */
    @DeleteMapping("")
    public ReturnVO<String> deleteConfig(@RequestBody ConfigDeleteCommand command) {
        service.deleteConfig(command);
        return ReturnVO.success();
    }

    @ExcelResponseBody
    @GetMapping("configs/excel")
    public List<IConfigView> findAllExcel(Pageable pageable) {
        return service.findAll(pageable).getContent();
    }
}

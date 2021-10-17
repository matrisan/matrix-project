package com.matrixboot.server.evaluate.infrastructure.context;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import com.matrixboot.server.evaluate.infrastructure.AbstractStrategyManager;
import com.matrixboot.strategy.ScenarioMetaEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * create in 2021/9/18 5:48 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public abstract class AbstractEvaluateContext {

    /**
     * 策略容器
     * key 是业务场景的基本信息,
     * value 是策略的管理器
     * 这个 map 必须是 并发容器
     */
    private final Map<ScenarioMetaEntity, AbstractStrategyManager> managerMap;

    @Resource
    private AbstractStrategyManager defaultManager;

    protected AbstractEvaluateContext() {
        managerMap = new ConcurrentHashMap<>();
    }

    /**
     * 新增业务
     *
     * @param scenarioMeta    key 是业务场景的基本信息,
     * @param strategyManager value 是策略的管理器
     */
    public void addScenario(ScenarioMetaEntity scenarioMeta, AbstractStrategyManager strategyManager) {
        managerMap.put(scenarioMeta, strategyManager);
    }

    /**
     * 删除
     *
     * @param scenarioMeta key 是业务场景的基本信息,
     */
    public void removeScenario(ScenarioMetaEntity scenarioMeta) {
        managerMap.remove(scenarioMeta);
    }

    /**
     * 对请求进行评估
     *
     * @param decision 请求的信息
     * @return EvaluateResult
     */
    public EvaluateResult evaluate(EvaluateEntity decision) {
        AbstractStrategyManager strategyManager = managerMap.getOrDefault(decision, defaultManager);
        return strategyManager.evaluateResult(decision);
    }


    /**
     * 关联变量
     */
    public abstract void associateCompute();

    public abstract Map<String, Integer> getAllCompute();

    /**
     * 关联策略
     */
    public abstract void associateStrategy();


    /**
     * 添加策略结果
     * 默认返回最高的结果,会先进判断
     * 如果击中策略也会进行添加到击中的策略列表中
     *
     * @param result 策略结果
     */
    public abstract void addEvaluateResult(@NotNull EvaluateResult result);

    /**
     * 获取策略的执行结果
     *
     * @return EvaluateResult
     */
    public abstract Optional<EvaluateResult> getReturnResult();

    /**
     * 获取最终的结果
     *
     * @return EvaluateResult
     */
    public abstract EvaluateResult getFinalResult();

    /**
     * 启用的规则
     *
     * @return List
     */
    public abstract List<EvaluateResult> getResultEnable();

    /**
     * 灰度的规则
     *
     * @return List
     */
    public abstract List<EvaluateResult> getResultCanary();

    /**
     * 记录的规则
     *
     * @return List
     */
    public abstract List<EvaluateResult> getResultRecord();

    /**
     * 未启用的规则
     *
     * @return List
     */
    public abstract List<EvaluateResult> getResultDisable();
}

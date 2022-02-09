//package com.matrixboot.server.evaluate.infrastructure.context;
//
//import com.matrixboot.server.evaluate.application.EvaluateResult;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//
///**
// * <p>
// * create in 2021/9/18 5:33 下午
// *
// * @author shishaodong
// * @version 0.0.1
// */
//
//@Slf4j
//@Component
//public class EvaluateHttpServletRequestContext extends AbstractEvaluateContext {
//
//    /**
//     * 存放最终的结果的 key
//     */
//    private static final String RESULT_KEY = "EVALUATE_RESULT";
//
//    /**
//     * 所有击中策略的 key
//     */
//    private static final String RESULT_ENABLE_KEY = "EVALUATE_RESULT_ENABLE";
//
//    /**
//     * 灰度发布 key
//     */
//    private static final String RESULT_CANARY_KEY = "EVALUATE_RESULT_CANARY";
//
//    /**
//     * 记录 key
//     */
//    private static final String RESULT_WATCH_KEY = "EVALUATE_RESULT_WATCH";
//
//    /**
//     * 不启用 key
//     */
//    private static final String RESULT_DISABLE_KEY = "EVALUATE_RESULT_DISABLE";
//
//    @Resource
//    private HttpServletRequest request;
//
//
//    @Override
//    public void associateCompute() {
//
//    }
//
//    @Override
//    public Map<String, Integer> getAllCompute() {
//        return null;
//    }
//
//    @Override
//    public void associateStrategy() {
//
//    }
//
//    @Override
//    public void addEvaluateResult(@NotNull EvaluateResult result) {
//        switch (result.getStatus()) {
//            case ENABLE:
//                addEnableResult(result);
//                break;
//            case CANARY:
//                addCanaryResult(result);
//                break;
//            case WATCH:
//                addWatchResult(result);
//                break;
//            default:
//                addDisableResult(result);
//        }
//    }
//
//    @Override
//    public Optional<EvaluateResult> getReturnResult() {
//        Object attribute = request.getAttribute(RESULT_KEY);
//        if (Objects.isNull(attribute)) {
//            return Optional.empty();
//        }
//        return Optional.of((EvaluateResult) attribute);
//    }
//
//    @Override
//    public EvaluateResult getFinalResult() {
//        Object attribute = request.getAttribute(RESULT_KEY);
//        if (Objects.isNull(attribute)) {
//            return EvaluateResult.defaultEvaluateResult();
//        }
//        return ((EvaluateResult) attribute);
//    }
//
//    @Override
//    public List<EvaluateResult> getResultEnable() {
//        return getEvaluateResult(RESULT_ENABLE_KEY);
//    }
//
//    @Override
//    public List<EvaluateResult> getResultCanary() {
//        return getEvaluateResult(RESULT_CANARY_KEY);
//    }
//
//    @Override
//    public List<EvaluateResult> getResultRecord() {
//        return getEvaluateResult(RESULT_WATCH_KEY);
//    }
//
//    @Override
//    public List<EvaluateResult> getResultDisable() {
//        return getEvaluateResult(RESULT_DISABLE_KEY);
//    }
//
//    private void addEnableResult(@NotNull EvaluateResult result) {
//        Optional<EvaluateResult> resultOptional = getReturnResult();
//        if (resultOptional.isEmpty()) {
//            setReturnResult(result);
//            return;
//        }
//        if (resultOptional.get().getScore() < result.getScore()) {
//            getEvaluateResult(RESULT_ENABLE_KEY).add(result);
//            setReturnResult(result);
//        }
//    }
//
//    private void addCanaryResult(@NotNull EvaluateResult result) {
//        extracted(result, RESULT_CANARY_KEY);
//    }
//
//    private void addWatchResult(@NotNull EvaluateResult result) {
//        extracted(result, RESULT_WATCH_KEY);
//    }
//
//    private void addDisableResult(@NotNull EvaluateResult result) {
//        extracted(result, RESULT_DISABLE_KEY);
//    }
//
//    private void extracted(@NotNull EvaluateResult result, String evaluateKey) {
//        if (result.getScore() > 0) {
//            getEvaluateResult(evaluateKey).add(result);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private List<EvaluateResult> getEvaluateResult(String evaluateKey) {
//        Object attribute = request.getAttribute(evaluateKey);
//        if (Objects.isNull(attribute)) {
//            List<EvaluateResult> list = new ArrayList<>();
//            request.setAttribute(evaluateKey, list);
//            return list;
//        } else {
//            return (List<EvaluateResult>) attribute;
//        }
//    }
//
//    private void setReturnResult(EvaluateResult result) {
//        request.setAttribute(RESULT_KEY, result);
//    }
//}

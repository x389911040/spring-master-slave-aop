package com.xue.aop.common.aop_impl;

import com.xue.aop.common.annotations.DBMaster;
import com.xue.aop.common.annotations.DBSlave;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author xuejh
 * @description 选择数据源
 * @create 2020-07-07 15:30
 **/
@Aspect
@Data
@Slf4j
@Order(-999)
@Component
@ConfigurationProperties(prefix = "define-datasource")
public class DataSourceAspect {

    /**
     * 获取定义的拦截方法信息
     */
    private Map<String, String> interceptMethods;

    @Before("execution(* com.xue.aop.service..*.*(..))")
    public void getDataSourceKey(JoinPoint point) throws Throwable {
        // 获取当前执行方法相关信息
        MethodSignature signature = (MethodSignature)point.getSignature();
        // 方法名称
        String methodName = signature.getName();
        // 方法参数
        Class[] parameterTypes = signature.getParameterTypes();

        log.info("=============拦截的方法名称==============>：" + methodName);

        // 获取切点目标
        Object target = point.getTarget();
        // 获取当前目标方法
        Method method = target.getClass().getMethod(methodName, parameterTypes);

        // 首先判断方法上是否有标明注解
        Annotation[] annotations = method.getAnnotations();
        if (ArrayUtils.isNotEmpty(annotations)) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof DBMaster) {
                    // 标明访问主库
                    DataSourceHolder.master();
                    return;
                } else if (annotation instanceof DBSlave) {
                    // 表明访问从库
                    DataSourceHolder.slave();
                    return;
                }
            }
        }

        // 获取配置拦截方法信息
        if (CollectionUtils.isEmpty(interceptMethods)) {
            DataSourceHolder.master();
            return;
        }

        Set<Map.Entry<String, String>> entrySet = interceptMethods.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (StringUtils.isBlank(value)) {
                DataSourceHolder.master();
                return;
            }

            String[] split = value.split(",");
            for (String s : split) {
                if (methodName.startsWith(s)) {
                    // 方法以该开头
                    if (key.equals("write")) {
                        DataSourceHolder.master();
                    } else {
                        DataSourceHolder.slave();
                    }

                    return;
                }
            }
        }

        log.info("=========================未匹配规则和注解，默认走主库=================");

        // 不满足上述，默认使用主库
        DataSourceHolder.master();
    }

    @After("execution(* com.xue.aop.service..*.*(..))")
    public void after() {
        DataSourceHolder.clear();
        log.info("清理引用");
    }
}

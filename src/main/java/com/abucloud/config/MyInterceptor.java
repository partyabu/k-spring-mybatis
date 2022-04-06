package com.abucloud.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @formatter:on
 * 当有多个插件时，target会有多层target对象
 * public Object pluginAll(Object target) {
 *     for (Interceptor interceptor : interceptors) {
 *       target = interceptor.plugin(target);
 *     }
 *     return target;
 *   }
 * @formatter:off
 */

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("前置处理");

        // 执行目标方法
        Object proceed = invocation.proceed();

        System.out.println("后置处理");

        return proceed;
    }

    /**
     * 四大对象在创建时都会调用interceptorChain.pluginAll()方法，会为 指定的对象 创建相应的 代理对象，
     * 比如现在目标是Executor，就会为其创建代理对象，其他三个对象创建时则直接返回
     * 当代理对象调用目标方法时，会直接调用intercept()方法
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 读取配置中的参数
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties);
    }
}

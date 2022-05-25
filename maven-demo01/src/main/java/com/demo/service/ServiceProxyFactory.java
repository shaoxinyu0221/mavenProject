package com.demo.service;

import com.demo.util.MyBatisUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ServiceProxyFactory {

    public static<T> T getPorxy(T t){
        //创建enchaner对象
        Enhancer enhancer = new Enhancer();
        //设置代理对象的父类
        enhancer.setSuperclass(t.getClass());
        //设置处理方式
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                T returnVal = null;
                try {
                    returnVal = (T)methodProxy.invokeSuper(proxy, args);
                    MyBatisUtil.getSqlSession().commit();
                } catch (Exception e) {
                    MyBatisUtil.getSqlSession().close();
                    throw e;
                }finally {
                    MyBatisUtil.getSqlSession().close();
                }
                return returnVal;
            }
        });

        return (T)enhancer.create();
    }


}

package com.woniumall.service;

import com.woniumall.util.MyBatisUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory {

    public static<T> T getProxy(T t){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                T returnVal = null;
                try {
                    returnVal = (T) methodProxy.invokeSuper(proxy,args);
                    MyBatisUtil.getSqlSession().commit();
                } catch (Throwable e) {
                    MyBatisUtil.getSqlSession().rollback();
                    e.printStackTrace();
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

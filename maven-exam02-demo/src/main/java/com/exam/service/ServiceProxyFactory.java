package com.exam.service;

import com.exam.util.MyBatisUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ServiceProxyFactory {

    public static<T> T getProxy(T t){
        //创建enhancer对象
        Enhancer enhancer = new Enhancer();
        //通过enhancer为代理对象创建父类
        enhancer.setSuperclass(t.getClass());
        //设置方法回调,作为所有代理方法的统一处理方式
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //创建返回值
                T returnVal = null;
                try {
                    //通过代理方法对象调用代理对象的父类方法,也就是目标对象的方法
                    returnVal = (T) methodProxy.invokeSuper(proxy, args);
                    MyBatisUtil.getSqlSession().commit();
                } catch (Exception e) {
                    throw e;
                }finally {
                    MyBatisUtil.close(MyBatisUtil.getSqlSession());
                }
                return returnVal;
            }
        });
        //创建代理对象
        return (T) enhancer.create();
    }


}

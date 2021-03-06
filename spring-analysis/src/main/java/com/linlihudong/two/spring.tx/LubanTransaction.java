package com.linlihudong.two.spring.tx;/**
 * Created by Administrator on 2018/8/29.
 */

import com.linlihudong.two.service.UserSerivce;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/29
 **/
public class LubanTransaction {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-tx.xml");
        final UserSerivce userSerivce = context.getBean(UserSerivce.class);

        UserSerivce proxyUserSerivce = (UserSerivce) Proxy.newProxyInstance(LubanTransaction.class.getClassLoader(),
                new Class[]{UserSerivce.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            System.out.println("开启事物:"+method.getName());
                            return method.invoke(userSerivce, args);
                        } finally {
                            System.out.println("关闭事物:"+method.getName());
                        }
                    }
                });
        proxyUserSerivce.createUser("luban");
//        开启事物:createUser
//        开启事物: addAccount
//        关闭事物:addAccount
//       关闭事物:createUser
    }
}

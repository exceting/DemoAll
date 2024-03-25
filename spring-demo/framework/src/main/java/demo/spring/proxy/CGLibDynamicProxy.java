package demo.spring.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibDynamicProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        Class<?> superClass = MysqlTestDatasource.class;

        enhancer.setSuperclass(superClass);
        enhancer.setInterfaces(new Class[]{TestDatasource.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                try {
                    System.out.println("CGLIB Proxy start!");
                    return methodProxy.invokeSuper(o, objects);
                } finally {
                    System.out.println("CGLIB Proxy finished!");
                }
            }
        });

        MysqlTestDatasource mysqlTestDatasource = (MysqlTestDatasource) enhancer.create();
        mysqlTestDatasource.getConnection();

    }

    private void t() {
    }

    private void t(int a) {
    }

    private void t(long a) {
    }

    private int t(char a) {
        return 'a';
    }


    public static class A{
        private int count;

        public synchronized void incr(){
            count++;
        }
    }

}

package demo.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DatasourceProxy2 {

    private TestDatasource proxy;

    public DatasourceProxy2(TestDatasource target) {
        this.proxy = (TestDatasource) Proxy.newProxyInstance(TestDatasource.class.getClassLoader(),
                new Class[]{TestDatasource.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            System.out.println("proxy 2 started!");
                            return method.invoke(target, args);
                        } finally {
                            System.out.println("proxy 2 finished!");
                        }
                    }
                });
    }

    public TestDatasource getProxy() {
        return proxy;
    }

}

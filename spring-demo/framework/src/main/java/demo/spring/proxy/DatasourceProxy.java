package demo.spring.proxy;

import java.lang.reflect.Proxy;

public class DatasourceProxy {

    private final TestDatasource proxy;

    public DatasourceProxy(TestDatasource targetDatasource) {
        this.proxy = (TestDatasource) Proxy.newProxyInstance(TestDatasource.class.getClassLoader(),
                new Class[]{TestDatasource.class},
                (proxy, method, args) -> {
                    System.out.println("proxy 1 started!");
                    try {
                        return method.invoke(targetDatasource, args);
                    } finally {
                        System.out.println("proxy 1 finished!");
                    }
                });
    }

    public TestDatasource getProxy() {
        return proxy;
    }

}

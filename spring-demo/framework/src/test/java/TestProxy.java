import demo.spring.proxy.DatasourceProxy;
import demo.spring.proxy.DatasourceProxy2;
import demo.spring.proxy.MysqlTestDatasource;
import demo.spring.proxy.TestDatasource;
import org.junit.Test;

public class TestProxy {

    /**
     * jdk 动态代理
     */
    @Test
    public void test1() {
        TestDatasource t1 = new DatasourceProxy2(new DatasourceProxy(new MysqlTestDatasource()).getProxy()).getProxy();
        t1.getConnection();

        t1.createStatement();
    }

}

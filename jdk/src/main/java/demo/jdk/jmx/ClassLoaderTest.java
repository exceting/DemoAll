package demo.jdk.jmx;

/**
 * Create by 18073 on 2019/2/26.
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        t();
        ClassLoaderTest t = new ClassLoaderTest();

        Class c = ClassLoaderTest.class;

        ClassLoader.getSystemClassLoader(); //系统类加载器

        ClassLoader.getSystemClassLoader().getParent(); //扩展类加载器

        ClassLoader.getSystemClassLoader().getParent().getParent(); //引导类加载器

        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }

        ClassLoaderTest.class.getClassLoader();
    }

    public static synchronized void t() {
        System.out.println("t");
    }

}

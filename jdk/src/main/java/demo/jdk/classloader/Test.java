package demo.jdk.classloader;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Test {

    private static CL cl;

    static {
        System.out.println("test load");
    }

    public static void m1() {
        CL cl1 = new CL();
    }

    public static void main(String[] args) throws Exception {
        //CL cl1 = CL.class.newInstance();
        //Class<CL> clClass = CL.class;
        //Method method = clClass.getMethod("xx");
        //method.invoke(null);
        //CL.xx();
        //CL cl1 = new CL();
        // System.out.println("=====>"+(cl1.getClass() == CL.class));
        CL.xx();
        System.out.println("vvvvvv");

        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> c = customClassLoader.loadClass("agent.test.XxxService");
        //c.newInstance();
        //System.out.println("======> "+ Arrays.toString(c.getDeclaredFields()));

        System.out.println("======> "+ Arrays.toString(c.getDeclaredMethods()));

        Method method = c.getDeclaredMethod("sum", int.class, int.class);
        System.out.println("+++++++  " + (method.invoke(c.getDeclaredConstructor().newInstance(), 1, 2)));
    }

}

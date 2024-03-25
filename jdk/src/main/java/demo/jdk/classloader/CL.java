package demo.jdk.classloader;

public class CL extends SuperCL {

    static {
        System.out.println("demo load and before demo");
    }

    private Demo demo = new Demo();

    static {
        System.out.println("after demo");
    }

    public static void xx(){
        System.out.println("CL xx trigger");
    }

}

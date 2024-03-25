package demo.jdk.classloader;

import java.io.FileInputStream;

public class CustomClassLoader extends ClassLoader {

    private final String path = "/Users/butersam/Documents/IdeaProjects/DemoAll/jagent/build/classes/java/main";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("类加载：" + name);
        byte[] data = loadByte(name);
        if (data == null) {
            return super.findClass(name);
        }
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loadByte(String name) {
        name = name.replaceAll("\\.", "/");
        String classPath = path + "/" + name + ".class";
        try (FileInputStream fileInputStream = new FileInputStream(classPath)) {
            int len = fileInputStream.available();

            byte[] data = new byte[len];

            fileInputStream.read(data);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

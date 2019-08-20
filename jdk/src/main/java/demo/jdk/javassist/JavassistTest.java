/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.javassist;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import demo.jdk.javassist.test.Student;
import javassist.*;
import javassist.bytecode.AccessFlag;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

/**
 * @author sunqinwen
 * @version \: JavassistTest.java,v 0.1 2019-05-23 12:04
 */
public class JavassistTest {

    public static void main(String[] args) throws Exception {
        /*ClassPool pool = ClassPool.getDefault();
        //Class.forName("demo.jdk.javassist.test.Student");
        //Class.forName("demo.jdk.javassist.test.Person");
        CtClass cc = pool.get("demo.jdk.javassist.test.Student");
        cc.setSuperclass(pool.get("demo.jdk.javassist.test.Person"));
        CtMethod[] methods = cc.getDeclaredMethods();
        for(CtMethod m : methods){
            String beforeCode = "System.out.println(\"方法\"+\""+m.getName()+"\"+\"被调用-开始\");";
            String afterCode = "System.out.println(\"方法\"+\""+m.getName()+"\"+\"被调用-结束\");";
            System.out.println(beforeCode);
            System.out.println(afterCode);
            m.insertBefore(beforeCode);
            m.insertAfter(afterCode, true);
        }
        System.out.println(Student.class.getResource("").getPath());
        cc.writeFile(Student.class.getResource("/").getPath());
        //JavassistTest test = new JavassistTest();
        //test.dynGenerateClass();



        System.out.println("-------------------------------------------------");

        Student student = new Student();
        student.setId(1);
        student.setHomework("sdds");
        student.getId();
        student.getHomework();

        new Thread(()->{
            try {
                Thread.sleep(2000L);
                Thread.currentThread().getContextClassLoader().loadClass(Student.class.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Student student2 = new Student();
            student2.setId(1);
            student2.setHomework("sdds");
            student2.getId();
            student2.getHomework();
        }).start();

        System.out.println("main end");*/

        //System.out.println(String.format("%03d", 34146 % 500));

        System.out.println(transferJdbcUrl("jdbc:mysql://172.18.16.44:3370/bili2_platform?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull"));
    }

    static String transferJdbcUrl(String url) {
        try {
            if (Strings.isNullOrEmpty(url)) {
                return null;
            }
            URI uri = new URI(url.replace(":", ""));
            List<String> segments = Splitter.on("/").splitToList(uri.getPath());
            return segments.get(segments.size() - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public void dynGenerateClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.makeClass("demo.jdk.javassist.test.GenerateClass");//创建类
        ct.setInterfaces(new CtClass[]{pool.makeInterface("java.lang.Cloneable")});//让类实现Cloneable接口
        try {
            System.out.println(String.format("%s", "oo"));
            CtField f= new CtField(CtClass.intType,"id",ct);//获得一个类型为int，名称为id的字段
            f.setModifiers(AccessFlag.PUBLIC);//将字段设置为public
            ct.addField(f);//将字段设置到类上
            //添加构造函数
            CtConstructor constructor= CtNewConstructor.make("public GeneratedClass(int pId){this.id=pId;}",ct);
            ct.addConstructor(constructor);
            //添加方法
            CtMethod helloM=CtNewMethod.make("public void hello(String des){ System.out.println(des);}",ct);
            ct.addMethod(helloM);

            ct.writeFile();//将生成的.class文件保存到磁盘

            //下面的代码为验证代码
            Field[] fields = ct.toClass().getFields();
            System.out.println("属性名称：" + fields[0].getName() + "  属性类型：" + fields[0].getType());
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

}

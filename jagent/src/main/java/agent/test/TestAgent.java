package agent.test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class TestAgent {

    public static void premain(String arg, Instrumentation instrumentation) {
        System.out.println("----------> TestAgent trigger!");

        ClassPool classPool = new ClassPool();
        classPool.appendSystemPath();

        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (!"agent/test/XxxService".equals(className)) {
                    return null;
                }
                try {
                    CtClass ctClass = classPool.get("agent.test.XxxService");

                    String originalMethodName = "sum";
                    String agentMethodName = String.format("%s$agent", originalMethodName);
                    CtMethod ctMethod = ctClass.getDeclaredMethod(originalMethodName);

                    CtMethod proxyMethod = CtNewMethod.copy(ctMethod, ctClass, null);
                    proxyMethod.setName(agentMethodName);
                    ctClass.addMethod(proxyMethod);

                    StringBuilder sb =new StringBuilder();
                    ctMethod.setBody(sb.append("{long start = System.currentTimeMillis();")
                                    .append("try{")
                                    .append("return ")
                                    .append(agentMethodName)
                                    .append("($1, $2);")
                                    .append("} finally {")
                                    .append("System.out.println(\"********* dur = \" + (System.currentTimeMillis() - start));")
                                    .append("}}")
                                    .toString());
                    return ctClass.toBytecode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

}

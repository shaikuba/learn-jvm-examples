package it.learn.gc.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.util.HashSet;
import java.util.Set;

/**
 * 永久代、元空间
 * <p/>
 * 自JDK7起，JVM通过元空间替代永久代，在JDK8中，完全使用元空间替代永久代。
 * <p/>
 * 在JDK7及之前的版本中，可以使用参数-XX:PermSize=5M -XX:MaxPermSize=5M来限制永久代(元空间)内存大小，
 * 在JDK7之后使用-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m来限制元空间大小。
 * <p/>
 * 在JDK6或之前版本，常量池存放在永久代，而JDK7开始，常量池存放在堆内存中。
 */
public class MetaSpaceExample {


    public static void main(String[] args) {
//        usingHeapSpace();

        usingMetaSpace();

        String ss = "java";
        String sb = new StringBuilder().append("ja").append("va").toString();
        System.out.println(ss.intern() == sb);
    }

    public static void usingHeapSpace() {

        Set<String> stringSet = new HashSet<>();

        int i = 0;
        while (true) {
            stringSet.add(String.valueOf(i++).intern());
        }
    }

    public static void usingMetaSpace() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MetaSpaceExample.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(NoOp.INSTANCE);
            enhancer.create();
        }
    }
}

package it.learn.gc.oom;

import it.learn.gc.pojo.DirtyObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Out of memory exception happens when there is no enough memory in heap.
 * <p/>
 * VM Args：<code>-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${APP_HOME}/dump<code/>
 *
 * <p/>
 * The exception message outputs is "java.lang.OutOfMemoryError: Java heap space" when heap memory overflow
 *
 * @author Ray.Xu
 */
public class OomExample {


    public static void main(String[] args) throws InterruptedException {
        List<DirtyObj> dirtyObjs = new ArrayList<>();

        while (true) {
            //System.out.printf("heap size: %dkb\n", dirtyObjs.size());
            //Thread.sleep(2);
            dirtyObjs.add(new DirtyObj(1024));
        }
    }
}

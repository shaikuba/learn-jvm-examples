package it.learn.gc.oom;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Direct Memory OOM Example
 * <p>
 * -XX:MaxDirectMemorySize=size
 * <p>
 * Command line: java -Xms256m -Xmx256m -XX:MaxDirectMemorySize=256m it/learn/gc/oom/DirectMemoryExample
 */
public class DirectMemoryExample {

    private static final int _1MB = 1 * 1024 * 1024;

    public static void main(String[] args) {
        allocateDirectMemory(1024 * _1MB);
    }

    @SneakyThrows
    private static void allocateDirectMemory(int memSize) {

        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        for (int i = 1; ; ) {
            System.out.printf("allocate start...");
            long base = unsafe.allocateMemory(memSize);
            unsafe.setMemory(base, memSize, (byte) 0);
            System.out.printf("allocate: %04d\n", i++);
        }

    }
}

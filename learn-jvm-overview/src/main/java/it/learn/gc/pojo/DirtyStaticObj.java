package it.learn.gc.pojo;

import java.util.ArrayList;
import java.util.List;

public final class DirtyStaticObj {

    public static List<byte[]> dataPool = new ArrayList<>();

    public static void put(int byteSize) {
        dataPool.add(new byte[byteSize]);
    }
}

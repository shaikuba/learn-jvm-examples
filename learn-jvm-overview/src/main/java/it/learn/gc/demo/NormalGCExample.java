package it.learn.gc.demo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import it.learn.gc.pojo.DirtyObj;

import java.time.Duration;

public class NormalGCExample {

    private final static int _1KB = 1024;

    private final static LoadingCache<String, DirtyObj> LOADING_CACHE = CacheBuilder.newBuilder()
            .maximumSize(256)
            .expireAfterWrite(Duration.ofMillis(200))
            .build(CacheLoader.from(NormalGCExample::load));

    private static DirtyObj load(String key) {
        return new DirtyObj(1);
    }

    public static void main(String[] args) {
        while (true) {
            DirtyObj dirtyObj = new DirtyObj(_1KB * _1KB);
            LOADING_CACHE.put(String.valueOf(dirtyObj.hashCode()), dirtyObj);
        }
    }
}

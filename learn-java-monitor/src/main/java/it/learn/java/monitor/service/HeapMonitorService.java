package it.learn.java.monitor.service;

import it.learn.java.monitor.model.DirtyObj;

public interface HeapMonitorService {

    void heapLeak(DirtyObj dirtyObj);

    void heapOom(DirtyObj dirtyObj);
    
}

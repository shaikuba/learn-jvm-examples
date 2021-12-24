package it.learn.java.monitor.service.impl;

import it.learn.java.monitor.model.DirtyObj;
import it.learn.java.monitor.service.HeapMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
public class HeapMonitorServiceImpl implements HeapMonitorService {

    private static final List<DirtyObj> DIRTY_OBJS_LIST = new LinkedList<>();

    private static BlockingQueue<DirtyObj> DIRTY_OBJS_QUEUE = new LinkedBlockingQueue<>();

    private static ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

    @PostConstruct
    public void init() {
        taskScheduler.setPoolSize(5);
        taskScheduler.setThreadNamePrefix("Delay_queue_consumer");
        taskScheduler.afterPropertiesSet();
        taskScheduler.scheduleWithFixedDelay(() -> log.info("Processing one dirty object: {}", DIRTY_OBJS_QUEUE.poll()), Duration.ofSeconds(2));
    }

    @Override
    public void heapLeak(DirtyObj dirtyObj) {
        DIRTY_OBJS_QUEUE.offer(dirtyObj);
    }

    @Override
    public void heapOom(DirtyObj dirtyObj) {
        DIRTY_OBJS_LIST.add(dirtyObj);
    }
}

package it.learn.java.monitor.controller;

import it.learn.java.monitor.model.DirtyObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
@RequestMapping(value = "monitor/heap")
@RestController
public class LearnMonitorController {

    private static final int _1K = 1024;

    private static final int _1M = 1024 * 1024;

    private static final List<DirtyObj> DIRTY_OBJS_LIST = new LinkedList<>();

    private static BlockingQueue<DirtyObj> DIRTY_OBJS_QUEUE = new LinkedBlockingDeque<>();

    private static ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

    @PostConstruct
    public void init() {
        taskScheduler.setPoolSize(2);
        taskScheduler.setThreadNamePrefix("Delay_queue_consumer");
        taskScheduler.afterPropertiesSet();
        taskScheduler.scheduleWithFixedDelay(() -> log.info("Processing one dirty object: {}", DIRTY_OBJS_QUEUE.poll()), Duration.ofSeconds(2));
    }


    @GetMapping(value = "leak", params = "times")
    public Integer heapLeakDemo(Integer times) {

        while (times-- > 0) {
            DIRTY_OBJS_QUEUE.offer(new DirtyObj(_1K));
        }

        return times;
    }

    @GetMapping(value = "oom", params = "times")
    public Integer heapOomDemo(Integer times) {
        while (times-- > 0) {
            DIRTY_OBJS_LIST.add(new DirtyObj(_1M));
        }

        return times;
    }

}

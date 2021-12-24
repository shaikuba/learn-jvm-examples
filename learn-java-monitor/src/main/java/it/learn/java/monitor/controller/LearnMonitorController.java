package it.learn.java.monitor.controller;

import it.learn.java.monitor.model.DirtyObj;
import it.learn.java.monitor.service.HeapMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static it.learn.java.monitor.constant.SystemConfigConsts._1B;
import static it.learn.java.monitor.constant.SystemConfigConsts._1M;

@Slf4j
@RequestMapping(value = "monitor/heap")
@RestController
public class LearnMonitorController {

    @Autowired
    private HeapMonitorService heapMonitorService;

    @GetMapping(value = "leak", params = "times")
    public Integer heapLeakDemo(@RequestParam(defaultValue = "1") Integer times) {

        while (times-- > 0) {
            heapMonitorService.heapLeak(new DirtyObj(_1B));
        }

        return times;
    }

    @GetMapping(value = "oom", params = "times")
    public Integer heapOomDemo(@RequestParam(defaultValue = "1") Integer times) {
        while (times-- > 0) {
            heapMonitorService.heapOom(new DirtyObj(_1M));
        }

        return times;
    }

}

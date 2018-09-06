package com.ken.other;

import com.ken.other.aware.AwareService;
import com.ken.other.condition.ListService;
import com.ken.other.el.ElConfig;
import com.ken.other.event.DemoPublisher;
import com.ken.other.thread.AsyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/other")
public class OtherController {
    @Autowired
    private ElConfig elConfig;

    @Autowired
    private DemoPublisher demoPublisher;

    @Autowired
    private AwareService awareService;

    @Autowired
    private AsyncTaskService asyncTaskService;

    @Autowired
    private ListService listService;

    @GetMapping("/testEL")
    public String testEl() {
        return elConfig.outputResource();
    }

    @GetMapping("/testPublish/{msg}")
    public String testPublish(@PathVariable String msg) {
        demoPublisher.publish(msg);
        return msg;
    }

    @GetMapping("/testAware")
    public String testAware() {
        awareService.outputResult();
        return "success";
    }

    @GetMapping("/testAsync")
    public String testAsync() {
        for (int i = 0; i < 10; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        return "success";
    }

    @GetMapping("/testCondition")
    public String testCondition() {
        return listService.showListCmd();
    }
}

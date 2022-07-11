package com.magic.spel_params.controller;

import com.magic.spel_params.annotation.EnableNodeMetricsReport;
import com.magic.spel_params.model.NodeMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author mzk
 * @create 2022-07-09 10:39
 */
@RestController
@RequestMapping("node-metrics")
@Slf4j
public class NodeMetricsController {

    @GetMapping("hello")
    @EnableNodeMetricsReport(objId = "#objId")
    public void hello(String objId) {
        log.info("NodeMetricsController，objId：{}", objId);
    }

    @PostMapping("hello2")
    @EnableNodeMetricsReport(objId = "#map.objId")
    public void hello2(@RequestBody Map<String,String> map){
        log.info("hello2{}",map);
    }

    @PostMapping("hello3")
    @EnableNodeMetricsReport(objId = "#map.objId")
    public void hello3(@RequestBody NodeMetrics map){
        log.info("hello3{}",map);
    }

}

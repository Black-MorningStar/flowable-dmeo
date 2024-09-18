package com.example.flowabledemo.controller;

import com.example.flowabledemo.model.ProcessDefineModel;
import com.example.flowabledemo.model.ProcessResponse;
import com.example.flowabledemo.service.ProcessService;
import liquibase.pro.packaged.S;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 君墨笑
 * @date 2024/9/18
 */
@RequestMapping("/process")
@RestController
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @PostMapping("/deployProcessDefine")
    public ProcessResponse<String> deployProcessDefine() {
        String id = processService.deployProcessDefine();
        return ProcessResponse.build(id);
    }

    @PostMapping("/queryProcessDefine")
    public ProcessResponse queryProcessDefine(String deploymentId) {
        ProcessDefinition processDefinition = processService.queryProcessDefine(deploymentId);
        ProcessDefineModel model = new ProcessDefineModel();
        BeanUtils.copyProperties(processDefinition,model);
        return ProcessResponse.build(model);
    }

    @PostMapping("/startProcessInstance")
    public ProcessResponse startProcessInstance(@RequestBody Map<String,Object> map) {
        String instanceId = processService.startProcessInstance(map);
        return ProcessResponse.build(instanceId);
    }

    @PostMapping("/taskList")
    public void taskList() {
        List<Task> tasks = processService.queryTaskList();
    }
}

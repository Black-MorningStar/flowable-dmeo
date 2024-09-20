package com.example.flowabledemo.service;

import liquibase.pro.packaged.S;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 君墨笑
 * @date 2024/9/18
 */
@Service
public class ProcessService {

    @Autowired
    private ProcessEngine processEngine;

    /**
     * 部署一个流程定义模版
     */
    public String deployProcessDefine() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        return deployment.getId();
    }

    /**
     * 查询一个流程定义模版信息
     *
     * @param deploymentId
     * @return
     */
    public ProcessDefinition queryProcessDefine(String deploymentId) {
        ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
        return processDefinition;
    }

    /**
     * 开启一个流程实例
     *
     * @param param
     */
    public String startProcessInstance(Map<String,Object> param) {
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey("holidayRequest", param);
        return processInstance.getProcessInstanceId();
    }


    /**
     * 获取task任务列表
     *
     * @return
     */
    public List<Task> queryTaskList() {
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().taskCandidateGroup("managers").list();
        for (Task task : taskList) {
            Map<String, Object> variables = processEngine.getTaskService().getVariables(task.getId());
            System.out.println("ID:"+task.getId()+" name:"+task.getName()+" Assignee:"+task.getAssignee()+" owner:"+task.getOwner()+" describle:" + task.getDescription());
            System.out.println("employee:"+variables.get("employee")+" nrOfHolidays:"+variables.get("nrOfHolidays"));
        }
        return taskList;
    }

    /**
     * 完成task
     */
    public void completeTask() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("approved",true);
        processEngine.getTaskService().complete("5012",map);
    }

    /**
     * 获取历史流转数据
     */
    public void getHistoryData() {
        List<HistoricActivityInstance> list = processEngine.getHistoryService().createHistoricActivityInstanceQuery()
                .processInstanceId("5004")
                .finished()
                .orderByHistoricActivityInstanceEndTime()
                .asc().list();
        for (HistoricActivityInstance activity : list) {
            System.out.println("结果分割线=======================");
            System.out.println("结果分割线=======================");
            System.out.println("结果分割线=======================");
            System.out.println(activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }
    }
}

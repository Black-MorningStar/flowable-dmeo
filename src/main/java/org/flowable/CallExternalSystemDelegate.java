package org.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author 君墨笑
 * @date 2024/9/19
 */
public class CallExternalSystemDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("请假审批通过，开始发送邮件，申请人为: " + delegateExecution.getVariable("employee"));
    }
}

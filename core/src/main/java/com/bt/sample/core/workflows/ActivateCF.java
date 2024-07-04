package com.bt.sample.core.workflows;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.Replicator;


@Component(property = {
		Constants.BUNDLE_NAME+ "=Custom Workflow Process",
  Constants.SERVICE_DESCRIPTION + "=Custom workflow component to Activate Page",
  Constants.SERVICE_VENDOR + "=BT Sample"  
})
public class ActivateCF extends WorkflowHelper implements WorkflowProcess {
 
  @Reference
Replicator replicator;


  @Override
  public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
    try {  
       payloadPublishProcess(workItem,workflowSession,metaDataMap,ReplicationActionType.ACTIVATE,replicator);
    } catch (Exception e) {    
      throw new WorkflowException(e.getMessage(), e);
    }
  }
}

package com.bt.sample.core.workflows;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;

public class WorkflowHelper {

	private static final Logger log = LoggerFactory.getLogger(WorkflowHelper.class);

	public void payloadPublishProcess(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap,
			ReplicationActionType actionType, Replicator replicator) throws WorkflowException {
		try {
			
			log.info("Custom Workflow Helper PayloadProcess - Start");
			
			WorkflowData workflowData = workItem.getWorkflowData();

	        //Getting payload from Workflow
	        String payloadType = workflowData.getPayloadType();

			Session jcrSession = workflowSession.adaptTo(Session.class);
	            // Check type of payload; there are two - JCR_PATH and JCR_UUID
	            if (StringUtils.equals(payloadType, "JCR_PATH")) {
	                log.info("Payload type: {}", payloadType);

	                
	                // Get the JCR path from the payload
	                String path = workItem.getWorkflowData().getPayload().toString();
	                log.info("Payload path: {}", path);
	            }

			// String[] publishTargets =
			// metaDataMap.get("PROCESS_ARGS",String.class).split(",");
			List<String> paths = new ArrayList<String>();
			// getPayloads(workItem,workflowSession,rcManager);
			paths.add("/content/bt-sample-project/us/en/sling-model-with-service");
			//paths.add("/content/experience-fragments/bt-sample/language-masters/en/site/experience-fragment/master");
			for (String payloadPath : paths) {
				log.debug("Custom Workflow Helper PayloadProcess : Payload and Action {}  {}", payloadPath,
						actionType);
				replicate(jcrSession, payloadPath, actionType, replicator);
			}
			log.info("Custom Workflow Helper PayloadProcess - End");
		} catch (Exception e) {
			throw new WorkflowException(e.getMessage(), e);
		}

	}

	public void replicate(Session jcrSession, String payLoadPath, ReplicationActionType actionType,
			Replicator replicator) {
		log.info("Custom Workflow Helper replicate - Start");
		try {
			replicator.replicate(jcrSession, actionType, payLoadPath);
			log.info("Custom Workflow Helper replicate - END");
		} catch (ReplicationException e) {
			log.error("Custom Workflow Helper replication error {} \n Agents used {}", e.getMessage());
		}
		log.debug("Custom Workflow Helper replicate - End");
	}
	
	
	
	/*
	 * public List<String> getPayloadPaths(String path, ResourceCollection
	 * rcCollection) {
	 * log.debug("Reliant Custom Workflow Helper getPayloadPaths - Start");
	 * List<String> paths = new ArrayList<>(); // This Handle single payload
	 * instance if (rcCollection == null) { paths.add(path); } // This handles
	 * multiple payload instance else { log.
	 * debug("Reliant Custom Workflow Helper getPayloadPaths ResourceCollection detected  {}"
	 * , rcCollection.getPath()); try { List<Node> members = rcCollection.list(new
	 * String[] { "cq:Page" }); for (Node member : members) { String mPath =
	 * member.getPath(); paths.add(mPath); } } catch (RepositoryException re) { log.
	 * error("Cannot build path list out of the resource collection for path  {}",
	 * rcCollection.getPath()); } }
	 * log.debug("Reliant Custom Workflow Helper getPayloadPaths - End"); return
	 * paths; }
	 */
	/*
	 * public List<String> getPayloads(WorkItem workItem,WorkflowSession
	 * workflowSession,ResourceCollectionManager rcManager) throws
	 * RepositoryException{ List<String> payloadList = new ArrayList<>(); Session
	 * jcrSession = workflowSession.adaptTo(Session.class); WorkflowData
	 * workflowData = workItem.getWorkflowData(); String path = null; String type =
	 * workflowData.getPayloadType(); if (type.equals("JCR_PATH") &&
	 * workflowData.getPayload() !=null) { path =
	 * workflowData.getPayload().toString(); } if(path !=null){ Node node = (Node)
	 * jcrSession.getItem(path); //Resource collection will be null for single
	 * payload , this handled in getPayloadPaths() ResourceCollection rcCollection =
	 * ResourceCollectionUtil.getResourceCollection(node, rcManager); payloadList =
	 * getPayloadPaths(path, rcCollection); } return payloadList;
	 * 
	 * }
	 */

}

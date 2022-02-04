/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.projectjobscheduling.persistence;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.jdom.Element;
//import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.examples.common.persistence.AbstractXmlSolutionExporter;
import org.optaplanner.examples.projectjobscheduling.domain.Schedule;
import org.optaplanner.examples.projectjobscheduling.domain.Allocation;
import org.optaplanner.core.api.score.constraint.ConstraintMatchTotal;
import org.optaplanner.core.api.score.constraint.ConstraintMatch;
import org.optaplanner.examples.common.business.SolutionBusiness;
import org.optaplanner.examples.common.app.CommonApp;
import org.optaplanner.examples.projectjobscheduling.domain.ResourceRequirement;
import org.optaplanner.examples.projectjobscheduling.domain.JobType;
import org.optaplanner.examples.projectjobscheduling.domain.TimeRestriction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
//import java.time.LocalDateTime;
//import java.time.Duration;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.temporal.ChronoUnit;

public class ProjectJobSchedulingXmlExporter extends AbstractXmlSolutionExporter<Schedule> {
    private static final String OUTPUT_FILE_SUFFIX = "xml"; 

    public XmlOutputBuilder<Schedule> createXmlOutputBuilder() {
        return new ProjectJobSchedulingOutputBuilder();
    }
    
    @Override
    public String getOutputFileSuffix() {
        return OUTPUT_FILE_SUFFIX;
    }

    /*public static void main(String[] args) {
        new ProjectJobSchedulingXmlExporter().convertAll();
    }*/

    /*public ProjectJobSchedulingXmlExporter() {
        super(new ProjectJobSchedulingDao());
    }*/


    public static class ProjectJobSchedulingOutputBuilder extends XmlOutputBuilder<Schedule> {

        private Schedule schedule;        

        @Override
        public void setSolution(Schedule solution) {
            schedule = solution;
        } 
       

        @Override
        public void writeSolution() throws IOException {
            Element solutionElement = new Element("Solution");
            document.setRootElement(solutionElement);

            Element schedulingIDElement = new Element("SchedulingID");
            schedulingIDElement.setText(schedule.getCode());
            solutionElement.addContent(schedulingIDElement);

            Element hardConstraintsPenaltyElement = new Element("HardConstraintsPenalty");
            hardConstraintsPenaltyElement.setText(Integer.toString(schedule.getScore().getHardScore()));
            solutionElement.addContent(hardConstraintsPenaltyElement);
            
            Element soft1ConstraintsPenaltyElement = new Element("SoftConstraintsPenalty1");
            soft1ConstraintsPenaltyElement.setText(Integer.toString(schedule.getScore().getMediumScore()));
            solutionElement.addContent(soft1ConstraintsPenaltyElement);
            
            Element soft2ConstraintsPenaltyElement = new Element("SoftConstraintsPenalty2");
            soft2ConstraintsPenaltyElement.setText(Integer.toString(schedule.getScore().getSoftScore()));
            solutionElement.addContent(soft2ConstraintsPenaltyElement);
          
            /*Element cmtListElement = new Element("ConstraintMatchTotalList");
            solutionElement.addContent(cmtListElement);     
            
            if (getSolutionBusiness().isConstraintMatchEnabled()) {
                List<ConstraintMatchTotal> constraintMatchTotalList = getSolutionBusiness().getConstraintMatchTotalList();
                for (int x=0; x < constraintMatchTotalList.size(); x++) {
                    ConstraintMatchTotal constraintMatchTotal = constraintMatchTotalList.get(x);
                    
                    Element cmTotalElement = new Element("ConstraintMatchTotal");
                    cmtListElement.addContent(cmTotalElement);
                    
                    Element nameElement = new Element("ConstraintName");
                    String cName = constraintMatchTotal.getConstraintName();
                    nameElement.setText(cName);
                    cmTotalElement.addContent(nameElement);
                                                            
                    

                    Element scoreLevelElement = new Element("ScoreTotal");                    
                    String scoreTotal = constraintMatchTotal.getScoreTotal().toString();
                    scoreLevelElement.setText(scoreTotal);
                    cmTotalElement.addContent(scoreLevelElement);                    

                    Element matchCountElement = new Element("MatchCount");                    
                    int nCnt = constraintMatchTotal.getConstraintMatchCount();
                    matchCountElement.setText(Integer.toString(nCnt));
                    cmTotalElement.addContent(matchCountElement);                           
                    
                    Element cmListElement = new Element("ConstraintMatchList");
                    cmTotalElement.addContent(cmListElement);
                    
                    Set<? extends ConstraintMatch> constraintMatchSet = constraintMatchTotal.getConstraintMatchSet();
                    for (ConstraintMatch constraintMatch : constraintMatchSet) {
                    	Element cMatchElement = new Element("ConstraintMatch");
                        cmListElement.addContent(cMatchElement);
                    	
                        String cMatch = constraintMatch.toString();
                        cMatch = cMatch.substring(cMatch.indexOf("["), cMatch.length());
                        cMatchElement.setText(cMatch);
//                        System.out.println(cMatch);
                    }
                                          
                }
            }
           */
            Element allocListElement = new Element("AllocationTotalList");
            solutionElement.addContent(allocListElement);                
            
            for (Allocation allocation : schedule.getAllocationList()) {
//            	JobType jt = allocation.getJob().getJobType(); 
//            	if ((jt == JobType.SOURCE) || (jt == JobType.SINK)) continue;
            	
            	Element allocationElement = new Element("Allocation");
            	allocListElement.addContent(allocationElement);
            	
            	Element allocIdElement = new Element("Id");
            	allocIdElement.setText("Allocation-" + Long.toString(allocation.getId()));
            	allocationElement.addContent(allocIdElement);
           	
            	Element allocOperationElement = new Element("OID");
            	allocOperationElement.setText(allocation.getJob().getOID());
            	allocationElement.addContent(allocOperationElement);
           	
                Calendar calendar = Calendar.getInstance();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            	Element startDateElement = new Element("StartDate");
                int startDate = allocation.getStartDate();
                Date startDt = schedule.getStartDate();                   
                //LocalDateTime startDateTime = startDt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                //startDateTime = startDateTime.plus(Duration.of(startDate, ChronoUnit.MINUTES));
                
                calendar.setTime(startDt);
                //long stimeInMinutes = calendar.getTimeInMillis() / 1000 / 60;
                //startDateElement.setText(dateFormat.format(Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant())));
                calendar.add(Calendar.MINUTE, startDate);
               	startDateElement.setText(dateFormat.format(calendar.getTime()));
            	allocationElement.addContent(startDateElement);
            	
            	Element endDateElement = new Element("EndDate");
                int endDate = allocation.getEndDate();
                Date endDt = schedule.getStartDate();            
                //LocalDateTime endDateTime = endDt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                //endDateTime = endDateTime.plus(Duration.of(endDate, ChronoUnit.MINUTES));    
                calendar.setTime(endDt);
                //long etimeInMinutes = calendar.getTimeInMillis() / 1000 / 60;
                //endDateElement.setText(dateFormat.format(Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant()))); 
                calendar.add(Calendar.MINUTE, endDate);
               	endDateElement.setText(dateFormat.format(calendar.getTime()));
            	allocationElement.addContent(endDateElement);

            	Element quantityElement = new Element("Quantity");
            	quantityElement.setText(Integer.toString(allocation.getExecutionMode().getDuration()));
            	allocationElement.addContent(quantityElement);

            	Element resReqListElement = new Element("ResourceList");
            	allocationElement.addContent(resReqListElement);
            	
            	for (ResourceRequirement resReq: allocation.getExecutionMode().getResourceRequirementList()) {
            		Element resReqElement = new Element("Resource");
                    Element ridElement = new Element("RID");
                    ridElement.setText(resReq.getResource().getRID());
                    resReqElement.addContent(ridElement);
                    Element resQuantityElement = new Element("Quantity");                        
                    resQuantityElement.setText(Integer.toString(resReq.getRequirement()));
                    resReqElement.addContent(resQuantityElement);                	
                	resReqListElement.addContent(resReqElement);
            	}                
            	
            	Element predAllocListElement = new Element("PredAllocationList");
            	allocationElement.addContent(predAllocListElement);
            	
            	for (Allocation predAlloc: allocation.getPredecessorAllocationList()) {
            		Element predAllocElement = new Element("PredAllocation");
                	predAllocElement.setText("Allocation-" + Long.toString(predAlloc.getId()));
                	predAllocListElement.addContent(predAllocElement);
            	}   
            	
            	Element orderElement = new Element("Zakaz");
            	if (allocation.getJob().getProject() != null) {
            		orderElement.setText(allocation.getJob().getProject().getZakaz());
            	}	
            	allocationElement.addContent(orderElement);
            	
            }
            
        }
    }

}

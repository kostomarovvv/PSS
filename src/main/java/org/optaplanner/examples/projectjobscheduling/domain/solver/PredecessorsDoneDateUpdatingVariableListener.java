/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

package org.optaplanner.examples.projectjobscheduling.domain.solver;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.examples.projectjobscheduling.domain.Allocation;
import org.optaplanner.examples.projectjobscheduling.domain.JobType;
import org.optaplanner.examples.projectjobscheduling.domain.Schedule;

public class PredecessorsDoneDateUpdatingVariableListener implements VariableListener<Schedule, Allocation> {

    @Override
    public void beforeEntityAdded(ScoreDirector<Schedule> scoreDirector, Allocation allocation) {
        // Do nothing
    }

    @Override
    public void afterEntityAdded(ScoreDirector<Schedule> scoreDirector, Allocation allocation) {
        updateAllocation(scoreDirector, allocation);
    }

    @Override
    public void beforeVariableChanged(ScoreDirector<Schedule> scoreDirector, Allocation allocation) {
        // Do nothing
    }

    @Override
    public void afterVariableChanged(ScoreDirector<Schedule> scoreDirector, Allocation allocation) {
        updateAllocation(scoreDirector, allocation);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<Schedule> scoreDirector, Allocation allocation) {
        // Do nothing
    }

    @Override
    public void afterEntityRemoved(ScoreDirector<Schedule> scoreDirector, Allocation allocation) {
        // Do nothing
    }

    protected void updateAllocation(ScoreDirector<Schedule> scoreDirector, Allocation originalAllocation) {
        //if (originalAllocation.getStage() == 1) {
            Queue<Allocation> uncheckedSuccessorQueue = new ArrayDeque<>();
            uncheckedSuccessorQueue.addAll(originalAllocation.getSuccessorAllocationList());
            while (!uncheckedSuccessorQueue.isEmpty()) {
                Allocation allocation = uncheckedSuccessorQueue.remove();
                boolean updated = updatePredecessorsDoneDate(scoreDirector, allocation);            
                if (updated) {
                    uncheckedSuccessorQueue.addAll(allocation.getSuccessorAllocationList());
                }
            }
        //}
/*
        if (originalAllocation.getStage() == 2) {
            Queue<Allocation> uncheckedPredecessorQueue = new ArrayDeque<>();
            uncheckedPredecessorQueue.addAll(originalAllocation.getPredecessorAllocationList());
            while (!uncheckedPredecessorQueue.isEmpty()) {
                Allocation allocation = uncheckedPredecessorQueue.remove();
                boolean updated = updateDelayStartDate(scoreDirector, allocation);
                if (updated) {
                    uncheckedPredecessorQueue.addAll(allocation.getPredecessorAllocationList());
                }
            }
        }       
*/
    }

    /**
     * @param scoreDirector never null
     * @param allocation never null
     * @return true if the startDate changed
     */
    protected boolean updatePredecessorsDoneDate(ScoreDirector<Schedule> scoreDirector, Allocation allocation) {
        // For the source the doneDate must be 0.
        Integer doneDate = 0;
        //*if (allocation.getStage() == 2)
        //*    return false;
        //Integer predDoneDate = 0;
        for (Allocation predecessorAllocation : allocation.getPredecessorAllocationList()) {
            int endDate = predecessorAllocation.getEndDate();
            doneDate = Math.max(doneDate, endDate);
            /*if (Objects.equals(doneDate, endDate))
                predDoneDate = predecessorAllocation.getPredecessorsDoneDate();*/
        }
        if (Objects.equals(doneDate, allocation.getPredecessorsDoneDate())) {
            return false;
        }
        scoreDirector.beforeVariableChanged(allocation, "predecessorsDoneDate");
        allocation.setPredecessorsDoneDate(doneDate);
        scoreDirector.afterVariableChanged(allocation, "predecessorsDoneDate");

/*        // выравниваем предыдущие операции по правому краю
        for (Allocation predecessorAllocation : allocation.getPredecessorAllocationList()) {
            int endDate = predecessorAllocation.getEndDate();
            if (Objects.equals(endDate, doneDate)) continue;
            scoreDirector.beforeVariableChanged(predecessorAllocation, "predecessorsDoneDate");
            predecessorAllocation.setPredecessorsDoneDate(predDoneDate);
            scoreDirector.afterVariableChanged(predecessorAllocation, "predecessorsDoneDate");            
        }*/

        return true;
    }
    
    protected boolean updateDelayStartDate(ScoreDirector<Schedule> scoreDirector, Allocation allocation) {        
        Integer doneDate = 0; //allocation.getSinkAllocation().getEndDate();
        Allocation successorAllocation = allocation.getSuccessorAllocationList().get(0);
        for (Allocation predSuccessorAllocation : successorAllocation.getPredecessorAllocationList()) {
            int endDate = predSuccessorAllocation.getEndDate();
            doneDate = Math.max(doneDate, endDate);         
        }
        if (Objects.equals(doneDate, allocation.getPredecessorsDoneDate())) { 
            return false;
        }
        //*int delay = allocation.getDelay() + doneDate - allocation.getEndDate();
        if (allocation.getEndDate() >= doneDate) 
            return false;        
        int predDoneDate = allocation.getPredecessorsDoneDate() + doneDate - allocation.getEndDate();
/*
        Allocation optimAllocation = null;
        if (allocation.getEndDate() < curEndDate) {
            curEndDate = allocation.getEndDate();
        }
        int startDate = allocation.getEndDate();
        for (Allocation predAllocation : allocation.getPredecessorAllocationList()) {
            if (predAllocation.getEndDate() < curEndDate) { 
                predAllocation.setDelay(predAllocation.getDelay() + curEndDate - predAllocation.getEndDate()); 
            }
            if (predAllocation.getIsOptimizationJob()) {
                optimAllocation = predAllocation;
            }
            if (predAllocation.getStartDate() < startDate) {
                startDate = predAllocation.getStartDate();
            }
        }     
        if (optimAllocation != null) {
            curEndDate = optimAllocation.getStartDate();        
        }
*/                
        scoreDirector.beforeVariableChanged(allocation, "predecessorsDoneDate");
        //Integer delta = doneDate - allocation.getEndDate();
        allocation.setPredecessorsDoneDate(predDoneDate);
        allocation.setDelay(0);
        scoreDirector.afterVariableChanged(allocation, "predecessorsDoneDate");
        return true;
    }
}

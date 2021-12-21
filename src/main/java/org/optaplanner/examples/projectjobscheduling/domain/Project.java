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

package org.optaplanner.examples.projectjobscheduling.domain;

import java.util.List;

import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.projectjobscheduling.domain.resource.LocalResource;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PjsProject")
public class Project extends AbstractPersistable {

    private int releaseDate;
    private int criticalPathDuration;
    private int priority;

    private List<LocalResource> localResourceList;
    private List<Job> jobList;

    private String zakaz;

    private Schedule schedule;

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getCriticalPathDuration() {
        return criticalPathDuration;
    }

    public void setCriticalPathDuration(int criticalPathDuration) {
        this.criticalPathDuration = criticalPathDuration;
    }

    public List<LocalResource> getLocalResourceList() {
        return localResourceList;
    }

    public void setLocalResourceList(List<LocalResource> localResourceList) {
        this.localResourceList = localResourceList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getZakaz() {
        return this.zakaz;
    }

    public void setZakaz(String zakaz) {
        this.zakaz = zakaz;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public int getCriticalPathEndDate() {
        return releaseDate + criticalPathDuration;
    }

    public String getLabel() {
        return "Project " + id;
    }

    public int getCriticalPeriod() {
        return schedule.getCriticalPathDuration();
    }

}

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

package org.optaplanner.examples.projectjobscheduling.domain.resource;

import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.projectjobscheduling.domain.TimeRestriction;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamInclude;

import java.util.List;

@XStreamAlias("PjsResource")
@XStreamInclude({
        GlobalResource.class,
        LocalResource.class
})
public abstract class Resource extends AbstractPersistable {

    private int capacity;

    private String RID;
    private List<TimeRestriction> timeRestrictionList;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUsedDayCapacity(int usedDay) {
        int resCapacity = capacity;
        for (TimeRestriction timeRestriction : getTimeRestrictionList()) {
            if ((usedDay >= timeRestriction.getStartRestriction()) && (usedDay < timeRestriction.getEndRestriction())) {
                resCapacity = timeRestriction.getQuantity();
                break;
            }
        }
        return resCapacity;
    }

    public String getRID() {
        return this.RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }
    
    public List<TimeRestriction> getTimeRestrictionList() {
        return this.timeRestrictionList;
    }

    public void setTimeRestrictionList(List<TimeRestriction> timeRestrictionList) {
        this.timeRestrictionList = timeRestrictionList;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public abstract boolean isRenewable();

}

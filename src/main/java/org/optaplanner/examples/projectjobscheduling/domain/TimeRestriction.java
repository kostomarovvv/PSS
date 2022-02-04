package org.optaplanner.examples.projectjobscheduling.domain;

public class TimeRestriction {
    private Integer startRestriction;
    private Integer endRestriction;
    private int quantity;


    public Integer getStartRestriction() {
        return this.startRestriction;
    }

    public void setStartRestriction(Integer startRestriction) {
        this.startRestriction = startRestriction;
    }

    public Integer getEndRestriction() {
        return this.endRestriction;
    }

    public void setEndRestriction(Integer endRestriction) {
        this.endRestriction = endRestriction;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}

package com.mla.task_board.domain.dtos.outputmodels;

public class GetStatesOutputModel {
    public Integer position;
    public String state;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

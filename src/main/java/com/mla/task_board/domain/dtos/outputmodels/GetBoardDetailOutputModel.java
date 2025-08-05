package com.mla.task_board.domain.dtos.outputmodels;

import com.mla.task_board.domain.dtos.base.GroupNamePositionKey;

import java.util.List;

public class GetBoardDetailOutputModel {
    public Integer id;
    public String name;
    public Integer position;
    public String backgroundColor;
    public String fontColor;
    public String backgroundColorCard;
    public String fontColorCard;
    public List<CardDetailOutputModel> cardDetails;

    public GetBoardDetailOutputModel(GroupNamePositionKey group, List<CardDetailOutputModel> cardDetails){
        this.id = group.id();
        this.name = group.name();
        this.position = group.position();
        this.backgroundColor = group.backgroundColor();
        this.fontColor = group.fontColor();
        this.cardDetails = cardDetails;
        this.backgroundColorCard = group.backgroundColorCard();
        this.fontColorCard = group.fontColorCard();
    }
}

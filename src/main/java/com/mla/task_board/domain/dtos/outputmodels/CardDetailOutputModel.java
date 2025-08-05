package com.mla.task_board.domain.dtos.outputmodels;

import com.mla.task_board.domain.dtos.repositorymodels.GetBoardDetailsRepositoryModel;

public class CardDetailOutputModel {
    public Integer id;
    public String title;
    public String description;
    public String state;
    public Boolean block;

    public CardDetailOutputModel() {}

    public CardDetailOutputModel(GetBoardDetailsRepositoryModel repositoryModel){
        this.id = repositoryModel.getCardId();
        this.title = repositoryModel.getCardTitle();
        this.description = repositoryModel.getCardDescription();
        this.state = repositoryModel.getCardState();
        this.block = repositoryModel.getCardBlock();
    }
}

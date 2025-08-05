package com.mla.task_board.domain.interfaces.Repository;


import com.mla.task_board.domain.dtos.inputmodels.*;
import com.mla.task_board.domain.dtos.repositorymodels.GetBoardDetailsRepositoryModel;

public interface ICardRepository {
    GetBoardDetailsRepositoryModel getDetailById(Integer id);
    Integer create(CreateCardInputModel input);
    void update(Integer id, ChangeCardInputModel card);
    void changeBlock(int cardId, String reason);
    void changeBoard(Integer id, Integer boardId);
    void delete(Integer id);
}

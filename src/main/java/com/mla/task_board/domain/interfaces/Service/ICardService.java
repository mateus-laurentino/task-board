package com.mla.task_board.domain.interfaces.Service;

import com.mla.task_board.domain.dtos.base.ResultViewModel;
import com.mla.task_board.domain.dtos.inputmodels.*;
import com.mla.task_board.domain.dtos.outputmodels.CardDetailOutputModel;

import java.util.List;

public interface ICardService {
    ResultViewModel<CardDetailOutputModel> getDetail(Integer id);
    ResultViewModel<CardDetailOutputModel> create(CreateCardInputModel card);
    ResultViewModel<CardDetailOutputModel> update(Integer id, ChangeCardInputModel card);
    ResultViewModel<Boolean> delete(Integer id);
    ResultViewModel<CardDetailOutputModel> changeBlock(Integer id, ChangeBlockInputModel input);
    ResultViewModel<CardDetailOutputModel> changeBoard(Integer id, Integer boardId);
}

package com.mla.task_board.domain.interfaces.Service;

import com.mla.task_board.domain.dtos.base.ResultViewModel;
import com.mla.task_board.domain.dtos.outputmodels.GetBoardDetailOutputModel;

import java.util.List;

public interface IBoardService {
    ResultViewModel<List<GetBoardDetailOutputModel>> getAll();
}

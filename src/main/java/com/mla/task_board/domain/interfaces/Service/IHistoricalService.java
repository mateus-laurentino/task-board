package com.mla.task_board.domain.interfaces.Service;

import com.mla.task_board.domain.dtos.base.ResultViewModel;
import com.mla.task_board.domain.dtos.outputmodels.GetHistoricalOutputModel;

import java.util.List;

public interface IHistoricalService {
    ResultViewModel<List<GetHistoricalOutputModel>> getHistoricByCardId(Integer cardId);
}

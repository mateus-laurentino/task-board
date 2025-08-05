package com.mla.task_board.domain.interfaces.Repository;

import com.mla.task_board.domain.dtos.outputmodels.GetHistoricalOutputModel;

import java.util.List;

public interface IHistoricalRepository {
    List<GetHistoricalOutputModel> getByCardId(Integer cardId);
}

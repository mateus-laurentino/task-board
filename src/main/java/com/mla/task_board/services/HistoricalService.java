package com.mla.task_board.services;

import com.mla.task_board.domain.dtos.base.ResultViewModel;
import com.mla.task_board.domain.dtos.outputmodels.CardDetailOutputModel;
import com.mla.task_board.domain.dtos.outputmodels.GetHistoricalOutputModel;
import com.mla.task_board.domain.interfaces.Repository.IHistoricalRepository;
import com.mla.task_board.domain.interfaces.Service.IHistoricalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricalService implements IHistoricalService {

    private final IHistoricalRepository _historicalRepository;

    public HistoricalService(IHistoricalRepository historicalRepository){
        _historicalRepository = historicalRepository;
    }

    @Override
    public ResultViewModel<List<GetHistoricalOutputModel>> getHistoricByCardId(Integer cardId) {
        return cardId == 0
                ? new ResultViewModel<List<GetHistoricalOutputModel>>().addErrors("The id field is invalid")
                : new ResultViewModel<>(_historicalRepository.getByCardId(cardId));
    }
}

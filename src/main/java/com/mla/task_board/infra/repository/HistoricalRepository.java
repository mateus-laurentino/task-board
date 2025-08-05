package com.mla.task_board.infra.repository;

import com.mla.task_board.domain.dtos.outputmodels.GetHistoricalOutputModel;
import com.mla.task_board.domain.dtos.repositorymodels.GetBoardDetailsRepositoryModel;
import com.mla.task_board.domain.interfaces.Repository.IHistoricalRepository;
import com.mla.task_board.infra.data.AppBoardContext;
import com.mla.task_board.infra.repository.queries.HistoricalQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoricalRepository  implements IHistoricalRepository {

    private final AppBoardContext _appBoardContext;

    public HistoricalRepository(AppBoardContext appBoardContext){
        _appBoardContext = appBoardContext;
    }

    @Override
    public List<GetHistoricalOutputModel> getByCardId(Integer cardId) {
        return  _appBoardContext.query(HistoricalQuery.getByCardId, new BeanPropertyRowMapper<>(GetHistoricalOutputModel.class), cardId);
    }
}

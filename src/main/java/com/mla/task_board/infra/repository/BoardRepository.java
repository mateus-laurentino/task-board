package com.mla.task_board.infra.repository;

import com.mla.task_board.domain.dtos.repositorymodels.GetBoardDetailsRepositoryModel;
import com.mla.task_board.domain.interfaces.Repository.IBoardRepository;
import com.mla.task_board.infra.data.AppBoardContext;
import com.mla.task_board.infra.repository.queries.BoardQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository implements IBoardRepository {

    private final AppBoardContext _appBoardContext;

    public BoardRepository(AppBoardContext appBoardContext){
        _appBoardContext = appBoardContext;
    }

    public List<GetBoardDetailsRepositoryModel> getDetails(){
        return _appBoardContext.query(BoardQuery.getBoardDetails, new BeanPropertyRowMapper<>(GetBoardDetailsRepositoryModel.class));
    }
}
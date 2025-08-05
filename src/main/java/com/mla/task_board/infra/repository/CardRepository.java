package com.mla.task_board.infra.repository;

import com.mla.task_board.domain.dtos.inputmodels.*;
import com.mla.task_board.domain.dtos.repositorymodels.GetBoardDetailsRepositoryModel;
import com.mla.task_board.domain.interfaces.Repository.ICardRepository;
import com.mla.task_board.infra.data.AppBoardContext;
import com.mla.task_board.infra.repository.queries.CardQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;

@Repository
public class CardRepository implements ICardRepository {

    private final AppBoardContext _appBoardContext;

    public CardRepository(AppBoardContext appBoardContext){
        _appBoardContext = appBoardContext;
    }

    public GetBoardDetailsRepositoryModel getDetailById(Integer id){
        return _appBoardContext.queryForObject(CardQuery.getDetailById, new BeanPropertyRowMapper<>(GetBoardDetailsRepositoryModel.class), id);
    }

    public Integer create(CreateCardInputModel input){
        var keyHolder = new GeneratedKeyHolder();

        _appBoardContext.update(conn -> {
            var statement = conn.prepareStatement(CardQuery.insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, input.title);
            statement.setString(2, input.description);
            statement.setInt(3, input.boardId);
            return statement;
        }, keyHolder);

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : null;
    };

    public void update(Integer id, ChangeCardInputModel card){
        _appBoardContext.update(CardQuery.update, card.title, card.description, id);
    }

    public void changeBlock(int cardId, String reason) {
        int rowsAffected = _appBoardContext.update(CardQuery.unlockCard, reason, cardId);

        if (rowsAffected == 0) _appBoardContext.update(CardQuery.blockCard, cardId, reason);
    }

    public void changeBoard(Integer id, Integer boardId){
        _appBoardContext.update(CardQuery.updateBoard, boardId, id);
    }

    public void delete(Integer id){
        _appBoardContext.update(CardQuery.disable, id);
    }
}

package com.mla.task_board.services;

import com.mla.task_board.domain.dtos.base.ResultViewModel;
import com.mla.task_board.domain.dtos.inputmodels.*;
import com.mla.task_board.domain.dtos.outputmodels.CardDetailOutputModel;
import com.mla.task_board.domain.interfaces.Repository.ICardRepository;
import com.mla.task_board.domain.interfaces.Service.ICardService;
import org.springframework.stereotype.Service;

@Service
public class CardService implements ICardService {
    private final ICardRepository _cardRepository;

    public CardService(ICardRepository cardRepository){
        _cardRepository = cardRepository;
    }

    @Override
    public ResultViewModel<CardDetailOutputModel> getDetail(Integer id) {
        var card = _cardRepository.getDetailById(id);
        var output = new CardDetailOutputModel(card);
        return new ResultViewModel<>(output);
    }

    @Override
    public ResultViewModel<CardDetailOutputModel> create(CreateCardInputModel card) {
        var id = _cardRepository.create(card);
        if (id == null)
            return new ResultViewModel<CardDetailOutputModel>().addErrors("An error occurred while creating the task.");

        return getDetail(id);
    }

    @Override
    public ResultViewModel<CardDetailOutputModel> update(Integer id, ChangeCardInputModel card) {
        if (id == 0)
            return new ResultViewModel<CardDetailOutputModel>().addErrors("The id field is invalid");

        _cardRepository.update(id, card);
        return getDetail(id);
    }

    @Override
    public ResultViewModel<Boolean> delete(Integer id) {
        if (id == 0)
            return new ResultViewModel<Boolean>().addErrors("The id field is invalid");

        _cardRepository.delete(id);
        return new ResultViewModel<>(true);
    }

    @Override
    public ResultViewModel<CardDetailOutputModel> changeBlock(Integer id, ChangeBlockInputModel input) {
        if (id == 0)
            return new ResultViewModel<CardDetailOutputModel>().addErrors("The id field is invalid");

        _cardRepository.changeBlock(id, input.reason);
        return getDetail(id);
    }

    @Override
    public ResultViewModel<CardDetailOutputModel> changeBoard(Integer id, Integer boardId) {
        if (id == 0)
            return new ResultViewModel<CardDetailOutputModel>().addErrors("The id field is invalid");

        _cardRepository.changeBoard(id,boardId);
        return getDetail(id);
    }
}

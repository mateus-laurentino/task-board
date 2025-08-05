package com.mla.task_board.controllers;

import com.mla.task_board.controllers.base.ControllerBase;
import com.mla.task_board.domain.dtos.base.ResultViewModel;
import com.mla.task_board.domain.dtos.inputmodels.*;
import com.mla.task_board.domain.dtos.outputmodels.CardDetailOutputModel;
import com.mla.task_board.domain.dtos.outputmodels.GetHistoricalOutputModel;
import com.mla.task_board.domain.interfaces.Service.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/card", produces = "application/json")
public class CardController extends ControllerBase {
    private final ICardService _cardService;
    private final IHistoricalService _historicalService;

    public CardController(ICardService cardService, IHistoricalService historicalService){
        _cardService = cardService;
        _historicalService = historicalService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultViewModel<CardDetailOutputModel>> getDetails(@PathVariable Integer id){
        return response(_cardService.getDetail(id));
    }

    @GetMapping("{id}/historic")
    public ResponseEntity<ResultViewModel<List<GetHistoricalOutputModel>>> getHistoric(@PathVariable Integer id){
        return response(_historicalService.getHistoricByCardId(id));
    }

    @PostMapping
    public ResponseEntity<ResultViewModel<CardDetailOutputModel>> create(@Valid @RequestBody CreateCardInputModel card){
        return response(_cardService.create(card));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResultViewModel<CardDetailOutputModel>> update(@PathVariable Integer id, @Valid @RequestBody ChangeCardInputModel card){
        return response(_cardService.update(id, card));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResultViewModel<Boolean>> delete(@PathVariable Integer id){
        return response(_cardService.delete(id));
    }

    @PatchMapping("{id}/block")
    public ResponseEntity<ResultViewModel<CardDetailOutputModel>> changeBlock(@PathVariable Integer id, @RequestBody ChangeBlockInputModel input){
        return response(_cardService.changeBlock(id, input));
    }

    @PatchMapping("{id}/board/{boardId}")
    public ResponseEntity<ResultViewModel<CardDetailOutputModel>> changeBoard(@PathVariable Integer id, @PathVariable Integer boardId){
        return response(_cardService.changeBoard(id, boardId));
    }
}

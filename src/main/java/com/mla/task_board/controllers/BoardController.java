package com.mla.task_board.controllers;

import com.mla.task_board.controllers.base.ControllerBase;
import com.mla.task_board.domain.dtos.base.ResultViewModel;
import com.mla.task_board.domain.dtos.outputmodels.*;
import com.mla.task_board.domain.interfaces.Service.IBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/board", produces= "application/json")
public class BoardController extends ControllerBase {

    private final IBoardService _boardService;

    public BoardController(IBoardService boardService){
        _boardService = boardService;
    }

    @GetMapping()
    public ResponseEntity<ResultViewModel<List<GetBoardDetailOutputModel>>> getAll(){
        return response(_boardService.getAll());
    }
}

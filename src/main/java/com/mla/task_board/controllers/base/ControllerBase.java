package com.mla.task_board.controllers.base;

import com.mla.task_board.domain.dtos.base.ResultViewModel;
import org.springframework.http.ResponseEntity;

public class ControllerBase {
    protected <T> ResponseEntity<ResultViewModel<T>> response(ResultViewModel<T> result)  {
        if (result.success) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}

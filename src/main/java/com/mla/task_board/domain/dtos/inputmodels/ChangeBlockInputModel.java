package com.mla.task_board.domain.dtos.inputmodels;

import jakarta.validation.constraints.*;

public class ChangeBlockInputModel {
    @NotBlank(message = "RequiredField")
    @Size( min = 1, max = 100, message = "MaximumLength")
    public String reason;
}

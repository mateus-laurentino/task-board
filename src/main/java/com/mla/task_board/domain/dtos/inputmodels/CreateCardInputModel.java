package com.mla.task_board.domain.dtos.inputmodels;

import jakarta.validation.constraints.*;

public class CreateCardInputModel {
    @NotBlank(message = "RequiredField")
    @Size( min = 1, max = 50, message = "MaximumLength")
    public String title;

    @NotBlank(message = "RequiredField")
    @Size( min = 1, max = 1000, message = "MaximumLength")
    public String description;

    @NotNull(message = "RequiredField")
    @Positive(message = "GreaterZero")
    public Integer boardId;
}

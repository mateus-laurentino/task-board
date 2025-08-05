package com.mla.task_board.domain.interfaces.Repository;

import com.mla.task_board.domain.dtos.repositorymodels.GetBoardDetailsRepositoryModel;

import java.util.List;

public interface IBoardRepository {
    List<GetBoardDetailsRepositoryModel> getDetails();
}

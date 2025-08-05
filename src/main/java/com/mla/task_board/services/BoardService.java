package com.mla.task_board.services;

import com.mla.task_board.domain.dtos.base.*;
import com.mla.task_board.domain.dtos.outputmodels.*;
import com.mla.task_board.domain.dtos.repositorymodels.GetBoardDetailsRepositoryModel;
import com.mla.task_board.domain.interfaces.Repository.IBoardRepository;
import com.mla.task_board.domain.interfaces.Service.IBoardService;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService implements IBoardService {

    private final IBoardRepository _boardRepository;

    public BoardService(IBoardRepository boardRepository){
        _boardRepository = boardRepository;
    }

    @Override
    public ResultViewModel<List<GetBoardDetailOutputModel>> getAll() {
        var agrupar = _boardRepository.getDetails();
        var retorno = agrupar
                .stream()
                .collect(Collectors.groupingBy(
                        s -> new GroupNamePositionKey(
                                s.getId(),
                                s.getName(),
                                s.getPosition(),
                                s.getBackgroundColor(),
                                s.getFontColor(),
                                s.getBackgroundColorCard(),
                                s.getFontColorCard()
                    )
                ))
                .entrySet().stream().map(x -> {
                    var key = x.getKey();
                    var groupCards = x.getValue().stream().filter(GetBoardDetailsRepositoryModel::notEmptyCards).map(CardDetailOutputModel::new).toList();
                    return new GetBoardDetailOutputModel(key, groupCards);
                })
                .sorted(Comparator.comparingInt(s -> s.position))
                .toList();

        return new ResultViewModel<>(retorno);
    }
}

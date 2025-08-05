package com.mla.task_board.domain.dtos.base;

public record GroupNamePositionKey(
        Integer id,
        String name,
        Integer position,
        String backgroundColor,
        String fontColor,
        String backgroundColorCard,
        String fontColorCard
) {
}

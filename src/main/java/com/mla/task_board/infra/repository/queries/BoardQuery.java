package com.mla.task_board.infra.repository.queries;

public class BoardQuery {
    public static final String getBoardDetails = """
        SELECT
            b.id,
            b.name,
            b.position,
            b.backgroundcolor as backgroundColor,
            b.fontcolor as fontColor,
            b.backgroundcolorcard as backgroundColorCard,
            b.fontcolorcard as fontColorCard,
            c.id AS cardId,
            c.title AS cardTitle,
            c.description AS cardDescription,
            b.state AS cardState,
            COALESCE(
                (SELECT bk.active FROM block bk WHERE bk.cardid = c.id ORDER BY bk.id DESC LIMIT 1),
                0
            ) AS cardBlock
        FROM board b
        LEFT JOIN card c ON c.boardid = b.id
        ORDER BY b.position, c.id ASC
    """;
}

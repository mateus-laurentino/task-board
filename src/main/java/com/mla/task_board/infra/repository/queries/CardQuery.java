package com.mla.task_board.infra.repository.queries;

public class CardQuery {
    public static final String getDetailById = """
        SELECT
            c.id AS cardId,
            c.title AS cardTitle,
            c.description AS cardDescription,
            b.state AS cardState,
            COALESCE(
                (SELECT bk.active FROM block bk WHERE bk.cardid = c.id ORDER BY bk.id DESC LIMIT 1),
                0
            ) AS cardBlock,
            b.backgroundcolorcard as backgroundColorCard,
            b.fontcolorcard as fontColorCard
        FROM card c
        INNER JOIN board b ON c.boardid = b.id
        WHERE c.id = ?
    """;

    public static final String insert = """
        INSERT INTO card (title, description, boardid) VALUES (?, ?, ?);
    """;

    public static final String update = """
        UPDATE card
        SET title = ?,
            description = ?
        WHERE id = ?
    """;

    public static final String updateBoard = """
        UPDATE card
        SET boardid = ?
        WHERE id = ?
    """;

    public static final String unlockCard = """
        UPDATE block
        SET active = false,
            unlockreason = ?,
            unlockat = CURRENT_TIMESTAMP
        WHERE cardid = ?
            AND active = true
    """;

    public static final String blockCard = """
        INSERT INTO block (cardid, blockingreason, active)
        VALUES (?, ?, true)
    """;

    public static final String disable = """
        UPDATE card
        SET active = false
        WHERE id = ?
    """;
}

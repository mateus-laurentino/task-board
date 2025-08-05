package com.mla.task_board.infra.repository.queries;

public class HistoricalQuery {
    public static final String getByCardId = """
        SELECT
            h.description as description
        FROM historical h
        WHERE h.cardid = ?
        ORDER BY h.id DESC;
    """;
}

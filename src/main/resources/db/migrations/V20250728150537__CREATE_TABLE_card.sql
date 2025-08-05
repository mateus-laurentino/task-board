CREATE TABLE card(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    boardid INTEGER NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT 1,
    CONSTRAINT fk_card_boardid_board_id
        FOREIGN KEY (boardid) 
        REFERENCES board(id) 
        ON DELETE CASCADE
);

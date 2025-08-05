CREATE TABLE block (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cardid INTEGER NOT NULL,
    blockingreason TEXT NOT NULL,
    unlockreason TEXT,
    blockedat TIMESTAMP NOT NULL DEFAULT (datetime('now', 'localtime')),
    unlockat TIMESTAMP,
    active BOOLEAN NOT NULL,

    CONSTRAINT fk_block_cardid_card_id
        FOREIGN KEY (cardid)
        REFERENCES card(id)
        ON DELETE CASCADE
);
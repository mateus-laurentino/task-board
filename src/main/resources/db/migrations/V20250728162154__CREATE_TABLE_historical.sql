CREATE TABLE historical (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cardid INTEGER NOT NULL,
    description TEXT NOT NULL,
    state TEXT NOT NULL,
    block BOOLEAN NOT NULL,
    CONSTRAINT fk_historical_cardid_card_id
        FOREIGN KEY (cardid) 
        REFERENCES card(id) 
        ON DELETE CASCADE
);
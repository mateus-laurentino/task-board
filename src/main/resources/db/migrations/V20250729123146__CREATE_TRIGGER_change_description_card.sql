CREATE TRIGGER trigger_historical_change_description_card
AFTER UPDATE ON card
FOR EACH ROW
WHEN OLD.description != NEW.description
BEGIN
    INSERT INTO historical(
        cardid,
        description,
        state,
        block
    )
    VALUES(
        NEW.id,
        'Descrição alterada em ' || strftime('%d/%m/%Y %H:%M:%S', 'now', 'localtime'),
        (SELECT b.state FROM board b WHERE b.id = NEW.boardid),
        COALESCE(
            (SELECT bl.active FROM block bl WHERE bl.cardid = NEW.id ORDER BY bl.id DESC LIMIT 1),
            0
        )
    );
END;
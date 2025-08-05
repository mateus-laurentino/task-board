CREATE TRIGGER trigger_historical_change_title_card
AFTER UPDATE ON card
FOR EACH ROW
WHEN OLD.title != NEW.title
BEGIN
    INSERT INTO historical(
        cardid,
        description,
        state,
        block
    )
    VALUES(
        NEW.id,
        'Titulo alterado em ' || strftime('%d/%m/%Y %H:%M:%S', 'now', 'localtime'),
        (SELECT b.state FROM board b WHERE b.id = NEW.boardid),
        COALESCE(
            (SELECT bl.active FROM block bl WHERE bl.cardid = NEW.id ORDER BY bl.id DESC LIMIT 1),
            0
        )
    );
END;
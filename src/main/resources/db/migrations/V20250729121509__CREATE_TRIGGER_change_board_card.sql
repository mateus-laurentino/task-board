CREATE TRIGGER trigger_historical_change_board_card
AFTER UPDATE ON card
FOR EACH ROW
WHEN OLD.boardid != NEW.boardid
BEGIN
    INSERT INTO historical(
        cardid,
        description,
        state,
        block
    )
    SELECT
        NEW.id,
        'Movido de ' || ob.name || ' para ' || nb.name || ' em ' || strftime('%d/%m/%Y %H:%M:%S', 'now', 'localtime'),
        nb.state,
        COALESCE(
            (SELECT bl.active FROM block bl WHERE bl.cardid = NEW.id ORDER BY bl.id DESC LIMIT 1),
            0
        )
    FROM
        board ob,
        board nb
    WHERE
        ob.id = OLD.boardid AND
        nb.id = NEW.boardid;
END;
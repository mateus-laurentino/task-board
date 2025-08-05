CREATE TRIGGER trigger_historical_create_card
AFTER INSERT ON card
FOR EACH ROW
BEGIN
    INSERT INTO historical(
        cardid,
        description,
        state,
        block
    )
    VALUES(
        NEW.id,
        'Criado em ' || strftime('%d/%m/%Y %H:%M:%S', 'now', 'localtime'),
        (SELECT b.state FROM board b WHERE b.id = NEW.boardid),
        0
    );
END;
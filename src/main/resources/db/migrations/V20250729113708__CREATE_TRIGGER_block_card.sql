CREATE TRIGGER trigger_historical_block_card
AFTER INSERT ON block
FOR EACH ROW
BEGIN
    INSERT INTO historical(
        cardid,
        description,
        state,
        block
    )
    SELECT
        NEW.cardid,
        'Bloqueado em ' || strftime('%d/%m/%Y %H:%M:%S', NEW.blockedat) || ' pelo motivo:<br>' || NEW.blockingreason,
        nb.state,
        1
    FROM card nc
    INNER JOIN board nb ON nb.id = nc.boardid
    WHERE nc.id = NEW.cardid;
END;
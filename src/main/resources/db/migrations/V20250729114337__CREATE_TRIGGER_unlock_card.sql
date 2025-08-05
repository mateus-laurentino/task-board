CREATE TRIGGER trigger_historical_unlock_card
AFTER UPDATE ON block
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
        'Desbloqueado em ' || strftime('%d/%m/%Y %H:%M:%S', NEW.blockedat) || 'pelo motivo:<br>' || NEW.unlockreason,
        nb.state,
        0
    FROM card nc
    INNER JOIN board nb ON nb.id = nc.boardid
    WHERE nc.id = NEW.cardid;
END;
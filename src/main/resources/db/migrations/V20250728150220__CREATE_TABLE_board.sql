CREATE TABLE board (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    position INTEGER NOT NULL,
    state TEXT NOT NULL,
    createdat TIMESTAMP NOT NULL DEFAULT (datetime('now', 'localtime')),
    backgroundcolor TEXT NOT NULL DEFAULT '#555',
    fontcolor TEXT NOT NULL DEFAULT '#000',
    backgroundcolorcard TEXT NOT NULL DEFAULT '#555',
    fontcolorcard TEXT NOT NULL DEFAULT '#000',
    CONSTRAINT uk_board_name UNIQUE (name)
);

--Create boards
INSERT INTO board (name, position, state, backgroundcolor, backgroundcolorcard)
VALUES('Backlog', 1, 'NEW', '#ffff01', '#8b8b1c'),
	  ('To Do', 2, 'TO DO', '#ff9501', '#916528'),
	  ('In Progress', 3, 'IN PROGRESS', '#ff72d7', '#916384'),
	  ('Code Review', 4, 'REVIEW', '#9700ff', '#6a348f'),
	  ('Teste', 5, 'TEST', '#01ffff', '#488f8f'),
	  ('Done', 6, 'DONE', '#61fe01', '#56853a');
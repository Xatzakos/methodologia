CREATE TABLE IF NOT EXISTS Note(id IDENTITY PRIMARY KEY, done BOOLEAN, text VARCHAR(255));
DELETE FROM Note;
INSERT INTO Note VALUES(1, FALSE , 'note 1');
INSERT INTO Note VALUES(2, FALSE , 'note 2');
INSERT INTO Note VALUES(3, false, 'note 3');

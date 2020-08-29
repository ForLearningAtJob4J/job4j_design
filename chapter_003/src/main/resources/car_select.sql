SELECT
	c.name "Марка",
	b.name "Кузов",
	round(cast(e.volume as numeric (6, 2)) / 1000, 1) "Объем двигателя",
	t.name "Коробка передач"
FROM
    car c
LEFT JOIN body b ON (b.id = c.id_body)
LEFT JOIN engine e ON (e.id = c.id_engine)
LEFT JOIN transmission t ON (t.id = c.id_transmission);

-- =================================== --
-- =================================== --
-- =================================== --

SELECT b.* FROM body b LEFT JOIN car c ON c.id_body = b.id WHERE c.id IS NULL;

SELECT e.* FROM engine e LEFT JOIN car c ON c.id_engine = e.id WHERE c.id IS NULL;

SELECT t.* FROM transmission t LEFT JOIN car c ON c.id_transmission = t.id WHERE c.id IS NULL;
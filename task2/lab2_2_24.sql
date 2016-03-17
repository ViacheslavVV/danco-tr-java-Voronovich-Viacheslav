SELECT Model
FROM Laptop
WHERE Laptop.Price=
	(SELECT max(Price)
	 FROM Laptop)
UNION
SELECT Model
FROM PC
WHERE PC.Price=
	(SELECT max(Price)
	 FROM PC)
UNION
SELECT Model
FROM Printer
WHERE Printer.Price=
	(SELECT max(Price)
	 FROM Printer)


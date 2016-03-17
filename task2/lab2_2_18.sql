SELECT Maker,min(Price) AS Price
FROM Product JOIN Printer
on Product.Model=Printer.Model
WHERE Color="y"
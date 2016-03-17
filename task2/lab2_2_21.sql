SELECT Maker,max(Price) as MaxPrice
FROM Product JOIN PC
on Product.Model=PC.Model
GROUP BY Maker

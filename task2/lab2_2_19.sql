SELECT Maker,avg(Screen)
FROM Product JOIN Laptop
on Product.Model=Laptop.Model
GROUP BY Maker
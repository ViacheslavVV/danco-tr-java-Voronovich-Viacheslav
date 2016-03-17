SELECT avg(Speed)
FROM Product JOIN PC
on Product.Model=PC.Model  
WHERE Maker="Maker1"
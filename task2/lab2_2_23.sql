SELECT *
FROM (SELECT Maker
FROM Product JOIN Laptop
ON Product.Model=Laptop.Model
WHERE Laptop.Speed>=750) AS LTMakers
WHERE Maker IN
(SELECT Maker
FROM Product JOIN PC
ON Product.Model=PC.Model
WHERE PC.Speed>=750)
SELECT Product.Type,Laptop.Model,Laptop.Speed
FROM Product JOIN Laptop
on Product.Model=Laptop.Model
WHERE Laptop.Speed<(
SELECT max(Speed)
FROM PC
)
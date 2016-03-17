SELECT DISTINCT Maker
FROM Product
where Type="PC" AND
Maker NOT IN 
(SELECT  Maker
FROM Product
WHERE Type = "LT") 
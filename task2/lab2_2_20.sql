SELECT Maker,count(distinct(Model)) as Count
FROM Product
WHERE Type="PC"
GROUP BY Maker
HAVING count(distinct(Model))>=3 
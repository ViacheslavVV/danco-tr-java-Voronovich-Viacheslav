SELECT Speed,avg(Price) AS Price
FROM PC
WHERE Speed>600
GROUP BY Speed
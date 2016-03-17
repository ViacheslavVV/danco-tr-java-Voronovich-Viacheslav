SELECT max(Model) AS Model_1,min(Model) AS Model_2,Speed,Ram
FROM PC
GROUP BY Speed,Ram
HAVING count(Speed)>=2 AND count(Ram)>=2
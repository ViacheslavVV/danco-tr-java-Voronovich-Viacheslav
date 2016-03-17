SELECT DISTINCT Maker
FROM Product
WHERE Type = 'PR' AND Maker IN 
(SELECT Maker 
 FROM Product JOIN PC 
 ON Product.Model = PC.Model 
 WHERE Type = 'PR' AND Ram = 
	(SELECT min(Ram)
     FROM PC) AND 
		Speed = 
		(SELECT max(Speed) 
		 FROM PC 
		 WHERE Ram = 
			(SELECT min(Ram) 
			 FROM PC)
		)
)
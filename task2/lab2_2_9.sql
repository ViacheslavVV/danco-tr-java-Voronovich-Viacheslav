SELECT product.maker
FROM Product JOIN PC
on Product.model = PC.model
where Speed >= 450
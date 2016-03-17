select product.model, laptop.price
from product join laptop, pc, printer as prod
where product.model = prod.model 
and product.maker = 'Maker2'
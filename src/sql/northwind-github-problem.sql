Easy question level northwind database
question.1
select product_name, list_price
from products;

question.2
select concat(id,' ',company) as name_of_customers
from customers
where country_region = 'USA';

question.3
select product_id, quantity, unit_price
from order_details
where order_id = 10248;

question.4
select count(*)
from employees;

question.5
select concat(category_id, ' ', category_name)
from categories;

question.6
select product_name, units_in_stock
from products
where units_in_stock<10;

Question medium level
question.1
select id, order_date, ship_country_region
from orders
where customer_id = 'ALFKI';

question.2
select product_id, Round(SUM(quantity*unit_price),2) as sales
from order_details
GROUP BY product_id;

question.3
select concat(e.first_name, ' ', e.last_name) as employee_name, count(o.id) as num_of_orders
from orders o
join employees e on e.id = o.employee_id
group by e.id, e.country_region;

question.4
select suppliers.id, company, product_name
from suppliers
join products on products.supplier_ids = suppliers.id;

question.5
select *
from products
order by list_price desc
limit 5;

question.6
select *
from orders
where shipped_date > order_date;

Question difficult level
question.1
select concat(customers.id, ' ', company) as customer
from customers
left join orders o on o.customer_id = customers.id
where o.order_date is null;

question.2
select sum(od.quantity*od.unit_price) as sales
from order_details od
join orders o on o.id = od.order_id
group by ship_country_region;

question.3
select e.id,
concat(e.first_name,' ', e.last_name) as employee_name,
count(o.id) as num_of_orders,
round(sum(od.quantity*od.unit_price),2) as sales
from employees e
join orders o on o.employee_id = e.id
join order_details od on o.id = od.order_id
group by o.employee_id;

question.4
select concat(id, ' ', product_name) as product, list_price
from products p1
where list_price > (select avg(list_price) 
                        from products p2 group by p1.category);

question.5
select round(sum(od.quantity * od.unit_price),2) as sales
from order_details od
join orders o on o.id = od.order_id
group by month(o.order_date), year(o.order_date);

question.6
select concat(c.id,' ', c.company) as company,
count(o.id) as num_of_orders
from orders o
join customers c on c.id = o.customer_id
group by o.customer_id
having num_of_orders > 1;
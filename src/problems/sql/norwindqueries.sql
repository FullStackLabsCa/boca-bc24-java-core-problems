==================================EASY=================================
select p.product_name, o.unit_price from products p JOIN order_details o ON p.id=o.product_id;
select id, company from customers where country_region='USA';
select product_id, quantity, unit_price from order_details where order_id=10248;
select count(*) from employees;
select id, category_name from categories;
select product_name from products where quantity_per_unit < 10;


===============================MEDIUM===============================
select o.id, o.order_date, o.ship_country_region from orders o JOIN customers c ON o.customer_id = c.id where c.first_name="ALFKI"

select p.product_code, sum(o.unit_price) from order_details o JOIN products p ON o.product_id=p.id group by o.product_id;

SELECT
    CONCAT(e.first_name, ' ', e.last_name) AS full_name,
    COUNT(o.id) AS total_orders,
    e.country_region
FROM
    employees e
LEFT JOIN
    orders o ON e.id = o.employee_id
GROUP BY
    e.id, e.country_region;


select s.id supplier_id,
s.company,
group_concat(p.product_name SEPARATOR ', ') AS product_names
from suppliers s
JOIN products p ON s.id IN( p.supplier_ids)
group by s.id, s.company


select p.*, o.unit_price from products p JOIN order_details o ON o.product_id=p.id
order by unit_price desc limit 5

select o.* from orders o where o.shipped_date > o.order_date

=============================DIFFICULT==============================
select c.id, c.company from customers c LEFT JOIN orders o ON c.id = o.customer_id
where o.id IS NULL;

select
    CASE
        when c.country_region IS NOT NULL THEN c.country_region
        ELSE o.ship_country_region
    END AS country,
sum(od.quantity * od.unit_price) total_sales_amount
from order_details od JOIN orders o
ON o.id = od.order_id
JOIN customers c ON c.id = o.customer_id
group by c.country_region, o.ship_country_region

select
e.id as employee_id,
concat(e.first_name, ' ', e.last_name) as full_name,
count(DISTINCT o.id) as total_orders,
sum(od.quantity * od.unit_price) total_sales_amount
from employees e LEFT JOIN orders o ON e.id = o.employee_id
LEFT JOIN order_details od ON o.id = od.order_id
group by e.id,  e.first_name,
    e.last_name;

select p.id, p.product_name from products p
JOIN order_details od ON p.id = od.product_id
where od.unit_price > (select avg(list_price) from products where category = p.category)

select MONTH(order_date), YEAR(order_date),
sum(od.quantity * od.unit_price) total_sales_amount
from orders o group by MONTH(order_date), YEAR(order_date)

select c.id, c.company, count(o.id) from customers c
JOIN orders o on c.id=o.customer_id group by c.id, c.company having count(o.id) > 1




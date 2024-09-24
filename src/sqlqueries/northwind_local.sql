    use northwind;
1. SELECT
    product_name,
    quantity_per_unit
FROM
    products;
2.SELECT
    id,
    company
FROM
    customers
WHERE
    country_region = 'USA';
3. SELECT
    product_id,
    quantity,
    unit_price
FROM
    order_details;
4.SELECT
    COUNT(*) AS total_employee
FROM
    employees
5. SELECT
    category_id,
    category_name
FROM
    categories;
6. SELECT
    product_name
FROM
    products
WHERE
    unit_stock < 10;
7.SELECT
    orders.id,
    orders.order_date,
    orders.ship_country_region
FROM
         orders
    JOIN customers ON orders.customer_id = customers.id
WHERE
    customers.first_name = 'ALFKI';
8.SELECT
    concat(employees.first_name, ' ', employees.last_name) AS full_name,
    employees.country_region,
    COUNT(orders.id)                                       AS total_order
FROM
    employees
    LEFT JOIN orders ON employees.id = orders.employee_idgroup
BY EMPLOYEES . ID , EMPLOYEES . COUNTRY_REGIONORDER BY EMPLOYEES . COUNTRY_REGION , FULL_NAME;
9. SELECT
    suppliers.id,
    concat(suppliers.first_name, suppliers.last_name) AS supplier_name,
    products.product_name
FROM
         suppliers
    JOIN products ON suppliers.id = products.supplier_ids;
10.SELECT
    list_price
FROM
    products
ORDER BY
    list_price DESC
LIMIT 5 ;
11.SELECT
    customers.id,
    customers.company
FROM
    customers
    LEFT JOIN orders ON customers.id = orders.customer_id
WHERE
    orders.id IS NULL;
12.
SELECT
    customers.country_region,
    SUM(order_details.quantity * order_details.unit_price) AS sales_amount
FROM
         customers
    JOIN orders ON customers.id = orders.customer_id
    JOIN order_details ON order_details.order_id = orders.id
GROUP BY
    customers.country_region
ORDER BY
    sales_amount;
13. SELECT
    employees.id,
    concat(employees.first_name, ' ', employees.last_name) AS full_name,
    COUNT(DISTINCT orders.id)                              AS totat_orders,
    SUM(order_details.quantity * order_details.unit_price) AS sales_amount
FROM
         employees
    JOIN orders ON employees.id = orders.employee_id
    JOIN order_details ON order_details.order_id = orders.id
GROUP BY
    employees.id,
    full_name
ORDER BY
    employees.id;
14.SELECT
    id,
    product_name
FROM
    products
WHERE
    list_price > (
        SELECT
            AVG(list_price)
        FROM
            products
    );

15. SELECT
    year(orders.order_date)                                AS order_year,
    month(orders.order_date)                               AS order_month,
    SUM(order_details.quantity * order_details.unit_price) AS totalsales
FROM
         orders
    JOIN order_details ON orders.id = order_details.order_id
GROUP BY
    order_year,
    order_month
ORDER BY
    order_year,
    order_month;
16.
SELECT
    customers.id,
    customers.company
FROM
         customers
    JOIN orders ON orders.customer_id = customers.id
GROUP BY
    customers.id
HAVING
    COUNT(orders.id) > 1;

 

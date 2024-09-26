package edu.sm.frame;

public class Sql{
    // Customer 관련 쿼리
    public static String insertCustomer = "INSERT INTO customer (username, pw, name, p_number, signup_date) VALUES (?, ?, ?, ?, ?)";
    public static String updateCustomer = "UPDATE customer SET pw = ?, name = ?, p_number = ? WHERE cust_id = ?";
    public static String deleteCustomer = "DELETE FROM customer WHERE cust_id = ?";
    public static String selectOneCustomer = "SELECT * FROM customer WHERE cust_id = ?";
    public static String selectCustomer = "SELECT * FROM customer";

    // Product 관련 쿼리
    public static String insertProduct = "INSERT INTO product (category_id, name, price, reg_date, description, img1, img2, img3, img4, img5, count, is_public) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static String updateProduct = "UPDATE product SET category_id = ?, name = ?, price = ?, description = ?, img1 = ?, img2 = ?, img3 = ?, img4 = ?, img5 = ?, count = ?, is_public = ? WHERE product_id = ?";
    public static String deleteProduct = "DELETE FROM product WHERE product_id = ?";
    public static String selectOneProduct = "SELECT * FROM product WHERE product_id = ?";
    public static String selectProduct = "SELECT * FROM product";

    // Order 관련 쿼리
    public static String insertOrder = "INSERT INTO `order` (cust_id, product_count, price, order_date, name, phone, address1, address2, zip_code, request, card, used_mileage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static String updateOrder = "UPDATE `order` SET product_count = ?, price = ?, name = ?, phone = ?, address1 = ?, address2 = ?, zip_code = ?, request = ?, card = ?, used_mileage = ? WHERE order_id = ?";
    public static String deleteOrder = "DELETE FROM `order` WHERE order_id = ?";
    public static String selectOneOrder = "SELECT * FROM `order` WHERE order_id = ?";
    public static String selectOrder = "SELECT * FROM `order`";

    // Cart 관련 쿼리
    public static String insertCart = "INSERT INTO cart (cust_id, product_id, count, reg_date) VALUES (?, ?, ?, ?)";
    public static String updateCart = "UPDATE cart SET count = ? WHERE cart_id = ?";
    public static String deleteCart = "DELETE FROM cart WHERE cart_id = ?";
    public static String selectOneCart = "SELECT * FROM cart WHERE cart_id = ?";
    public static String selectCart = "SELECT * FROM cart";

}

package edu.sm.frame;

public class Sql {
    // 회원 관리 (MM)
    public static String insertCustomer = "INSERT INTO customer (username, pw, name, p_number, signup_date, role) VALUES (?, ?, ?, ?, ?, ?)";

    public static String searchMembers =
            "SELECT * FROM customer WHERE username LIKE ? OR name LIKE ? OR p_number LIKE ?";

    public static String listMembers =
            "SELECT * FROM customer LIMIT ? OFFSET ?";

    public static String viewMemberDetails =
            "SELECT * FROM customer WHERE cust_id = ?";

    public static String editMemberInfo =
            "UPDATE customer SET username = ?, pw = ?, name = ?, p_number = ? WHERE cust_id = ?";

    public static String deleteMember =
            "DELETE FROM customer WHERE cust_id = ?";

    // 통계 및 분석 (SA)
    public static String viewSalesStatistics =
            "SELECT DATE(order_date) as date, SUM(price) as total_sales FROM `order` GROUP BY DATE(order_date)";

    public static String listSalesStatistics =
            "SELECT DATE(order_date) as date, SUM(price) as total_sales FROM `order` GROUP BY DATE(order_date) ORDER BY ? ?";

    public static String viewSalesDetails =
            "SELECT * FROM `order` WHERE DATE(order_date) = ?";

    public static String analyzeProducts =
            "SELECT p.product_id, p.name, COUNT(od.product_id) as sales_count, SUM(od.price) as total_sales " +
                    "FROM product p LEFT JOIN order_detail od ON p.product_id = od.product_id " +
                    "GROUP BY p.product_id, p.name";

    public static String viewPopularProducts =
            "SELECT p.product_id, p.name, COUNT(od.product_id) as sales_count " +
                    "FROM product p LEFT JOIN order_detail od ON p.product_id = od.product_id " +
                    "GROUP BY p.product_id, p.name ORDER BY sales_count DESC LIMIT 10";

    public static String analyzeCustomers =
            "SELECT c.cust_id, c.name, COUNT(o.order_id) as order_count, SUM(o.price) as total_spent " +
                    "FROM customer c LEFT JOIN `order` o ON c.cust_id = o.cust_id " +
                    "GROUP BY c.cust_id, c.name";

    public static String viewCustomerPurchasePatterns =
            "SELECT c.cust_id, c.name, p.category_id, COUNT(od.product_id) as purchase_count " +
                    "FROM customer c " +
                    "JOIN `order` o ON c.cust_id = o.cust_id " +
                    "JOIN order_detail od ON o.order_id = od.order_id " +
                    "JOIN product p ON od.product_id = p.product_id " +
                    "GROUP BY c.cust_id, c.name, p.category_id";

    // 시스템 설정 (SS)
    public static String manageGeneralSettings =
            "SELECT * FROM system_settings";

    public static String managePaymentSettings =
            "SELECT * FROM payment_settings";

    public static String manageShippingSettings =
            "SELECT * FROM shipping_settings";

    public static String setPaymentFees =
            "UPDATE payment_method SET fee = ? WHERE method_id = ?";

    public static String viewPaymentMethods =
            "SELECT * FROM payment_method";

    public static String addPaymentMethod =
            "INSERT INTO payment_method (name, fee) VALUES (?, ?)";

    public static String editPaymentMethod =
            "UPDATE payment_method SET name = ?, fee = ? WHERE method_id = ?";

    public static String deletePaymentMethod =
            "DELETE FROM payment_method WHERE method_id = ?";

    public static String viewShippingCompanies =
            "SELECT * FROM shipping_company";

    public static String addShippingCompany =
            "INSERT INTO shipping_company (name, tracking_url) VALUES (?, ?)";

    public static String editShippingCompany =
            "UPDATE shipping_company SET name = ?, tracking_url = ? WHERE company_id = ?";

    public static String deleteShippingCompany =
            "DELETE FROM shipping_company WHERE company_id = ?";

    // 상품 관리 (PM)
    public static String searchProducts =
            "SELECT * FROM product WHERE product_id = ?";

    public static String listProducts =
            "SELECT * FROM product LIMIT ? OFFSET ?";

    public static String registerProduct =
            "INSERT INTO product (category_id, name, price, description, img1, img2, img3, img4, img5, count, is_public) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String manageCategories =
            "SELECT * FROM category";

    public static String manageProductInquiries =
            "SELECT * FROM product_inquiry WHERE product_id = ?";

    public static String viewProductDetails =
            "SELECT * FROM product WHERE product_id = ?";

    public static String editProduct =
            "UPDATE product SET category_id = ?, name = ?, price = ?, description = ?, " +
                    "img1 = ?, img2 = ?, img3 = ?, img4 = ?, img5 = ?, count = ?, is_public = ? " +
                    "WHERE product_id = ?";

    public static String changeProductStatus =
            "UPDATE product SET is_public = ? WHERE product_id = ?";

    public static String addCategory =
            "INSERT INTO category (name, category_id2) VALUES (?, ?)";

    public static String editCategory =
            "UPDATE category SET name = ?, category_id2 = ? WHERE category_id = ?";

    public static String deleteCategory =
            "DELETE FROM category WHERE category_id = ?";

    public static String listProductInquiries =
            "SELECT * FROM product_inquiry WHERE product_id = ?";

    public static String answerInquiry =
            "UPDATE product_inquiry SET answer = ?, answer_date = NOW() WHERE inquiry_id = ?";

    // 주문 관리 (OM)
    public static String insertOrder =
            "INSERT INTO `order` (cust_id, product_count, price, order_date, name, phone, " +
                    "address1, address2, zip_code, request, card, used_mileage) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String searchOrders =
            "SELECT * FROM `order` WHERE order_id = ?";

    public static String listOrders =
            "SELECT * FROM `order`";

    public static String viewOrderDetails =
            "SELECT o.*, od.* FROM `order` o JOIN order_detail od ON o.order_id = od.order_id WHERE o.order_id = ?";

    public static String listDelivery =
            "SELECT * FROM delivery";

    public static String managePayments =
            "SELECT * FROM payment WHERE order_id = ?";

    public static String editOrder =
            "UPDATE `order` SET product_count = ?, price = ?, name = ?, phone = ?, " +
                    "address1 = ?, address2 = ?, zip_code = ?, request = ?, card = ?, used_mileage = ? " +
                    "WHERE order_id = ?";


    public static String deleteOrder =
            "DELETE FROM `order` WHERE order_id = ?";

    public static String viewShippingStatus =
            "SELECT * FROM delivery WHERE order_id = ?";

    public static String editShippingInfo =
            "UPDATE delivery SET status = ?, tracking_number = ?, estimated_delivery = ?, actual_delivery = ? " +
                    "WHERE delivery_id = ?";
    public static String insertDelivery =
            "INSERT INTO delivery (order_id, status, tracking_number, estimated_delivery, actual_delivery) VALUES (?, ?, ?, ?, ?)";
    public static String viewPaymentHistory =
            "SELECT * FROM payment WHERE order_id = ?";

    public static String processRefund =
            "INSERT INTO refund (order_id, amount, reason) VALUES (?, ?, ?)";

    // 상품 (PD)
    public static String showPopularProducts =
            "SELECT p.*, COUNT(od.product_id) as sales_count " +
                    "FROM product p LEFT JOIN order_detail od ON p.product_id = od.product_id " +
                    "GROUP BY p.product_id ORDER BY sales_count DESC LIMIT 10";

    public static String setCategorySystem =
            "SELECT * FROM category";

    public static String displayProductList =
            "SELECT * FROM product WHERE category_id = ? LIMIT ? OFFSET ?";


    public static String displayReviewsAndRatings =
            "SELECT * FROM review WHERE product_id = ?";

    public static String showRelatedProducts =
            "SELECT * FROM product WHERE category_id = ? AND product_id != ? LIMIT 5";

    public static String addReview =
            "INSERT INTO review (product_id, cust_id, rating, content) VALUES (?, ?, ?, ?)";

    public static String editReview =
            "UPDATE review SET rating = ?, content = ? WHERE review_id = ? AND cust_id = ?";

    public static String deleteReview =
            "DELETE FROM review WHERE review_id = ? AND cust_id = ?";

    public static String addProductInquiry =
            "INSERT INTO product_inquiry (product_id, cust_id, content) VALUES (?, ?, ?)";

    public static String deleteProductInquiry =
            "DELETE FROM product_inquiry WHERE inquiry_id = ? AND cust_id = ?";

    // 장바구니 (CT)

    // 장바구니 (Cart) 관련 쿼리
    public static String insertCart =
            "INSERT INTO cart (cust_id, product_id, count, reg_date) VALUES (?, ?, ?, ?)";

    public static String updateCart =
            "UPDATE cart SET count = ? WHERE cart_id = ?";

    public static String deleteCart =
            "DELETE FROM cart WHERE cart_id = ?";

    public static String selectCart =
            "SELECT * FROM cart WHERE cart_id = ?";

    public static String selectAllCarts =
            "SELECT * FROM cart";

    public static String selectCartsByCustomer =
            "SELECT * FROM cart WHERE cust_id = ?";
    public static String displayCartList =
            "SELECT c.*, p.name, p.price FROM cart c JOIN product p ON c.product_id = p.product_id WHERE c.cust_id = ?";

    public static String processPayment =
            "INSERT INTO payment (order_id, amount, method) VALUES (?, ?, ?)";

    public static String changeProductQuantity =
            "UPDATE cart SET count = ? WHERE cart_id = ? AND cust_id = ?";

    public static String deleteCartItem =
            "DELETE FROM cart WHERE cart_id = ? AND cust_id = ?";

    public static String enterShippingInfo =
            "UPDATE `order` SET name = ?, phone = ?, address1 = ?, address2 = ?, zip_code = ?, request = ? " +
                    "WHERE order_id = ?";

    public static String selectPaymentMethod =
            "UPDATE `order` SET card = ? WHERE order_id = ?";

    public static String confirmOrder =
            "SELECT * FROM `order` WHERE order_id = ?";


    // 마이페이지 (MP)
    public static String viewOrderHistory =
            "SELECT * FROM `order` WHERE cust_id = ? ORDER BY order_date DESC";

    public static String managePersonalInfo =
            "SELECT * FROM customer WHERE cust_id = ?";

    public static String listAddresses =
            "SELECT * FROM address";
    public static String listAddressesDetails =
            "SELECT * FROM address where address_key =  ?";


    public static String viewMileageHistory =
            "SELECT * FROM mileage WHERE cust_id = ? UNION ALL SELECT * FROM used_mileage WHERE cust_id = ? ORDER BY date DESC";

    public static String manageWishList =
            "SELECT w.*, p.name, p.price FROM wish w JOIN product p ON w.product_id = p.product_id WHERE w.cust_id = ?";

    public static String searchOrderHistory =
            "SELECT * FROM `order` WHERE cust_id = ? AND order_date BETWEEN ? AND ? ORDER BY order_date DESC";

    public static String showOrderDetails =
            "SELECT o.*, od.* FROM `order` o JOIN order_detail od ON o.order_id = od.order_id WHERE o.order_id = ? AND o.cust_id = ?";

    public static String checkShippingStatus =
            "SELECT * FROM delivery WHERE order_id = ?";

    public static String cancelOrder =
            "UPDATE `order` SET status = 'CANCELLED' WHERE order_id = ? AND cust_id = ? AND status = 'PENDING'";

    public static String requestReturn =
            "INSERT INTO return_request (order_id, reason) VALUES (?, ?)";

    public static String setDefaultAddress =
            "UPDATE address SET def = CASE WHEN address_key = ? THEN 'Y' ELSE 'N' END WHERE cust_id = ?";

    public static String addAddress =
            "INSERT INTO address (cust_id, name, phone, address, address_detail, zip_code, request, def) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static String editAddress =
            "UPDATE address SET name = ?, phone = ?, address = ?, address_detail = ?, zip_code = ?, request = ? " +
                    "WHERE address_key = ? AND cust_id = ?";

    public static String deleteAddress =
            "DELETE FROM address WHERE address_key = ?";

    public static String viewEarnedMileage =
            "SELECT * FROM mileage WHERE cust_id = ? ORDER BY date DESC";

    public static String viewUsedMileage =
            "SELECT * FROM used_mileage WHERE cust_id = ? ORDER BY used_date DESC";

    public static String deleteWishItem =
            "DELETE FROM wish WHERE wish_id = ? AND cust_id = ?";

    public static String addToCartFromWishList =
            "INSERT INTO cart (cust_id, product_id, count) " +
                    "SELECT cust_id, product_id, 1 FROM wish WHERE wish_id = ? AND cust_id = ?";
}
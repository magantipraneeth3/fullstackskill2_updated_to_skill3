package com.example.Product_CURD;

public class App {
    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // INSERT
        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setDescription("HP Laptop");
        p1.setPrice(105000);
        p1.setQuantity(5);
        dao.saveProduct(p1);

        Product p2 = new Product();
        p2.setName("Mouse");
        p2.setDescription("Wireless Mouse");
        p2.setPrice(500);
        p2.setQuantity(10);
        dao.saveProduct(p2);

        // READ
        dao.getAllProducts();

        // UPDATE
        dao.updateProduct(1, "Laptop", 105000);

        // DELETE
        dao.deleteProduct(2);

        System.out.println("CRUD Operations Completed");
    }
}

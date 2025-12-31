package com.example.Product_CURD;

public class App {
    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setDescription("Electronics");
        p1.setPrice(105000);
        p1.setQuantity(5);
        dao.saveProduct(p1);

        Product p2 = new Product();
        p2.setName("Mouse");
        p2.setDescription("Accessories");
        p2.setPrice(500);
        p2.setQuantity(10);
        dao.saveProduct(p2);

        Product p3 = new Product();
        p3.setName("Keyboard");
        p3.setDescription("Accessories");
        p3.setPrice(1500);
        p3.setQuantity(7);
        dao.saveProduct(p3);

        Product p4 = new Product();
        p4.setName("Monitor");
        p4.setDescription("Electronics");
        p4.setPrice(12000);
        p4.setQuantity(3);
        dao.saveProduct(p4);

        Product p5 = new Product();
        p5.setName("Printer");
        p5.setDescription("Electronics");
        p5.setPrice(8000);
        p5.setQuantity(0);
        dao.saveProduct(p5);

        // HQL operations
        dao.sortByPriceAsc();
        dao.sortByPriceDesc();
        dao.sortByQuantity();

        dao.getProductsWithPagination(0, 3);
        dao.getProductsWithPagination(3, 3);

        dao.countProducts();
        dao.countAvailableProducts();
        dao.groupByDescription();
        dao.minMaxPrice();

        dao.priceBetween(1000, 15000);

        dao.nameStartsWith("M");
        dao.nameEndsWith("r");
        dao.nameContains("top");
        dao.nameWithExactLength(5);

        System.out.println("All HQL Tasks Completed");
    }
}

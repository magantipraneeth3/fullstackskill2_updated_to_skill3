package com.example.Product_CURD;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProductDAO {

    // INSERT
    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        session.close();
    }

    // READ ALL
    public void getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("from Product", Product.class).list();
        list.forEach(p -> System.out.println(p.getName() + " | " + p.getPrice()));
        session.close();
    }

    // 3a. Sort by price ASC
    public void sortByPriceAsc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list =
                session.createQuery("from Product order by price asc", Product.class).list();
        list.forEach(p -> System.out.println(p.getName() + " : " + p.getPrice()));
        session.close();
    }

    // 3b. Sort by price DESC
    public void sortByPriceDesc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list =
                session.createQuery("from Product order by price desc", Product.class).list();
        list.forEach(p -> System.out.println(p.getName() + " : " + p.getPrice()));
        session.close();
    }

    // 4. Sort by quantity (highest first)
    public void sortByQuantity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list =
                session.createQuery("from Product order by quantity desc", Product.class).list();
        list.forEach(p -> System.out.println(p.getName() + " : " + p.getQuantity()));
        session.close();
    }

    // 5. Pagination
    public void getProductsWithPagination(int start, int size) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery("from Product", Product.class)
                .setFirstResult(start)
                .setMaxResults(size)
                .list();
        list.forEach(p -> System.out.println(p.getName()));
        session.close();
    }

    // 6a. Count total products
    public void countProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery(
                "select count(p) from Product p", Long.class).uniqueResult();
        System.out.println("Total Products: " + count);
        session.close();
    }

    // 6b. Count products where quantity > 0
    public void countAvailableProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery(
                "select count(p) from Product p where p.quantity > 0", Long.class)
                .uniqueResult();
        System.out.println("Available Products: " + count);
        session.close();
    }

    // 6c & 7. Group by description (FIXED)
    public void groupByDescription() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Object[]> list = session.createQuery(
                "select p.description, count(p) from Product p group by p.description",
                Object[].class
        ).list();

        for (Object[] row : list) {
            System.out.println(row[0] + " : " + row[1]);
        }
        session.close();
    }

    // 6d. Min & Max price
    public void minMaxPrice() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Object[] result = session.createQuery(
                "select min(p.price), max(p.price) from Product p",
                Object[].class
        ).uniqueResult();

        System.out.println("Min Price: " + result[0]);
        System.out.println("Max Price: " + result[1]);
        session.close();
    }

    // 8. WHERE price between range
    public void priceBetween(double min, double max) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery(
                "from Product p where p.price between :min and :max", Product.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();

        list.forEach(p -> System.out.println(p.getName() + " : " + p.getPrice()));
        session.close();
    }

    // 9a. Names starting with certain letters
    public void nameStartsWith(String prefix) {
        executeLikeQuery(prefix + "%");
    }

    // 9b. Names ending with certain letters
    public void nameEndsWith(String suffix) {
        executeLikeQuery("%" + suffix);
    }

    // 9c. Names containing substring
    public void nameContains(String word) {
        executeLikeQuery("%" + word + "%");
    }

    // 9d. Names with exact character length (FIXED)
    public void nameWithExactLength(int length) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery(
                "from Product p where char_length(p.name) = :len", Product.class)
                .setParameter("len", length)
                .list();

        list.forEach(p -> System.out.println(p.getName()));
        session.close();
    }

    // Helper method for LIKE queries
    private void executeLikeQuery(String value) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> list = session.createQuery(
                "from Product p where p.name like :val", Product.class)
                .setParameter("val", value)
                .list();

        list.forEach(p -> System.out.println(p.getName()));
        session.close();
    }
}

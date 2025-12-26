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

        System.out.println("Product Inserted: " + product.getName());
    }

    // READ ALL
    public void getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Product> list = session.createQuery("from Product", Product.class).list();

        System.out.println("Product List:");
        for (Product p : list) {
            System.out.println(
                "ID: " + p.getId() +
                " | Name: " + p.getName() +
                " | Price: " + p.getPrice()
            );
        }
        session.close();
    }

    // UPDATE
    public void updateProduct(int id, String newName, double newPrice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);
        if (product != null) {
            product.setName(newName);
            product.setPrice(newPrice);
            session.update(product);
            System.out.println("Product Updated ID: " + id);
        }

        tx.commit();
        session.close();
    }

    // DELETE
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);
        if (product != null) {
            session.delete(product);
            System.out.println("Product Deleted ID: " + id);
        }

        tx.commit();
        session.close();
    }
}

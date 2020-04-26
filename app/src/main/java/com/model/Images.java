package com.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "images", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<Product>();

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Images() {
    }

    public Images(String image) {
        this.image = image;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Images(String image, Set<Product> products) {
        this.image = image;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Images)) return false;
        Images images = (Images) o;
        return id == images.id &&
                Objects.equals(image, images.image) &&
                Objects.equals(products, images.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, products);
    }
}

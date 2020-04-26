package com.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "category")
	private String category;
	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Product> products = new HashSet<Product>();

	public Category(String category, Set<Product> products) {
		this.category = category;
		this.products = products;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	public Category() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + ", products=" + products + "]";
	}

}

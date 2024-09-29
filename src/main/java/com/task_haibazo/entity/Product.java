package com.task_haibazo.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "product_name",nullable = false)
	private String productName;
	
	@Lob
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "average_stars", nullable = false)
	@Min(0)
    @Max(5)
	private double averageStars;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "total_view", nullable = false)
	private long totalView;
	
	@Column(name = "sale_end_date")
	private Date saleEndDate;
	
	@Column(name = "discount")
	@Min(0)
    @Max(100)
	private int discount;
	
	@Lob
	@Column(name = "image", nullable = false)
	private String image;
	
	@ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images = new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductStyle> productStyles = new HashSet<>();

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductOption> productOptions = new HashSet<>();


}

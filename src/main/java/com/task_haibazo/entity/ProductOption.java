package com.task_haibazo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_options")
public class ProductOption {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
	
	@ManyToOne
	@JoinColumn(name = "color_id", nullable = false)
	private Color color; 
	 
	@ManyToOne
	@JoinColumn(name = "size_id", nullable = false)
	private Size size;
	 
	@Column(name = "quantity", nullable = false)
	private int quantity;
}

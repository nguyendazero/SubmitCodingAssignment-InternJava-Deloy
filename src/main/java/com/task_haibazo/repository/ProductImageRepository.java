package com.task_haibazo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_haibazo.entity.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>{
	List<ProductImage> findByProductId(long productId);
}

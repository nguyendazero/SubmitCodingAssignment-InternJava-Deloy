package com.task_haibazo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task_haibazo.entity.Product;
import com.task_haibazo.entity.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long>{
	@Query("SELECT p FROM Product p JOIN p.productOptions po JOIN po.size s WHERE (:sizeId IS NULL OR s.id = :sizeId) AND (:colorId IS NULL OR po.color.id = :colorId) AND (:minPrice IS NULL OR p.price >= :minPrice) AND (:maxPrice IS NULL OR p.price <= :maxPrice) ORDER BY CASE WHEN :sortOrder = 'asc' THEN p.price END ASC, CASE WHEN :sortOrder = 'desc' THEN p.price END DESC")
	List<Product> findAllByCriteria(@Param("sizeId") Long sizeId, @Param("colorId") Long colorId, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("sortOrder") String sortOrder);

}

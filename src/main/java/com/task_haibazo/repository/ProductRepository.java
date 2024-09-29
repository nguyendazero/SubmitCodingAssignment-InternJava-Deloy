package com.task_haibazo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task_haibazo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("SELECT p FROM Product p " +
		       "JOIN p.productOptions po " +
		       "LEFT JOIN p.productStyles ps ON ps.product.id = p.id " +
		       "LEFT JOIN ps.style s ON s.id = ps.style.id " +
		       "WHERE (:sizeId IS NULL OR po.size.id = :sizeId) " +
		       "AND (:colorId IS NULL OR po.color.id = :colorId) " +
		       "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
		       "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
		       "AND (:styleId IS NULL OR s.id = :styleId) " + 
		       "AND (:categoryId IS NULL OR p.category.id = :categoryId) " + 
		       "ORDER BY " +
		       "CASE WHEN :sortBy = 'price' AND :sortOrder = 'asc' THEN p.price END ASC, " +
		       "CASE WHEN :sortBy = 'price' AND :sortOrder = 'desc' THEN p.price END DESC, " +
		       "CASE WHEN :sortBy = 'averageStars' AND :sortOrder = 'asc' THEN p.averageStars END ASC, " +
		       "CASE WHEN :sortBy = 'averageStars' AND :sortOrder = 'desc' THEN p.averageStars END DESC")
		List<Product> findProducts(@Param("sizeId") Long sizeId,
		                            @Param("minPrice") Double minPrice,
		                            @Param("maxPrice") Double maxPrice,
		                            @Param("colorId") Long colorId,
		                            @Param("styleId") Long styleId, 
		                            @Param("categoryId") Long categoryId, 
		                            @Param("sortBy") String sortBy,
		                            @Param("sortOrder") String sortOrder);


}

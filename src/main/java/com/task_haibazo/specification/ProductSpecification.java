package com.task_haibazo.specification;

import org.springframework.data.jpa.domain.Specification;

import com.task_haibazo.entity.Product;
import com.task_haibazo.entity.ProductOption;
import com.task_haibazo.entity.ProductStyle;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class ProductSpecification {

    public static Specification<Product> hasSize(Long sizeId) {
        return (root, query, criteriaBuilder) -> {
            if (sizeId == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Product, ProductOption> productOptionJoin = root.join("productOptions");
            return criteriaBuilder.equal(productOptionJoin.get("size").get("id"), sizeId);
        };
    }

    public static Specification<Product> hasColor(Long colorId) {
        return (root, query, criteriaBuilder) -> {
            if (colorId == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Product, ProductOption> productOptionJoin = root.join("productOptions");
            return criteriaBuilder.equal(productOptionJoin.get("color").get("id"), colorId);
        };
    }

    public static Specification<Product> hasMinPrice(Double minPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    public static Specification<Product> hasMaxPrice(Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<Product> hasStyle(Long styleId) {
        return (root, query, criteriaBuilder) -> {
            if (styleId == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Product, ProductStyle> productStyleJoin = root.join("productStyles", JoinType.LEFT);
            return criteriaBuilder.equal(productStyleJoin.get("style").get("id"), styleId);
        };
    }

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }

    public static Specification<Product> sortBy(String sortBy, String sortOrder) {
        return (root, query, criteriaBuilder) -> {
            if (sortBy == null || sortOrder == null) {
                return criteriaBuilder.conjunction();
            }
            if (sortBy.equals("price")) {
                if (sortOrder.equals("asc")) {
                    query.orderBy(criteriaBuilder.asc(root.get("price")));
                } else {
                    query.orderBy(criteriaBuilder.desc(root.get("price")));
                }
            } else if (sortBy.equals("averageStars")) {
                if (sortOrder.equals("asc")) {
                    query.orderBy(criteriaBuilder.asc(root.get("averageStars")));
                } else {
                    query.orderBy(criteriaBuilder.desc(root.get("averageStars")));
                }
            }
            return null;
        };
    }
}

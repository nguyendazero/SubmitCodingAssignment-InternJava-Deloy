package com.task_haibazo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_haibazo.entity.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long>{

}

package com.task_haibazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_haibazo.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long>{

}

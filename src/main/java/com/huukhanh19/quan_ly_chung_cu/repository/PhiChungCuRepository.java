package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.MonthlyFeeId;
import com.huukhanh19.quan_ly_chung_cu.entity.PhiChungCu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhiChungCuRepository extends JpaRepository<PhiChungCu, MonthlyFeeId> {
}
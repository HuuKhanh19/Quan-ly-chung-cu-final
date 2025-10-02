package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.BangTamVang;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BangTamVangRepository extends JpaRepository<BangTamVang, String> {
}

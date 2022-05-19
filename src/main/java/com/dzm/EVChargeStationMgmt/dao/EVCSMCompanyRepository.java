package com.dzm.EVChargeStationMgmt.dao;

import com.dzm.EVChargeStationMgmt.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EVCSMCompanyRepository extends  JpaRepository<Company,Integer>{

    @Query(value = "SELECT * FROM COMPANY WHERE PARENT_COMPANY_ID = ?1",nativeQuery = true)
    List<Company> findChildren(@Param("companyId") Integer id);

    List<Company> findByName(String name);
}

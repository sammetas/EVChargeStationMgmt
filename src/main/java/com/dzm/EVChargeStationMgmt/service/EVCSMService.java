package com.dzm.EVChargeStationMgmt.service;

import com.dzm.EVChargeStationMgmt.dao.EVCSMRepository;
import com.dzm.EVChargeStationMgmt.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EVCSMService {

    @Autowired
    private EVCSMRepository repository;

    public void processAndSaveCompany(Company company) {
        repository.save(company);

    }

    public boolean processAndUpdateCompany(Company company) {
        if(repository.existsById(company.getId())){
            Optional<Company> existing=repository.findById(company.getId());
            Company newCompany=existing.get();
            newCompany.setName(company.getName());
            newCompany.setParentId(company.getParentId());
            repository.save(newCompany);
            return true;
        }
        return false;
    }

    public boolean processAndDelete(int companyId) {
        if(repository.existsById(companyId)) {
            repository.deleteById(companyId);
            return true;
        }
        return false;
    }

    public List<Company> getAllCompanies() {
      return   repository.findAll(Sort.by(Company.class.getName()));
    }
}

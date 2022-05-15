package com.dzm.EVChargeStationMgmt.service;

import com.dzm.EVChargeStationMgmt.dao.EVCSMCompanyRepository;
import com.dzm.EVChargeStationMgmt.dao.EVCSMStationRepository;
import com.dzm.EVChargeStationMgmt.model.Company;
import com.dzm.EVChargeStationMgmt.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EVCSMCompanyService {

    @Autowired
    private EVCSMCompanyRepository companyRepository;

    public void processAndSaveCompany(Company company) {
        companyRepository.save(company);

    }

    public boolean processAndUpdateCompany(Company company) {
        if(companyRepository.existsById(company.getId())){
            Optional<Company> existing=companyRepository.findById(company.getId());
            Company newCompany=existing.get();
            newCompany.setName(company.getName());
            newCompany.setParentId(company.getParentId());
            companyRepository.save(newCompany);
            return true;
        }
        return false;
    }

    public boolean processAndDelete(int companyId) {
        if(companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
            return true;
        }
        return false;
    }

    public List<Company> getAllCompanies() {
      //  List<Company> companies= new ArrayList<>();
     return   companyRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
     /*   System.out.println("sizeeee---->"+list.size());
      list.forEach(company -> {
          if(company.getParentId()!=0){
              company.getChildrens().add(getChildren(list,company));
              companies.add(company);
          }else {
              companies.add(company);
          }
      });
      return companies;*/
    }

    private Company getChildren(List<Company> list, Company company) {
      list.stream().filter(a->a.getId()==company.getParentId()).collect(Collectors.toList());
      return  null;
    }


}

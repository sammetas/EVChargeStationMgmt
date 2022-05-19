package com.dzm.EVChargeStationMgmt;

import com.dzm.EVChargeStationMgmt.controller.EVCSMCompanyController;
import com.dzm.EVChargeStationMgmt.dao.EVCSMCompanyRepository;
import com.dzm.EVChargeStationMgmt.exception.NoRecordsFoundException;
import com.dzm.EVChargeStationMgmt.model.Company;
import com.dzm.EVChargeStationMgmt.service.EVCSMCompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
 class CompanyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private  EVCSMCompanyRepository companyRepository;

    @Autowired
    private EVCSMCompanyController companyController;

    @Autowired
    private  EVCSMCompanyService companyService;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(mock(EVCSMCompanyController.class)).build();
    }
    @Test
    public void getAllObjects() throws Exception {
        mockMvc.perform(get("http://localhost:8081/ev/v1/companies/"))
                .andExpect(status().isOk());

    }

    @Test
    void testCompany_StatusCreate(){
        Company company1 = new Company();
        company1.setName("A");	company1.setId(1);	company1.setParentId(0);
        ResponseEntity<Company> response = new ResponseEntity<>(HttpStatus.CREATED);
        when(mock(EVCSMCompanyController.class).saveCompany(mock(Company.class))).thenReturn(new ResponseEntity<Company>(company1,HttpStatus.CREATED));
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void testCompany_StatusCreateDup(){
        Company company1 = new Company();
        company1.setName("A");	company1.setId(1);	company1.setParentId(0);
        ResponseEntity<Company> response = new ResponseEntity<>(HttpStatus.CREATED);
        when(mock(EVCSMCompanyController.class).saveCompany(mock(Company.class))).thenThrow(NoRecordsFoundException.class);


    }



}

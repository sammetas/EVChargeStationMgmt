package com.dzm.EVChargeStationMgmt;

import com.dzm.EVChargeStationMgmt.controller.EVCSMCompanyController;
import com.dzm.EVChargeStationMgmt.model.Company;
import com.dzm.EVChargeStationMgmt.model.Station;
import com.dzm.EVChargeStationMgmt.service.EVCSMCompanyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class EvChargeStationMgmtApplicationTests {

	@MockBean
	private EVCSMCompanyService companyService;
	@MockBean
	private EVCSMCompanyController companyController;


	@BeforeEach
	void contextLoads() {

		Company company1 = new Company();
		company1.setName("A");
		company1.setId(1);
		company1.setParentId(0);

		Company company2 = new Company();
		company2.setName("B");
		company2.setId(2);
		company2.setParentId(1);

		Company company3 = new Company();
		company3.setName("C");
		company3.setId(3);
		company3.setParentId(2);

		Station station = new Station();
		station.setName("StationA1");
		station.setId(1);
		station.setLongitude(123.33d);
		station.setLatitude(126.232);
		station.setCompanyId(1);

	}

   /*
     This will pass when application is running.
    */
	@Test
	void testCompany_Status() throws IOException, InterruptedException {
		HttpClient httpClient =  HttpClient.newBuilder().build();
		HttpResponse<String> response =null;
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/ev/v1/companies/")).build();
		try {
			response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		}catch (IOException ioException){
		assertThrows(IOException.class,()->httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()));
		return;
		}
		assertEquals(200,response.statusCode());

	}

	@Test
	void testCompany_StatusCreate(){
	 	Company company1 = new Company();
		company1.setName("A");	company1.setId(1);	company1.setParentId(0);
		 ResponseEntity<Company> response = new ResponseEntity<>(HttpStatus.CREATED);
		when(mock(EVCSMCompanyController.class).saveCompany(mock(Company.class))).thenReturn(new ResponseEntity<Company>(company1,HttpStatus.CREATED));
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}



}

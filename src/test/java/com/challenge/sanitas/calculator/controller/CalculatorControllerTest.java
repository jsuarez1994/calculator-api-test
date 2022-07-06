package com.challenge.sanitas.calculator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge.sanitas.calculator.service.OperationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {
	
	private static final String URI = "/api/calculation";

	@MockBean
	@Qualifier("add")
	OperationService employeeServiceAdd;

	@MockBean
	@Qualifier("substract")
	OperationService employeeServiceSubstract;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void test_result_add() throws Exception {

		when(employeeServiceAdd.calculate(Mockito.any())).thenReturn("11");

		mockMvc.perform(post(URI).param("operator", "+").contentType(MediaType.APPLICATION_JSON)
				.content("[1.5,9.5]")).andExpect(status().isOk())
				.andExpect(response -> assertEquals(response.getResponse().getContentAsString(), "11"));
	}

	@Test
	public void test_result_substract() throws Exception {

		when(employeeServiceSubstract.calculate(Mockito.any())).thenReturn("8");

		mockMvc.perform(post(URI).param("operator", "-").contentType(MediaType.APPLICATION_JSON)
				.content("[9.5,1.5]")).andExpect(status().isOk())
				.andExpect(response -> assertEquals(response.getResponse().getContentAsString(), "8"));
	}

	@Test
	public void test_result_exception_operator() throws Exception {

		mockMvc.perform(post(URI).param("operator", "*").contentType(MediaType.APPLICATION_JSON)
				.content("[9.5,1.5]")).andExpect(status().isInternalServerError()).andExpect(
						response -> assertEquals(response.getResponse().getContentAsString(), "Operator not exists"));
	}

}
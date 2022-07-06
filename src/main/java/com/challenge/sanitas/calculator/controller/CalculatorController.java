package com.challenge.sanitas.calculator.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.sanitas.calculator.exception.OperatorNotExistException;
import com.challenge.sanitas.calculator.utils.OperatorsType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("${endpoints.requestmain}")
@Slf4j
public class CalculatorController extends BaseController {

	/**
	 * Method result to resolve operation with params
	 * 
	 * 
	 * @param operator the Operator to calculate
	 * @param params   the params to resolve calculation
	 * @return Resulto of Operation (String)
	 */
	@Operation(summary = "Get a result of operation")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Result operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid operator", content = @Content),
			@ApiResponse(responseCode = "404", description = "Invalid operator", content = @Content) })
	@PostMapping(path = "${endpoints.calculation}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> result(
			@Parameter(description = "Operator to do", example = "+") @RequestParam(defaultValue = "+") String operator,
			@RequestBody List<Double> params) {
		log.info("Start CalculatorController.result with the param: {}", operator);
		ResponseEntity<Object> responseEntity = null;
		try {
			if (null == StringUtils.trimToNull(operator) || !OperatorsType.getAllSymbols().contains(operator)) {
				responseEntity = new ResponseEntity<Object>("Symbol not found", HttpStatus.BAD_REQUEST);
			}
			final String response = getCalculate(operator, params);
			responseEntity = new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (OperatorNotExistException e) {
			log.error("Error calculate");
			responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			log.error("Error calculate");
			responseEntity = new ResponseEntity<Object>(errorGeneral, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("End CalculatorController.result with the param: {}", operator);
		return responseEntity;
	}

}

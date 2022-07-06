package com.challenge.sanitas.calculator.service;

import java.util.List;

public interface OperationService {

	/**
	 * Resolve calculation numbers
	 * 
	 * @param params the params
	 * @return result String
	 */
	String calculate(final List<Double> params);
	
	

}

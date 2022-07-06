package com.challenge.sanitas.calculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.challenge.sanitas.calculator.exception.OperatorNotExistException;
import com.challenge.sanitas.calculator.service.OperationService;
import com.challenge.sanitas.calculator.utils.OperatorsType;

public class BaseController {
	// SERVICES
	/** the operationService */
	@Autowired
	@Qualifier("add")
	private OperationService operationAdd;

	/** the operationService */
	@Autowired
	@Qualifier("substract")
	private OperationService operationSubstract;

	// EXCEPTIONS
	/** the operatornotfound */
	@Value("${exceptions.operatornotfound}")
	protected String operatornotfound;

	/** the errorGeneral */
	@Value("${exceptions.general}")
	protected String errorGeneral;

	/**
	 * 
	 * Switch to how calculate operator
	 * 
	 * @param operator the operator
	 * @param params   the List Double
	 * @return String response
	 * @throws OperatorNotExistException the Exception
	 */
	protected String getCalculate(final String operator, final List<Double> params) throws OperatorNotExistException {
		// Get operator type for operator param
		OperatorsType ot = OperatorsType.findBySymbol(operator);
		if(null == ot) {
			throw new OperatorNotExistException(operatornotfound);
		} else {
			switch (ot) {
			case ADD:
				return operationAdd.calculate(params);
			case SUBSTRACT:
				return operationSubstract.calculate(params);
			default:
				throw new OperatorNotExistException(operatornotfound);
			}
		}
	}

}

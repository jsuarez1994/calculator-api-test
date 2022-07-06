package com.challenge.sanitas.calculator.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.challenge.sanitas.calculator.service.OperationService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "add")
@Slf4j
public class OperationAddServiceImpl extends OperationBase implements OperationService {

	@Override
	public String calculate(final List<Double> params) {
		log.info("Start OperationService.calculate");
		Double result = params.stream().mapToDouble(item -> item).sum();
		log.info("End OperationService.calculate");
		return df.format(result);
	}

}

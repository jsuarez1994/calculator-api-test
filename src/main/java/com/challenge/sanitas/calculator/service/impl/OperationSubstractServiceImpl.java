package com.challenge.sanitas.calculator.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.challenge.sanitas.calculator.service.OperationService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "substract")
@Slf4j
public class OperationSubstractServiceImpl extends OperationBase implements OperationService {

	@Override
	public String calculate(final List<Double> params) {
		log.info("Start OperationService.calculate");
		Double response = null == params.get(0) ? 0D : params.get(0);
		for (int i = 1; i < params.size(); i++) {
			response -= null == params.get(i) ? 0D : params.get(i);
		}
		log.info("End OperationService.calculate");
		return df.format(response);
	}

}

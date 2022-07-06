package com.challenge.sanitas.calculator.service.impl;

import java.text.DecimalFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OperationBase {
	/** the df */
	DecimalFormat df = new DecimalFormat("#.##");
}

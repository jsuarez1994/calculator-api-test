package com.challenge.sanitas.calculator.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperatorsType {

	ADD("+"), SUBSTRACT("-");

	/** the symbol */
	public String symbol;

	/**
	 * Get all symbols
	 * 
	 * @return Set String symnols
	 */
	public static Set<String> getAllSymbols() {
		return Arrays.asList(values()).stream().map(OperatorsType::getSymbol).collect(Collectors.toSet());
	}

	/**
	 * Find operator type by symbol
	 * 
	 * @param symbol the symbol
	 * @return the OperatorsType
	 */
	public static OperatorsType findBySymbol(final String symbol) {
		return Arrays.asList(values()).stream().filter((OperatorsType ot) -> StringUtils.equals(ot.getSymbol(), symbol))
				.findFirst().orElse(null);
	}

}

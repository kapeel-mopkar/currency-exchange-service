package com.kaps.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		/*
		 * ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to,
		 * BigDecimal.valueOf(65));
		 * exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")))
		 * ;
		 */
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		LOGGER.info("/currency-exchange : {}", exchangeValue);
		return exchangeValue;
	}

}

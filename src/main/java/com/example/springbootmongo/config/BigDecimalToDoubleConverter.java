package com.example.springbootmongo.config;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
 
public class BigDecimalToDoubleConverter implements Converter<BigDecimal, Double> {
 
    @Override
    public Double convert(BigDecimal source) {
        return source.doubleValue();
    }
}
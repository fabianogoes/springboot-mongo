package com.example.springbootmongo.config;

import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
 
 
public class DoubleToBigDecimalConverter implements Converter<Double, BigDecimal> {
 
    @Override
    public BigDecimal convert(Double source) {
        return new BigDecimal(source);
    }
}
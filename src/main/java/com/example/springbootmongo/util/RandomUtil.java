package com.example.springbootmongo.util;

import java.math.BigDecimal;
import java.math.MathContext;

public class RandomUtil {

    private static int PRECISION_ROUND = 2;

    public static BigDecimal bigDecimalRandom(){
        return bigDecimalRandom(PRECISION_ROUND);
    }

    public static BigDecimal bigDecimalRandom(int precision){
        BigDecimal bigDecimal = new BigDecimal(Math.random());
        MathContext mc = new MathContext(precision); // 2 precision
        return bigDecimal.round(mc);
    }

}

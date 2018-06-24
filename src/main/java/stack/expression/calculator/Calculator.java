package stack.expression.calculator;


import stack.expression.CalculateContext;

import java.math.BigDecimal;


public interface Calculator {

    /**
     * 公式计算器
     * @param context 计算上下文
     * @return
     * @throws Exception
     */
    BigDecimal calculate(CalculateContext context) throws Exception;
}

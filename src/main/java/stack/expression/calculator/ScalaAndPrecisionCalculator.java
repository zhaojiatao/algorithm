package stack.expression.calculator;

import stack.expression.CalculateContext;
import stack.expression.command.OperatorCommand;

import java.math.BigDecimal;

/**
 * 表达式计算类
 * <p>
 * 计算结果使用指定的精度计算和保留指定的小数位数
 */
public class ScalaAndPrecisionCalculator extends AbstractCalculator implements Calculator {

    @Override
    public BigDecimal getResult(OperatorCommand command, CalculateContext context) throws Exception {
        return command.operator(context.getMathContext(), context.getScala(), context.getRoundingMode());
    }
}

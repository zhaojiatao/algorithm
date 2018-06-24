package stack.expression.calculator;

import stack.expression.CalculateContext;
import stack.expression.command.OperatorCommand;

import java.math.BigDecimal;

/**
 * 表达式计算类
 * <p>
 * 计算结果保留指定的上数位数
 */
public class ScalaCalculator extends AbstractCalculator implements Calculator {

    @Override
    public BigDecimal getResult(OperatorCommand command, CalculateContext context) throws Exception {
        return command.operator(context.getScala(), context.getRoundingMode());
    }
}

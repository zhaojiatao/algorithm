package stack.expression.command;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * 次方操作符实现
 */
public class PowCommand implements OperatorCommand {
    //操作数1
    private final BigDecimal opData;

    private final BigDecimal opData2;

    public PowCommand(BigDecimal opData, BigDecimal opData2) {
        this.opData = opData;
        this.opData2 = opData2;
    }

    @Override
    public BigDecimal operator(MathContext mathContext) {
        BigDecimal value = opData.pow(opData2.toBigInteger().intValue(), mathContext);

        return value;
    }

    @Override
    public BigDecimal operator(int scale, int roundingMode) {
        BigDecimal value = opData.pow(opData2.toBigInteger().intValue()).setScale(scale,roundingMode);

        return value;
    }

    @Override
    public BigDecimal operator(MathContext mathContext, int scale, int roundingMode) {
        BigDecimal value = opData.pow(opData2.toBigInteger().intValue(),mathContext).setScale(scale,roundingMode);

        return value;
    }
}

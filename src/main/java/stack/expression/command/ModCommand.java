package stack.expression.command;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * 求余操作符实现
 */
public class ModCommand implements OperatorCommand {
    //操作数1
    private final BigDecimal opData;

    private final BigDecimal opData2;

    public ModCommand(BigDecimal opData, BigDecimal opData2) {
        this.opData = opData;
        this.opData2 = opData2;
    }

    @Override
    public BigDecimal operator(MathContext mathContext) {
        BigDecimal value = opData.divideAndRemainder(opData2, mathContext)[1];

        return value;
    }

    @Override
    public BigDecimal operator(int scale, int roundingMode) {
        BigDecimal value = opData.divideAndRemainder(opData2)[1];

        return value;
    }

    @Override
    public BigDecimal operator(MathContext mathContext, int scale, int roundingMode) {
        BigDecimal value = opData.divideAndRemainder(opData2, mathContext)[1];

        return value;
    }
}

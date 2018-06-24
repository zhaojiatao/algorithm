package stack.expression.command;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * 加法操作符实现
 */
public class PlusCommand implements OperatorCommand {
    //操作数1
    private final BigDecimal opData;

    private final BigDecimal opData2;

    public PlusCommand(BigDecimal opData, BigDecimal opData2) {
        this.opData = opData;
        this.opData2 = opData2;
    }

    @Override
    public BigDecimal operator(MathContext mathContext) {
        BigDecimal value = opData.add(opData2, mathContext);

        return value;
    }

    @Override
    public BigDecimal operator(int scale, int roundingMode) {
        BigDecimal value = opData.add(opData2).setScale(scale, roundingMode);

        return value;
    }

    @Override
    public BigDecimal operator(MathContext mathContext, int scale, int roundingMode) {
        BigDecimal value = opData.add(opData2, mathContext).setScale(scale, roundingMode);

        return value;
    }
}

package stack.expression.command;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * 除法操作符实现
 */
public class DivisionCommand implements OperatorCommand {
    //操作数1
    private final BigDecimal opData;

    private final BigDecimal opData2;

    public DivisionCommand(BigDecimal opData, BigDecimal opData2) {
        this.opData = opData;
        this.opData2 = opData2;
    }

    @Override
    public BigDecimal operator(MathContext mathContext) {
        //NumberFormat format = NumberFormat.getInstance();
        //BigDecimal data1 = new BigDecimal(format.parse(opData).toString());
        //保留4位小数，4舍5入
        //data1.setScale(4,4);

        //BigDecimal data2 = new BigDecimal(format.parse(opData2).toString());
        //保留4位小数，4舍5入
        //data2.setScale(4,4);

        BigDecimal value = opData.divide(opData2, mathContext);
        return value;
    }

    @Override
    public BigDecimal operator(int scale, int roundingMode) {
        BigDecimal value = opData.divide(opData2, scale,roundingMode);
        return value;
    }

    @Override
    public BigDecimal operator(MathContext mathContext, int scale, int roundingMode) {
        BigDecimal value = opData.divide(opData2, mathContext).setScale(scale,roundingMode);
        return value;
    }
}

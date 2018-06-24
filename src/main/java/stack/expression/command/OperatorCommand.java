package stack.expression.command;

import java.math.BigDecimal;
import java.math.MathContext;

public interface OperatorCommand {

    /**
     * 计算表达式结果
     * @param mathContext 计算上下文参数，指定精度及进位模式
     * @return
     * @throws Exception
     */
    BigDecimal operator(MathContext mathContext) throws Exception;

    /**
     * 计算表达式结果
     *
     * @param scale 小数点后位数
     * @param roundingMode 进位模式
     * @return
     * @throws Exception
     */
    BigDecimal operator(int scale, int roundingMode) throws Exception;

    /**
     * 计算表达式结果
     *
     * @param mathContext 计算上下文参数，指定精度及进位模式
     * @param scale 小数点后位数
     * @param roundingMode 进位模式
     * @return
     * @throws Exception
     */
    BigDecimal operator(MathContext mathContext, int scale, int roundingMode) throws Exception;

}

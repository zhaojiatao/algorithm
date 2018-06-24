package stack.expression.calculator;



import stack.expression.CalculateContext;
import stack.expression.Operator;
import stack.expression.command.OperatorCommand;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 表达式计算类
 * <p>
 * 计算结果使用指定的精度计算
 */
public abstract class AbstractCalculator implements Calculator{

    /**
     * 计算后缀表达式值
     *
     * @param context 计算上下文
     * @return
     */
    @Override
    public BigDecimal calculate(CalculateContext context) throws Exception {
        /*计算后缀表达式的临时栈*/
        Stack<BigDecimal> opData = new Stack<>();
        /*由于中缀表达式元素之间是用逗号分割的*/
        StringTokenizer tokenizer = new StringTokenizer(context.getSuffixExpression(), ",");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (!Operator.isSupport(token)) {
                /*如果当前元素不是操作符，则入栈*/
                opData.push(new BigDecimal(token));
            } else {
                /*根据当前的运算符返回与当前运算负匹配的运算命令对象，并通过运算命令对象的构造方法将栈顶依此出栈的两个被操作数和操作数绑定到命令对象中*/
                OperatorCommand command = Operator.getInstantByOp(token).build(opData);
                /*执行命令，返回执行结果*/
                BigDecimal result = getResult(command,context);
                /*将栈顶两个数的操作结果入栈*/
                opData.push(result);
            }
        }
        /*最终，栈中仅村存的，即为运算结果*/
        return opData.pop();
    }

    protected abstract BigDecimal getResult(OperatorCommand command,CalculateContext context) throws Exception;
}

package stack;

import stack.expression.CalculateContext;
import stack.expression.calculator.Calculator;
import stack.expression.calculator.ScalaAndPrecisionCalculator;

/**
 * @author zhaojiatao
 * @date 2018/6/24
 * 本测试，主要测试java中stack数据结构的经典应用——四则混合运算
 * 其中中缀表达式转后缀表达式，以及根据后缀表达式进行计算，是stack的典型用法，应熟练掌握
 */
public class StackTest {
    private static String exp = "名义贷款金额*(1+对外利率/12)^期数*对外利率/12*((1+银行利率/12)^期数-1)/(((1+对外利率/12)^期数-1)*(1+银行利率/12)^期数*银行利率/12)-名义贷款金额";

    public static void main(String[] args) throws Exception{
        /*构造计算上下文*/
        CalculateContext context = new CalculateContext(exp);
        context.bind("名义贷款金额","10000");
        context.bind("对外利率","0.1488");
        context.bind("银行利率","5.99%");
        context.bind("期数","36");
        //调用工具，计算表达式值。

        /*后缀表达式*/
        System.out.println(context.getSuffixExpression());

        /*不同的计算器实现类*/
        Calculator calculator=new ScalaAndPrecisionCalculator();
        System.out.println(calculator.calculate(context));

    }

}

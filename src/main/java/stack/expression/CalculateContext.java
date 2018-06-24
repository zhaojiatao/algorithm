package stack.expression;

import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Map;

/**
 * 中缀表达式计算上下文
 */
public class CalculateContext {
    
    /**
     * 中缀表达式
     */
    private String infixExpression;
    
    /**
     * 后缀表达式
     */
    private String suffixExpression;
    /**
     * 百分数格式化对象
     */
    private NumberFormat format = NumberFormat.getPercentInstance();
    
    /**
     * 计算上下文，指定精确度及进位模式
     */
    private MathContext mathContext = new MathContext(7, RoundingMode.HALF_UP);
    
    /**
     * 小数点后几位
     */
    private int scala = 8;
    
    /**
     * @see RoundingMode
     */
    private int roundingMode = RoundingMode.HALF_UP.ordinal();
    
    
    public CalculateContext(String infixExpression) {
        this.infixExpression = infixExpression;
        this.suffixExpression = ExpressionConverter.toReversePolishNotation(infixExpression);
    }
    
    
    public CalculateContext bind(String variable, String value) throws Exception {
        if (value.contains("%")) {
            //转换value中的%号
            infixExpression = infixExpression.replaceAll(variable, format.parse(value).toString());
            suffixExpression = suffixExpression.replaceAll(variable, format.parse(value).toString());
        } else {
            infixExpression = infixExpression.replaceAll(variable, value);
            suffixExpression = suffixExpression.replaceAll(variable, value);
        }
        return this;
    }
    
    
    public CalculateContext bind(Map<String, String> variables) throws Exception {
        //转换value中的%号
        for (Map.Entry<String, String> variable : variables.entrySet()) {
            if (variable.getValue().contains("%")) {
                infixExpression = infixExpression
                        .replaceAll(variable.getKey(), format.parse(variable.getValue()).toString());
                suffixExpression = suffixExpression
                        .replaceAll(variable.getKey(), format.parse(variable.getValue()).toString());
            } else {
                infixExpression = infixExpression.replaceAll(variable.getKey(), variable.getValue());
                suffixExpression = suffixExpression.replaceAll(variable.getKey(), variable.getValue());
            }
        }
        return this;
    }
    
    
    public String getInfixExpression() {
        return infixExpression;
    }
    
    
    public String getSuffixExpression() {
        return suffixExpression;
    }
    
    
    public MathContext getMathContext() {
        return mathContext;
    }
    
    
    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }
    
    
    public int getScala() {
        return scala;
    }
    
    
    public void setScala(int scala) {
        this.scala = scala;
    }
    
    
    public int getRoundingMode() {
        return roundingMode;
    }
    
    
    public void setRoundingMode(int roundingMode) {
        this.roundingMode = roundingMode;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CalculateContext{infixExpression='").append(infixExpression)
                .append("', suffixExpression='").append(suffixExpression).append("', mathContext={")
                .append("}").append(", scala=").append(scala).append(", roundingMode=").append(roundingMode)
                .append("}");
        return sb.toString();
    }
}


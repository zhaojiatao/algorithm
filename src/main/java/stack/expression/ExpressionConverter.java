package stack.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 表达式转换工具类
 */
public class ExpressionConverter {
    /**
     * 中缀表达式转后缀表达式
     * <p>
     *     算法如下：
     * <li>遇到数字或字符：直接输出</li>
     * <li>遇到'('：压栈</li>
     * <li>遇到')'：持续出栈，如果出栈的符号不是'('则输出，否则终止出栈。</li>
     * <li>遇到符号则判断该符号与栈顶符号的运算优先级，如果栈顶符号的运算优先级高或相等，则出栈并输出，直到优先级上于或栈为空；如果栈顶符号的运算优先级低于当前符号的运算优先级，则将当前符号压栈。</li>
     * <li>处理完字符串后将栈中剩余的符号全部输出。</li>
     * @param infixExpression 中缀表达式
     * @return 后缀表达式
     */
    public static String toReversePolishNotation(String infixExpression) {
        /*将中缀表达式转为字符数组*/
        char[] expChars = infixExpression.trim().toCharArray();
        /*操作符临时存储栈*/
        Stack<Character> opStack = new Stack<>();
        /*后缀表达式顺序存储队列*/
        List<String> queue = new ArrayList<>();

        /*每两个相邻操作符之间的字符串长度*/
        int temp = 0;
        for (int i = 0; i < expChars.length; i++) {
            char c = expChars[i];
            //过滤掉：空格，水平制表符，换行符，回车符
            if (c == 32 || c == 9 || c == 10 || c == 13) {
                continue;
            }
            //判断是否是操作符，

            if (c == '(') {
                /*遇到'('：入栈*/
                opStack.push(c);
            } else if (!Operator.isSupport(String.valueOf(c)) && c != ')') {
                /*如果当前字符既不是操作符也不是右括号，则代表数字字符，将代表数字字符串的长度临时变量加一*/
                temp++;
            } else if (Operator.isSupport(String.valueOf(c))) {
                /*如果当前读取的是操作符，且如果其前面相邻的是数字字符串，则将其之前的字符串输出*/
                if (temp > 0) {
                    queue.add(String.valueOf(Arrays.copyOfRange(expChars, i - temp, i)));
                    temp = 0;
                }
                /*如果栈为空栈，则无须判断优先级，直接入栈*/
                if (opStack.isEmpty()) {
                    opStack.push(c);
                    /*进入下一个循环*/
                    continue;
                }
                /*如果不为空栈，则证明栈中已经存在运算符，则需要判断当前读取的运算符和栈中运算符的优先级*/
                /*得到当前运算符的优先级*/
                Integer cLevel = Operator.getInstantByOp(String.valueOf(c)).getPriority();
                while (!opStack.isEmpty()) {
                    /*得到栈顶的操作符*/
                    char tPeek = opStack.peek();
                    /*如果栈顶是左操作符，则直接入栈*/
                    if (tPeek == '(') {
                        opStack.push(c);
                        break;
                    }
                    /*如果栈顶操作符不是左括号，则得到栈顶操作符的优先级*/
                    Integer tLevel = Operator.getInstantByOp(String.valueOf(tPeek)).getPriority();
                    /*如果栈顶操作符的优先级大于等于当前操作符的优先级，则将栈顶操作符出栈输出*/
                    if (tLevel >= cLevel) {
                        queue.add(String.valueOf(opStack.pop()));
                    } else {
                        /*如果栈顶操作符的优先级小于当前操作符的优先级，则将当前操作符入栈*/
                        opStack.push(c);
                        /*当前操作符一旦入栈，则结束循环*/
                        break;
                    }
                }
                /*如果刚才的循环之后，栈为空，则意味着刚才循环将比本次读取的操作符优先级高的操作符全部出栈了，则当前操作符入栈*/
                if (opStack.isEmpty()) {
                    opStack.push(c);
                }
            } else if (c == ')') {
                /*如果当前读取的是右括号，且如果其前面相邻的是数字字符串，则将其之前的字符串输出*/
                if (temp > 0) {
                    queue.add(String.valueOf(Arrays.copyOfRange(expChars, i - temp, i)));
                    temp = 0;
                }
                /*遇到')'：持续出栈，如果出栈的符号不是'('则输出，否则终止出栈。*/
                while (true) {
                    Character t = opStack.pop();
                    if (!t.equals('(')) {
                        queue.add(String.valueOf(t));
                    } else {
                        break;
                    }
                }

            }
        }
        /*将最后的一个字符串输出*/
        if (temp > 0) {
            queue.add(String.valueOf(Arrays.copyOfRange(expChars, expChars.length - temp, expChars.length)));
        }
        /*处理完字符串后将栈中剩余的符号全部输出。*/
        while (!opStack.isEmpty()) {
            queue.add(String.valueOf(opStack.pop()));
        }
        return queue.stream().collect(Collectors.joining(","));
    }
}

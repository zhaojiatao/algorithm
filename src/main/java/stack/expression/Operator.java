package stack.expression;


import stack.expression.command.CommandBuilder;
import stack.expression.command.DivisionCommand;
import stack.expression.command.MinusCommand;
import stack.expression.command.ModCommand;
import stack.expression.command.MultipCommand;
import stack.expression.command.OperatorCommand;
import stack.expression.command.PlusCommand;
import stack.expression.command.PowCommand;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Author: jiyanbin
 * <p>
 * JDK: 1.8
 * <p>
 * Created on 2017/6/14.
 * <p>
 * example-projects-parent
 * <p>
 */

public enum Operator implements CommandBuilder {
    /**
     * 加法
     */
    PLUS("+", 1){
        @Override
        public OperatorCommand build(Stack<BigDecimal> data) {
            BigDecimal opData2 = data.pop();
            BigDecimal opData1 = data.pop();
            return new PlusCommand(opData1,opData2);
        }
    },
    /**
     * 减法
     */
    MINUS("-", 1){
        @Override
        public OperatorCommand build(Stack<BigDecimal> data) {
            BigDecimal opData2 = data.pop();
            BigDecimal opData1 = data.pop();
            return new MinusCommand(opData1,opData2);
        }
    },
    /**
     * 求余
     */
    MOD("%", 2){
        @Override
        public OperatorCommand build(Stack<BigDecimal> data) {
            BigDecimal opData2 = data.pop();
            BigDecimal opData1 = data.pop();
            return new ModCommand(opData1,opData2);
        }
    },
    /**
     * 乘法
     */
    MULTIP("*", 2){
        @Override
        public OperatorCommand build(Stack<BigDecimal> data) {
            BigDecimal opData2 = data.pop();
            BigDecimal opData1 = data.pop();
            return new MultipCommand(opData1,opData2);
        }
    },
    /**
     * 除法
     */
    DIVISION("/", 2){
        @Override
        public OperatorCommand build(Stack<BigDecimal> data) {
            BigDecimal opData2 = data.pop();
            BigDecimal opData1 = data.pop();
            return new DivisionCommand(opData1,opData2);
        }
    },
    /**
     * 次方
     */
    POW("^", 3){
        @Override
        public OperatorCommand build(Stack<BigDecimal> data) {
            BigDecimal opData2 = data.pop();
            BigDecimal opData1 = data.pop();
            return new PowCommand(opData1,opData2);
        }
    };

    /**
     * 操作符
     */
    private String op;
    /**
     * 优先级
     */
    private int priority;

    Operator(String op, int priority) {
        this.op = op;
        this.priority = priority;
    }

    public static Operator getInstantByOp (String op){
        if(op == null) {
            throw new IllegalArgumentException("操作符为空");
        }
        for(Operator operator: Operator.values()){
            if(op.equals(operator.op)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("没有找到对应的操作");
    }

    public static boolean isSupport(String op){
        if(op == null) {
            throw new IllegalArgumentException("操作符为空");
        }
        for(Operator operator: Operator.values()){
            if(op.equals(operator.op)) {
                return true;
            }
        }
        return false;
    }

    public String getOp() {
        return op;
    }

    public int getPriority() {
        return priority;
    }

}


package stack.expression.command;

import java.math.BigDecimal;
import java.util.Stack;

public interface CommandBuilder {
    /**
     * 根据操作数栈构造操作符命令对象
     * @param data 操作数栈
     * @return 操作符命令对象
     */
    OperatorCommand build(Stack<BigDecimal> data);
}

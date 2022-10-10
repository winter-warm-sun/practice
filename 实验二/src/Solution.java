import java.util.Stack;

/**
 * 使用 Solution.evalRPN(tokens) 求得表达式的结果
 *
 * tokens:后缀表达式（字符串数组）
 */
public class Solution {
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(String s:tokens) {
            if(!isOperation(s)) {
                //不是操作符就进行入栈
                stack.push(Integer.parseInt(s));
            }else {
                int num2=stack.pop();
                int num1=stack.pop();
                switch (s) {
                    case "+":
                        stack.push(num1+num2);
                        break;
                    case "-":
                        stack.push(num1-num2);
                        break;
                    case "*":
                        stack.push(num1*num2);
                        break;
                    case "/":
                        stack.push(num1/num2);
                        break;
                }
            }
        }
        return stack.peek();
    }
    //判断是否为操作符
    private static boolean isOperation(String s) {
        if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/"))
            return true;
        return false;
    }
}
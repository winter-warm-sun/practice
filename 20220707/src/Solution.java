import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(String s:tokens) {
            if(!isOperation(s)) {
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
    private boolean isOperation(String s) {
        if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/"))
        return true;
        return false;
    }
}
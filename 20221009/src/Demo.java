import java.util.*;

/**使用方法:Stack<String> result = function.switch(expression)
 * expression:中缀表达式，字符串类型*/
class function {

    /** 静态方法：以栈的形式返回后缀表达式 */
    public static Stack<String> switchList(String infixExpression) {
        // 字符串分割成数组
        // 遍历数组代替扫描表达式
        List<String> list = function.toList(infixExpression);
        System.out.println(list);
        Stack<String> numStack = new Stack<>();
        Stack<String> operateStack = new Stack<>();

        for (int i = 0; i < list.size();) {
            // 逐个取出列表中的元素
            String element = list.get(i);
            if (element.charAt(0) >= '0' && element.charAt(0) <= '9') {// 如果是数字直接压到numStack
                numStack.push(element);
                i++;
            }
            else if (operateStack.isEmpty()) {// 如果栈空直接将元素压入栈
                operateStack.push(element);
                i++;
            }
            else if (element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/")) {// 如果为运算符 则比较优先级
                if (operateStack.peek().equals("(") || operateStack.isEmpty()) {// 如果栈为空或者栈顶元素为左括号 则直接该元素压入operateStack栈中
                    operateStack.push(element);
                    i++;
                }
                // 如果当前运算符高于栈顶运算符 则直接将当前运算符压入operateStack栈中
                else if ((element.equals("*") || element.equals("/")) && ((operateStack.peek().equals("+")) || (operateStack.peek().equals("-")))) {
                    operateStack.push(element);
                    i++;
                }
                else { // 否则 当前运算符优先级不大于栈顶运算符的优先级 则将栈顶运算符弹出压入到numStack栈中 再将当前运算符与下一个栈顶元素比较优先级 则i不递增
                    numStack.push(operateStack.pop());
                }
            }
            else if (element.equals("(")){  // 如果当前元素为左括号 则直接压入operateStack栈中
                operateStack.push(element);
                i++;
            }
            else if (element.equals(")")) {  // 如果当前元素为右括号 依次将栈顶元素弹出压到numStack栈中 直到弹出元素为左括号 然后丢弃这一对括号
                while (!(operateStack.peek().equals("("))) {
                    numStack.push(operateStack.pop());
                }
                operateStack.pop();
                i++;
            }
        }

        // 当中缀表达式从左到右扫描完后 执行一下步骤
        // 把operateStack中剩余的元素弹出压到numStack栈中
        while (!operateStack.isEmpty()) {
            numStack.push(operateStack.pop());
        }

        // 再将numStack的元素顺序反转 即为后缀表达式
        while (!numStack.isEmpty()) {
            operateStack.push(numStack.pop());
        }

        return operateStack;
    }

    /** 静态方法：将字符串分割后返回List */
    public static List<String> toList(String str) {
        List<String> list = new ArrayList<>();

        String s = "";
        for (int i = 0; i < str.length(); i++) {
            // 如果是运算符 直接添加到列表中
            if ((str.charAt(i) >=  '9' || str.charAt(i) <= '0') && str.charAt(i) != ' ')
                list.add(str.charAt(i) + "");
            else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {  // 如果该字符是数字 需要处理是不是多位数
                s += str.charAt(i);
                // 如果字符串没有到达边界 且下一个字符也是数字就进行拼接
                if (i + 1 >= str.length()  || str.charAt(i + 1) < '0' || str.charAt(i + 1) > '9') {
                    list.add(s);
                    s = "";
                }
            }
        }

        return list;
    }
}

public class Demo {
    public static void main(String[] args) {
        System.out.println(function.switchList("4+(13/5)"));
    }
}

package ru.itis.inform;

import java.util.Stack;

public class RPN {
    public String calculate(String input){
        Stack<Integer> stack = new Stack<Integer>();
        char inputArr[] = input.toCharArray();
        int i = 0;

        while(i < inputArr.length) {
            int ch = (int)inputArr[i] - 48;
            if (inputArr[i] == ' '){
                i++;
            }
            else if(ch >= 0 && ch <= 9) {
                i++;
                while(inputArr[i] != ' '){
                    ch = 10 * ch + ((int)inputArr[i] - 48);
                    i++;
                }
                stack.add(ch);
            }
            else if(inputArr[i] == '+'){
                int a1 = stack.pop();
                int a2 = stack.pop();
                stack.add(a2 + a1);
                i++;
            }
            else if(inputArr[i] == '-'){
                int a1 = stack.pop();
                int a2 = stack.pop();
                stack.add(a2 - a1);
                i++;
            }
            else if(inputArr[i] == '*'){
                int a1 = stack.pop();
                int a2 = stack.pop();
                stack.add(a2 * a1);
                i++;
            }
            else if(inputArr[i] == '/'){
                int a1 = stack.pop();
                int a2 = stack.pop();
                stack.add(a2 / a1);
                i++;
            }
        }
        return stack.pop().toString();
    }
}
package com.killian.SpringBoot.ExamApp.services;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.springframework.stereotype.Service;

@Service
public class FormulaService {
    public Map<String, Double> myMap = new HashMap<>();

    public boolean isOperator(char c) {
        return (!(c >= 'a' && c <= 'z') &&
                !(c >= '0' && c <= '9') &&
                !(c >= 'A' && c <= 'Z') &&
                (c != '.'));
    }

    public boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    public boolean isSymbol(char c) {
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }

    public int getPriority(char C) {
        if (C == '-' || C == '+')
            return 1;
        else if (C == '*' || C == '/')
            return 2;
        else if (C == '^')
            return 3;
        return 0;
    }

    public String getNumber(String text, int pos) {
        String value = "";
        for (int i = pos; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isDigit(c) || c == '.')
                value = value + c;
            else
                break;
        }
        return value;
    }

    public String getNumberBackward(String text, int pos) {
        String value = "";
        for (int i = pos; i >= 0; i--) {
            char c = text.charAt(i);
            if (isDigit(c) || c == '.')
                value = c + value;
            else
                break;
        }
        return value;
    }

    public String infixToPrefix(String infix) {

        Stack<Character> operators = new Stack<Character>();

        Stack<String> operands = new Stack<String>();

        for (int i = 0; i < infix.length(); i++) {

            if (infix.charAt(i) == ' ')
                continue;

            if (infix.charAt(i) == '(') {
                operators.push(infix.charAt(i));
            }

            else if (infix.charAt(i) == ')') {
                while (!operators.empty() &&
                        operators.peek() != '(') {

                    String op1 = operands.peek();
                    operands.pop();

                    String op2 = operands.peek();
                    operands.pop();

                    char op = operators.peek();
                    operators.pop();

                    String tmp = op + op2 + op1;
                    operands.push(tmp);
                }

                operators.pop();
            }

            else if (!isOperator(infix.charAt(i))) {
                if (isDigit(infix.charAt(i))) {
                    String value = getNumber(infix, i);
                    operands.push(value);
                    i += value.length() - 1;
                } else
                    operands.push(infix.charAt(i) + "");
            }

            else {
                while (!operators.empty() &&
                        getPriority(infix.charAt(i)) <= getPriority(operators.peek())) {

                    String op1 = operands.peek();
                    operands.pop();

                    String op2 = operands.peek();
                    operands.pop();

                    char op = operators.peek();
                    operators.pop();

                    String tmp = op + " " + op2 + " " + op1;
                    operands.push(tmp);
                }

                operators.push(infix.charAt(i));
            }
        }

        while (!operators.empty()) {
            String op1 = operands.peek();
            operands.pop();

            String op2 = operands.peek();
            operands.pop();

            char op = operators.peek();
            operators.pop();

            String tmp = op + " " + op2 + " " + op1;
            operands.push(tmp);
        }

        return operands.peek();
    }

    public double myRounding(double db) {        
        DecimalFormat df = new DecimalFormat("#.###");
        double rdb = Double.valueOf(df.format(db));
        return rdb;
        // BigDecimal bd = new BigDecimal(Double.toString(db));
        // bd = bd.setScale(3, RoundingMode.HALF_UP);
        // return bd.doubleValue();
    }

    public double evaluatePrefix(String exprsn) {
        Stack<Double> Stack = new Stack<Double>();

        for (int j = exprsn.length() - 1; j >= 0; j--) {

            if (exprsn.charAt(j) == ' ')
                continue;

            if (isDigit(exprsn.charAt(j))) {
                String value = getNumberBackward(exprsn, j);
                Stack.push(myRounding(Double.parseDouble(value)));
                j = j - value.length() + 1;
            }

            else if (isSymbol(exprsn.charAt(j))) {
                Stack.push(myRounding(myMap.get(exprsn.charAt(j) + "")));
            }

            else {

                double o1 = Stack.peek();
                Stack.pop();
                double o2 = Stack.peek();
                Stack.pop();

                switch (exprsn.charAt(j)) {
                    case '+':
                        Stack.push(myRounding(o1 + o2));
                        break;
                    case '-':
                        Stack.push(myRounding(o1 - o2));
                        break;
                    case '*':
                        Stack.push(myRounding(o1 * o2));
                        break;
                    case '/':
                        Stack.push(myRounding(o1 / o2));
                        break;
                    case '^':
                        Stack.push(myRounding(Math.pow(o1, o2)));
                        break;
                }
            }
        }
        return myRounding(Stack.peek());
    }

    public double calculate(String formula) {
        return evaluatePrefix(infixToPrefix(formula));
    }
}

package com.asid.algorithms.calculator;

import com.asid.foundation.datastructure.list.CustomArrayList;
import com.asid.foundation.datatype.CustomStack;

import java.util.List;
import java.util.Scanner;

/**
 * Infix notation calculator
 */
public class InfixNotationCalculatorImpl implements InfixNotationCalculator {
    @Override
    public double calculate(String expression) {
         /* (TODO Lab No. 2) Please introduce a sensible implementation */
        return calculatePostfixTokens(toPostfixTokens(toInfixTokens(expression)));
    }

    private List<String> toInfixTokens(String expression){
        String infixWithSpaces = "";
        for(int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')'){
                infixWithSpaces += " " + ch + " ";
            }
            else{
                infixWithSpaces += ch;
            }
        }
        Scanner scanner = new Scanner(infixWithSpaces);
        List<String> tokens = new CustomArrayList<>();
        while (scanner.hasNext()){
            tokens.add(scanner.next());
        }
        return tokens;
    }

    private List<String> toPostfixTokens(List<String> infixTokens){
        CustomStack<String> stack = new CustomStack<>(new CustomArrayList<>());
        List<String> postfixTokens = new CustomArrayList<>();
        String token;
        for(int i = 0; i < infixTokens.size(); i++){
            token = infixTokens.get(i);
            String stackTop;

            if(isOperator(token)){
                if(stack.isEmpty()){
                    stack.push(token);
                } else{
                    stackTop = stack.pop();
                    if(stackTop.equals("(")){
                        stack.push(stackTop);
                        stack.push(token);
                    } else{
                        //tu można zmienić kierunek ewaluacji
                        postfixTokens.add(stackTop);
                        stack.push(token);
                    }
                }
            }
            else if(token.equals("(")){
                stack.push(token);
            } else if (token.equals(")")) {
                stackTop = stack.pop();
                while(!stackTop.equals("(")){
                    postfixTokens.add(stackTop);
                    stackTop = stack.pop();
                }
            }else {
                //liczba
                postfixTokens.add(token);
            }
        }
        while (!stack.isEmpty()){
            postfixTokens.add(stack.pop());
        }
        return postfixTokens;
    }

    private boolean isOperator(String token){
        return (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"));
    }

    private double calculatePostfixTokens(List<String> postfixTokens){
        CustomStack<Double> stack = new CustomStack<>(new CustomArrayList<>());
        String token;
        for(int i = 0; i < postfixTokens.size(); i++){
            token = postfixTokens.get(i);

            if(isOperator(token)){
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = 0;

                if(token.equals("*")){
                    result = operand1 * operand2;
                }
                else if(token.equals("+")){
                    result = operand1 + operand2;
                }
                else if(token.equals("/")){
                    result = operand1 / operand2;
                }
                else if(token.equals("-")){
                    result = operand1 - operand2;
                }

                stack.push(result);
            }
            else {
                //liczba
                Double newDouble = Double.parseDouble(token);
                stack.push(newDouble);
            }
        }

        return stack.pop();
    }
}

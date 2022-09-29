package main;

import java.util.*;


class RPNCalc {

    public static  List<Token> scanning (String expr) throws Exception {
        List<Token> list_tokens = new ArrayList<>();
        int start = 0;

        do
        {
            int space = expr.substring(start).indexOf(' ');
            int end = space == -1 ? expr.length() : start + space;
            String current = expr.substring(start,end);//current number or operator
            if(isNumeric(current)){
                list_tokens.add(new Token(TokenType.NUM, current));

            } else if ("+".indexOf(current.charAt(0)) != -1) {
                list_tokens.add(new Token(TokenType.PLUS, current));

            }else if ("-".indexOf(current.charAt(0)) != -1) {
                list_tokens.add(new Token(TokenType.MINUS, current));

            }else if ("*".indexOf(current.charAt(0)) != -1) {
                list_tokens.add(new Token(TokenType.STAR, current));

            }else if ("/".indexOf(current.charAt(0)) != -1) {
                list_tokens.add(new Token(TokenType.SLASH, current));

            }else{
                throw new Exception( "Unexpected character: " + current);
            }
            start = end + 1;//start over at index after the space
        }while(start < expr.length());

        return list_tokens;
    }
    public static double evaluate(String expr) {
        if (expr.isEmpty()) return 0;

        int start = 0;

        Stack stack = new Stack();

        do
        {
            int space = expr.substring(start).indexOf(' ');
            int end = space == -1 ? expr.length() : start + space;
            String current = expr.substring(start,end);//current number or operator

            if("+-*/".indexOf(current.charAt(0)) != -1)//check if current is operator
            {//pop 2 and apply operation
                Double a = (Double) stack.pop();
                Double b =(Double) stack.pop();
                stack.push(operate(current.charAt(0),b,a));
            }
            else
            {
                stack.push(Double.parseDouble(current));
            }
            start = end + 1;//start over at index after the space
        }while(start < expr.length());

        Double result = (Double) stack.pop();

        while(!stack.isEmpty())//stack non-empty -> return greatest val
        {
            Double current = (Double) stack.pop();
            result = current > result ? current : result;
        }

        return result;
    }
    //return a <operand> b
    public static double operate(char operand,double a, double b){

        Hashtable<Character,Double> opHash = new Hashtable<Character,Double>();
        opHash.put('+',a + b);
        opHash.put('-',a - b);
        opHash.put('*',a * b);
        opHash.put('/',a / b);

        return opHash.get(operand);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
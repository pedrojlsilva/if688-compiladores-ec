package main;

import java.io.*;
import java.util.Scanner;



public class Main {



    public static void main(String[] args) throws IOException {
        System.out.println("RPN Calc");
        System.out.println("Type '.exit' at any time to quit");
        String inp = "";
        Scanner inpScan = new Scanner(System.in);


        File myObj = new File("Calc1.stk");
        StringBuilder resultStringBuilder = new StringBuilder();
        String finalString=new String();

        try{
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                resultStringBuilder.append(data).append(" ");
            }
            finalString=resultStringBuilder.toString();
        }catch(IOException error){
            System.out.println(error.toString());

        }




            System.out.println("Expressão RPN de Entrada");
            System.out.print("-> " + finalString+" = ");


            try{
                System.out.println(RPNCalc.evaluate(finalString));
            } catch(Exception e)
            {
                System.out.println("Expressão invalida.");
            }


    }


}
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

    class Main {
    static char operation;
    static int result;
    static int number1;
    static int number2;

    public static void main(String[] args) {
        Main.calc("input");
    }

    public static int calc(String input){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input:");
        input = scanner.nextLine();
        char[] under_char=new char[10];
        int k = 0;
        int q = 0;
        for (int i = 0; i < input.length();++i){
            under_char[i] = input.charAt(i);
            if(under_char[i] == '+'){
                operation = '+';
                k=k+1;
            }
            if(under_char[i] == '*'){
                operation = '*';
                k=k+1;
            }
            if(under_char[i] == '-'){
                operation = '-';
                k=k+1;
            }
            if(under_char[i] == '/'){
                operation = '/';
                k=k+1;
            }
            if(under_char[i] == '.'){
                q=q+1;
            }
            if(under_char[i] == ','){
                q=q+1;
            }
        }
        if (k>1){
            System.out.println("Output:");
            System.out.println("throws Exception // т.к. формат математической операциии не удовлетворяет заданию - два операнда и один оператор (+,-,/,*)");
            System.exit(0);
        } else if (k==0) {
            System.out.println("Output:");
            System.out.println("throws Exception // т.к. строка не является математической операцией");
            System.exit(0);
        } else if (q>0) {
            System.out.println("Output:");
            System.out.println("throws Exception // т.к. введены не целые числа");
            System.exit(0);
        }
        String under_charString = String.valueOf(under_char);
        String[] array = under_charString.split("[+-/*]");
        String num1 = array[0];
        String op = array[1];
        String num2 = op.trim();
        String[] arab = {"1","2","3","4","5","6","7","8","9","10"};
        String[] rome = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        String s1 = num1;
        String s2 = num2;
        int a = 0;
        int b = 0;
        int ar = 0;
        for(String string:arab){
            if (s1.equals(string)){a=1;}
            if (s2.equals(string)){b=1;}
        }
        ar = a+b;
        int c = 0;
        int d = 0;
        int ro = 0;
        for(String string:rome){
            if (s1.equals(string)){c=1;}
            if (s2.equals(string)){d=1;}
        }
        ro = c+d;

        if (ar == 2) {
            Main.calcArab(num1, num2, operation);
            }
        else {
            if (ro == 2) {
                Main.calcRoman(num1, num2, operation);
            } else if ((ar ==1)&(ro ==1)){
                System.out.println("Output:");
                System.out.println("throws Exception // т.к. используются одновременно разные системы счисления");
                System.exit(0);
            } else {
                System.out.println("Output:");
                System.out.println("throws Exception // введены некорректные данные");
                System.exit(0);
            }
        }
        return (result);
    }
    static int calcRoman(String num1, String num2, char operation) {
        number1 = RomanToInteger.romanToInteger(num1);
        number2 = RomanToInteger.romanToInteger(num2);
        int result = 0;
        switch (operation) {
            case '*':
                result = number1 * number2;
                break;
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '/':
                try {
                    result = number1 / number2;
                }
                catch (InputMismatchException | ArithmeticException var5) {
                    System.out.println("Метод выбрасывает исключение");
                }
        }
        if (result<=0){
            System.out.println("Output:");
            System.out.println("throws Exception // т.к. в римской системе нет отрицательных чисел");
            System.exit(0);
        }
        System.out.println("Output:");
        String resultRoman = IntegerToRoman.convertRoman(result);
            System.out.println(resultRoman);
        return result;
    }
        static int calcArab(String num1, String num2, char operation) {
            number1 = Integer.parseInt(num1);
            number2 = Integer.parseInt(num2);
            int result = 0;
            switch (operation) {
                case '*':
                    result = number1 * number2;
                    break;

                case '+':
                    result = number1 + number2;
                    break;
                case '-':
                    result = number1 - number2;
                    break;
                case '/':
                    try {
                        result = number1 / number2;
                    } catch (InputMismatchException | ArithmeticException var5) {
                        System.out.println("Метод выбрасывает исключение");
                    }
            }
            System.out.println("Output:");
            System.out.println(result);
            return result;
        }
    }
class IntegerToRoman {
    static String convertRoman(int num){
        String [] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String [] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX","XC"};
        String [] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC","CM"};
        String [] thousands = { "", "M", "MM", "MMM"};
        return thousands[num/1000] + hundreds[(num%1000/100)]+tens[(num%100/10)]+ones[num%10];
    }
}
class RomanToInteger {
    static int romanToInteger(String val) {
        Map<Character, Integer> symbolMap = new HashMap<>();
        symbolMap.put('I', 1);
        symbolMap.put('V', 5);
        symbolMap.put('X', 10);
        symbolMap.put('L', 50);
        symbolMap.put('C', 100);
        symbolMap.put('D', 500);
        symbolMap.put('M', 1000);
        int output = 0;
        int strLen = val.length();
        for (int i = 0; i < strLen; i++) {
            if ((i + 1) < (strLen) && symbolMap.get(val.charAt(i)) >= symbolMap.get(val.charAt(i + 1))) {
                output = output + symbolMap.get(val.charAt(i));
            } else if (((i + 1) < (strLen) && symbolMap.get(val.charAt(i)) < symbolMap.get(val.charAt(i + 1)))) {
                output = output - symbolMap.get(val.charAt(i));
            } else {
                output = output + symbolMap.get(val.charAt(i));
            }
        }
        return output;
    }
}








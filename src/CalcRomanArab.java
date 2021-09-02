import java.util.Scanner;

public class CalcRomanArab {
    static Scanner scan = new Scanner(System.in);
    static int num1, num2;
    static char arithmeticOperation;
    static String operands[];
    static String leftPartPre;
    static String rightPartPre;
    static int result;
    static int numRoman1;
    static int numRoman2;
    static int resultRomanInt;


    public static void main(String[] args) throws Exception{
        System.out.println("\nКонсольный калькулятор для арабсикх и римских цифр.\n\n" +
                "Возможные арифметические операции: сложение, вычитание, умножение и деление.\n" +
                "Калькулятор работает только с одним видом цифр. Либо только арабские либо наоборот.\n" +
                "Для получения результата введите цифру, знак арифметический операции и вторую цифру.\n" +
                "Затем нажмите Enter."
        );
        String InputExpression = scan.nextLine();

        if (InputExpression.contains("+") == true) {
            arithmeticOperation = '+';
        } else if (InputExpression.contains("-") == true) {
            arithmeticOperation = '-';
        } else if (InputExpression.contains("*") == true) {
            arithmeticOperation = '*';
        } else if (InputExpression.contains("/") == true) {
            arithmeticOperation = '/';
        } else {
            throw new NullPointerException("Нет знака математической операции. ");
        }



        String operands[] = InputExpression.split("[+-/*]");
        String leftPartPre = operands[0];
        String rightPartPre = operands[1];


//        try {
//            String leftPartPre = operands[0];
//            String rightPartPre = operands[1];
//        }catch (NullPointerException e){
//            System.out.println("Вы ввели выражение не соответствующее условиям. " + e);
//        }





        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
//      Проверка, есть ли в частях выражения римские числа
        try {
            for (int i = 0; i < roman.length; i++) {
                if (roman[i].equals(leftPartPre)) {
                    numRoman1 = i;
                }
            }
            for (int i = 0; i < roman.length; i++) {
                if (roman[i].equals(rightPartPre)) {
                    numRoman2 = i;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Вы ввели выражение не соответствующее условиям." + e);
        }

        //  Если они есть и полученные значения не ноль, т.е. нашли соответствия в массиве,
        //  выполняем операцию с римскими числами.

        if ((numRoman1 !=0)&(numRoman2!=0)) {
            if ((numRoman1<numRoman2)&arithmeticOperation=='-') {
                throw new ArithmeticException("Римские числа не бывают отрицательными.");
            }
            else {
                resultRomanInt = calculated(numRoman1, numRoman2, arithmeticOperation);
                String resultRoman = roman[resultRomanInt];
                System.out.println(resultRoman);
            }
        }
        //  Если римские числа после проверки оказались ноль, значит соответствий в массиве нет.
        //  И значит это или число арабское, или вообще не число.
        //  Выполняем блок с арабскими числами.
        else {
            String regex = "[0-9]+";

            if ((leftPartPre.matches(regex) == true)&(rightPartPre.matches(regex) == true)) {

                num1 = Integer.parseInt(leftPartPre);
                num2 = Integer.parseInt(rightPartPre);

                try {
                    if (num1 < 0) {
                        throw new MyExceptionOverBorderN1();
                    } else if (num1 > 10) {
                        throw new MyExceptionOverBorderN1();
                    } else if (num2 < 0) {
                        throw new MyExceptionOverBorderN1();
                    } else if (num2 > 10) {
                        throw new MyExceptionOverBorderN1();
                    }
                } catch (MyExceptionOverBorderN1 s) {
                    System.out.println("Exception: " + s.toString());
                    return;
                }

                if ((num2 == 0)&(arithmeticOperation=='/')) {
                        throw new IllegalArgumentException("Нельзя делить на ноль 0");
                        }else {
                            result = calculated(num1, num2, arithmeticOperation);
                            System.out.println(result);
                        }
                    }
            else {
                throw new MyExceptionAlfa();
            }
        }
    }

    public static int calculated(int num1, int num2, char arithmeticOperation) {

        switch (arithmeticOperation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
        }
        return result;
    }


}

class MyExceptionOverBorderN1 extends Exception {
    public String toString() {
        return "Вы ввели числа больше 10 или меньше нуля";
    }
}

class MyExceptionAlfa extends Exception {
    public String toString() {
        return "Вы ввели не число.";
    }
}

class MyExceptionNoOperation extends Exception {
    public String toString() {
        return "Отсутствует знак математической операции";
    }
}
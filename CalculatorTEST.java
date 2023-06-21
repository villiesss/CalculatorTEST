package arabian;

import java.util.Scanner;

public class CalculatorArabian {
    public static void main(String[] args) {

        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        //Определяем арифметическое действие:
        int actionIndex=-1;
       int k = 0;

        for (int i = 0; i < 3; i++) {
            if(exp.contains(actions[i])){
                actionIndex = i;
               k++;
            }
        }
        //Если не нашли арифметического действия или их больше 1, завершаем программу
        if(actionIndex==-1 || k>1){
            System.out.println("Некорректное выражение");
            return;
        }
        //Делим строчку по найденному арифметическому знаку

        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //если римские, то конвертируем их в арабские

                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            }else{
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            //выполняем с числами арифметическое действие
            int result;
            switch (actions[actionIndex]){
                case "+":
                    result = a+b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "*":
                    result = a*b;
                    break;
                default:
                    result = a/b;
                    break;
            }
            if(isRoman){
                //если числа были римские, возвращаем результат в римском числе
                System.out.println("Ответ: " + converter.intToRoman(result));
            }
            else{
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println("Ответ: " + result);
            }
        }else{
            System.out.println("Числа должны быть в одном формате");
        }


    }
}
package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Результат 2.1: " + repeat21(in));
        System.out.println("Результат 2.2: " + differenceMaxMin22(in));
        System.out.println("Результат 2.3: " + isAvgWhole23(in));
        System.out.println("Результат 2.4: " + cumulativeSum24(in));
        System.out.println("Результат 2.5: " + getDecimalPlaces25(in));
        System.out.println("Результат 2.6: " + Fibonacci26(in));
        System.out.println("Результат 2.7: " + isValid27(in));
        System.out.println("Результат 2.8: " + isStrangePair28(in));
        System.out.println("Результат 2.9:\n\tПрефикс\tСуффикс\n\t " + Arrays.toString(isPrefixOrSuffix29(in)));
        System.out.println("Результат 2.10: " + boxSeq210(in));
        in.close();
    }

    public static StringBuilder repeat21(Scanner scanner) {
        System.out.println("\n----\nЗадача 2.1\nВведите строку для изменения и количество повторений");
        String inputString = scanner.nextLine();
        int multiplier = scanner.nextInt();
        //Используется StringBuilder во избежание создания множественных объектов типа String
        StringBuilder result = new StringBuilder();
        char[] stringArray = inputString.toCharArray();
        for (int counter = 0; counter < inputString.length(); counter++)
            result.append(String.valueOf(stringArray[counter]).repeat(Math.max(0, multiplier)));
        return result;
        //Возвращается значение StringBuilder
    }

    public static float differenceMaxMin22(Scanner scanner) {
        System.out.println("\n----\nЗадача 2.2\nВведите размер массива и значения его элементов");
        int size = scanner.nextInt();
        float[] array = new float[size];
        for (int counter = 0; counter < size; counter++)
            array[counter] = scanner.nextFloat();
        Arrays.sort(array);
        return array[array.length - 1] - array[0];
        //Возвращается результат вычисления с целью уменьшения списка временных переменных
    }

    public static boolean isAvgWhole23(Scanner scanner) {
        System.out.println("\nЗадача 2.3\nВведите размер массива и значения его элементов");
        int size = scanner.nextInt();
        float[] array = new float[size]; //Создание массива пользовательского размера
        for (int counter = 0; counter < size; counter++)
            array[counter] = scanner.nextFloat();
        float sum = 0;
        for (float element : array)
            sum += element;
        double average = sum / array.length;
        return average % 1 == 0;
        //Возвращается логическое значение выражения с целью уменьшения списка временных переменных
    }

    public static String cumulativeSum24(Scanner scanner) {
        System.out.println("\nЗадача 2.4\nВведите размер массива и значения его элементов");
        int size = scanner.nextInt();
        float[] array = new float[size]; //Создание массива пользовательского размера
        for (int counter = 0; counter < size; counter++)
            array[counter] = scanner.nextFloat();
        float sum = 0;
        for (int counter = 0; counter < size; counter++) {
            sum += array[counter];
            array[counter] = sum;
        }
        return Arrays.toString(array);
        //Возвращается массив, приведенный к строке
    }

    public static int getDecimalPlaces25(Scanner scanner) {
        System.out.println("\nЗадача 2.5\nВведите число для подсчета значащих цифр после запятой");
        double number = scanner.nextDouble();
        String[] splitted = String.valueOf(number).split("\\."); //Разделение строки по точке
        return splitted[1].length();
        //Возвращается результат операции с целью уменьшения списка временных переменных
    }

    public static int Fibonacci26(Scanner scanner) {
        System.out.println("\nЗадача 2.6\nВведите порядковый номер числа Фибоначчи для подсчета самого числа");
        int number = scanner.nextInt();
        int number0 = 1; // Предыдущий член ряда
        int number1 = 1; // Позапрошлый член ряда
        int currentNumber; // Текущий член ряда
        for (int counter = 3; counter <= number; counter++) {
            currentNumber = number0 + number1;
            number1 = number0;
            number0 = currentNumber;
        }
        return number0 + number1;
        //Возвращается результат вычисления с целью уменьшения списка временных переменных
    }

    public static boolean isValid27(Scanner scanner) {
        System.out.println("\nЗадача 2.7\nВведите почтовый индекс для проверки его корректности");
        String indexToCheck = scanner.nextLine();
        return indexToCheck.matches("\\d{5}"); //Проверка строки на присутствие ровно 5 цифр
        //Возвращается логическое значение операции с целью уменьшения списка временных переменных
    }

    public static boolean isStrangePair28(Scanner scanner) {
        System.out.println("\nЗадача 2.8\nВведите две строки для их изучения");
        String string1 = scanner.nextLine();
        String string2 = scanner.nextLine();
        boolean res; //Инициализация переменной для дальнейшего возвращения
        //Сравнение элементов строки, приведенной к массиву символов
        if (string1.charAt(0) == string2.charAt(string2.length() - 1)) {
            res = (string1.charAt(string1.length() - 1) == string2.charAt(0));
        } else {
            res = false;
        }
        return res;
    }

    public static boolean[] isPrefixOrSuffix29(Scanner scanner) {
        //Функции нахождения суффикса и префикса объединены в одну для сохранения общей логики решений0
        System.out.println("\nЗадача 2.9\nВведите строку, префикс и суффикс для их поиска");
        String inputString = scanner.nextLine();
        String prefix = scanner.nextLine();
        String suffix = scanner.nextLine();
        //В массив помещаются напрямую логические значения выражений с целью уменьшения списка временных переменных
        return new boolean[]{inputString.startsWith(prefix), inputString.endsWith(suffix)};
        //Так как в функции объединены две, возвращается массив логических выражений
    }

    public static int boxSeq210(Scanner scanner) {
        System.out.println("\nЗадача 2.10\nВведите номер шага (от нуля и больше)");
        int number = scanner.nextInt();
        int res; //Инициализация переменой для дальнейшего назначения и возвращения
        if (number % 2 == 0) {
            res = number;
        } else {
            res = number - 1 + 3;
        }
        return res;
    }
}

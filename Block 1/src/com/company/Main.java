package com.company;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Результат 1.1: " + remainder11(in));
        System.out.println("Результат 1.2: " + triangleSquare12(in));
        System.out.println("Результат 1.3: " + animals13(in));
        System.out.println("Результат 1.4: " + profitableGamble14(in));
        System.out.println("Результат 1.5: " + operation15(in));
        System.out.println("Результат 1.6: " + ctoa16(in));
        System.out.println("Результат 1.7: " + addUpTo17(in));
        System.out.println("Результат 1.8: " + nextEdge18(in));
        System.out.println("Результат 1.9: " + sumOfCubes19(in));
        System.out.println("Результат 1.10: " + abcmath110(in));
        in.close();
    }

    public static double remainder11(Scanner scanner) {
        System.out.println("----\nЗадача 1.1\nВведите делимое и делитель для поиска остатка от деления");
        float num1 = scanner.nextFloat();
        float num2 = scanner.nextFloat();
        return num1 % num2;
        //Возвращается результат вычисления для уменьшения списка временных переменных
    }

    public static double triangleSquare12(Scanner scanner) {
        System.out.println("\n----\nЗадача 1.2\nВведите длину основания треугольника и его высоту");
        float base = scanner.nextFloat();
        float height = scanner.nextFloat();
        return base * height * 0.5;  //(формула площади треугольника)
        // Возвращается результат вычисления для уменьшения списка временных переменных
    }

    public static int animals13(Scanner scanner) {
        //Задание количества ног у животных
        int chickens = 2;
        int cows = 4;
        int pigs = 4;
        System.out.println("\n----\nЗадача 1.3\nВведите количество кур, коров и свиней для подсчета их ног (серьезно?)");
        int chickenCount = scanner.nextInt();
        int cowCount = scanner.nextInt();
        int pigsCount = scanner.nextInt();
        return chickens * chickenCount + cows * cowCount + pigs * pigsCount;
        //Возвращается результат вычисления для уменьшения списка временных переменных
    }

    public static boolean profitableGamble14(Scanner scanner) {
        System.out.println("\n----\nЗадача 1.4\nВведите значения prob, prize и pay");
        float prob = scanner.nextFloat();
        float prize = scanner.nextFloat();
        float pay = scanner.nextFloat();
        return prob * prize > pay;
        //Возвращается логическое значение выражения для уменьшения списка временных переменных
    }

    public static String operation15(Scanner scanner) {
        System.out.println("\n----\nЗадача 1.5\nВведите два числа для оперирования и результат для сравнения");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();
        String result;  //Задание переменной для дальнейшего назначения значения и вывода
        if (num1 * num2 == num3) {
            result = "Умножение";
        } else if (num1 / num2 == num3) {
            result = "Деление";
        } else if (num1 + num2 == num3) {
            result = "Сложение";
        } else if (num1 - num2 == num3) {
            result = "Вычитание";
        } else {
            result = "Ничего не помогло";
        }
        return result;
    }

    public static int ctoa16(Scanner scanner) {
        System.out.println("\n----\nЗадача 1.6\nВведите символ для получения его ASCII кода");
        return scanner.next().charAt(0);
        //Возвращается нулевой (по заданию - единственный) элемент строки, приведенной к символьному массиву
    }

    public static int addUpTo17(Scanner scanner) {
        System.out.println("\n----\nЗадача 1.6\nВведите натуральное число - последний член последовательного ряда");
        int number = scanner.nextInt();
        return IntStream.range(0, number + 1).sum(); //Sream Api
        //Возвращается результат операции с целью уменьшения списка временных переменных
    }

    public static float nextEdge18(Scanner scanner) {
        System.out.println("\n----\nЗадача 1.8\nВведите длины двух ребер (целые числа)");
        int edge1 = scanner.nextInt();
        int edge2 = scanner.nextInt();
        //Максимально возможное значения ребра - это lim(x), где x->edge1+edge2.
        return (edge1 + edge2 - 1); //Находится целое
        //Возвращется результат вычисления с целью уменьшения списка временных переменных
    }

    public static int sumOfCubes19(Scanner scanner) {
        System.out.println("\n----\nЗадача 1.9\nВведите количество элементов массива, и затем последовательно - эти элементы");
        int size = scanner.nextInt();
        int[] array = new int[size]; //Создание массива пользовательского размера
        int sum = 0; //Задание нулевого значения суммы кубов для дальнейшего увеличения
        for (int counter = 0; counter < size; counter++)
            array[counter] = scanner.nextInt();
        for (int counter = 0; counter < size; counter++)
            sum += Math.pow(array[counter], 3);
        return sum;
    }

    public static boolean abcmath110(Scanner scanner) {
        System.out.println("\n----\nЗадача 1.10\nВведите три числа: a, b, c");
        float num1 = scanner.nextFloat();
        float num2 = scanner.nextFloat();
        float num3 = scanner.nextFloat();
        return (num1 + num2) % num3 == 0;
        //Возвращается логическое значение выражения с целью уменьшения списка временных переменных
    }
}

package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        System.out.println("Результат 3.1: " + solutions31(in));
//        System.out.println("Результат 3.2: " + findZip32(in));
//        System.out.println("Результат 3.3: " + checkPerfect33(in));
//        System.out.println("Результат 3.4: " + flipEndChars34(in));
//        System.out.print("Результат 3.5: " + isValidHexCode35(in));
//        System.out.println("Результат 3.6: " + same36(in));
//        System.out.println("Результат 3.7: " + isKaprekar37(in));
        System.out.println("Результат 3.8: " + longestZero38(in));
//        System.out.println("Результат 3.9: " + nextPrime39(in));
//        System.out.println("Результат 3.10: " + rightTriangle310(in));
        in.close();
    }

    public static String solutions31(Scanner scanner) {
        System.out.println("\nЗадача 3.1\nВведите коэффициенты a, b, c для уравнения ax^2 + bx + c = 0");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        String res = "Уравнение " + a + "x^2+" + b + "x+" + c + "=0 "; //Создание общей части строки для дальнейшего вывода
        double discr = Math.pow(b, 2) - 4 * a * c; //Нахождение дискриминанта уравнения
        double mean1;
        double mean2;
        //Нахождение корней уравнения в зависимости от значения дискриминанта
        if (discr > 0) {
            System.out.println(discr);
            mean1 = (-b + Math.sqrt(discr)) / (2 * a);
            mean2 = (-b - Math.sqrt(discr) / (2 * a));
            res += "имеет два корня: " + mean1 + " и " + mean2;
        } else {
            if (discr == 0) {
                mean1 = (double) -b / (2 * a);
                res += "имеет один корень: " + mean1;
            } else {
                res += "не имеет корней";
            }
        }
        return res;
    }

    public static int findZip32(Scanner scanner) {
        System.out.println("\nЗадача 3.2\nВведите строку для поиска \"zip\" в ней");
        String inputString = scanner.nextLine();
        int index = -1; //Отрицательное значение для дальнейшего сравнения с индексом первого вхождения
        int first = inputString.indexOf("zip");
        int second = inputString.indexOf("zip", first + 1); //Нахождение второго вхождения
        if (second > first) { //Если есть два вхождения
            index = second;
        }
        return index + 1;
        //Увеличение на один возвращаемого значения, т.к. индексирование с нуля
    }

    public static boolean checkPerfect33(Scanner scanner) {
        System.out.println("\nЗадача 3.3\nВведите число для проверки на совершенность");
        int number = scanner.nextInt();
        int sum = 0;
        boolean res = false; //Задания стандартно ложного значения переменной
        for (int counter = 1; counter <= number - 2; counter++) {
            if (number % counter == 0) {
                sum += counter;
            }
            if (sum == number)
                res = true;
        }
        return res;
    }

    public static String flipEndChars34(Scanner scanner) {
        System.out.println("\nЗадача 3.4\nВведите исходную строку");
        String inputString = scanner.nextLine();
        String result = null;
        char[] stringArray = inputString.toCharArray(); //Приведение строки к массиву символов для дальнейшего анализа
        //Проверка на выполнение условий задачи
        if (inputString.length() > 2 && stringArray[0] != stringArray[inputString.length() - 1]) {
            result = stringArray[inputString.length() - 1] +
                    inputString.substring(1, inputString.length() - 1)
                    + stringArray[0]; //Преобразование строки к требуемому виду
        } else if (inputString.length() <= 2) {
            result = "Длина строки не превышает двух символов.";
        } else if (stringArray[0] == stringArray[inputString.length() - 1]) {
            result = "Символы в начале и конце строки совпадают";
        }
        return result;
    }

    public static boolean isValidHexCode35(Scanner scanner) {
        System.out.println("\nЗадача 3.5\nВведите строку для проверки на корректность шестнадцатеричного кода");
        String inputString = scanner.nextLine();
        return inputString.matches("^#[a-zA-Z0-9]{6}$");
        //Возвращается логическое значение выражения с целью уменьшения списка временных переменных
    }

    public static boolean same36(Scanner scanner) {
        int count1 = 0;
        int count2 = 0;
        System.out.println("\nЗадача 3.6");
        System.out.println("Введите количество элементов первого массива и эти элементы");
        int size1 = scanner.nextInt();
        int[] array1 = new int[size1]; //Создание массива пользовательского размера
        for (int pos = 0; pos < size1; pos++) {
            array1[pos] = scanner.nextInt(); //Заполнение массива
        }
        System.out.println("Введите количество элементов второго массива и эти элементы");
        int size2 = scanner.nextInt();
        int[] array2 = new int[size2];
        for (int pos = 0; pos < size2; pos++) {
            array2[pos] = scanner.nextInt();
        }
        for (int pos = 0; pos < size1; pos++) {
            count1++; //Увеличение счетчика на каждом элементе
            for (int pos1 = 0; pos1 < pos; pos1++) {
                if (array1[pos] == array1[pos1]) {
                    count1--; //Уменьшение счетчика на неуникальном элементе
                    break;
                }
            }
        }
        for (int pos = 0; pos < size2; pos++) {
            count2++;
            for (int pos1 = 0; pos1 < pos; pos1++) {
                if (array2[pos] == array2[pos1]) {
                    count2--;
                    break;
                }
            }
        }
        return count1 == count2;
        //Возвращается логическое значение выражения с целью уменьшения списка временных переменных
    }

    public static boolean isKaprekar37(Scanner scanner) {
        System.out.println("\nЗадача 3.7\nВведите число");
        int number = scanner.nextInt();
        boolean res;
        int countDigits = 1;
        double square = Math.pow(number, 2);
        int part1;
        int part2;
        if (Math.log10(square) < 1) {
            res = square == number;
        } else {
            double temp = square;
            while (temp > 10) {
                temp /= 10;
                countDigits++;
            }
            if (countDigits % 2 == 0) {
                part1 = (int) (square / Math.pow(10, countDigits / 2));
                part2 = (int) (square % Math.pow(10, countDigits / 2));
            } else {
                part1 = (int) (square / Math.pow(10, (countDigits + 1) / 2));
                part2 = (int) (square % Math.pow(10, (countDigits + 1) / 2));
            }
            res = part1 + part2 == number;
        }
        return res;
    }

    public static String longestZero38(Scanner scanner) {
        System.out.println("\nЗадача 3.8\nВведите строку для поиска длиннейшей последовательности нулей");
        String inputString = scanner.nextLine();
        int count = 0;
        int bestCount = 0;
        StringBuilder res = new StringBuilder();
        char[] charArray = inputString.toCharArray();
        for (int counter = 0; counter < charArray.length; counter++){
            if (charArray[counter] == '0'){
                count++;
            }
            if (counter + 1 == charArray.length || charArray[counter] != '0'){
                if (count > bestCount){
                    bestCount = count;
                }
                count = 0;
            }
        }
        if (bestCount > 0){
            res.append("0".repeat(bestCount));
        } else {
            res.append("Не встречено нулей в строке");
        }
        return res.toString();
    }

    public static int nextPrime39(Scanner scanner) {
        System.out.println("\nЗадача 3.9\nВведите число для проверки на простоту");
        int res = 0;
        int number = scanner.nextInt();
        boolean checkPrime = false;
        if (number <= 2) { //Проверка на корректность введенных данных
            res = number; //Число является простым
        } else {
            for (int i = 3; i < Math.sqrt(number); ) { //Проверка числа на простоту
                if (number % i == 0) { //Если есть делитель нацело
                    break;
                } else {
                    i++;
                }
            }
            while (!checkPrime) {
                //Проверка до корня из числа с целью оптимизации быстродействия
                for (int i = 2; i < Math.sqrt(number) + 1; i++) {
                    if (number % i == 0) { //В случае наличия делителя помимо 1 и самого числа
                        number++;
                        break;
                    } else if (i > Math.sqrt(number)) { //В случае отсутствия делителей помимо 1 и самого числа
                        checkPrime = true; //Выход из цикла с условием
                        res = number; //Присвоение финального значения
                    }
                }
            }
        }
        return res;
    }

    public static boolean rightTriangle310(Scanner scanner) {
        System.out.println("\nЗадача 3.10\nВведите a, b, c - длины сторон треугольника");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        return Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2) ||
                Math.pow(b, 2) + Math.pow(c, 2) == Math.pow(a, 2) ||
                Math.pow(a, 2) + Math.pow(c, 2) == Math.pow(b, 2);
        //Возвращается логическое значение выражения с целью уменьшения списка временных переменных
    }
}

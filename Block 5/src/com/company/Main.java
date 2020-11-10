package com.company;

import com.sun.source.tree.BreakTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        System.out.println("Результат 5.1:\n" + Arrays.toString(encrypt51(in)));
//        System.out.println("Результат 5.3: " + canComplete53(in));
//        System.out.println("Результат 5.4: " + sumDigProd54(in));
        System.out.println("Результат 5.5: " + sameVowelGroup55(in));
        System.out.println("Результат 5.6: " + validateCard56(in));
        System.out.println("Результат 5.7:\n" + numToEng57(in));
        System.out.println("Результат 5.9:\n" + correctTitle59(in));
        System.out.println("Результат 5.10:\n" + hexLattice510(in));
        in.close();
    }

    /**
     * Два метода объединены. Возвращается массив
     **/
    public static Object[] encrypt51(Scanner scanner) {
        Object[] finalArray = new Object[2];
        System.out.println("Задача 5.1\nВведите строку для кодировки и массив для декодирования (элементы разделить пробелом)");
        String stringCrypt = scanner.nextLine();
        /*Алгоритм кодирования*/
        char[] charArray = stringCrypt.toCharArray();
        int first = charArray[0];
        int[] outputArray = new int[charArray.length];
        outputArray[0] = first;
        for (int counter = 1; counter < charArray.length; counter++) {
            outputArray[counter] = charArray[counter] - first;
        }
        finalArray[0] = Arrays.toString(outputArray);
        /*Алгоритм декодирования*/
        String toArray = scanner.nextLine();
        StringBuilder res = new StringBuilder();
        String[] inputArray = toArray.split(" ");
        res.append(String.valueOf(Character.toChars(Integer.parseInt(inputArray[0]))));
        for (int count = 1; count < inputArray.length; count++) {
            res.append(String.valueOf(Character.toChars(Integer.parseInt(inputArray[0])
                    + Integer.parseInt(inputArray[count]))));
        }
        finalArray[1] = res.toString();
        return finalArray;
    }

    public static boolean canComplete53(Scanner scanner) {
        System.out.println("Задача 5.3\nВведите две строки для проверки на возможность превращение первой во вторую");
        String startString = scanner.nextLine();
        String finalString = scanner.nextLine();
        StringBuilder matcher = new StringBuilder("[a-zA-Z]*");
        for (char element : startString.toCharArray()) {
            matcher.append(element).append("[a-zA-Z]*");
        }
        return finalString.matches(matcher.toString());
    }

    public static int sumDigProd54(Scanner scanner){
        System.out.println("Задача 5.4\nВведите числа, разделяя их пробелами");
        /*Ввод чисел в строку*/
        String inputString = scanner.nextLine();
        int[] numbers = new int[inputString.split(" ").length];
        /*Заполнение массива целочисленными данными*/
        for (int counter = 0; counter < inputString.split(" ").length; counter++){
            numbers[counter] = Integer.parseInt(inputString.split(" ")[counter]);
        }
        /*нахождение суммы цифр*/
        int sum = 0;
        for (int element:numbers){
            sum += element;
        }
        int buf = sum;
        while (buf > 9){
            /*Нахождение произведения цифр*/
            sum = 1;
            while (buf / 10 > 0){
                sum *= buf % 10;
                buf /= 10;
            }
            sum *= buf;
            buf = sum;
        }
        return sum;
    }

    public static String sameVowelGroup55(Scanner scanner){
        System.out.println("Задача 5.7\nВведите слова для проверки " +
                "на наличие тех же гласных, что и первое слово");
        String inputString = scanner.nextLine();
        String[] splitted = inputString.split(" ");
        String[] vowels = new String[splitted.length];
        ArrayList<String> res = new ArrayList<>();
        for (int counter = 0; counter < splitted.length; counter++){
            HashSet<Character> unique = new HashSet<>();
            vowels[counter] = splitted[counter].replaceAll("[^aeiouAEIOU]", "").toLowerCase();
            for (char letter:vowels[counter].toCharArray()){
                unique.add(letter);
            }
            vowels[counter] = "";
            ArrayList<Character> sorted = new ArrayList<>(unique);
            Collections.sort(sorted);
            for (Character letter: sorted) {
                vowels[counter] += letter;
            }
        }
        if (vowels.length > 1){
            res.add(splitted[0]);
            for (int counter = 1; counter < vowels.length; counter++){
                if (vowels[counter].equals(vowels[0])){
                    res.add(splitted[counter]);
                }
            }
        } else {
            res.add(splitted[0]);
        }
        return res.toString();
    }

    public static boolean validateCard56(Scanner scanner) {
        System.out.println("Задача 5.6\nВведите номер карты для проверки на валидность");
        StringBuffer inputNum = new StringBuffer(scanner.nextLine());
        if (inputNum.toString().matches("\\b\\d{14,19}\\b")) {
            /*Получение контрольной цифры*/
            int checkDig = Character.getNumericValue(inputNum.charAt(inputNum.length() - 1));
            /*Получение усеченного числа и его инверсия*/
            inputNum = new StringBuffer(inputNum.substring(0, inputNum.length() - 1)).reverse();
            /*Удвоение цифр в нечетных позициях*/
            for (int count = 0; count < inputNum.length(); count++) {
                if ((count + 1) % 2 != 0) {
                    int buf = Character.getNumericValue(inputNum.charAt(count));
                    buf *= 2;
                    if (buf >= 10) {
                        buf = buf / 10 + buf % 10;
                        inputNum.deleteCharAt(count).insert(count, buf);
                    } else {
                        inputNum.deleteCharAt(count).insert(count, buf);
                    }
                }
            }
            /*Суммирование всех чисел числа*/
            int sumOfDig = 0;
            for (int count = 0; count < inputNum.length(); count++) {
                sumOfDig += Character.getNumericValue(inputNum.charAt(count));
            }
            /*Сравнение с контрольной цифрой*/
            return 10 - (sumOfDig % 10) == checkDig;
        } else {
            return false;
        }
    }

    public static String numToEng57(Scanner scanner) {
        System.out.println("Задача 5.7\nВведите число для представления его в строковой записи");
        int inputNum = scanner.nextInt();
        /*Задание одномерного массива значений для сотен*/
        String[] hundreds = new String[]
                {"сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        /*Задание двумерного массива для десятков*/
        String[][] decimals = new String[][]{
                {"надцать", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"},
                {"teen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"}};
        /*Задание двумерного массива для единиц*/
        String[][] numbers = new String[][]{
                {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
                {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}};
        /*Задание двумерного массива для нестандартных значений*/
        String[][] specials = new String[][]{{"две", "десять", "", "", ""}, {"eleven", "twelve", "thir", "fif", "ten"}};
        /*Англо- и русскоязычный вывод*/
        StringBuilder resEng = new StringBuilder();
        StringBuilder resRus = new StringBuilder();
        /*Проверка на соответствие диапазону*/
        if (inputNum < 0 || inputNum > 999) {
            return "Недопустимое значение";
        } else {
            if (inputNum >= 100) {
                resRus.append(hundreds[inputNum / 100 - 1]);
                resEng.append(numbers[1][inputNum / 100]).append(" hundred");
                /*В случае ненулевых разрядов десятков и единиц*/
                if (inputNum % 100 > 0) {
                    if (inputNum % 100 >= 10){
                        switch (inputNum % 100){
                            case 10:
                                resRus.append(" ").append(specials[0][1]);
                                resEng.append(" ").append(specials[1][4]);
                                break;
                            case 11:
                                resRus.append(" ").append(specials[0][1]).append(decimals[0][0]);
                                resEng.append(" ").append(specials[1][0]);
                                break;
                            case 12:
                                resRus.append(" ").append(numbers[0][1]).append(decimals[0][0]);
                                resEng.append(" ").append(specials[1][1]);
                                break;
                            case 13:
                                resRus.append(" ").append(numbers[0][3]).append(decimals[0][0]);
                                resEng.append(" ").append(specials[1][2]).append(decimals[1][0]);
                                break;
                            case 15:
                                resRus.append(" ").append(numbers[0][5], 0, numbers[0][5].length() - 1).append(decimals[0][0]);
                                resEng.append(" ").append(specials[1][3]).append(decimals[1][0]);
                                break;
                            case 18:
                                resRus.append(" ").append(numbers[0][8], 0, numbers[0][8].length() - 1).append(decimals[0][0]);
                                resEng.append(" ").append(numbers[1][8], 0, numbers[1][8].length() - 1).append(decimals[1][0]);
                                break;
                            default:
                                if (inputNum % 100 < 20){
                                    resRus.append(" ").append(numbers[0][inputNum % 10], 0, numbers[0][inputNum % 10]
                                            .length() - 1).append(decimals[0][0]);
                                    resEng.append(" ").append(numbers[1][inputNum % 10]).append(decimals[1][0]);
                                } else if (inputNum % 100 >= 20){
                                    if (inputNum % 10 == 0){
                                        resRus.append(" ").append(decimals[0][inputNum % 100 / 10 - 1]);
                                        resEng.append(" ").append(decimals[1][inputNum % 100 / 10 -1]);
                                    } else {
                                        resRus.append(" ").append(decimals[0][inputNum % 100 / 10 - 1]).append(" ").append(numbers[0][inputNum % 10]);
                                        resEng.append(" ").append(decimals[1][inputNum % 100 / 10 - 1]).append(" ").append(numbers[1][inputNum % 10]);
                                    }
                                }
                                break;
                        }
                    }
                }
            } else if (inputNum >= 10){
                switch (inputNum ){
                    case 10:
                        resRus.append(specials[0][1]);
                        resEng.append(specials[1][4]);
                        break;
                    case 11:
                        resRus.append(specials[0][1]).append(decimals[0][0]);
                        resEng.append(specials[1][0]);
                        break;
                    case 12:
                        resRus.append(numbers[0][1]).append(decimals[0][0]);
                        resEng.append(specials[1][1]);
                        break;
                    case 13:
                        resRus.append(numbers[0][3]).append(decimals[0][0]);
                        resEng.append(specials[1][2]).append(decimals[1][0]);
                        break;
                    case 15:
                        resRus.append(numbers[0][5], 0, numbers[0][5].length() - 1).append(decimals[0][0]);
                        resEng.append(specials[1][3]).append(decimals[1][0]);
                        break;
                    case 18:
                        resRus.append(numbers[0][8], 0, numbers[0][8].length() - 1).append(decimals[0][0]);
                        resEng.append(numbers[1][8], 0, numbers[1][8].length() - 1).append(decimals[1][0]);
                        break;
                    default:
                        if (inputNum < 20){
                            resRus.append(" ").append(numbers[0][inputNum % 10], 0, numbers[0][inputNum % 10]
                                    .length() - 1).append(decimals[0][0]);
                            resEng.append(" ").append(numbers[1][inputNum % 10]).append(decimals[1][0]);
                        } else {
                            if (inputNum % 10 == 0){
                                resRus.append(decimals[0][inputNum / 10 - 1]);
                                resEng.append(decimals[1][inputNum / 10 -1]);
                            } else {
                                resRus.append(decimals[0][inputNum / 10 - 1]).append(" ").append(numbers[0][inputNum % 10]);
                                resEng.append(decimals[1][inputNum / 10 - 1]).append(" ").append(numbers[1][inputNum % 10]);
                            }
                        }
                        break;
                }
            } else {
                resRus.append(numbers[0][inputNum]);
                resEng.append(numbers[1][inputNum]);
            }
            return resRus.append("\n").append(resEng).toString();
        }
    }

    public static String correctTitle59(Scanner scanner){
        System.out.println("Задача 5.9\nВведите заголовок серии для его корректного представления");
        StringBuilder correctTitle = new StringBuilder();
        String inputString = scanner.nextLine();
        String[] words = inputString.split(" ");
        for (String word:words) {
            if (word.contains("-")) {
                String[] word1 = word.split("-");
                for (String newWord : word1) {
                    if (newWord.toLowerCase().equals("and") || newWord.toLowerCase().equals("of") ||
                            newWord.toLowerCase().equals("in") || newWord.toLowerCase().equals("the")) {
                        if (correctTitle.charAt(correctTitle.length() - 1) != '-') {
                            correctTitle.append(" ").append(newWord.toLowerCase()).append("-");
                        } else {
                            correctTitle.append(newWord.toLowerCase()).append("-");
                        }
                    } else {
                        if (correctTitle.length() == 0) {
                            correctTitle.append(newWord.substring(0, 1).toUpperCase()).
                                    append(newWord.substring(1).toLowerCase()).append("-");
                        } else if (correctTitle.charAt(correctTitle.length() - 1) != '-') {
                            correctTitle.append(" ").append(newWord.substring(0, 1).toUpperCase())
                                    .append(newWord.substring(1).toLowerCase()).append("-");
                        } else {
                            correctTitle.append(newWord.substring(0, 1).toUpperCase())
                                    .append(newWord.substring(1).toLowerCase()).append("-");
                        }
                    }
                }
                correctTitle.deleteCharAt(correctTitle.length() - 1);
            } else {
                if (word.toLowerCase().equals("and") || word.toLowerCase().equals("of") ||
                        word.toLowerCase().equals("in") || word.toLowerCase().equals("the")) {
                    if (correctTitle.length() == 0) {
                        correctTitle.append(word.toLowerCase());
                    } else {
                        correctTitle.append(" ").append(word.toLowerCase());
                    }
                } else {
                    if (correctTitle.length() == 0) {
                        correctTitle.append(word.substring(0, 1).toUpperCase()).
                                append(word.substring(1).toLowerCase());
                    } else {
                        correctTitle.append(" ").append(word.substring(0, 1).toUpperCase())
                                .append(word.substring(1).toLowerCase());
                    }
                }
            }
        }
        return correctTitle.toString();
    }

    public static String hexLattice510(Scanner scanner){
        System.out.println("Задача 5.10\nВведите число для проверки его на гексагональность");
        int inputNum = scanner.nextInt();
        StringBuilder res = new StringBuilder();
        if (inputNum > 0){
            int buf = inputNum - 1;
            int count = 0;
            while (buf > 0){
                count += 1;
                buf -= 6 * count;
            }
            if (buf == 0){
                for (int counter = count + 1; counter < 2 * count + 1; counter++){
                    res.append("\t").append("\s".repeat(2 * count + 1 - counter)).append("o\s".repeat(counter)).append("\n");
                }
                for (int counter= 2 * count + 1; counter >= count + 1; counter--){
                    res.append("\t").append("\s".repeat(2 * count + 1 - counter)).append("o\s".repeat(counter)).append("\n");
                }
            } else {
                res.append("Некорректный формат числа");
            }
        } else {
            res.append("\to");
        }
        return res.toString();
    }
}
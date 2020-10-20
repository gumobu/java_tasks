package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Задача 6.1: " + bell61(in));
        System.out.println("Задача 6.2: " + translateSentence62(in));
        System.out.println("Задача 6.3: " + validColor63(in));
        System.out.println("Задача 6.5: " + getHashTags65(in));
        System.out.println("Задача 6.7: " + longestNonRepeatingSubstring67(in));
        System.out.println("Задача 6.8: " + convertToRoman68(in));
        System.out.println("Задача 6.9: " + formula69(in));
        System.out.println("Задача 6.10: " + palindromeDescendant610(in));
        in.close();
    }

    public static int bell61(Scanner scanner){
        System.out.println("Задача 6.1\nВведите количество элементов массива");
        int num = scanner.nextInt();
        int sum = 0;
        for (int count = 0; count <= num; count++){
            sum += stirling61_1(num, count);
        }
        return sum;
    }
    /*Вспомогательный метод. Нахождение чисел Стирлинга*/
    static int stirling61_1 (int num1, int num2){
        if (num1==num2) return 1;
        else
        if (num2==0) return 0;
        else
            return stirling61_1(num1-1,num2-1) + num2 * stirling61_1(num1-1,num2);
    }

    static String translateSentence62(Scanner scanner){
        System.out.println("Задача 6.2\nВведите предложение для перевода его на \"свинский латинский\"");
        String[] words = scanner.nextLine().split(" ");
        String[] wordsClone = words.clone();
        StringBuilder res = new StringBuilder();
        for (int count = 0; count < words.length; count++){
            String buf = words[count].trim();
            if (buf.endsWith(",") || buf.endsWith(".") ||
                    buf.endsWith(";") || buf.endsWith("-")){
                buf = buf.substring(0, buf.length()-1);
            }
            buf = translateWord62_1(buf);
            /*Обработка регистра*/
            if (Character.isUpperCase(wordsClone[count].toCharArray()[0])){
                buf = Character.toUpperCase(buf.toCharArray()[0]) + buf.substring(1);
            }
            /*Обработка пунктуации*/
            if (wordsClone[count].endsWith(",")){
                res.append(buf).append(",\s");
                continue;
            }
            if (wordsClone[count].endsWith(".")){
                res.append(buf).append(".");
                continue;
            }
            if (wordsClone[count].endsWith(";")){
                res.append(buf).append(";\s");
                continue;
            }
            if (wordsClone[count].endsWith("-")){
                res.append(buf).append("\s-\s");
                continue;
            }
            res.append(buf).append("\s");
        }
        return res.toString();
    }
    /*Вспомогательный метод. Перевод слова*/
    public static  String translateWord62_1(String word){
        word = word.toLowerCase();
        if (String.valueOf(word.charAt(0)).matches("[aeiou]")){
            word += "yay";
        } else {
            String buf = "";
            for(int count = 0; count < word.length(); count++) {
                if(!String.valueOf(word.charAt(count)).matches("[aeiou]")) {
                    buf += word.charAt(count);
                }else {
                    word = word.substring(count) + buf + "ay";
                    break;
                }
            }
        }
        return word;
    }

    public static boolean validColor63(Scanner scanner){
        System.out.println("Задача 6.3\nВведите цветовое значение");
        String color = scanner.nextLine().replaceAll(" ", "").trim();
        if (color.startsWith("rgba")){
            color = color.substring(5, color.length() - 1);
            String[] values = color.split(",");
            /*Базовая проверка на наличие необходимого количества "полей" данных*/
            if (values.length != 4){
                return false;
            }
            /*Проверка на соответствие условиям относительных количеств трех цветов*/
            for (int count = 0; count < values.length - 1; count++){
                if (values[count].length() > 0){
                    int value;
                    try {
                        value = Integer.parseInt(values[count]);
                        if (value < 0 || value > 255){
                            return false;
                        }
                    } catch (Exception e){
                        return false;
                    }
                }
            }
            /*Проверка на присутствие значения прозрачности цвета*/
            if (values[3].length() > 0){
                float value;
                try {
                    value = Float.parseFloat(values[3]);
                    if (value < 0 || value > 1){
                        return false;
                    }
                } catch (Exception e){
                    return false;
                }
            } else {
                return false;
            }
        } else if (color.startsWith("rgb")){
            color = color.substring(4, color.length() - 1);
            String[] values = color.split(",");
            System.out.println("\tvalues: " + Arrays.toString(values));
            /*Базовая проверка на наличие необходимого количества "полей" данных*/
            if (values.length < 3){
                return false;
            }
            /*Проверка на соответствие условиям относительных количеств трех цветов*/
            for (String colorValue : values) {
                if (colorValue.length() > 0) {
                    int value;
                    try {
                        value = Integer.parseInt(colorValue);
                        if (value < 0 || value > 255) {
                            return false;
                        }
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static String getHashTags65(Scanner scanner){
        System.out.println("Задача 65\nВведите строку для нахождения хештегов");
        String inputString = scanner.nextLine();
        String[] words = inputString.split(" ");
        /*Сортировка массива слов по длине*/
        Arrays.sort(words, (a, b) -> Integer.compare(b.length(), a.length()));
        int length;
        /*Нахождение количества хештегов*/
        length = Math.min(words.length, 3);
        String[] res = new String[length];
        for (int count = 0; count < length; count++){
            if (words[count].endsWith(",") || words[count].startsWith(";") || words[count].startsWith(":")){
                res[count] = '#' + words[count].substring(0, words[count].length() - 1).toLowerCase();
            } else {
                res[count] = '#' + words[count].toLowerCase();
            }
        }
        return Arrays.toString(res);
    }

    public static String longestNonRepeatingSubstring67(Scanner scanner){
        System.out.println("Задача 6.7\nВведите строку для нахождения длиннейшей неповторяющейся подстроки");
        String inputString = scanner.nextLine();
        char[] chars = inputString.toCharArray();
        int startInd = 0;
        int finInd = 0;
        int count1;
        for (int count = 0; count < chars.length; count++){
            char compareTo = chars[count];
            if (count != chars.length - 1){
                count1 = count + 1;
                while (chars[count1] != compareTo && count1 < chars.length - 1) {
                    count1++;
                }
            } else {
                count1 = count;
            }
            if (count1 - count > finInd - startInd){
                finInd = count1;
                startInd = count;
            }
        }
        return inputString.substring(startInd, finInd);
    }

    public static String convertToRoman68(Scanner scanner){
        System.out.println("Задача6.8\nВведите арабское число для перевода в римскую СИ");
        int inputNum = scanner.nextInt();
        if (inputNum <= 0 || inputNum > 3999){
            return ("Некорректный диапазон");
        }
        StringBuilder res = new StringBuilder();
        String[] romanNum = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int count = 0;
        while (inputNum > 0 && count < romanNum.length){
            if (values[count] <= inputNum){
                res.append(romanNum[count]);
                inputNum -= values[count];
            } else {
                count++;
            }
        }
        return res.toString();
    }

    /**Метод не предназначен для работы с отрицательными числами и требует незначительной доработки**/
    public static boolean formula69(Scanner scanner){
        System.out.println("Задача 6.9\nВведите формулу для проверки");
        String formula = scanner.nextLine().replaceAll(" ", "").strip();
        String[] parts = formula.split("=");
        int[] res = new int[parts.length];
        for (int count = 0; count < parts.length; count++){
            /*Проверка на наличие арифметических знаков в части неравенства*/
            if (parts[count].contains("-")){
                if (parts[count].indexOf("-") > 0 &&
                        parts[count].lastIndexOf("-") < parts[count].length() - 1) {
                    String[] eqParts = parts[count].split("-");
                    int buf = Integer.parseInt(eqParts[0]) - Integer.parseInt(eqParts[1]);
                    if (eqParts.length > 2){
                        for (int count1 = 1; count1 < eqParts.length; count1++){
                            buf -= Integer.parseInt(eqParts[count1]);
                        }
                    }
                    res[count] = buf;
                } else {
                    return false;
                }
            }
            if (parts[count].contains("+")){
                if (parts[count].indexOf("+") > 0 &&
                        parts[count].lastIndexOf("+") < parts[count].length() - 1) {
                    String[] eqParts = parts[count].split("\\+");
                    int buf = Integer.parseInt(eqParts[0]) + Integer.parseInt(eqParts[1]);
                    if (eqParts.length > 2){
                        for (int count1 = 1; count1 < eqParts.length; count1++){
                            buf += Integer.parseInt(eqParts[count1]);
                        }
                    }
                    res[count] = buf;
                } else {
                    return false;
                }
            }
            if (parts[count].contains("/")){
                if (parts[count].indexOf("/") > 0 && parts[count].lastIndexOf("/") < parts[count].length() - 1){
                    String[] eqParts = parts[count].split("\\/");
                    try {
                        int buf = Integer.parseInt(eqParts[0]) / Integer.parseInt(eqParts[1]);
                        if (eqParts.length > 2){
                            for (int count1 = 1; count1 < eqParts.length; count1++){
                                buf /= Integer.parseInt(eqParts[count]);
                            }
                        }
                        res[count] = buf;
                    } catch (Exception e){
                        return false;
                    }
                }
            }
            if (parts[count].contains("*")){
                if (parts[count].indexOf("*") > 0 && parts[count].lastIndexOf("*") < parts[count].length() - 1){
                    String[] eqParts = parts[count].split("\\*");
                    try {
                        int buf = Integer.parseInt(eqParts[0]) * Integer.parseInt(eqParts[1]);
                        if (eqParts.length > 2){
                            for (int count1 = 1; count1 < eqParts.length; count1++){
                                buf += Integer.parseInt(eqParts[count]);
                            }
                        }
                        res[count] = buf;
                    } catch (Exception e){
                        return false;
                    }
                }
            }
            if (!(parts[count].contains("-") || parts[count].contains("+") ||
                    parts[count].contains("/") || parts[count].contains("*"))){
                res[count] = Integer.parseInt(parts[count]);
            }
        }
        /*Проверка на равенство частей выражения*/
        for (int count = 1; count <= res.length - 1; count++){
            if (res[count] != res[count - 1]){
                return false;
            }
        }
        return true;
    }

    public static boolean palindromeDescendant610(Scanner scanner){
        System.out.println("Задача 6.10\nВведите число для проверки его на палиндромность");
        int inputNum = scanner.nextInt();
        String value = String.valueOf(inputNum);
        /*Проверка на принадлежность допустимому диапазону значений и четное количество знаков*/
        if (inputNum < 10 || String.valueOf(inputNum).length() % 2 != 0) return false;
        while (value.length() >= 2 ){
            StringBuilder part2 = new StringBuilder(value.substring(value.length() / 2));
            if (value.substring(0, (value.length()) / 2).equals(part2.reverse().toString())) {
                return true;
            }
            StringBuilder temp = new StringBuilder();
            for (int count = 0; count <= value.length() - 2; count+=2){
                temp.append(Character.getNumericValue(value.charAt(count)) + Character.getNumericValue(value.charAt(count + 1)));
            }
            value = temp.toString();
            System.out.println(value);
        }
        return false;
    }
}

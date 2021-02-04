package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        System.out.println("Результат 1.1: " + hiddenAnagram1(in));
//        System.out.println("Результат 1.2: " + collect2(in));
        System.out.println("Результат 1.3: " + nicoCipher3(in));
//        System.out.println("Результат 1.4: " + twoProduct4(in));
//        System.out.println("Результат 1.5: " + isExact5(in));
    }


    public static String hiddenAnagram1(Scanner scanner) {
        System.out.println("Введите первую строку");
        String t = scanner.nextLine();
        System.out.println("Введите вторую строку");
        String p = scanner.nextLine();
        if (t == null || t.isEmpty() || p == null || p.isEmpty() || t.length() < p.length()) {
            return null;
        }
        char[] secStr = p.replaceAll("\\s","").toLowerCase().toCharArray();
        char[] firStr;
        String result = null;
        int beginIndex = 0;
        int secStrSize = secStr.length;
        t = t.replaceAll("\\s","");
        t = t.replaceAll("[^a-zA-Z]", "");
        while ((t.length() - beginIndex) >= secStrSize) {

            String str = t.substring(beginIndex, secStrSize+beginIndex);
            firStr = str.toLowerCase().toCharArray();

            if (isAnagram(firStr, secStr)) {
                result = String.valueOf(firStr);
                break;
            }
            beginIndex++;
        }
        return result == null ? "noutfond": result;

    }

    private static boolean isAnagram(char[] sentence, char[] input) {
        boolean anagram = false;
        int counter = 0;
        List<Integer> skipIndexes = new ArrayList<Integer>();
        for (char c:input) {
            if (!isCharPresentInSentence(sentence, c, skipIndexes)) {
                break;
            }
            counter++;
        }
        if (counter == input.length)
            anagram = true;
        return anagram;
    }

    private static boolean isCharPresentInSentence(char[] sentence, char c, List<Integer> skipIndexes) {
        boolean isExist = false;
        int index = 0;
        for (char ch : sentence) {

            if (ch == c && !skipIndexes.contains(index)) {
                skipIndexes.add(index);
                isExist = true;
                break;
            }
            index++;
        }
        return isExist;
    }



    public static String collect2(Scanner scanner){
        System.out.println("Введите строку для разбиения и длину строки для последующего разбиения");
        String inputString = scanner.nextLine().replaceAll("\\s+", "");
        int Substringlength = Integer.parseInt(inputString.split(",")[1]);
        inputString = inputString.split(",")[0];
        if (inputString.length() > 6){
            ArrayList<String> outputArray = new ArrayList<>();
            while (inputString.length() >= 6){
                outputArray.add(inputString.substring(0,6));
                inputString = inputString.substring(6);
            }
            if (inputString.length() != 0){
                outputArray.add(inputString);
            }
            Object[] res = outputArray.toArray();
            Arrays.sort(res);
            return Arrays.toString(res);
        } else if (inputString.length() == 6) {
            Object[] res = new Object[]{inputString};
            return Arrays.toString(res);
        }
        else {
            Object[] res = new Object[0];
            return Arrays.toString(res);
        }
    }

    public static String nicoCipher3(Scanner scanner) {
        System.out.println("Введите строку: ");
        String str = scanner.nextLine();
        System.out.println("Введите ключ: ");
        String key = scanner.nextLine();
        int c = 0;
        int strk = str.length() / key.length() + 2;
        int stol = key.length();
        char[] keynum = new char[key.length()];
        char[][] darr = new char[strk][stol];
        char[][] farr = new char[strk][stol];
        for (int i = 0; i < key.length(); i++) {
            int asc = (char) key.charAt(i);
            for (int j = 0; j < key.length(); j++) {
                int dopasc = (char) key.charAt(j);
                if (dopasc <= asc) {
                    c++;
                }
            }
            keynum[i] = (char) (c + 48);
            c = 0;
        }
        for (int i = 0; i < key.length(); i++) {
            darr[0][i] = keynum[i];
        }
        int st = 1;
        int k = 0;
        for (int i = 0; i < stol; i++) {
            if (k < str.length()) {
                darr[st][i] = str.charAt(k);
                k++;
            }
            else {
                while(i < stol)
                {
                    darr[st][i] = ' ';
                    i++;
                }
                break;
            }
            if (i + 1 == stol) {
                st++;
                i = -1;
            }
        }
        st = 0;
        for (int i = 0; i < stol; i++) {
            if (i + 1 == stol) {
                st++;
                if (st > strk - 1) {
                    break;
                }
                i = -1;
            }
        }

        int n = 1;
        int r = 0;
        for (int i = 0; i < stol; i++) {
            int asc = (char) darr[0][i] - 48;
            if (asc == n) {
                for (int j = 0; j < strk; j++) {
                    farr[j][r] = darr[j][i];
                }
                r++;
                i = -1;
                n++;
            }
        }

        st = 0;
        for (int i = 0; i < stol; i++) {
            if (i + 1 == stol) {
                st++;
                if (st > strk - 1) {
                    break;
                }
                i = -1;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < strk; i++) {
            for (int j = 0; j < stol; j++) {
                res.append(farr[i][j]);
            }
        }
        return res.toString();
    }

    public static String twoProduct4(Scanner scanner){
        System.out.println("Введите количество элементов массива");
        int size = scanner.nextInt();
        int[] numberArray = new int[size];
        int[] res = new int[2];
        System.out.println("Введите целочисленные элементы массива, разделяя их пробелами");
        String input = scanner.nextLine();
        System.out.println("Введите число, которому должно быть равно произведение");
        int checkNum = scanner.nextInt();
        for (int counter = 0; counter < size; counter++){
            numberArray[counter] = Integer.parseInt(input.split(" ")[counter]);
        }
        for (int i = 1; i < numberArray.length; i++){
            for(int j = 0; j < i; j++){
                if (numberArray[i] * numberArray[j] == checkNum){
                    res[0] = numberArray[i];
                    res[1] = numberArray[j];
                    return Arrays.toString(res);
                }
            }
        }
        return Arrays.toString(res);
    }

    public static String isExact5(Scanner scanner){
        System.out.println("Введите потенциальную верхнюю границу факториала");
        int border = scanner.nextInt();
        if (border == 0){
            int[] res = new int[]{border, 0};
            return Arrays.toString(res);
        } else {
            int counter = 1;
            int number = counter;
            while (number < border){
                counter++;
                number *= counter;
            }
            int[] res;
            if (number == border){
                res = new int[]{border, counter};
            } else {
                res = new int[0];
            }
            return Arrays.toString(res);
        }
    }
}

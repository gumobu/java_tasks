package com.company;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        System.out.println("Результат 4.1:\n" + Bessie41(in) + "\n<<======>>");
//        System.out.println("Результат 4.2: " + split42(in) + "\n<<======>>");
//        System.out.println("Результат 4.3:\n" +  toCamelOrSnakeCase43(in) + "\n<<======>>");
//        System.out.println("Результат 4.4:\n" +  overtime44(in) + "\n<<======>>");
//        System.out.println("Результат 4.5:\n" + BMI45(in) + "\n<<======>>");
//        System.out.println("Результат 4.6:\n" + bugger46(in) + "\n<<======>>");
//        System.out.println("Результат 4.7:\n" + toStarShortHand47(in) + "\n<<======>>");
//        System.out.println("Результат 4.8:\n" + doesRhyme48(in) + "\n<<======>>");
//        System.out.println("Результат 4.9:\n" + trouble49(in) + "\n<<======>>");
        System.out.println("Результат 4.10:\n" + countUniqueBooks410(in) + "\n<<======>>");
        in.close();
    }

    public static String Bessie41(Scanner scanner) {
        System.out.println("Задача 4.1\nВведите максимальное количество символов в строке и саму строку," +
                " разделяя слова пробелами");
        int maxLet = Integer.parseInt(scanner.nextLine()); //Максимальное количество символов в строке
        int count = 0;
        String inputString = scanner.nextLine();
        String[] stringArray = inputString.split(" ");
        StringBuilder res = new StringBuilder();
        for (String element:stringArray){
            if (element.length() + count <= maxLet && count != 0){
                res.append(" ").append(element);
                count += element.length();
            } else if (element.length() + count <= maxLet && count == 0){
                res.append(element);
                count += element.length();
            } else if (element.length() + count > maxLet && count != 0){
                res.append("\n").append(element);
                count = element.length();
            } else if (element.length() + count > maxLet && count == 0){
                res.append(element).append("\n");
            }
        }
        return res.toString();
    }

    public static ArrayList<String> split42(Scanner scanner) {
        System.out.println("\nЗадача 4.2\nВведите строку из скобок для разбиения на кластеры");
        String inputString = scanner.nextLine();
        char[] charArray = inputString.toCharArray();
        int openCounter = 0;
        int closeCounter = 0;
        int lastIndex = 0;
        ArrayList<String> finalArray = new ArrayList<>();
        for (int counter = 0; counter < inputString.length(); counter++) {
            if (charArray[counter] == '(') {
                openCounter++;
            }
            if (charArray[counter] == ')') {
                closeCounter++;
            }
            if (openCounter == closeCounter) {
                finalArray.add(inputString.substring(lastIndex, counter + 1));
                lastIndex = counter + 1;
                closeCounter = 0;
                openCounter = 0;
            }
        }
        return finalArray;
    }

    public static String toCamelOrSnakeCase43(Scanner scanner) {
        System.out.println("\nЗадача 4.3\nВведите строку для преобразования в CamelCase и snake_case");
        String inputString = scanner.nextLine();
        StringBuilder res1 = new StringBuilder();
        StringBuilder res2 = new StringBuilder();
        String[] inputStringArray = inputString.split(" ");
        for (String s : inputStringArray) {
            res1.append(s.substring(0, 1).toUpperCase()).
                    append(s.substring(1).toLowerCase());
            res2.append(s.toLowerCase()).append("_");
        }
        String[] outputArray = new String[]{res1.toString(), res2.substring(0, res2.length() - 1)};
        return String.join("\n", outputArray);
    }

    public static String overtime44(Scanner scanner) {
        System.out.println("\nЗадача 4.4\nВведите начало рабочего дня, конец рабочего дня, почасовую ставку и коэффициент сверхурочной работы");
        String data = scanner.nextLine();
        float[] dataArray = new float[4];
        for (int counter = 0; counter < 4; counter++){
            dataArray[counter] = Float.parseFloat(data.split(" ")[counter]);
        }
        String res;
        if (dataArray[1] <= 17) {
            res = "$" + ((dataArray[1] - dataArray[0]) * dataArray[2]);
        } else {
            if (dataArray[0] < 17) {
                res = "$" + ((17 - dataArray[0]) * dataArray[2] + (dataArray[1] - 17) * dataArray[3] * dataArray[2]);
            } else {
                res = "$" + ((dataArray[1] - dataArray[0]) * dataArray[3] * dataArray[2]);
            }
        }
        return res;
    }

    public static String BMI45(Scanner scanner) {
        System.out.println("\nЗадача 4.5\nВведите вес в pounds или kg, а также рост в inches или meters");
        String res;
        String weight = scanner.nextLine();
        String height = scanner.nextLine();
        String[] heightArray = height.split(" ");
        String[] weightArray = weight.split(" ");
        float weightFinal;
        float heightFinal;
        if (Objects.equals(weightArray[1], "pounds")) {
            weightFinal = new Float(weightArray[0]);
            weightFinal = (float) (weightFinal / 2.2);
        } else {
            weightFinal = new Float(weightArray[0]);
        }
        if (Objects.equals(heightArray[1], "inches")) {
            heightFinal = new Float(heightArray[0]);
            heightFinal = (float) (heightFinal / 39.4);
        } else {
            heightFinal = new Float(heightArray[0]);
        }
        float BMI = (float) (weightFinal / Math.pow(heightFinal, 2));
        if (BMI < 18.5) {
            res = new DecimalFormat("#0.0").format(BMI) + " - Недостаточный вес";
        } else if (BMI > 25) {
            res = new DecimalFormat("#0.0").format(BMI) + " - Избыточный вес";
        } else {
            res = new DecimalFormat("#0.0").format(BMI) + " - Вес в пределах нормы";
        }
        return res;
    }

    public static int bugger46(Scanner scanner){
        System.out.println("Задача 4.6\nвведите число для нахождения его мультипликационного постоянства");
        int inputNum = Integer.parseInt(scanner.nextLine());
        int timesCount = 0;
        while (inputNum > 9){
            timesCount++;
            String fromNum = String.valueOf(inputNum);
            inputNum = 1;
            for (int count = 0; count < fromNum.length(); count++){
                inputNum *= Character.getNumericValue(fromNum.charAt(count));
            }
        }
        return timesCount;
    }

    public static String toStarShortHand47(Scanner scanner) {
        System.out.println("Задача 4.7\nВведите строку для преобразованию в звездную стенографию");
        String inputString = scanner.nextLine();
        StringBuilder res = new StringBuilder();
        char[] charArray = inputString.toCharArray();
        int symbolCount = 1;
        for (int counter = 1; counter < inputString.length(); counter++) {
            if (charArray[counter - 1] == charArray[counter]) {
                symbolCount += 1;
            } else if (symbolCount > 1) {
                res.append(charArray[counter - 1]).append("*").append(symbolCount);
                symbolCount = 1;
            } else {
                res.append(charArray[counter - 1]);
                symbolCount = 1;
            }
            if (counter == inputString.length() - 1 & symbolCount > 1) {
                res.append(charArray[counter - 1]).append("*").append(symbolCount);
            } else if (counter == inputString.length() - 1 & symbolCount == 1) {
                res.append(charArray[counter]);
            }
        }
        return res.toString();
    }

    public static boolean doesRhyme48(Scanner scanner){
        System.out.println("Задача 4.8\nВведите две строки для проверки на рифмованность");
        String inputString1 = scanner.nextLine();
        String inputString2 = scanner.nextLine();
        return inputString1.split(" ")[inputString1.split(" ").length - 1].replaceAll("[BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsTtVvWwXxZz]", "").
                equals(inputString2.split(" ")[inputString2.split(" ").length - 1].replaceAll("[BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsTtVvWwXxZz]", ""));
    }

    public static boolean trouble49(Scanner scanner) {
        System.out.println("Задача 4.9\nВведите два целых числа");
        String inputNumber1 = scanner.nextLine();
        String inputNumber2 = scanner.nextLine();
        boolean res = false;
        if ((inputNumber1.matches("[0-9&&[^0]]*0{3}[0-9&&[^0]]*") &&
                inputNumber2.matches("[0-9&&[^0]]*0{2}[0-9&&[^0]]*")) ||
                (inputNumber1.matches("[0-9&&[^1]]*1{3}[0-9&&[^1]]*") &&
                        inputNumber2.matches("[0-9&&[^1]]*1{2}[0-9&&[^1]]*")) ||
                (inputNumber1.matches("[0-9&&[^2]]*2{3}[0-9&&[^2]]*") &&
                        inputNumber2.matches("[0-9&&[^2]]*2{2}[0-9&&[^2]]*")) ||
                (inputNumber1.matches("[0-9&&[^3]]*3{3}[0-9&&[^3]]*") &&
                        inputNumber2.matches("[0-9&&[^3]]*3{2}[0-9&&[^3]]*")) ||
                (inputNumber1.matches("[0-9&&[^4]]*4{3}[0-9&&[^4]]*") &&
                        inputNumber2.matches("[0-9&&[^4]]*4{2}[0-9&&[^4]]*")) ||
                (inputNumber1.matches("[0-9&&[^5]]*5{3}[0-9&&[^5]]*") &&
                        inputNumber2.matches("[0-9&&[^5]]*5{2}[0-9&&[^5]]*")) ||
                (inputNumber1.matches("[0-9&&[^6]]*6{3}[0-9&&[^6]]*") &&
                        inputNumber2.matches("[0-9&&[^6]]*6{2}[0-9&&[^6]]*")) ||
                (inputNumber1.matches("[0-9&&[^7]]*7{3}[0-9&&[^7]]*") &&
                        inputNumber2.matches("[0-9&&[^7]]*7{2}[0-9&&[^7]]*")) ||
                (inputNumber1.matches("[0-9&&[^8]]*8{3}[0-9&&[^8]]*") &&
                        inputNumber2.matches("[0-9&&[^8]]*8{2}[0-9&&[^8]]*")) ||
                (inputNumber1.matches("[0-9&&[^9]]*9{3}[0-9&&[^9]]*") &&
                        inputNumber2.matches("[0-9&&[^9]]*9{2}[0-9&&[^9]]*"))) {
            res = true;
        }
        return res;
    }

    public static int countUniqueBooks410(Scanner scanner) {
        System.out.println("Задача 4.10\nВведите строковую последовательность и \"границу\" книги");
        String inputString = scanner.nextLine();
        String bookmark = scanner.nextLine();
        String[] books = inputString.split(bookmark); //Разбиение строки по символу-границе в массив строк
        HashSet<Character> uniqueBooks = new HashSet<>();
        for (int counter = 1; counter < books.length; counter++) {
            if (counter % 2 != 0) {
                if (!books[counter].isBlank()) {
                    char[] charArray = books[counter].toCharArray();
                    for (char letter : charArray) {
                        uniqueBooks.add(letter);
                    }
                }
            }
        }
        return uniqueBooks.size();
    }
}

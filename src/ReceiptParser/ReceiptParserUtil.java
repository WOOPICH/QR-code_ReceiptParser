package ReceiptParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptParserUtil {

    private ReceiptParserUtil() {

    }

    public static void parse(String str, ReceiptParserCallback callback) {

        boolean validString = true;

        StringBuilder date = new StringBuilder();
        StringBuilder amount = new StringBuilder();
        StringBuilder fn = new StringBuilder();
        StringBuilder fp = new StringBuilder();
        StringBuilder i = new StringBuilder();
        StringBuilder n = new StringBuilder();

        //Проверка строки
        for (int j = 0; j < str.length() - 2; j++) {
            if (str.charAt(j) == 't' && str.charAt(j + 1) == '=') {
                j = StringBuild(str, j, date, 2);
            }
            if (str.charAt(j) == 's' && str.charAt(j + 1) == '=') {
                j = StringBuild(str, j, amount,2);
            }
            if (str.charAt(j) == 'f' && str.charAt(j + 1) == 'n' && str.charAt(j + 2) == '=') {
                int temp = StringBuild(str, j, fn, 3);
                if (temp - j == 19) {
                    j = temp;
                } else {
                    System.out.println("Invalid fn");
                    validString = false;
                    break;
                }
            }
            if (str.charAt(j) == 'i' && str.charAt(j + 1) == '=') {
                int temp = StringBuild(str, j, i ,2);
                if (temp - j <= 12) {
                    j = temp;
                } else {
                    System.out.println("Invalid i");
                    validString = false;
                    break;
                }
            }
            if (str.charAt(j) == 'f' && str.charAt(j + 1) == 'p' && str.charAt(j + 2) == '=') {
                int temp = StringBuild(str, j, fp, 3);
                if (temp - j <= 13) {
                    j = temp;
                } else {
                    System.out.println("Invalid fp");
                    validString = false;
                    break;
                }
            }
            if (str.charAt(j) == 'n' && str.charAt(j + 1) == '=') {
                int temp = StringBuild(str, j, n,2);
                if (temp - j <= 3) {
                    j = temp;
                } else {
                    System.out.println("Invalid n");
                    validString = false;
                    break;
                }
            }
        }
        if (date.toString().isEmpty() || amount.toString().isEmpty() || fn.toString().isEmpty() || i.toString().isEmpty() || fp.toString().isEmpty() || n.toString().isEmpty()) {
            System.out.println("Invalid input");
            validString = false;
        }

        //Если проверка пройдена
        if (validString) {
            //Проверка длины даты для использования паттерна
            DateTimeFormatter formatter;
            if (date.length() == 13) {
                formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
            } else {
                formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
            }

            try {
                // Отпарсенная строка с датой и временем
                LocalDateTime formatDateTime = LocalDateTime.parse(date.toString(), formatter);

                ReceiptInfo info = new ReceiptInfo(formatDateTime, Long.valueOf(amount.toString()), Long.valueOf(fn.toString()), Long.valueOf(i.toString()), Long.valueOf(fp.toString()), Integer.valueOf(n.toString()));

                callback.onResult(info);
            } catch (java.time.format.DateTimeParseException e){ //Если ввести неправильное время или дату
                e.getStackTrace();
                callback.onFailure();
            }
        //Если проверка не пройдена
        } else {
            callback.onFailure();
        }
    }

    //Метод для строения строк с данными
    private static int StringBuild(String str, int j, StringBuilder stringBuilder , int after /*Отступ после "t=","fn=" и т.д.*/ ) {
        int k = j + after;
        while (k < str.length() && str.charAt(k) != '&') {
            if (str.charAt(k) != '.') //Убирает отделение копеек от целой части
                stringBuilder.append(str.charAt(k));
            k++;
        }
        return j + (k - j);
    }
}


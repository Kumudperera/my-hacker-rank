package easy.timeConversion;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'timeConversion' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String timeConversion(String s) {
        // Write your code here
        // Input format: 12-hour time as hh:mm:ssAM or hh:mm:ssPM
        // Output: 24-hour time as HH:mm:ss

        String period = s.substring(8, 10); // AM or PM
        String hourPart = s.substring(0, 2);
        String rest = s.substring(2, 8); // :mm:ss

        int hour = Integer.parseInt(hourPart);

        if ("AM".equals(period)) {
            if (hour == 12) {
                hour = 0; // 12AM -> 00
            }
        } else { // PM
            if (hour != 12) {
                hour += 12; // 1PM-11PM -> 13-23
            }
        }

        String convertedHour = String.format("%02d", hour);
        return convertedHour + rest;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

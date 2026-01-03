package fraudulentActivityNotifications;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int result = 0;
        List<Integer> sorted = updateSortedList(expenditure.subList(0, d), null, null);

        for (int i = 0; i < expenditure.size() - d; i++) {

            if (i != 0) {
                sorted = updateSortedList(sorted, expenditure.get(i - 1), expenditure.get(i + d - 1));
            }

            double median = median(sorted);
            if (expenditure.get(i + d) >= 2 * median) {
                result++;
            }
        }

        return result;
    }

    public static List<Integer> updateSortedList(List<Integer> list, Integer remove, Integer add) {
        if (remove == null && add == null) {
            Collections.sort(list);
        } else if (remove != add) {
            int removeIndex = Collections.binarySearch(list, remove);
            if (removeIndex >= 0) {
                list.remove(removeIndex);
            }

            int insertIndex = Collections.binarySearch(list, add);
            if (insertIndex < 0) {
                insertIndex = -insertIndex - 1;
            }

            list.add(insertIndex, add);
        }

        return list;
    }

    private static double median(List<Integer> sorted) {
        int n = sorted.size();
        int mid = n / 2;

        if ((n & 1) == 1) {
            return sorted.get(mid);
        }

        return (sorted.get(mid - 1) + sorted.get(mid)) / 2.0;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}


package medium.redKnightsShortestPath;

import java.io.*;

import static java.util.stream.Collectors.joining;

class Result {
    private static int MAX_MOVE = 2;
    private static int MIN_MOVE = 1; // This is only can for horizontal moves
    private static String printMoves = "";
    /*
     * Complete the 'printShortestPath' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER i_start
     *  3. INTEGER j_start
     *  4. INTEGER i_end
     *  5. INTEGER j_end
     */

    public static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
//        int iDiff = i_end - i_start;
//        int jDiff = j_end - j_start;
//
//        if (iDiff % MAX_MOVE != 0 || (iDiff == 0 && jDiff % MAX_MOVE != 0) || (jDiff == 0 && )) {
//            System.out.println("Impossible");
//            return;
//        }



        int i = i_start;
        int j = j_start;
        int iDiff = i_end - i_start;
        int jDiff = j_end - j_start;

        int count = 0;

        if (iDiff % MAX_MOVE != 0 || (iDiff == 0 && jDiff % MAX_MOVE != 0)) {
            System.out.println("Impossible");
            return;
        }

        while (true) {
            iDiff = i - i_end;
            jDiff = j - j_end;

            if (jDiff == 0 && iDiff == 0) {
                System.out.println(count);
                System.out.println(printMoves);
                return;
            }

            try {
                if (iDiff == 0) {
                    if (jDiff % MAX_MOVE != 0) {
                        throw new TestException();
                    }
                    j = rightOrLeft(n, j, jDiff, true);
                    count++;
                } else if (iDiff > 0) {
                    i = up(i, true);
                    j = rightOrLeft(n, j, jDiff, false);
                    count++;
                } else {
                    i = down(i, true);
                    j = rightOrLeft(n, j, jDiff, false);
                    count++;
                }
            } catch (TestException e) {
                System.out.println("Impossible");
                return;
            }
        }
    }

    public static int down(int i, boolean print) {
        i = i + MAX_MOVE;
        if (print) printMoves = printMoves + "L";
        return i;
    }
    public static int up(int i, boolean print) {
        i = i - MAX_MOVE;
        if (print) printMoves = printMoves + "U";
        return i;
    }

    public static int rightOrLeft(int n, int j, int jDiff, boolean maxMove) throws TestException {
        int tempJ = Integer.valueOf(j);

        if (jDiff == 0) {
            try {
                tempJ = left(tempJ, false, true);
            } catch (TestException e) {
                tempJ = right(tempJ, n, false, true);
            }
            j = tempJ;
        } else if (jDiff > 0) {
            j = left(j, maxMove, true);
        } else {
            j = right(j, n, maxMove, true);
        }

        return j;
    }
    public static int right(int j, int n, boolean maxMove, boolean print) throws TestException {
        if (maxMove) {
            j = j + MAX_MOVE;
        } else {
            j = j + MIN_MOVE;
        }

        if (j > n - 1) {
            throw new TestException();
        }

        if (print) printMoves = printMoves + "R ";
        return j;
    }

    public static int left(int j, boolean maxMove, boolean print) throws TestException {
        if (maxMove) {
            j = j - MAX_MOVE;
        } else {
            j = j - MIN_MOVE;
        }

        if (j < 0) {
            throw new TestException();
        }

        if (print) printMoves = printMoves + "L ";
        return j;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int i_start = Integer.parseInt(firstMultipleInput[0]);

        int j_start = Integer.parseInt(firstMultipleInput[1]);

        int i_end = Integer.parseInt(firstMultipleInput[2]);

        int j_end = Integer.parseInt(firstMultipleInput[3]);

        Result.printShortestPath(n, i_start, j_start, i_end, j_end);

        bufferedReader.close();
    }
}

class TestException extends Exception {
    TestException() {
        super();
    }
}



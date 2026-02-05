package medium.climbingladder;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> leaderboard = new HashMap<>();

        AtomicInteger currentRank = new AtomicInteger(1);
        for (int i = 0; i < ranked.size() ; i++) {
            leaderboard.computeIfAbsent(ranked.get(i), k -> currentRank.getAndIncrement());
        }

        int i = 0;
        int j = ranked.size() - 1;

        while (i != player.size()) {
            boolean increaseI = false;
            boolean decreaseJ = false;
            int playerScore = player.get(i);
            int leaderboardScore = ranked.get(j);

            if (playerScore < leaderboardScore) {
                result.add(leaderboard.get(leaderboardScore) + 1);
                increaseI = true;
            } else if (playerScore == leaderboardScore) {
                result.add(leaderboard.get(leaderboardScore));
                increaseI = true;
            } else {
                decreaseJ = j != 0;
                if (j == 0) {
                    increaseI = true;
                    result.add(leaderboard.get(leaderboardScore));
                }
            }

            if (increaseI) {
                i++;
            }

            if (decreaseJ) {
                j--;
            }
        }

        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}


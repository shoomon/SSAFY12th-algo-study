package codingTest;

import java.util.*;

public class 연습_연속펄스부분수열의합_wooseok {

    public long solution(int[] sequence) {
        long answer = 0;
        int size = sequence.length;

        long [][] DP = new long [size+1][2];

        for(int i = 1; i <= size; i++){
            int target = sequence[i-1];

            if(i % 2 == 1){
                DP[i][0] = target;
                DP[i][1] = target * -1;
            }
            else{
                DP[i][0] = target * -1;
                DP[i][1] = target;
            }
            if(DP[i][1] < DP[i-1][1] + DP[i][1]) {
                DP[i][1] += DP[i-1][1];
            }

            if(DP[i][0] < DP[i-1][0] + DP[i][0]) {
                DP[i][0] += DP[i-1][0];
            }

            answer = Math.max(answer , Math.max(DP[i][1], DP[i][0]));
        }

        return answer;
    }
}

package codingTest;

import java.util.*;

public class 연습_점찍기_wooseok {
    public static long solution(int k, int d) {
        long answer = 0;

        for (int i = 0; i <= d; i += k) {
            int yPoint = yPosiblePoint(i, d);
            answer += yCnt(yPoint, k);
        }
        return answer;
    }
    private static int yPosiblePoint(int x, int d) {
        long xSquare = (long) Math.pow(x, 2);
        long dSquare = (long) Math.pow(d, 2);

        int y = (int) (Math.sqrt(dSquare - xSquare));
        return y;
    }

    private static int yCnt(int yPoint, int k) {
        int y = (yPoint / k) ;
        return y+1;

    }
}

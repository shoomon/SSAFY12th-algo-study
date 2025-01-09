import java.util.*;
import java.io.*;

public class 연습문제_두원사이의정수쌍 {
	public long solution(int r1, int r2) {
        long answer = 0;
        
        // 큰 원에 들어가는 점의 수 - 작은 원에 들어가는 점의 수
        // 4개의 사분면 중 1개만 구한 뒤 4를 곱한다.
        // 0부터 시작하면 x축, y축에 해당하는 부분이 계속 반복되므로 1부터 시작
        for (int i=1; i<=r2; i++) {
            double y2 = Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2));
            double y1 = Math.sqrt(Math.pow(r1, 2) - Math.pow(i, 2));
            answer += (long) y2 - (long) Math.ceil(y1) + 1;
        }
        answer *= 4;
        
        return answer;
    }
}

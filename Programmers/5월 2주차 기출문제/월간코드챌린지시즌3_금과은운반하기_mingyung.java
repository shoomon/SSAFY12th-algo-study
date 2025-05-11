
import java.util.*;
import java.io.*;

public class 월간코드챌린지시즌3_금과은운반하기_mingyung {
	int A, B, size;
    int[] G; int[] S; int[] W; int[] T;
    
    boolean check(long time) {
        long totalSum = 0;
        long totalA = 0;
        long totalB = 0;
        
        for (int i=0; i<size; i++) {
            long count = time/(2L * T[i]);
            // 편도 시간이 남았다면
            if (time % (2L * T[i]) >= T[i]) {
                count++;
            }
            // 가져갈 수 있는 총 무게
            long sum = count * W[i];
            totalSum += Math.min(sum, G[i] + S[i]);
            totalA += Math.min(sum, G[i]);
            totalB += Math.min(sum, S[i]);
            if (totalSum >= A + B && totalA >= A && totalB >= B) return true;
        }
        
        return false;
    }
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        A = a; B = b; G = g; S = s; W = w; T = t; size = s.length;
        
        // a + b 최댓값 = 10^9 * 2
        // w[i] 최소 = 1
        // 왕복 최대 10^5 * 2
        // 최대로 걸리는 시간 = 10^9 * 2 * 10^5 * 2 = 10^14 * 4
        long answer = 0;
        long end = (long) Math.pow(10, 14) * 4;
        while (answer < end) {
            long mid = (answer + end) / 2;
            if (check(mid)) {
                end = mid;
            }
            else answer = mid + 1;
        }
        
        return answer;
    }
}


import java.util.*;
import java.io.*;

public class 연습_우박수열정적분_mingyung {
	public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        // 우박수열 리스트
        List<Integer> list = new ArrayList<>();
        list.add(k);
        while (k > 1) {
            if (k%2==0) k /= 2;
            else k = k*3 + 1;
            list.add(k);
        }
        int len = list.size();
        
        // 사다리꼴 누적합 구하기
        double[] sum = new double[len];
        for (int i=1; i<len; i++) {
            sum[i] = sum[i-1] + ((list.get(i) + list.get(i-1)) / 2.0);
        }
        
        // 각 넓이 구하기
        for (int i=0; i<ranges.length; i++) {
            int start = ranges[i][0];
            int end = len - 1 + ranges[i][1];
            
            // 시작점이 끝점보다 뒤면 -1
            if (start > end) {
                answer[i] = -1.0;
                continue;
            }
            
            // 누적합 활용해서 답 구하기
            answer[i] = sum[end] - sum[start];
        }
        
        return answer;
    }
}

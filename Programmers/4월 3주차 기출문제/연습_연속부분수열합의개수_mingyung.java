
import java.util.*;
import java.io.*;

public class 연습_연속부분수열합의개수_mingyung {
	public int solution(int[] elements) {
        int answer = 0;
        
        // 누적합 위해 elements 배열 길이 2배로 만들기
        int len = elements.length;
        int[] prefixSum = new int[len*2+1];
        for (int i=0; i<len*2; i++) {
            prefixSum[i+1] = prefixSum[i] + elements[i%len];
        }
        
        // 연속 부분 수열 합 개수, 중복 피하기 위해 set 사용
        HashSet<Integer> set = new HashSet<>();
        // 1 ~ 배열 길이까지 연속 부분 수열 길이
        for (int i=1; i<=len; i++) {
            // 길이만큼 배열 돌면서 합 구하기
            for (int j=0; j<len; j++) {
                int sum = prefixSum[j+i] - prefixSum[j];
                set.add(sum);
            }
        }
        
        answer = set.size();
        
        return answer;
    }
}

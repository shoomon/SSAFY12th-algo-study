import java.util.*;
import java.io.*;

public class 연습문제_연속된부분수열의합 {
	public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        // 시작점, 끝점 기준 잡아 배열 돌면서 합 구하기
        int length = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = sequence[0];
        
        while (start < sequence.length && end < sequence.length) {
            // 합이 k면 길이 체크해서 갱신
            if ( sum == k ) {
                if ( end - start + 1 < length) {
                    length = end - start + 1;
                    answer[0] = start;
                    answer[1] = end;
                }
            }
            
            // k보다 작으면 end 올려서 계속 진행
            if ( sum <= k ) {
                end++;
                if ( end < sequence.length ) sum += sequence[end];
            }
            
            // 합이 k 넘어가면 start 올려서 다음 부분수열 찾기
            else {
                if ( start < sequence.length ) sum -= sequence[start];
                start++;
            }
            
        }
        
        return answer;
    }
	
	/* 시간 초과 or 통과
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        // 시작점, 끝점 기준 잡아 배열 돌면서 합 구하기
        int length = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        
        while (start < sequence.length && end < sequence.length) {
            // 부분 합 더하기
            sum += sequence[end];
            
            // k보다 작으면 end 올려서 계속 진행
            if ( sum < k ) end++;
            
            // 합이 k 넘어가면 start 올려서 다음 부분수열 찾기
            if ( sum > k ) {
                start++;
                end = start;
                sum = 0;
                continue;
            }
            
            // 합이 k면 길이 체크해서 갱신
            if ( sum == k ) {
                if ( end - start + 1 < length) {
                    length = end - start + 1;
                    answer[0] = start;
                    answer[1] = end;
                    continue;
                }
            }
        }
        
        return answer;
    }
    */
}

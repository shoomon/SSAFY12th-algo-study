import java.util.*;
import java.io.*;

class PCCP_2_mingyung {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        // diffs 배열 내 난이도 최소, 최대값 찾기
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i=0; i<diffs.length; i++) {
            if (min>diffs[i]) min = diffs[i];
            if (max<diffs[i]) max = diffs[i];
        }
        
        // 난이도 중간 값부터 이분탐색
        while (min <= max) {
            int mid = (max + min)/2;
            
            // mid에 해당하는 난이도가 걸리는 시간 계산
            long time = 0; // limit 범위 참고하여 long으로 잡음
            for (int i=0; i<diffs.length; i++) {
                if (mid >= diffs[i]) { // level이 난이도보다 높거나 같다면 틀리지 않음
                    time += times[i];
                }
                else { // level이 난이도보다 낮다면 낮은 만큼 틀림. 틀리면 이전 문제 다시 풀기
                    time += (diffs[i]-mid)*(times[i] + times[i-1]) + times[i];
                }
            }
            
            // 문제 푸는데 걸린 시간이 제한시간보다 짧으면 해결 가능하므로
            if (time <= limit) max = mid-1;
            // 제한시간보다 길었다면 해결이 불가능했던 것이므로
            else min = mid+1;
        }
        
        answer = min;
        
        return answer;
    }
}
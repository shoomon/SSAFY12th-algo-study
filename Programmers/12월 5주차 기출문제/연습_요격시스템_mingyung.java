import java.util.*;
import java.io.*;

public class 연습문제_요격시스템 {
	public int solution(int[][] targets) {
        int answer = 0;
        
        // 일단 미사일 끝나는 점을 기준으로 정렬
        Arrays.sort(targets, (o1, o2) -> 
                    o1[1]-o2[1] != 0 ? o1[1]-o2[1] : o1[0]-o2[0]);
        
        // 앞에서부터 돌면서 최대한 뒤에서 쏘기
        int pos = 0;
        for (int i=0; i<targets.length; i++) {
            // 만약 현재 미사일 범위 끝나는 점보다 뒤에 시작되는 미사일이라면
            // 앞에 부분은 쏴서 없애고, 뒤부터 다시 탐색
            if (targets[i][0] >= pos) {
                answer++;
                pos = targets[i][1];
            }
        }
        
        return answer;
    }
}

import java.util.*;
import java.io.*;

public class 연습_인사고과_mingyung {
	public int solution(int[][] scores) {
        int answer = 1;
        
        // 일단 완호 점수 기억
        int[] wan = new int[2];
        wan[0] = scores[0][0];
        wan[1] = scores[0][1];
        
        // 근태는 내림차순으로 정렬 -> 뒤에 오면 무조건 낮음
        // 동료평가는 오름차순 : 같은 점수일 때 낮다고 걸러지면 안됨!
        Arrays.sort(scores, (o1, o2) -> 
                   o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        // 동료평가 최고 점수 따로 기억해서 비교해주기
        int maxScore = scores[0][1];
        
        // 정렬된 것 앞에서부터 돌면서 체크
        for (int i=1; i<scores.length; i++) {
            // maxScore보다 작은지 체크하기
            if (scores[i][1] < maxScore) {
                // 완호면 -1 리턴
                if (wan[0] == scores[i][0] && wan[1] == scores[i][1]) return -1;
                // 아니면 인센티브 못받으니 값 변경
                scores[i][0] = -1;
                scores[i][1] = -1;
            }
            // maxScore 갱신
            else maxScore = scores[i][1];
        }
        
        // 점수 순으로 정렬
        Arrays.sort(scores, (o1, o2) -> (o2[0]+o2[1]) - (o1[0]+o1[1]));
        int wanScore = wan[0] + wan[1];
        for (int i=0; i<scores.length; i++) {
            if (wanScore < scores[i][0] + scores[i][1]) answer++;
            else break;
        }
        
        return answer;
    }
}

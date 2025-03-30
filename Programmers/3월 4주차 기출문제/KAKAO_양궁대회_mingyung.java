
import java.util.*;
import java.io.*;

public class KAKAO_양궁대회_mingyung {
	int maxDiff = 0;
    int[] best = new int[11];
    
    // dfs로 화살 쏘는 수 탐색
    void dfs(int arrowsLeft, int idx, int[] ryan, int[] apeach) {
        // 0점까지 쏘면 확인하기
        if (idx==11) {
            // 화살 갯수 남아있으면 0점에 쏘기
            if (arrowsLeft > 0) {
                ryan[10] += arrowsLeft;
            }
            
            int ryanScore = 0;
            int apeachScore = 0;
            for (int i=0; i<11; i++) {
                // 라이언이 더 쐈으면 라이언 점수
                if (ryan[i] > apeach[i]) {
                    ryanScore += 10 - i;
                }
                // 아니면 어피치가 쐈을 경우 어피치 점수
                else if (apeach[i] > 0) {
                    apeachScore += 10 - i;
                }
            }
            
            // 라이언 점수가 높으면 원래 배열과 비교
            if (ryanScore > apeachScore) {
                int diff = ryanScore - apeachScore;
                if (diff > maxDiff || (diff==maxDiff && isBetter(ryan, best))) {
                    maxDiff = diff;
                    best = Arrays.copyOf(ryan, 11);
                }
            }
            
            if (arrowsLeft > 0) {
                ryan[10] -= arrowsLeft;
            }
            
            return;
        }
        
        // idx 점수 가져가기
        if (arrowsLeft > apeach[idx]) {
            ryan[idx] = apeach[idx] + 1;
            dfs(arrowsLeft-ryan[idx], idx+1, ryan, apeach);
            ryan[idx] = 0;
        }
        
        // idx 점수 포기하기
        dfs(arrowsLeft, idx+1, ryan, apeach);
    }
    
    // 낮은 점수 많이 쏜 경우 판별
    boolean isBetter(int[] newArr, int[] oldArr) {
        for (int i=10; i>=0; i--) {
            // 뒤에서부터 비교해서 숫자에 차이가 있으면 return
            if (newArr[i] > oldArr[i]) return true;
            if (newArr[i] < oldArr[i]) return false;
        }
        return false;
    }
    
    public int[] solution(int n, int[] info) {
        dfs(n, 0, new int[11], info);
        
        return maxDiff>0 ? best : new int[] {-1};
    }
}


import java.util.*;
import java.io.*;

public class 연습_피로도_mingyung {
	int max = 0;
    boolean[] vis;
    
    void dfs(int k, int[][] dungeons, int depth) {
        for (int i=0; i<dungeons.length; i++) {
            // 방문하지 않은 상태면서 k가 최소 필요 피로도보다 크거나 같은 경우
            if (!vis[i] && dungeons[i][0] <= k) {
                vis[i] = true; // 방문 처리
                dfs(k - dungeons[i][1], dungeons, depth + 1); // 재귀 호출
                vis[i] = false; // 방문 초기화
            }
        }
        
        max = Math.max(max, depth);
    }
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        vis = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        answer = max;
        
        return answer;
    }
}

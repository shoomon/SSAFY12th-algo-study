
import java.util.*;
import java.io.*;

public class KAKAO_미로탈출명령어_mingyung {
	// 델타배열: d   l  r   u  (사전순)
    int[] dr = {1, 0, 0, -1};
    int[] dc = {0, -1, 1, 0};
    String answer;
    char[] a = {'d', 'l', 'r', 'u'};
    int N, M, R, C;
    
    // dfs 돌리는 거 멈추기 위한 flag
    boolean finish = false;
    
    // dfs
    public void dfs(int x, int y, int k, String ans) {
        // 이미 됐으면 더이상 안돌기
        if(finish) return;
        
        // 남은 이동 횟수로 도달 가능한지 체크
        int dis = Math.abs(x-R) + Math.abs(y-C);
        if (dis > k || (k - dis)%2 == 1) return;

        // k만큼 이동했는데 r, c에 도달했으면 벗어나기
        if (k==0 && x==R && y==C) {
            answer = ans;
            finish = true;
            return;
        }
        
        for (int d=0; d<4; d++) {
            int nr = x + dr[d];
            int nc = y + dc[d];

            if (nr>0 && nr<=N && nc>0 && nc<=M) {
                dfs(nr, nc, k-1, ans+a[d]);
            }
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "";
        N = n; M = m; R = r; C = c;
        
        // 도달 가능한지 체크
        // (x, y)에서 (r, c)까지의 거리(dis)가 k보다 작거나
        // k - dis가 홀수라면 불가능
        int dis = Math.abs(x-r) + Math.abs(y-c);
        if (dis > k || (k - dis)%2 == 1) return "impossible";
        
        // dfs로 돌기
        dfs(x, y, k, "");
        
        return answer;
    }
}

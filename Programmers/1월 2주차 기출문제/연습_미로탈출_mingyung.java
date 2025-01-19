import java.util.*;
import java.io.*;

public class 연습_미로탈출_mingyung {
	public int solution(String[] maps) {
        int answer = 0;
        
        int R = maps.length;
        int C = maps[0].length();
        char[][] map = new char[R][C];
        boolean[][] vis = new boolean[R][C];
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        // char 배열로 바꾸면서 시작점S, 출구E, 레버L 찾기
        int sR=0, sC=0;
        int eR=0, eC=0;
        int lR=0, lC=0;
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                map[r][c] = maps[r].charAt(c);
                if (map[r][c] == 'S') {
                    sR = r;
                    sC = c;
                } else if (map[r][c] == 'E') {
                    eR = r;
                    eC = c;
                } else if (map[r][c] == 'L') {
                    lR = r;
                    lC = c;
                }
            }
        }
        
        // 일단 레버로 가자
        Queue<int[]> q = new LinkedList<>();
        vis[sR][sC] = true;
        q.offer(new int[] {sR, sC, 0});
        boolean lever = false;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            
            if (r == lR && c == lC) {
                answer += cnt;
                lever = true;
                break;
            }
            
            for (int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc] != 'X' && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.offer(new int[] {nr, nc, cnt+1});
                }
            }
        }
        
        // 만약에 레버에 도달하지 못했다면 -1 return
        if (!lever) return -1;
        
        // 이번엔 도착점으로 가자
        q = new LinkedList<>();
        vis = new boolean[R][C];
        vis[lR][lC] = true;
        q.offer(new int[] {lR, lC, 0});
        boolean exit = false;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            
            if (r == eR && c == eC) {
                answer += cnt;
                exit = true;
                break;
            }
            
            for (int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc] != 'X' && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.offer(new int[] {nr, nc, cnt+1});
                }
            }
        }
        
        if (!exit) return -1;
        
        return answer;
    }
}

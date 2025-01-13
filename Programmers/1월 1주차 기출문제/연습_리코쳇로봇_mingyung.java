import java.util.*;
import java.io.*;

public class 연습_리코쳇로봇_mingyung {
	// 변수 설정
    static char[][] map; // 게임판
    static boolean[][] vis; // 방문배열
    static int min; // 이동 최소 횟수
    static int sR, sC, gR, gC, R, C; // 시작점, 도착점, 크기
    // 델타배열         상  하  좌  우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static int solution(String[] board) {
        int answer = 0;
        
        // 우선 map 배열 만들기
        R = board.length;
        C = board[0].length();
        map = new char[R][C];
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                map[r][c] = board[r].charAt(c);
                // 출발점 저장
                if ( map[r][c] == 'R') {
                    sR = r;
                    sC = c;
                }
                // 도착점 저장
                else if ( map[r][c] == 'G') {
                    gR = r;
                    gC = c;
                }
            }
        }
        
        // 필요한 변수 설정
        vis = new boolean[R][C];
        min = Integer.MAX_VALUE;
        
        find(sR, sC);
        
        answer = min == Integer.MAX_VALUE ? -1 : min;
        
        return answer;
    }
    
    static void find(int sR, int sC) {
    	Queue<int[]> q = new LinkedList<>();
    	vis[sR][sC] = true;
    	q.offer(new int[] {sR, sC, 0});
    	
    	while (!q.isEmpty()) {
    		int[] cur = q.poll();
    		int r = cur[0];
    		int c = cur[1];
    		int cnt = cur[2];
    		
    		if (r==gR && c==gC && min > cnt) {
    			min = cnt;
    			continue;
    		}
    		
    		for (int d=0; d<4; d++) {
    			int nr = r;
    			int nc = c;
    			
    			// 배열 벗어날 때까지 이동
    			while (nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc] != 'D') {
    				nr += dr[d];
    				nc += dc[d];
    			}
    			
    			// 배열 내로 다시 이동
    			nr -= dr[d];
				nc -= dc[d];
				
				if (!vis[nr][nc]) {
					vis[nr][nc] = true;
					q.offer(new int[] {nr, nc, cnt+1});
				}
    		}
    	}
    }
}

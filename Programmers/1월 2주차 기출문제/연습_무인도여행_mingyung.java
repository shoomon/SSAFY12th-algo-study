import java.util.*;
import java.io.*;

public class 연습_무인도여행_mingyung2 {
	public int[] solution(String[] maps) {
        int[] answer = {};
        
        int R = maps.length;
        int C = maps[0].length();
        int[][] map = new int[R][C];
        // map 변환
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (maps[r].charAt(c) == 'X') map[r][c] = 0;
                else map[r][c] = maps[r].charAt(c) - '0';
            }
        }
        boolean[][] vis= new boolean[R][C];
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 돌면서 섬 체크
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (map[r][c] != 0 && !vis[r][c]) {
                    Queue<int[]> q = new LinkedList<>();
                    int sum = map[r][c];
                    vis[r][c] = true;
                    q.offer(new int[] {r, c});
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        for (int d=0; d<4; d++) {
                            int nr = cur[0] + dr[d];
                            int nc = cur[1] + dc[d];
                            if (nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc] != 0 && !vis[nr][nc]) {
                                vis[nr][nc] = true;
                                sum += map[nr][nc];
                                q.offer(new int[] {nr, nc});
                            }
                        }
                    }
                    pq.offer(sum);
                }
            }
        }
        
        if (pq.isEmpty()) {
            answer = new int[] {-1};
        }
        else {
            answer = new int[pq.size()];
            int idx = 0;
            while (!pq.isEmpty()) {
                answer[idx++] = pq.poll();
            }
        }
        
        return answer;
    }
}


import java.util.*;
import java.io.*;

class PCCP_2_mingyung {
    public int solution(int[][] land) {
        int answer = 0;
        
        // 맴 돌며 붙은 석유 크기 확인
        int R = land.length;
        int C = land[0].length;
        int[][] points = new int[R][C]; // 덩어리 석유 체크
        int point = 1; // 덩어리 석유 번호
        HashMap<Integer, Integer> map = new HashMap<>(); // 추후 점수 더할 때 사용하기 위한 자료
        map.put(0, 0); // NullPointerException 방지
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                if (points[r][c]==0 && land[r][c]==1) {
                    Queue<int[]> q = new LinkedList<>();
                    points[r][c] = point;
                    int cnt = 1;
                    q.add(new int[] {r, c});
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        
                        for (int d=0; d<4; d++) {
                            int nr = cur[0] + dr[d];
                            int nc = cur[1] + dc[d];
                            
                            if (nr>=0 && nr<R && nc>=0 && nc<C && points[nr][nc]==0 && land[nr][nc]==1) {
                                points[nr][nc] = point;
                                cnt++;
                                q.add(new int[] {nr, nc});
                            }
                        }
                    } // while
                    
                    // 끝나면 덩어리 작업 끝남
                    map.put(point++, cnt);
                }
            }
        }
        
        // 열 우선순회 하면서 큰 값 찾기
        for (int c=0; c<C; c++) {
            Set<Integer> set = new HashSet<>();
            for (int r=0; r<R; r++) {
                set.add(points[r][c]);
            }
            
            int sum = 0;
            for (int num : set) {
                sum += map.get(num);
            }
            
            if (answer < sum) {
                answer = sum;
            }
        }
        
        return answer;
    }
}
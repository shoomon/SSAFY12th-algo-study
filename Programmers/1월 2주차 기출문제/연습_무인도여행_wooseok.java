package codingTest;

import java.util.*;

public class 연습_무인도여행_wooseok {

    static int n, m;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static boolean[][] vis;

    public static int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        vis = new boolean[n][m];

        // 결과 리스트는 적절히 크기 예측을 해서 미리 할당
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] || maps[i].charAt(j) == 'X') continue;
                // BFS 탐색을 통해 섬의 합 구하기
                int sum = bfs(i, j, maps);
                if (sum > 0) ans.add(sum);
            }
        }

        // 결과가 없다면 -1을 반환
        if (ans.isEmpty()) return new int[]{-1};

        // 오름차순으로 정렬하여 반환
        return ans.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    // BFS 탐색
    public static int bfs(int r, int c, String[] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        vis[r][c] = true;

        int sum = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            sum += maps[cur[0]].charAt(cur[1]) - '0';
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                // 경계값 체크 및 방문 여부를 한번에 처리
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || maps[nr].charAt(nc) == 'X' || vis[nr][nc]) continue;
                q.offer(new int[]{nr, nc});
                vis[nr][nc] = true;
            }
        }
        return sum;
    }
    public static void main(String[] args) {

        String[] maps1 = {"X591X", "X1X5X", "X231X", "1XXX1"};
        String[] maps2 = {"XXX", "XXX", "XXX"};
        System.out.println(Arrays.toString(solution(maps1))); // [10, 16]
        System.out.println(Arrays.toString(solution(maps2))); // [-1]

    }
}

class Solution {
    public int solution(String numbers) {        
        //대각선 포함
        //손 처음에 4, 6 
        //최솟값 구하기
        int answer = 0;
        return answer;
    }
    static int bfs(int start, int end) {
        if (start == end) return 1; // 제자리 누르기
        
        int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
        int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][3];

        int[] sPos = pos.get(start);
        int[] ePos = pos.get(end);

        queue.offer(new int[]{sPos[0], sPos[1], 0});
        visited[sPos[0]][sPos[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], cost = cur[2];

            if (x == ePos[0] && y == ePos[1]) return cost;

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < 4 && ny >= 0 && ny < 3 && pad[nx][ny] != -1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, cost + ((d < 4) ? 2 : 3)});
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}

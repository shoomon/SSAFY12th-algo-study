import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main { // 테트로미노

    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};  // 사방 탐색을 위한 상하좌우
    static int N, M;        // 세로, 가로 크기
    static int[][] map;
    static boolean[][] visited; // 방문 확인 배열
    static int maxSum = Integer.MIN_VALUE;      // 최대 합을 저장할 변수

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // (4 <= N, M <= 500)
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                visited[row][col] = true;
                // ㅜ 모양을 제외한 4가지 블록은 DFS로 진행
                dfs(row, col, 1, map[row][col]);
                visited[row][col] = false;
                // ㅜ 모양은 별도로 진행
                maxSum = Math.max(getLast(row, col), maxSum);
            }
        }

        System.out.println(maxSum);
    }

    private static int getLast(int row, int col) {
        int center = map[row][col];
        int cnt = 0;    // ㅜ 모양의 사방 탐색 성공 횟수
        int min = Integer.MAX_VALUE;  // 사방 탐색 시 가장 작은 값
        // 중심점을 기준으로 사방 탐색
        for (int d = 0; d < dirs.length; d++) {
            int newR = row + dirs[d][0];
            int newC = col + dirs[d][1];

            if (isIn(newR, newC)) {
                cnt++;
                center += map[newR][newC];
                min = Math.min(min, map[newR][newC]);
            }
        }
        // 4방향 모두 성공
        if (cnt == 4) {
            return center - min; // 가장 작은 값 빼기
        }
        // 3 방향만 성공
        else if (cnt == 3) {
            return center;
        } else {
            return -1;  // 유효하지 않으면 -1 리턴
        }
    }

    private static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    private static void dfs(int row, int col, int cnt, int sum) {
        if (cnt == 4) {
            maxSum = Math.max(maxSum, sum);  // 블록 4개가 채워지면 최대값 갱신
            return;
        }

        for (int d = 0; d < dirs.length; d++) {
            int newR = row + dirs[d][0];
            int newC = col + dirs[d][1];

            if (isIn(newR, newC) && !visited[newR][newC]) {
                visited[newR][newC] = true;
                dfs(newR, newC, cnt + 1, sum + map[newR][newC]);
                visited[newR][newC] = false;
            }
        }
    }
}

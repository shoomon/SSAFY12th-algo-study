import java.io.*;
import java.util.*;

public class Main {
    static int n, d = 0;
    static int[][] map;
    static Map<Integer, String> moveInfo;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        map = new int[n][n];
        moveInfo = new HashMap<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = 1; // 음식 위치
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            moveInfo.put(time, direction);
        }

        solve();
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int time = 0;
        int px = 0, py = 0;

        while (true) {
            // 방향 전환 확인
            if (moveInfo.containsKey(time)) {
                String direction = moveInfo.get(time);
                if (direction.equals("L")) {
                    d = (d + 3) % 4; // 왼쪽 회전
                } else if (direction.equals("D")) {
                    d = (d + 1) % 4; // 오른쪽 회전
                }
            }

            int nx = px + dx[d];
            int ny = py + dy[d];
            time++;

            // 벽에 부딪힘
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                break;
            }

            // 몸통에 부딪힘
            if (q.contains(ny * n + nx)) {
                break;
            }

            // 음식이 있으면 먹고 꼬리 이동 안 함
            if (map[ny][nx] == 1) {
                map[ny][nx] = 0; // 음식 먹음
                q.add(ny * n + nx); // 머리 위치 업데이트
            } else {
                q.add(ny * n + nx); // 머리 위치 업데이트
                q.poll(); // 꼬리 이동
            }

            px = nx;
            py = ny;
        }

        System.out.println(time); // 게임이 끝난 시간 출력
    }
}

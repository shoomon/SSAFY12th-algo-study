package codingTest;
import java.util.*;
import java.io.*;

class CCTV {
    int x, y, type;

    public CCTV(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

public class BOJ15683_wooseok {
    static int N, M;
    static int[][] map;
    static List<CCTV> cctvs = new ArrayList<>();
    static int minBlindSpots = Integer.MAX_VALUE;

    // 각 방향 (우, 하, 좌, 상)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    // CCTV가 있는 위치를 리스트에 저장
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        // CCTV 배치를 위한 DFS 시작
        dfs(0, map);

        System.out.println(minBlindSpots);
    }

    // CCTV 감시 범위를 지도에 그리는 함수
    static void watch(int[][] tempMap, int x, int y, int dir) {
        dir %= 4; // 4방향 기준으로 회전

        while (true) {
            x += dx[dir];
            y += dy[dir];

            if (x < 0 || x >= N || y < 0 || y >= M || tempMap[x][y] == 6) {
                // 맵의 경계나 벽(6)을 만나면 종료
                break;
            }

            if (tempMap[x][y] == 0) {
                tempMap[x][y] = 7; // 감시 영역을 7로 표시
            }
        }
    }

    // 현재 CCTV 상태에서 감시 영역을 체크하는 함수
    static int[][] check(int[][] map, CCTV cctv, int dir) {
        int[][] tempMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            tempMap[i] = map[i].clone();
        }

        if (cctv.type == 1) {
            watch(tempMap, cctv.x, cctv.y, dir);
        } else if (cctv.type == 2) {
            watch(tempMap, cctv.x, cctv.y, dir);
            watch(tempMap, cctv.x, cctv.y, dir + 2); // 반대방향
        } else if (cctv.type == 3) {
            watch(tempMap, cctv.x, cctv.y, dir);
            watch(tempMap, cctv.x, cctv.y, dir + 1); // 직각 방향
        } else if (cctv.type == 4) {
            watch(tempMap, cctv.x, cctv.y, dir);
            watch(tempMap, cctv.x, cctv.y, dir + 1);
            watch(tempMap, cctv.x, cctv.y, dir + 2); // 3방향
        } else if (cctv.type == 5) {
            for (int i = 0; i < 4; i++) {
                watch(tempMap, cctv.x, cctv.y, dir + i); // 4방향 모두
            }
        }

        return tempMap;
    }

    // DFS를 통해 CCTV 방향을 결정하고 최소 사각지대를 계산하는 함수
    static void dfs(int idx, int[][] map) {
        if (idx == cctvs.size()) {
            // 사각지대 계산
            minBlindSpots = Math.min(minBlindSpots, countBlindSpots(map));
            return;
        }

        CCTV cctv = cctvs.get(idx);
        int[][] tempMap;

        for (int dir = 0; dir < 4; dir++) {
            tempMap = check(map, cctv, dir);
            dfs(idx + 1, tempMap); // 다음 CCTV로 이동
            // 타입 5는 모든 방향이 동일하므로 1회만 체크
            if (cctv.type == 5) break;
            // 타입 2는 2번의 방향만 필요 (반대 방향만 의미 있음)
            if (cctv.type == 2 && dir == 1) break;
        }
    }

    // 사각지대(감시되지 않은 영역)를 세는 함수
    static int countBlindSpots(int[][] map) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}

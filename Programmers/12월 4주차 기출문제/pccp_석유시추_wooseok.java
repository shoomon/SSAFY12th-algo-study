package codingTest;

import java.util.*;

public class pccp_석유시추_wooseok {

    private final int[] dx = {0, 1, 0, -1}; // 상, 우, 하, 좌로 이동하기 위한 x축 변화량
    private final int[] dy = {-1, 0, 1, 0}; // 상, 우, 하, 좌로 이동하기 위한 y축 변화량

    public static int solution(int[][] land) {
        int[] columnOil = new int[land[0].length]; // 각 열에서 뽑을 수 있는 석유량

        for (int i = 0; i < land.length; i++) { // 땅의 모든 칸을 순회
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] == 1) { // 석유가 발견된 경우
                    // 현재 석유 덩어리를 탐색하여 석유 덩어리 크기를 계산
                    Set<Integer> affectedColumns = new HashSet<>();
                    int regionSize = bfs(land, i, j, affectedColumns);

                    // 탐색된 석유 덩어리가 영향을 미치는 열에 석유량 추가
                    for (int column : affectedColumns) {
                        columnOil[column] += regionSize;
                    }
                }
            }
        }

        // 가장 많은 석유를 뽑을 수 있는 시추관 위치의 석유량 반환
        return Arrays.stream(columnOil).max().orElse(0);
    }

    private static int bfs(int[][] land, int startX, int startY, Set<Integer> affectedColumns) {
        int regionSize = 0; // 현재 석유 덩어리의 크기
        Queue<int[]> queue = new LinkedList<>(); // BFS를 위한 큐
        queue.add(new int[]{startX, startY});
        land[startX][startY] = -1; // 방문한 칸은 -1로 표시하여 중복 방문 방지

        int[] dx = {0, 1, 0, -1}; // 상, 우, 하, 좌로 이동하기 위한 x축 변화량
        int[] dy = {-1, 0, 1, 0}; // 상, 우, 하, 좌로 이동하기 위한 y축 변화량

        while (!queue.isEmpty()) {
            int[] current = queue.poll(); // 큐에서 현재 위치를 가져옴
            int x = current[0];
            int y = current[1];
            regionSize++; // 석유 덩어리 크기 증가

            // 영향을 미치는 열 기록
            affectedColumns.add(y);

            // 상, 우, 하, 좌로 이동하며 연결된 석유 덩어리를 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValid(land, nx, ny)) { // 유효한 위치인지 확인
                    queue.add(new int[]{nx, ny}); // 큐에 추가하여 BFS 탐색 계속
                    land[nx][ny] = -1; // 방문 처리
                }
            }
        }

        return regionSize; // 현재 석유 덩어리의 크기 반환
    }

    private static boolean isValid(int[][] land, int x, int y) {
        // 주어진 좌표가 땅의 범위 내에 있고 석유가 존재하는지 확인
        return x >= 0 && x < land.length && y >= 0 && y < land[0].length && land[x][y] == 1;
    }

    public static void main(String[] args) {
        // 입출력 예 1
        int[][] land1 = {
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1}
        };
        System.out.println("Result for land1: " + solution(land1)); // Expected: 9

        // 입출력 예 2
        int[][] land2 = {
                {1, 0, 1, 0, 1, 1},
                {1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1}
        };
        System.out.println("Result for land2: " + solution(land2)); // Expected: 16
    }
}

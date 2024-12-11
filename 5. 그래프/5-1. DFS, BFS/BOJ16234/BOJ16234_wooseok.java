// 24.12.11
// BFS
// 메모리 : 296036 KB
// 시간 : 520 ms

package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234_wooseok {

    // 방향 배열: 상하좌우 이동을 나타냄
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map; // 인구 수를 저장하는 지도
    static boolean[][] visited; // 방문 여부를 기록하는 배열
    static int N, L, R, day; // 지도 크기 N, 최소 인구 차 L, 최대 인구 차 R, 날짜(day)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 줄 입력: N, L, R
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N]; // N x N 크기의 지도 생성

        // 지도 데이터 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 인구 이동 시뮬레이션 실행
        while (move()) {
            day++; // 인구 이동이 발생할 때마다 날짜 증가
        }
        System.out.println(day); // 모든 이동이 종료된 후 총 날짜 출력
    }

    // 인구 이동을 수행하는 메서드
    private static boolean move() {
        boolean flag = false; // 인구 이동 발생 여부
        visited = new boolean[N][N]; // 방문 여부 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 방문하지 않은 국가에 대해 BFS 실행
                if (!visited[i][j]) {
                    if (bfs(i, j)) { // BFS 결과 인구 이동 발생 시
                        flag = true; // 플래그 true로 설정
                    }
                }
            }
        }
        return flag; // 인구 이동 발생 여부 반환
    }

    // BFS를 통해 그룹을 형성하고 인구 이동 처리
    private static boolean bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>(); // BFS 탐색용 큐
        ArrayList<int[]> group = new ArrayList<>(); // 그룹에 포함된 국가 좌표 리스트
        queue.add(new int[]{x, y}); // 시작점 큐에 추가
        group.add(new int[]{x, y}); // 시작점 그룹에 추가
        visited[x][y] = true; // 시작점 방문 처리

        int sum = map[x][y]; // 그룹의 총 인구수

        // BFS 탐색
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            // 상하좌우 인접 국가 탐색
            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                // 지도 범위를 벗어나지 않고, 방문하지 않았으며, 인구 차 조건을 만족하는 경우
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(map[cx][cy] - map[nx][ny]); // 인구 차 계산
                    if (diff >= L && diff <= R) { // 인구 차가 조건을 만족하면
                        visited[nx][ny] = true; // 방문 처리
                        queue.add(new int[]{nx, ny}); // 큐에 추가
                        group.add(new int[]{nx, ny}); // 그룹에 추가
                        sum += map[nx][ny]; // 그룹 총 인구수 업데이트
                    }
                }
            }
        }

        // 그룹의 크기가 2 이상인 경우 인구 이동 발생
        if (group.size() > 1) {
            int avg = sum / group.size(); // 그룹 내 국가들의 평균 인구 계산
            for (int[] pos : group) {
                map[pos[0]][pos[1]] = avg; // 그룹 내 모든 국가에 평균 인구 할당
            }
            return true; // 인구 이동 발생
        }
        return false; // 인구 이동 없음
    }
}


// 24.12.11
// DFS
// 메모리 : 293688 KB
// 시간 : 532 ms
/*
package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16234_wooseok {

    // 상하좌우 이동을 나타내는 방향 배열
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map; // 국가별 인구수를 저장하는 지도
    static boolean[][] visited; // 방문 여부를 저장하는 배열
    static int N, L, R, day; // 지도 크기 N, 최소 인구 차 L, 최대 인구 차 R, 총 날짜(day)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리: N, L, R
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N]; // N x N 크기의 지도 생성

        // 지도 데이터 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 인구 이동 시뮬레이션
        while (simulatePopulationMovement()) {
            day++; // 인구 이동이 발생할 때마다 날짜 증가
        }

        // 총 날짜 출력
        System.out.println(day);
    }

    // 인구 이동을 시뮬레이션하는 메서드
    private static boolean simulatePopulationMovement() {
        visited = new boolean[N][N]; // 방문 배열 초기화
        boolean hasMoved = false; // 인구 이동 여부 확인 플래그

        // 모든 좌표를 순회하며 그룹 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    // DFS로 그룹을 형성하고 인구 이동 처리
                    ArrayList<int[]> group = new ArrayList<>();
                    int totalPopulation = exploreGroup(i, j, group);

                    // 그룹의 크기가 2 이상인 경우 인구 이동 발생
                    if (group.size() > 1) {
                        redistributePopulation(group, totalPopulation);
                        hasMoved = true;
                    }
                }
            }
        }
        return hasMoved;
    }

    // DFS로 그룹을 형성하고 그룹 내 총 인구수를 반환
    private static int exploreGroup(int x, int y, ArrayList<int[]> group) {
        visited[x][y] = true; // 방문 처리
        group.add(new int[]{x, y}); // 현재 좌표를 그룹에 추가
        int totalPopulation = map[x][y]; // 현재 좌표의 인구수

        // 상하좌우 인접 국가 탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위를 벗어나는지 체크
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                int diff = Math.abs(map[x][y] - map[nx][ny]); // 인구 차 계산
                // 인구 차가 조건(L 이상 R 이하)을 만족하는 경우
                if (diff >= L && diff <= R) {
                    totalPopulation += exploreGroup(nx, ny, group); // 재귀 호출로 그룹 확장
                }
            }
        }
        return totalPopulation; // 그룹 내 총 인구수 반환
    }

    // 그룹 내 모든 국가에 평균 인구수 재분배
    private static void redistributePopulation(ArrayList<int[]> group, int totalPopulation) {
        int averagePopulation = totalPopulation / group.size(); // 그룹의 평균 인구수 계산
        for (int[] pos : group) {
            map[pos[0]][pos[1]] = averagePopulation; // 평균 인구수로 업데이트
        }
    }
}

*/

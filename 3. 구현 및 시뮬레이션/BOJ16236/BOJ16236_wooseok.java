//24.12.11
//BFS 탐색
//시간복잡도 O(N⁴)
//메모리: 17600 kb
//시간: 104 ms

package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236_wooseok {
    // 상어가 이동할 수 있는 방향(상, 하, 좌, 우)
    static int[] dR = {1, -1, 0, 0};
    static int[] dC = {0, 0, -1, 1};

    static int N; // 공간의 크기 (N x N)
    static int[][] ocean; // 공간의 상태를 저장하는 배열

    // 좌표와 거리를 저장하는 Point 클래스
    private static class Point implements Comparable<Point> {
        int r, c, d; // r: 행, c: 열, d: 거리

        Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Point o) {
            // 거리(d)를 우선으로 비교
            if (this.d != o.d) return this.d - o.d;
            // 거리가 같다면 행(r)을 우선으로 비교
            if (this.r != o.r) return this.r - o.r;
            // 행도 같다면 열(c)을 비교
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: 공간 크기
        N = Integer.parseInt(br.readLine());
        ocean = new int[N][N]; // 공간 배열 초기화

        int sharkR = 0, sharkC = 0; // 상어의 초기 위치

        // 공간 상태 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ocean[i][j] = Integer.parseInt(st.nextToken());
                if (ocean[i][j] == 9) { // 상어의 위치
                    sharkR = i;
                    sharkC = j;
                    ocean[i][j] = 0; // 상어가 있던 자리는 빈칸(0)으로 설정
                }
            }
        }

        // 결과 출력: 상어가 이동하며 걸린 총 시간
        System.out.println(solve(sharkR, sharkC));
    }

    // 상어의 이동 및 먹이를 찾는 메서드
    private static int solve(int r, int c) {
        int time = 0; // 총 이동 시간
        int size = 2; // 상어의 초기 크기
        int cnt = 0;  // 현재 크기에서 먹은 물고기 수

        while (true) {
            // bfs를 통해 가장 가까운 먹이를 찾음
            Point target = bfs(r, c, size);
            if (target == null) break; // 먹을 물고기가 없으면 종료

            time += target.d; // 먹은 물고기까지 이동한 시간 추가
            r = target.r;
            c = target.c;
            ocean[r][c] = 0; // 물고기를 먹은 위치를 빈칸으로 설정
            cnt++; // 먹은 물고기 수 증가

            if (cnt == size) { // 상어가 자신의 크기만큼 물고기를 먹으면 크기 증가
                size++;
                cnt = 0; // 먹은 물고기 수 초기화
            }
        }

        return time; // 상어가 이동하며 소모한 총 시간 반환
    }

    // BFS를 통해 가장 가까운 먹이를 찾는 메서드
    private static Point bfs(int startR, int startC, int size) {
        boolean[][] visited = new boolean[N][N]; // 방문 체크 배열
        PriorityQueue<Point> pq = new PriorityQueue<>(); // 우선순위 큐
        Queue<Point> queue = new LinkedList<>(); // BFS 탐색 큐

        // BFS 시작점 설정
        queue.add(new Point(startR, startC, 0));
        visited[startR][startC] = true;

        Point target = null; // 가장 가까운 먹이

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newR = now.r + dR[i];
                int newC = now.c + dC[i];

                // 배열 범위 밖으로 나가는 경우 무시
                if (newR < 0 || newR >= N || newC < 0 || newC >= N) continue;
                // 이미 방문했거나, 상어 크기보다 큰 물고기가 있는 경우 무시
                if (visited[newR][newC] || ocean[newR][newC] > size) continue;

                visited[newR][newC] = true;
                Point next = new Point(newR, newC, now.d + 1);

                // 먹을 수 있는 물고기인 경우
                if (ocean[newR][newC] != 0 && ocean[newR][newC] < size) {
                    pq.add(next); // 우선순위 큐에 추가
                } else {
                    queue.add(next); // 그냥 지나갈 수 있는 경우 큐에 추가
                }
            }
        }

        if (!pq.isEmpty()) target = pq.poll(); // 우선순위 큐에서 가장 가까운 먹이 선택
        return target; // 찾은 물고기 반환 (없으면 null 반환)
    }
}
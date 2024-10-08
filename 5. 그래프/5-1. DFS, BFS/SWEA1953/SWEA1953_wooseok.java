import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
 
    // 좌표를 저장할 클래스
    static class Point {
        int x, y;
        ArrayList<Integer> directions;
 
        Point(int x, int y, ArrayList<Integer> directions) {
            this.x = x;
            this.y = y;
            this.directions = directions;
        }
    }
 
    static StringBuilder result;
    static int N, M, R, C, L; // 맵 크기와 맨홀 위치, 탈출 가능 시간
    static int[][] map; // 터널 정보를 저장할 맵
    static boolean[][] visited; // 방문 여부를 저장하는 배열
    static final int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; // 상, 하, 좌, 우 방향
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        result = new StringBuilder();
 
        for (int testCase = 1; testCase <= T; testCase++) {
            // 테스트 케이스 번호 출력 준비
            result.append("#").append(testCase).append(" ");
 
            // 입력 처리
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 맵 세로 크기
            M = Integer.parseInt(st.nextToken()); // 맵 가로 크기
            R = Integer.parseInt(st.nextToken()); // 맨홀 Row 위치
            C = Integer.parseInt(st.nextToken()); // 맨홀 Column 위치
            L = Integer.parseInt(st.nextToken()); // 탈출 후 경과 시간
 
            // 맵 및 방문 배열 초기화
            map = new int[N][M];
            visited = new boolean[N][M];
 
            // 맵 입력 받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            // 맨홀 위치에서 탐색 시작
            visited[R][C] = true;
            bfs(new Point(R, C, getDirections(R, C)));
        }
 
        // 결과 출력
        System.out.print(result.toString().trim());
    }
 
    // 해당 좌표에서 이동 가능한 방향을 반환
    private static ArrayList<Integer> getDirections(int x, int y) {
        int currentTunnel = map[x][y];
        ArrayList<Integer> validDirections = new ArrayList<>();
 
        switch (currentTunnel) {
            case 1: // 상, 하, 좌, 우 이동 가능
                validDirections.add(0); // 상
                validDirections.add(1); // 하
                validDirections.add(2); // 좌
                validDirections.add(3); // 우
                break;
            case 2: // 상, 하 이동 가능
                validDirections.add(0); // 상
                validDirections.add(1); // 하
                break;
            case 3: // 좌, 우 이동 가능
                validDirections.add(2); // 좌
                validDirections.add(3); // 우
                break;
            case 4: // 상, 우 이동 가능
                validDirections.add(0); // 상
                validDirections.add(3); // 우
                break;
            case 5: // 하, 우 이동 가능
                validDirections.add(1); // 하
                validDirections.add(3); // 우
                break;
            case 6: // 하, 좌 이동 가능
                validDirections.add(1); // 하
                validDirections.add(2); // 좌
                break;
            case 7: // 상, 좌 이동 가능
                validDirections.add(0); // 상
                validDirections.add(2); // 좌
                break;
        }
        return validDirections;
    }
 
    // BFS 탐색 수행
    private static void bfs(Point startPoint) {
        int reachableCount = 1;
        Queue<Point> queue = new ArrayDeque<>();
        Queue<Point> nextPoints = new ArrayDeque<>(); // 임시 저장소
 
        queue.add(startPoint);
 
        while (L > 1) {
            while (!queue.isEmpty()) {
                Point current = queue.poll();
 
                // 현재 위치에서 이동할 수 있는 모든 방향 탐색
                for (int directionIndex : current.directions) {
                    int nx = current.x + directions[directionIndex][0];
                    int ny = current.y + directions[directionIndex][1];
 
                    // 범위를 벗어났거나, 이동 불가능한 경우 패스
                    if (!isInBounds(nx, ny) || map[nx][ny] == 0 || visited[nx][ny]) continue;
 
                    Point nextPoint = new Point(nx, ny, getDirections(nx, ny));
 
                    // 연결이 가능한지 확인
                    if (!isConnected(directionIndex, nextPoint)) continue;
 
                    // 방문 처리 후 임시 큐에 추가
                    nextPoints.add(nextPoint);
                    visited[nx][ny] = true;
                    reachableCount++;
                }
            }
 
            // 다음 단계로 진행
            queue.addAll(nextPoints);
            nextPoints.clear();
            L--; // 남은 시간 감소
        }
 
        // 결과 저장
        result.append(reachableCount).append("\n");
    }
 
    // 현재 파이프와 다음 파이프가 연결되어 있는지 확인
    private static boolean isConnected(int direction, Point nextPoint) {
        // 상(0) 또는 좌(2)의 경우는 다음 파이프가 하(1) 또는 우(3)여야 연결됨
        if (direction % 2 == 1) {
            return nextPoint.directions.contains(direction - 1);
        } else {
            return nextPoint.directions.contains(direction + 1);
        }
    }
 
    // 좌표가 맵 내에 있는지 확인
    private static boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
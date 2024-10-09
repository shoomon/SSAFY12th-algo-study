import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 맵의 행과 열
    static int rows, cols;
    // 지도 배열
    static int[][] map;
    // 상하좌우로 이동할 방향을 나타내는 배열
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    // 최대 안전 구역의 크기를 저장할 변수
    static int maxSafeArea = 0;

    // 바이러스의 위치를 저장할 클래스
    static class Position {
        int x, y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        rows = Integer.parseInt(tokenizer.nextToken());  // 행 수
        cols = Integer.parseInt(tokenizer.nextToken());  // 열 수

        // 지도 배열 초기화
        map = new int[rows][cols];

        // 지도 데이터 입력 받기
        for (int i = 0; i < rows; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < cols; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());  // 지도에 각 위치의 값 입력
            }
        }

        // 벽 3개 설치 시작
        buildWalls(0, 0);
        // 최대로 확보한 안전 구역 출력
        System.out.println(maxSafeArea);
    }

    // 벽을 3개 설치하는 함수
    public static void buildWalls(int wallCount, int index) {
        // 벽이 3개 설치되면 안전 구역을 계산
        if (wallCount == 3) {
            // 현재 상황에서 안전 구역 계산
            maxSafeArea = Math.max(maxSafeArea, calculateSafeArea());
            return;
        }

        // 1차원 인덱스에서 시작 행과 열을 계산
        int startRow = index / cols;
        int startCol = index % cols;

        // 벽을 설치할 빈 공간 찾기
        for (int i = startRow; i < rows; i++) {
            for (int j = startCol; j < cols; j++) {
                if (map[i][j] == 0) {  // 빈 공간(0)일 때
                    map[i][j] = 1;  // 벽 설치
                    // 벽을 추가로 설치하고 다음 인덱스에서 다시 재귀 호출
                    buildWalls(wallCount + 1, i * cols + j + 1);
                    map[i][j] = 0;  // 벽 원상 복구 (백트래킹)
                }
            }
            startCol = 0;  // 다음 행의 처음 열부터 탐색
        }
    }

    // 바이러스가 퍼진 후 안전 구역의 크기를 계산하는 함수
    public static int calculateSafeArea() {
        // 임시 맵을 만들어서 현재 상태를 복사
        int[][] tempMap = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            tempMap[i] = map[i].clone();  // 배열 깊은 복사
        }

        // 바이러스의 위치를 저장할 큐 생성
        Queue<Position> queue = new LinkedList<>();
        // 바이러스 위치를 큐에 저장
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tempMap[i][j] == 2) {  // 바이러스(2)가 있는 경우
                    queue.offer(new Position(i, j));  // 큐에 바이러스 좌표 추가
                }
            }
        }

        // BFS로 바이러스를 퍼뜨리는 과정
        while (!queue.isEmpty()) {
            Position virus = queue.poll();  // 큐에서 바이러스 위치를 꺼냄
            for (int d = 0; d < 4; d++) {  // 상하좌우로 탐색
                int nx = virus.x + dx[d];  // 다음 이동할 x좌표
                int ny = virus.y + dy[d];  // 다음 이동할 y좌표

                // 맵 범위 안에서 빈 공간(0)인 경우 바이러스 퍼뜨리기
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && tempMap[nx][ny] == 0) {
                    tempMap[nx][ny] = 2;  // 바이러스 퍼뜨리기
                    queue.offer(new Position(nx, ny));  // 큐에 다음 바이러스 위치 추가
                }
            }
        }

        // 안전 구역 계산 (빈 공간 개수 세기)
        int safeCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tempMap[i][j] == 0) {  // 빈 공간(0)일 때 안전 구역으로 간주
                    safeCount++;
                }
            }
        }
        return safeCount;  // 안전 구역 개수 반환
    }
}
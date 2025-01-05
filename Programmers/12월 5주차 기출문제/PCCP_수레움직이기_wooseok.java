package codingTest;
import java.util.*;

public class PCCP_수레움직이기_wooseok {

    // 최소 이동 횟수를 저장 (초기값: Integer.MAX_VALUE)
    static int answer = Integer.MAX_VALUE;

    // 이동 방향 (상, 하, 좌, 우)
    static int[] arx = {-1, 1, 0, 0};
    static int[] ary = {0, 0, -1, 1};

    // 빨간색과 파란색 방문 여부를 확인하는 배열
    static boolean[][] red;
    static boolean[][] blue;

    // 미로 배열 복사
    static int[][] map;

    // 미로의 크기 (a: 행, b: 열)
    static int a, b;

    // 빨간색과 파란색의 도착 위치
    static int rex, rey, bex, bey;

    /**
     * @param maze 미로를 나타내는 2D 배열
     *             maze[i][j]의 의미:
     *             - 0: 빈칸
     *             - 1: 빨간 수레의 시작 칸
     *             - 2: 파란 수레의 시작 칸
     *             - 3: 빨간 수레의 도착 칸
     *             - 4: 파란 수레의 도착 칸
     *             - 5: 벽
     * @return 빨간 수레와 파란 수레가 각각 도착 지점에 도달하는 최소 이동 횟수
     */
    public static int solution(int[][] maze) {
        int rx = 0; // 빨간 수레의 시작 x 좌표
        int ry = 0; // 빨간 수레의 시작 y 좌표
        int bx = 0; // 파란 수레의 시작 x 좌표
        int by = 0; // 파란 수레의 시작 y 좌표

        a = maze.length;   // 행의 크기
        b = maze[0].length; // 열의 크기
        map = new int[a][b]; // 미로 배열 복사
        red = new boolean[a][b]; // 빨간 수레 방문 여부
        blue = new boolean[a][b]; // 파란 수레 방문 여부

        // 미로 초기 설정
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (maze[i][j] == 0) continue; // 빈칸은 건너뜀
                if (maze[i][j] == 1) { // 빨간 수레의 시작 칸
                    red[i][j] = true;
                    rx = i;
                    ry = j;
                } else if (maze[i][j] == 2) { // 파란 수레의 시작 칸
                    blue[i][j] = true;
                    bx = i;
                    by = j;
                } else if (maze[i][j] == 3) { // 빨간 수레의 도착 칸
                    rex = i;
                    rey = j;
                } else if (maze[i][j] == 4) { // 파란 수레의 도착 칸
                    bex = i;
                    bey = j;
                } else if (maze[i][j] == 5) { // 벽
                    map[i][j] = maze[i][j];
                }
            }
        }

        // 백트래킹 시작
        move(rx, ry, bx, by, 0, false, false);

        // 결과 반환 (불가능한 경우 0)
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    // 공을 이동시키는 백트래킹 함수
    public static void move(int rx, int ry, int bx, int by, int move, boolean red_end, boolean blue_end) {

        // 빨간 수레가 도착하면 red_end를 true로 변경
        if (!red_end && rx == rex && ry == rey) red_end = true;

        // 파란 수레가 도착하면 blue_end를 true로 변경
        if (!blue_end && bx == bex && by == bey) blue_end = true;

        // 두 수레가 모두 도착한 경우 최소 이동 횟수를 갱신 후 종료
        if (red_end && blue_end) {
            answer = Math.min(answer, move);
            return;
        }

        // 이동 가능한 위치를 저장
        ArrayList<int[]> r_list = new ArrayList<>(); // 빨간 수레
        ArrayList<int[]> b_list = new ArrayList<>(); // 파란 수레

        // 빨간 수레 이동 가능한 위치 탐색
        if (!red_end) {
            for (int i = 0; i < 4; i++) {
                int nx = arx[i] + rx;
                int ny = ary[i] + ry;
                if (validation(nx, ny) && map[nx][ny] != 5 && !red[nx][ny])
                    r_list.add(new int[]{nx, ny});
            }
        } else {
            r_list.add(new int[]{rx, ry}); // 이미 도착한 경우 제자리 유지
        }

        // 파란 수레 이동 가능한 위치 탐색
        if (!blue_end) {
            for (int i = 0; i < 4; i++) {
                int nx = arx[i] + bx;
                int ny = ary[i] + by;
                if (validation(nx, ny) && map[nx][ny] != 5 && !blue[nx][ny])
                    b_list.add(new int[]{nx, ny});
            }
        } else {
            b_list.add(new int[]{bx, by}); // 이미 도착한 경우 제자리 유지
        }

        // 빨간 수레와 파란 수레의 모든 이동 조합 탐색
        for (int i = 0; i < r_list.size(); i++) {
            int[] r_arr = r_list.get(i);
            for (int j = 0; j < b_list.size(); j++) {
                int[] b_arr = b_list.get(j);

                // 두 수레가 같은 위치로 이동할 수 없음
                if (r_arr[0] == b_arr[0] && r_arr[1] == b_arr[1]) continue;

                // 두 수레가 서로의 위치를 교환할 수 없음
                if (rx == b_arr[0] && ry == b_arr[1] && bx == r_arr[0] && by == r_arr[1]) continue;

                // 현재 이동 위치 방문 처리
                red[r_arr[0]][r_arr[1]] = true;
                blue[b_arr[0]][b_arr[1]] = true;

                // 재귀적으로 다음 이동 탐색
                move(r_arr[0], r_arr[1], b_arr[0], b_arr[1], move + 1, red_end, blue_end);

                // 방문 해제
                red[r_arr[0]][r_arr[1]] = false;
                blue[b_arr[0]][b_arr[1]] = false;
            }
        }
    }

    // 유효한 좌표인지 확인하는 함수
    public static boolean validation(int nx, int ny) {
        return 0 <= nx && nx < a && 0 <= ny && ny < b;
    }

    // 메인 함수
    public static void main(String[] args) {
        int[][] maze = {
                {1, 0, 2}, // 빨간 수레 시작(1), 빈칸(0), 파란 수레 시작(2)
                {0, 0, 0}, // 빈칸(0)
                {5, 0, 5}, // 벽(5), 빈칸(0), 벽(5)
                {4, 0, 3}  // 파란 수레 도착(4), 빈칸(0), 빨간 수레 도착(3)
        };
        System.out.println(solution(maze)); // 최소 이동 횟수 출력
    }
}


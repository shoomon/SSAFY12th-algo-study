package codingTest;

import java.util.*;

public class 연습_미로탈출_wooseok {

    private static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int n, m;
    private static char[][] map;

    public static int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];

        Point start = null, lever = null, exit = null;

        // 맵 초기화 및 시작, 레버, 종료 위치 설정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'S') start = new Point(i, j, 0);
                else if (map[i][j] == 'L') lever = new Point(i, j, 0);
                else if (map[i][j] == 'E') exit = new Point(i, j, 0);
            }
        }

        // BFS로 거리 계산
        int startToLever = bfs(start, lever);
        if (startToLever == -1) return -1;

        int leverToExit = bfs(lever, exit);
        if (leverToExit == -1) return -1;

        return startToLever + leverToExit;
    }

    private static int bfs(Point start, Point target) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 목표 지점 도달 시 거리 반환
            if (current.x == target.x && current.y == target.y) {
                return current.depth;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isValid(nx, ny, visited)) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, current.depth + 1));
                }
            }
        }

        return -1; // 도달 불가
    }

    private static boolean isValid(int x, int y, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < n && y < m && !visited[x][y] && map[x][y] != 'X';
    }

    private static class Point {
        int x, y, depth;

        Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }


    public static void main(String[] args) {
        String[] maps1 = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        String[] maps2 = {"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"};

        System.out.println(solution(maps1));
        System.out.println(solution(maps2));

    }
}

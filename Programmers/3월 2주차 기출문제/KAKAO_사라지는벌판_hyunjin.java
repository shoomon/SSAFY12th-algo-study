class Solution {

    static class Point {
        int x, y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[][] board;
    static int n, m;
    static int answer = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        // 게임 board
        this.board = board;
        n = board.length;
        m = board[0].length;

        // 플레이어 A와 B의 시작 위치
        Point pointA = new Point(aloc[0], aloc[1]);
        Point pointB = new Point(bloc[0], bloc[1]);

        answer = dfs(pointA, pointB);


        return answer;
    }

    // dfs 결과 
    // "my(A)"가 먼저 시작하므로, 
    // 현재 플레이어가 이동할 수 없다면 패배 (턴 수: 0).
    // 게임이 끝나는 순간까지의 총 턴 수가 짝수라면, 내가 반드시 질 수밖에 없는 게임.
    // 게임이 끝나는 순간까지의 총 턴 수가 홀수라면, 내가 반드시 이길 수 있는 게임.

    public static int dfs(Point my, Point your){
        if(board[my.x][my.y] == 0) return 0;

        int currX = my.x;
        int currY = my.y;

        int result = 0; // 최종 걸린 게임 턴 수

        for(int dir = 0; dir < 4; dir++){
            int nx = currX + dx[dir];
            int ny = currY + dy[dir];

            if(nx < 0 || nx >= n || ny <0 || ny >= m || board[nx][ny] == 0) continue;

            // 이동 시 -> 기존의 위치 빈 칸으로 변경
            board[currX][currY] = 0;

            // 상대방 턴 => dfs 매개변수를 dfs(your, my) 순으로 진행
            // 상대방 턴이므로 your이 my가 됨.
            // val : 턴수 + 1
            int val = dfs(your, new Point(nx, ny)) + 1;

            board[currX][currY] = 1; // 사용한 칸을 원상 복구

            // val % 2 == 1 (홀수) => "이기는 경우"
            // val % 2 == 0 (짝수) => "지는 경우"

            // 지금까지 모두 진 경우 + 이번에 이겼을 때 => 승리하는 방법을 선택
            if(result % 2 == 0 && val % 2 == 1) result = val;

                // 지금까지 모두 진 경우, + 이번에도 진 경우  => 패배 확정 : 최대한 많이 움직인다.
            else if(result % 2 == 0 && val % 2 == 0) result = Math.max(result, val);

                // 지금까지 이긴 경우, + 이번에도 이긴 경우  => 최대한 적게 움직인다.
            else if(val % 2 == 1 && result % 2 == 1) result = Math.min(result, val);

        }

        return result;
    }
}
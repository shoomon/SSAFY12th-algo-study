import java.util.*;

public class Code17070 {
    static int count=0;
    static int[][] home;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        home = new int[N][N];
        int[][] pipePos = {{0,0}, {0,1}};

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                home[i][j] = sc.nextInt();
            }
        }

        DFS(pipePos);
        System.out.println(count);
    }

    static void DFS(int[][] curPos) {
        if(curPos[1][0] == N-1 && curPos[1][1] == N-1) {
            count++;
            return;
        }
//        System.out.println(curPos[0][0]+" "+curPos[0][1]+" "+curPos[1][0]+" "+curPos[1][1]);

        //파이프가 이동할 때, 파이프 꼬리 (curPos[0])는 파이프 머리 (curPos[1])의 위치로 온다.
        //파이프가 가로로 놓인 경우
        if(curPos[0][0] == curPos[1][0] && curPos[0][1]+1 == curPos[1][1]) {
            //가로로 이동 가능한지
            if(check(curPos[1][0], curPos[1][1]+1) && home[curPos[1][0]][curPos[1][1]+1] == 0) {
                //행 그대로 열 한 칸씩 오른쪽으로
                int[][] next = {curPos[1],{curPos[1][0], curPos[1][1]+1}};
                DFS(next);
            }
            //대각선으로 이동 가능한지
            if(check(curPos[1][0]+1, curPos[1][1]+1) && home[curPos[1][0]+1][curPos[1][1]+1] == 0 && home[curPos[1][0]][curPos[1][1]+1] == 0 && home[curPos[1][0]+1][curPos[1][1]] == 0) {
                //curPos[1]은 행 열 한 칸씩 증가
                int[][] next = {curPos[1],{curPos[1][0]+1, curPos[1][1]+1}};
                DFS(next);
            }

        }
        //파이프가 세로로 놓인 경우
        else if(curPos[0][0]+1 == curPos[1][0] && curPos[0][1] == curPos[1][1]) {
            //세로로 이동 가능한지
            if(check(curPos[1][0]+1, curPos[1][1]) && home[curPos[1][0]+1][curPos[1][1]] == 0) {
                //열 그대로 행 한 칸씩 증가
                int[][] next = {curPos[1],{curPos[1][0]+1, curPos[1][1]}};
                DFS(next);
            }
            //대각선으로 이동 가능한지
            if(check(curPos[1][0]+1, curPos[1][1]+1) && home[curPos[1][0]+1][curPos[1][1]+1] == 0 && home[curPos[1][0]][curPos[1][1]+1] == 0 && home[curPos[1][0]+1][curPos[1][1]] == 0) {
                // curPos[1] 행, 열 한 칸씩 증가
                int[][] next = {curPos[1],{curPos[1][0]+1, curPos[1][1]+1}};
                DFS(next);
            }
        }
        //파이프가 대각선으로 놓인 경우
        else if(curPos[0][0]+1 == curPos[1][0] && curPos[0][1]+1 == curPos[1][1]) {
            //가로로 이동 가능한지
            if(check(curPos[1][0], curPos[1][1]+1) && home[curPos[1][0]][curPos[1][1]+1] == 0) {
                //curPos[1] 열 한 칸 증가
                int[][] next = {curPos[1],{curPos[1][0], curPos[1][1]+1}};
                DFS(next);
            }
            //세로로 이동 가능한지
            if(check(curPos[1][0]+1, curPos[1][1]) && home[curPos[1][0]+1][curPos[1][1]] == 0) {
                int[][] next = {curPos[1],{curPos[1][0]+1, curPos[1][1]}};
                DFS(next);
            }
            //대각선으로 이동 가능한지
            if(check(curPos[1][0]+1, curPos[1][1]+1) && home[curPos[1][0]+1][curPos[1][1]+1] == 0 && home[curPos[1][0]][curPos[1][1]+1] == 0 && home[curPos[1][0]+1][curPos[1][1]] == 0) {
                int[][] next = {curPos[1],{curPos[1][0]+1, curPos[1][1]+1}};
                DFS(next);
            }
        }

    }

    static boolean check(int r, int c) {
        if(-1 < r && r < N && -1 < c && c < N) {
            return true;
        }
        return false;
    }

}

import java.util.*;
import java.io.*;
//날짜 24.10.07
//설계 시간: 20분
//구현 시간: 25분
//메모리: 14496 kb
//시간: 112 ms
//문제를 잘못 이해했다. 주사위에 숫자가 안 써있는 줄 몰랐다. 문제 조건에 없어서 테스트케이스를 직접 시뮬레이션 해봐야 유추 가능 했다.
//구현보다는 문제 해석이 약간 쉽지 않았다.
//아니 왜 x가 행이고 y가 열이야
public class Main {
    static class Position{
        int x,y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, x, y, K;
    static int[][] map, dice;
    static int[] command;
    static Position curPos;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        dice = new int[][] {{-1,0,-1},
                            {0,0,0},
                            {-1,0,-1},
                            {-1,0,-1}};

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K  =Integer.parseInt(st.nextToken());
        map = new int[N][M];
        command = new int[K];
        curPos = new Position(x,y);

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            command[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(simulation());
        bw.close();
    }

    static String simulation(){
        StringBuilder sb = new StringBuilder();
        for(int i : command){
            if(i == 1){
                if(!check(curPos.x, curPos.y+1)) continue;
                right();
                curPos.y++;
                copyNum();
                sb.append(dice[1][1]+"\n");
            }else if(i == 2){
                if(!check(curPos.x, curPos.y-1)) continue;
                left();
                curPos.y--;
                copyNum();
                sb.append(dice[1][1]+"\n");
            }else if(i == 3){
                if(!check(curPos.x-1, curPos.y)) continue;
                up();
                curPos.x--;
                copyNum();
                sb.append(dice[1][1]+"\n");
            }else if(i == 4){
                if(!check(curPos.x+1, curPos.y)) continue;
                down();
                curPos.x++;
                copyNum();
                sb.append(dice[1][1]+"\n");
            }
        }
        return sb.toString();
    }

    static void copyNum(){
        if(map[curPos.x][curPos.y] == 0){
            map[curPos.x][curPos.y] = dice[3][1];
        }else{
            dice[3][1] = map[curPos.x][curPos.y];
            map[curPos.x][curPos.y] = 0;
        }
    }

    //0열 2열 고정
    static void up(){
        int tmp = dice[0][1];

        for(int i = 0; i < 3; i++){
            dice[i][1] = dice[i+1][1];
        }
        dice[3][1] = tmp;
    }

    static void down(){
        int tmp = dice[3][1];

        for(int i = 3; i > 0; i--){
            dice[i][1] = dice[i-1][1];
        }
        dice[0][1] = tmp;
    }

    //0행 2행 고정
    static void right(){
        int tmp = dice[3][1];
        dice[3][1] = dice[1][2];
        for(int i = 2; i > 0; i--){
            dice[1][i] = dice[1][i-1];
        }
        dice[1][0] = tmp;
    }

    static void left(){
        int tmp = dice[3][1];
        dice[3][1] = dice[1][0];
        for(int i = 0; i < 2; i++){
            dice[1][i] = dice[1][i+1];
        }
        dice[1][2] = tmp;
    }

    static boolean check(int x, int y){
        if(-1 < x && x < N && -1 < y && y < M) return true;
        return false;
    }
}
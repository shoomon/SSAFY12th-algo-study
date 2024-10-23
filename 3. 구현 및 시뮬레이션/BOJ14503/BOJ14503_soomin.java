import java.util.*;
import java.io.*;
//날짜 24.10.13
//설계 시간: 1분
//구현 시간: 40분
//메모리: 14452 kb
//시간: 112 ms
public class Main {
    static class Robot{
        int r,c,d;
        public Robot(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public boolean clean(){
            if(map[r][c] == 0) {
                map[r][c] = -1;
                return true;
            }
            return false;
        }

        public void moveForward(){
            this.r = r+dR[d];
            this.c = c+dC[d];
        }

        public boolean moveBack(){
            int dir = (d+2)%4;
            int nR = r+dR[dir];
            int nC = c+dC[dir];

            if(check(nR,nC)){
                r = nR;
                c = nC;
                return true;
            }else{
                return false;
            }
        }

        public void turn(){
            this.d = (d+3)%4;
        }
    }

    static int N,M,R,C,D;
    static int[][] map;
    static int[] dR = {-1,0,1,0};
    static int[] dC = {0,1,0,-1};
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int count=0;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        Robot robot = new Robot(R,C,D);

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
//            System.out.println(robot.r+" "+robot.c+" "+robot.d+" "+count);

            if(robot.clean()) count++;

            boolean flag = false;
            for(int i = 0; i < 4; i++){
                int nY = robot.r+dR[i];
                int nX = robot.c+dC[i];

                if(map[nY][nX] == 0) flag = true;
            }
            //청소되지 않은 빈칸이 있는 경우 돌고 확인하지 않고 반시계로 돌면서 확인하고 해당 위치로 바로 이동해도 될 것 같음.
            if(flag){
                robot.turn();
                if(map[robot.r+dR[robot.d]][robot.c+dC[robot.d]] == 0) robot.moveForward();
            }else{
                if(!robot.moveBack()) break;
            }
//            printMap();
        }

        bw.write(count+"\n");
        bw.close();
    }

    static boolean check(int r, int c){
        if(-1 < r && r < N && -1 < c && c < M && map[r][c] != 1) return true;
        return false;
    }

    static void printMap(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }
}
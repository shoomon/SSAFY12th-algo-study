                                        import java.util.*;
import java.io.*;
//24.10.06
//설계 시간: 1 분
//구현 시간: 40분
//메모리: 16,084 kb
//시간: 114 ms
public class Solution {
    static class RC{
        int x,y,d;
        public RC(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    static int N, Q;
    static char[][] map, command;
    static int[] dX = {0,1,0,-1};
    static int[] dY = {-1,0,1,0};
    static RC initialRC;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;
        int T;
        T=Integer.parseInt(br.readLine());
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb = new StringBuilder();
            sb.append("#"+test_case+" ");
            N = Integer.parseInt(br.readLine());
            initialRC = null;
            map = new char[N][N];
            for(int  i = 0; i < N; i++){
                map[i] = br.readLine().toCharArray();
                if(initialRC != null) continue;
                for(int j = 0; j < N; j++){
                    if(map[i][j] == 'X'){
                        initialRC = new RC(j,i,0);
                    }
                }
            }
 
            Q = Integer.parseInt(br.readLine());
            command = new char[Q][];
            for(int i = 0; i < Q; i++){
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                command[i] = st.nextToken().toCharArray();
            }
 
            for(int i = 0; i < Q; i++){
                sb.append(simulation(command[i])+" ");
            }
 
            System.out.println(sb.toString());
 
        }
    }
 
    static int simulation (char[] command){
        RC rc = new RC(initialRC.x, initialRC.y, 0);
 
        for(int i = 0; i < command.length; i++){
            if(command[i] == 'A'){
                int nY = rc.y+dY[rc.d];
                int nX = rc.x+dX[rc.d];
//                System.out.println(nY+" "+nX);
 
                if(!check(nY,nX) || map[nY][nX] == 'T') continue;
                rc.y = nY;
                rc.x = nX;
            }else if(command[i] == 'L'){
                rc.d = (rc.d+3)%4;
            }else if(command[i] == 'R'){
                rc.d = (rc.d+1)%4;
            }
//            System.out.println(i+" "+command[i]+" "+rc.y+" "+rc.x);
        }
//        System.out.println(rc.y+" "+rc.x);
        if(map[rc.y][rc.x] == 'Y') return 1;
        return 0;
    }
 
    static boolean check(int y, int x){
        if(-1 < y && y < N && -1 < x && x < N) return true;
        return false;
    }
}
                                    
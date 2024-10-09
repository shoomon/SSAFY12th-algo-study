package swea;

import java.util.*;
import java.io.*;
//24.10.09
//설계 시간: 1 분
//구현 시간: 20 분
//메모리: 19,572 kb
//시간: 168 ms

public class Code1226 {
    static int[] dX = {0,1,0,-1};
    static int[] dY = {-1,0,1,0};
    static char[][] map;
    static int sY,sX;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            br.readLine();
            map = new char[16][16];

            boolean flag=false;
            for(int i = 0; i < 16; i++){
                map[i] = br.readLine().toCharArray();
                if(flag) continue;
                for(int j = 0; j < 16; j++){
                    if(map[i][j] == '2'){
                        sY = i;
                        sX = j;
                        flag = true;
                    }
                }
            }
            int answer = BFS() ? 1 : 0;
            bw.write("#"+test_case+" "+answer+"\n");
        }
        bw.close();
    }

    static boolean BFS(){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[16][16];

        q.offer(new int[] {sY,sX});
        visited[sY][sX] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nY = cur[0]+dY[i];
                int nX = cur[1]+dX[i];

                if(check(nY,nX) && !visited[nY][nX]){
                    if(map[nY][nX] == '3'){
                        return true;
                    }else if(map[nY][nX] == '0'){
                        q.offer(new int[] {nY,nX});
                        visited[nY][nX] = true;
                    }
                }
            }
        }
        return false;
    }

    static boolean check(int y, int x){
        if(-1 < y && y < 16 && -1 < x && x < 16) return true;
        return false;
    }
}

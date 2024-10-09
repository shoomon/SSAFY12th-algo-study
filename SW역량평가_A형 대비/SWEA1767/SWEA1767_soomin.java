import java.util.*;
import java.io.*;
//메모리: 108,580kb
//시간: 1,183ms
public class SWEA1767_soomin {
    static int maxCoreCnt, minLen, N, C;
    static int[][] map,core;
    static int[] dX = {0,1,0,-1};
    static int[] dY = {-1,0,1,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            C=0;
            map = new int[N][N];
            core = new int[12][2];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1){
                        if(i == 0 || i == N-1 || j == 0 || j == N-1){
                            maxCoreCnt++;
                            continue;
                        }
                        core[C][0] = i;
                        core[C++][1] = j;
                    }
                }
            }
//            for(int i = 0; i < C; i++){
//                System.out.println(Arrays.toString(core[i]));
//            }
            simulation(map, 0, maxCoreCnt, 0);
            bw.write("#"+t+" "+minLen+"\n");
        }
        bw.close();
    }
    //연결중인 코어, 연결된 코어 수, 전선 길이
    static void simulation(int[][] simMap, int depth, int cnt, int len){
//        for(int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(simMap[i]));
//        }
//        System.out.println(maxCoreCnt+" "+minLen+"===============cur depth: "+depth+", c: "+cnt+", l: "+len);
//        if(cnt < maxCoreCnt && len >= minLen) return; -> 코어를 더 연결할 수 있는 경우를 보지 못함
        if(depth == C){
            if(cnt > maxCoreCnt){
                maxCoreCnt = cnt;
                minLen = len;
            }else if(cnt == maxCoreCnt){
                minLen = Math.min(minLen, len);
            }
            return;
        }
        int[][] copy = new int[N][N];
//        for(int i = 0; i  < N; i++){
//            System.out.println(Arrays.toString(copy[i]));
//        }
//        System.out.println("==============");
        for(int i = 0; i < N; i++){
            copy[i] = Arrays.copyOf(simMap[i], N);
        }
//        for(int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(copy[i]));
//        }

        //연결 안 함
        simulation(simMap, depth+1, cnt, len);

        //상 우 하 좌
        for(int i = 0; i < 4; i++){
            int connect=0, count=0, curY=core[depth][0],curX=core[depth][1];
//            System.out.println("str/ "+curY+" "+curX);
            while(true){
                curY += dY[i];
                curX += dX[i];

//                System.out.println(curY+" "+curX+ " "+N);
                if((curY == 0 || curY == N-1 || curX == 0 || curX == N-1) && map[curY][curX] == 0){
                    simMap[curY][curX] = -1;
                    count++;
                    connect = 1;
                    break;
                }
                if(simMap[curY][curX] != 0) break;
                simMap[curY][curX] = -1;
                count++;
            }
            if(connect == 1){ //연결 가능
                simulation(simMap, depth+1, cnt+1, len+count);
            }
            //원복
            for(int j = 0; j < N; j++) simMap[j] = Arrays.copyOf(copy[j], N);
        }
    }
}
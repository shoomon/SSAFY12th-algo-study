package swea;

import java.util.*;
import java.io.*;
//24.10.09
//설계 시간: 1분
//구현 시간: 60 분
//메모리: 20,088 kb
//시간: 136 ms
//DFS 시뮬레이션 + 백트래킹, 최대 depth = 100
//BFS로 하려면 필드에 이동 횟수+나무 벤 횟수 저장 필요
//회전, 나무 베기도 횟수에 포함됨.
public class Code22683 {
    static int N,K,answer;
    static char[][] map, simulMap;
    static int[] dX = {0,1,0,-1};
    static int[] dY = {-1,0,1,0};
    static int[] startPoint;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            answer=Integer.MAX_VALUE;
            startPoint = new int[2];
            map = new char[N][N];
            simulMap = new char[N][N];

            //맵 순회하면서 시작지점, 끝 지점 좌표 저장
            for(int i = 0;i < N; i++){
                map[i] = br.readLine().toCharArray();
                for(int j = 0; j < N; j++){
                    if(map[i][j] == 'X'){
                        startPoint[0] = i;
                        startPoint[1] = j;
                    }
                }
            }

            DFS(startPoint[0], startPoint[1], 0, new boolean[N][N], 0, 0);
            if(answer == Integer.MAX_VALUE) answer = -1;
            bw.write("#"+test_case+" "+answer+"\n");
        }
        bw.close();
    }

    static void DFS(int curY, int curX, int curD, boolean[][] visited, int depth, int cutCnt){
        if(cutCnt > K) return;
//        System.out.println(map[curY][curX]+" "+curY+" "+curX+" "+depth+" "+cutCnt);
        if(map[curY][curX] == 'Y'){
            answer = Math.min(answer, depth);
            return;
        }

        visited[curY][curX] = true;

        for(int i = 0; i < 4; i++){
            int nY = curY+dY[i];
            int nX = curX+dX[i];
            //맵을 벗어나지 않았고 방문한적 없는 곳 중
            if(check(nY,nX) && !visited[nY][nX]){
                int dir = Math.abs(curD-i) > 2 ? 4-Math.abs(curD-i) : Math.abs(curD-i);
                //나무면 베고 이동
                 if(map[nY][nX] == 'T'){

                    DFS(nY,nX,i,visited,depth+1+dir,cutCnt+1);
                }else{
                     DFS(nY,nX,i,visited,depth+1+dir,cutCnt);
                 }
            }
        }
        visited[curY][curX] = false;
    }

    static boolean check(int y, int x){
        if(-1 < y && y < N && -1 < x && x < N) return true;
        return false;
    }
}

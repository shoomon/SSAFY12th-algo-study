//24.12.20
//설계: 5분
//구현: 25+8분

import java.util.*;

class Solution {
    static int size, N, M;
    static int[] dY = {-1, 0, 1, 0};
    static int[] dX = {0, 1, 0, -1};
    static int[] fuelCnt; //열 별 얻을 수 있는 석유량
    static boolean[] visitCol, visited[];
    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        size = 0;
        visitCol = new boolean[M];
        visited = new boolean[N][M];
        fuelCnt = new int[M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(land[i][j] == 1 && !visited[i][j]) {
                    size = 0;
                    Arrays.fill(visitCol,false);
                    BFS(land, i,j);
                    // System.out.println(size);
                    for(int k = 0; k < M; k++){
                        if(visitCol[k])fuelCnt[k] += size;
                    }
            }
        }
        } 
        
        for(int i = 0; i < M; i++) answer = Math.max(answer, fuelCnt[i]);
        
        return answer;
    }
    
    static void BFS(int[][] land, int i, int j){
        Queue<int[]> q = new ArrayDeque<>();
        
        q.offer(new int[] {i,j});
        visited[i][j] = true;
        visitCol[j] = true;
        size++;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int nY, nX;
            
            for(int k = 0; k < 4; k++){
                nY = cur[0]+dY[k];
                nX = cur[1]+dX[k];
                
                if(-1 < nY && nY < N && -1 < nX && nX < M){
                    if(land[nY][nX] == 1 && !visited[nY][nX]){
                        q.offer(new int[] {nY,nX});
                        visited[nY][nX] = true;
                        visitCol[nX] = true;
                        size++;
                    }
                }
            }
        }
    }
}
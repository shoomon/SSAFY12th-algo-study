//25.01.19
//설계: 1분
//구현: 30분
//BFS 2번 == 10000*2
import java.util.*;

class Solution {
    static int answer,N,M;
    static boolean isReached;
    static int[] dY = {-1,0,1,0};
    static int[] dX = {0,1,0,-1};
    public int solution(String[] maps) {
        answer = 0;
        N = maps.length;
        M = maps[0].length();
        isReached = false;
        int[] start={}, end={}, laver={};
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i].charAt(j) == 'S') {
                    start = new int[] {i,j};
                }else if(maps[i].charAt(j) == 'E'){
                    end = new int[] {i,j};
                }else if(maps[i].charAt(j) == 'L'){
                    laver = new int[] {i,j};
                }
            }
        }
        
        BFS(maps, start, laver);
        if(isReached){
            isReached = false;
            BFS(maps, laver, end);
            if(!isReached){
                answer = -1;
            }
        }else{
            answer = -1;
        }
        
        
        return answer;
    }
    
    static void BFS(String[] maps, int[] start, int[] end){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        q.offer(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int nY = cur[0]+dY[i];
                int nX = cur[1]+dX[i];
                
                if(-1 < nY && nY < N && -1 < nX && nX < M){
                    if(nY == end[0] && nX == end[1]){
                        answer += cur[2]+1;
                        isReached = true;
                        return;
                    }else if(maps[nY].charAt(nX) != 'X' && !visited[nY][nX]){
                        q.offer(new int[] {nY,nX,cur[2]+1});
                        visited[nY][nX] = true;
                    }
                }
            }
        }
    }
}
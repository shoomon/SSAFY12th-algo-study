//25.01.19
//설계: 1분
//구현: 분
//DFS, 100*100=10000
import java.util.*;

class Solution {
    static int[] dY = {-1,0,1,0};
    static int[] dX = {0,1,0,-1};
    static int N,M;
    public ArrayList<Integer> solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        ArrayList<Integer> answer = new ArrayList<>();
        boolean[][] visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i].charAt(j) != 'X' && !visited[i][j]) answer.add(DFS(maps,visited,i,j));
            }
        }
        
        if(answer.size() == 0) answer.add(-1);
        Collections.sort(answer);
        
        return answer;
    }
    
    static int DFS(String[] maps, boolean[][] visited, int curY, int curX){
        visited[curY][curX] = true;
        int sum = maps[curY].charAt(curX)-'0';
        
        for(int i = 0; i < 4; i++){
            int nY = curY+dY[i];
            int nX = curX+dX[i];
            
            if(-1 < nY && nY < N && -1 < nX && nX < M){
                if(maps[nY].charAt(nX) != 'X' && !visited[nY][nX]){
                    sum += DFS(maps, visited, nY, nX);
                }
            }
        }
        
        return sum;
    }
}
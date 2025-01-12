//25.01.12
//설계: 분
//구현: 분
//12:00
import java.util.*;

class Solution {
    static int R, C, startY, startX, endY, endX, answer;
    static int[] dX = {0,1,0,-1};
    static int[] dY = {-1,0,1,0};
    public int solution(String[] board) {
        answer = Integer.MAX_VALUE;
        R = board.length;
        C = board[0].length();
        
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(board[i].charAt(j) == 'R'){
                    startY = i;
                    startX = j;
                }else if(board[i].charAt(j) == 'G'){
                    endY = i;
                    endX = j;
                }
            }
        }
        
        answer = BFS(board, startY, startX);
        
        return answer;
    }
    
    static int BFS(String[] board, int y, int x){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        q.offer(new int[] {startY, startX, 0});
        visited[startY][startX] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(endY == cur[0] && endX == cur[1]) return cur[2];
            
            for(int i = 0; i < 4; i++){
                int nY = cur[0];
                int nX = cur[1];
                while(check(nY+dY[i],nX+dX[i]) && board[nY+dY[i]].charAt(nX+dX[i]) != 'D'){
                    nY += dY[i];
                    nX += dX[i];
                }
                if(visited[nY][nX]) continue;
                q.offer(new int[]{nY,nX,cur[2]+1});
                visited[nY][nX] = true;
            }
        }
        return -1;
    }
    
    static boolean check(int y, int x){
        if(-1 < y && y < R && -1 < x && x < C) return true;
        return false;
    }
}
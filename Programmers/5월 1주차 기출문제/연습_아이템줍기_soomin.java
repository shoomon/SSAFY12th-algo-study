import java.util.*;

class Solution {
    static int[][] map;
    static int[] dX = {0,1,0,-1};
    static int[] dY = {-1,0,1,0};
    static int[] dX8 = {0,1,1,1,0,-1,-1,-1};
    static int[] dY8 = {-1,-1,0,1,1,1,0,-1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[101][101];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[101][101];
        
        for(int[] i : rectangle){
            fillRectangle(i[0]<<1,i[1]<<1,i[2]<<1,i[3]<<1);
        }
        
        checkRoad(rectangle[0][1]<<1,rectangle[0][0]<<1);
        
        // for(int i = 0; i < 51; i++){
        //     for(int j = 0; j < 51; j++) System.out.print(map[i][j]+" ");
        //     System.out.println();
        // }
        
        q.offer(new int[] {characterY<<1, characterX<<1, 0});
        visited[characterY<<1][characterX<<1] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int nY = cur[0]+dY[i];
                int nX = cur[1]+dX[i];
                
                if(nY == itemY<<1 && nX == itemX<<1) return cur[2]/2+1;
                
                if(checkBound(nY,nX) && map[nY][nX] == 1 && !visited[nY][nX]){
                    q.offer(new int[] {nY,nX,cur[2]+1});
                    visited[nY][nX] = true;
                }
            }
        }
        
        return -1;
    }
    
    static void fillRectangle(int lX, int lY, int rX, int rY){
        for(int i = lY; i <= rY; i++){ //좌상단, 우하단
            for(int j = lX; j <= rX; j++) map[i][j] = -1;
        }
    }
    
    static void checkRoad(int sY,int sX){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[101][101];
        ArrayList<int[]> bound = new ArrayList<>();
        
        q.offer(new int[] {sY,sX});
        visited[sY][sX] = true;
        bound.add(new int[] {sY,sX});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int nY = cur[0]+dY[i];
                int nX = cur[1]+dX[i];
                
                if(checkBound(nY,nX) && map[nY][nX]==-1 && !visited[nY][nX]){
                    for(int j = 0; j < 8; j++){
                        if(!checkBound(nY+dY8[j],nX+dX8[j])) continue;
                        if(map[nY+dY8[j]][nX+dX8[j]]==0){
                            bound.add(new int[] {nY,nX});
                            break;
                        }
                    }
                    
                    q.offer(new int[] {nY,nX});
                    visited[nY][nX] = true;
                }
            }
        }
        
        for(int[] i : bound) map[i[0]][i[1]] = 1;
    }
    
    static boolean checkBound(int y, int x){
        if(-1 < y && y < 101 && -1 < x && x < 101) return true;
        return false;
    }
}
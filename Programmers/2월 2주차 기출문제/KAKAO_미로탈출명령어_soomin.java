import java.util.*;

class Solution {
    static int[] dY = {1, 0, 0, -1};
    static int[] dX = {0, -1, 1, 0};
    static char[] dir = {'d','l','r','u'};
    static String answer;
    
    static class Node{
        String path;
        int r, c;
        public Node(String path, int r, int c){
            this.path = path;
            this.r = r;
            this.c = c;
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "";
        
        if(distance(x,y,r,c) > k || (k-distance(x,y,r,c))%2 == 1) return "impossible";
        DFS(n,m,new Node("",x,y),r,c,k);
        
        return "".equals(answer) ? "impossible" : answer;
    }
    
    static int distance(int x, int y, int r, int c){
        return Math.abs(x-r)+Math.abs(y-c);
    }
    
    static void DFS(int n, int m, Node cur, int r, int c, int k){
        if(!"".equals(answer)) return;
        if(cur.path.length()+distance(cur.r,cur.c,r,c) > k) return;
        
        if(cur.path.length() == k){
            answer = cur.path;
            return;
        }
        
        for(int i = 0; i < 4; i++){
            int nY = cur.r+dY[i];
            int nX = cur.c+dX[i];
            
            if(0 < nY && nY <= n && 0 < nX && nX <= m){
                DFS(n,m,new Node(cur.path+dir[i],nY,nX),r,c,k);
            }
        }
    }
}
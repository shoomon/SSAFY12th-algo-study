import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[3*y+1];
        
        if(x == y) return 0;
        
        q.offer(new int[]{x,0});
        
        while(!q.isEmpty()){
            int[] cur  = q.poll();
            
            if(cur[0]+n == y || cur[0]*2 == y || cur[0]*3 == y){
                answer = cur[1]+1;
                break;
            }
            
            if(!visited[cur[0]+n] && cur[0]+n < y){
                visited[cur[0]+n] = true;
                q.offer(new int[]{cur[0]+n,cur[1]+1});
            }
            if(!visited[cur[0]*2] && cur[0]*2 < y){
                visited[cur[0]*2] = true;
                q.offer(new int[]{cur[0]*2,cur[1]+1});
            }
            if(!visited[cur[0]*3] && cur[0]*3 < y){
                visited[cur[0]*3] = true;
                q.offer(new int[]{cur[0]*3,cur[1]+1});
            }
        }
        return answer;
    }
}
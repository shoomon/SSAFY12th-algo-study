import java.awt.*;
import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0, X = routes.length, M = routes[0].length;
        int[] dest = new int[X];
        int[][] map = new int[101][101];
        Point[] robots = new Point[X];
        //로봇 위치 초기화
        for(int i = 0; i < X; i++){
            robots[i] = new Point(points[routes[i][0]-1][0], points[routes[i][0]-1][1]);
        }
        
        while(true){
            for(int i = 0; i < 101; i++) Arrays.fill(map[i],0);
            boolean flag=false;
            
            for(int i = 0; i < X; i++){
                if(dest[i] == -1) continue;
                
                flag = true;
                int destination = routes[i][dest[i]]-1;
                
                if(robots[i].y == points[destination][0] && robots[i].x == points[destination][1]){
                    if(dest[i] < M-1){
                        dest[i]++;
                        destination = routes[i][dest[i]]-1;
                    }else{
                        dest[i] = -1;
                        continue;
                    }
                }
                
                if(points[destination][0] < robots[i].y){
                    robots[i].y--;
                }else if(points[destination][0] > robots[i].y){
                    robots[i].y++;
                }else if(points[destination][1] < robots[i].x){
                    robots[i].x--;
                }else if(points[destination][1] > robots[i].x){
                    robots[i].x++;
                }
                
                if(map[robots[i].y][robots[i].x] == 1) answer++;
                map[robots[i].y][robots[i].x]++;
            }
            if(!flag) break;
        }
        
        return answer;
    }
}
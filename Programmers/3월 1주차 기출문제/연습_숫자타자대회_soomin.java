//못 풀었습니다
import java.util.*;

class Solution {
    static int[][] pad = {
        {1,2,3},
        {4,5,6},
        {7,8,9},
        {-1,0,-1}
    };
    
    static int[] dY = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dX = {0, 1, 0, -1, -1, 1, -1, 1};
    
    public int solution(String numbers) {
        int len = numbers.length();
        
        PriorityQueue<int[]> q = new PriorityQueue<>(
        (o1,o2) -> o1[4]-o2[4]
        );
        Set<String> visited = new HashSet<>();
        
        q.offer(new int[] {1,0,1,1,0});
        visited.add(1+","+0+","+1+","+1+","+0);
        
        for(int i = 0; i < len; i++){
            //각 턴마다 left로 눌렀을 경우, right로 눌렀을 경우의 좌표, 가중치를 큐로 저장
            int target = numbers.charAt(i)-'0';
            int size = q.size();
            
                while(size-- > 0){
                    int[] cur = q.poll();

                    if(pad[cur[0]][cur[1]] == target || pad[cur[2]][cur[3]] == target){
                        q.offer(new int[] {cur[0],cur[1],cur[2],cur[3],cur[4]+1});
                        visited.add(cur[0]+","+cur[1]+","+cur[2]+","+cur[3]+","+(cur[4]+1));
                        break;
                    }

                    //타겟 좌표 구하기
                    int[] point = targerPoint(target);
                    //8방 탐색 하면 안됨. 타겟 위치를 좌표로 변환, 멘헤튼 거리가 비용임.
                    int costL = Math.abs(cur[0]-point[0])+Math.abs(cur[1]-point[1]);
                    int costR = Math.abs(cur[2]-point[0])+Math.abs(cur[3]-point[1]);
                    
                    //왼손을 타겟 위치로 이동
                    if(!visited.contains(point[0]+","+point[1]+","+cur[2]+","+cur[3]+","+(cur[4]+costL))){
                        q.offer(new int[] {point[0], point[1], cur[2], cur[3], cur[4]+costL});
                        visited.add(point[0]+","+point[1]+","+cur[2]+","+cur[3]+","+(cur[4]+costL));
                    }
                    //오른손을 타겟 위치로 이동
                    if(!visited.contains(cur[0]+","+cur[1]+","+point[0]+","+point[1]+","+(cur[4]+costR))){
                        q.offer(new int[] {cur[0], cur[1], point[0], point[1], cur[4]+costR});
                        visited.add(cur[0]+","+cur[1]+","+point[0]+","+point[1]+","+(cur[4]+costR));
                    }
                }
            
        }
        
        int answer = q.poll()[4];
        return answer;
    }
    
    static int[] targerPoint(int target){
        if(target == 1) return new int[] {0,0};
        if(target == 2) return new int[] {0,1};
        if(target == 3) return new int[] {0,2};
        if(target == 4) return new int[] {1,0};
        if(target == 5) return new int[] {1,1};
        if(target == 6) return new int[] {1,2};
        if(target == 7) return new int[] {2,0};
        if(target == 8) return new int[] {2,1};
        if(target == 9) return new int[] {2,2};
        if(target == 0) return new int[] {3,1};
        return new int[] {};
    }
}
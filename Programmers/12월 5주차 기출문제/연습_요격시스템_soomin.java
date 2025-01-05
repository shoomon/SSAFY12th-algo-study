//24.12.30
//설계: 30분
//구현: 15분
//그리디 -> 가장 많이 겹치는 부분에 배치
//해당 칸에 해당하는 target list로 달아놓기 -> 근데 이거 1억개 어케 검색함
//pq로 시작좌표 작은, 끝 좌표 큰 순서로 정렬, 시작 좌표가 가장 작은 끝 좌표보다 크거나 같을 때마다 cnt++
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1, tail = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{return o1[0] == o2[0] ? o2[1]-o1[1] : o1[0]-o2[0];});
        
        for(int i = 0; i < targets.length; i++) pq.offer(new int[] {targets[i][0],targets[i][1]});
        
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            
            if(cur[0] >= tail){
                tail = cur[1];
                answer++;
            }else if(cur[1] < tail){
                tail = cur[1];
            }
        }
        return answer;
    }
}
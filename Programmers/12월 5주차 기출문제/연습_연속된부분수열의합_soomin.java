//25.01.05
//1번 순차탐색 -> 큐
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int start=0, sum=0;
        Queue<Integer> q = new ArrayDeque<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1,o2)->{
                if(o1[1]-o1[0] < o2[1]-o2[0]){
                    return -1;
                }else{
                    return 1;
                }
            });
        
        for(int i = 0; i < sequence.length; i++){
            q.offer(sequence[i]);
            sum += sequence[i];
            
            while(sum > k){
                sum -= q.poll();
                start++;
            }
            
            if(sum == k){
                pq.offer(new int[] {start,i});
            }
        }
        
        return pq.poll();
    }
}
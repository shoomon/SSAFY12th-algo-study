//25.01.10
//설계: 분
//구현: 분
//구간합, 앞으로 5개까지의 구간 합, 합이 높은 구간에 좋은 곡괭이 배정
//완탐 O(N^2) = 250
//합 순서로 인덱스와 함께 정렬 -> O(Nlog(N))
import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int CNT = minerals.length;
        int maxIdx = 0;
        //score[i] -> i번째부터 앞 5개 광물까지의 피로도 합, 돌 기준
        int[] score = new int[CNT];
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1,o2) -> o2[1]-o1[1]
        );
        
        for(int i = 0; i < 3; i++) maxIdx += picks[i];
        maxIdx *= 5;
        // System.out.println(maxIdx);
        
        int idx;
        for(idx = 0; idx < CNT; idx += 5){
            for(int j = idx; j < (idx+5 < CNT ? idx+5 : CNT) ; j++){
                // System.out.print("j: "+j+" ");
                if(minerals[j].equals("diamond")){
                    score[idx] += 25;
                }else if(minerals[j].equals("iron")){
                    score[idx] += 5;
                }else if(minerals[j].equals("stone")){
                    score[idx] += 1;
                }
            }
            pq.offer(new int[] {idx,score[idx]});
        }
        
        // for(int[] i : pq){
        //     System.out.print(i[0]+" "+i[1]+"/");
        // }
        
        while(!pq.isEmpty()){
            int pick=-1;
            int[] cur = pq.poll();
            if(cur[0] >= maxIdx) continue;
            //곡괭이 선택
            for(int i = 0; i < 3; i++){
                if(picks[i] > 0){
                    picks[i]--;
                    pick = i;
                    break;
                }
            }
            if(pick == -1) break;
            
            for(int i = cur[0]; i < (cur[0]+5 < CNT ?  cur[0]+5 : CNT); i++){
                if(pick == 0){
                    answer += 1;
                }else if(pick == 1){
                    if(minerals[i].equals("diamond")){
                        answer += 5;
                    }else if(minerals[i].equals("iron") || minerals[i].equals("stone")){
                        answer += 1;
                    }
                }else if(pick == 2){
                    if(minerals[i].equals("diamond")){
                        answer += 25;
                    }else if(minerals[i].equals("iron")){
                        answer += 5;
                    }else if(minerals[i].equals("stone")){
                        answer += 1;
                    }
                }
            }
        }
        
        return answer;
    }
}
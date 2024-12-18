//24.12.18
//조합, 가지치기
//10C5*6^10 -> 중복 수 제거
import java.util.*;

class Solution {
    static int N, maxWin, winCnt;
    static HashMap<Integer,Integer>[] diceCnt;
    static boolean[] ans;
    public int[] solution(int[][] dice) {
        N = dice.length;
        int[] answer = new int[N/2];
        maxWin = 0;
        diceCnt = new HashMap[N];
        ans = new boolean[N];
        
        for(int i = 0; i < N; i++) diceCnt[i] = new HashMap<>();
        
        for(int i = 0; i < N; i++){
            for(int j : dice[i]){
                diceCnt[i].put(j,diceCnt[i].getOrDefault(j,0)+1);
            }
        }
        
        combination(0,0,new boolean[N]);
        int index=0;
        for(int i = 0; i < N; i++){
            if(ans[i]) answer[index++] = i+1;
        }
        
        return answer;
    }
    
    static void combination(int depth, int cnt, boolean[] isSelected){
        if(depth == N) return;
        if(cnt == N/2){
            winCnt=0;
            calculate(isSelected,0,0,0,1);
            if(winCnt > maxWin){
                maxWin = winCnt;
                Arrays.fill(ans, false);
                for(int i = 0; i < N; i++){
                    if(isSelected[i]) ans[i] = true;
                }
            }
            return;
        }
        
        isSelected[depth] = true;
        combination(depth+1, cnt+1, isSelected);
        
        isSelected[depth] = false;
        combination(depth+1, cnt, isSelected);
    }
    
    static void calculate(boolean[] isSelected, int idx, int scoreA, int scoreB, int cntA){
        if(idx == N){
            // System.out.println(cntA);
            if(scoreA > scoreB) winCnt += cntA;
            return;
        }
        //주사위 점수 합을 계산하여 이긴 횟수 계산.
        for(int i = idx; i < N; i++){
            if(isSelected[i]){
                for(int j : diceCnt[i].keySet()){
                    calculate(isSelected, i+1, scoreA+j, scoreB, cntA*diceCnt[i].get(j));
                }
            }else{
                for(int j : diceCnt[i].keySet()){
                    calculate(isSelected, i+1, scoreA, scoreB+j, cntA);
                }
            }
        }
    }
}
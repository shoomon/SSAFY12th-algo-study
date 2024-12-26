//24.12.26
//설계: 분
//구현: 분
//조합, 시뮬레이션 X 그리디
import java.util.*;

class Solution {
    static int N, maxRound;
    public int solution(int coin, int[] cards) {
        int answer = 0;
        N = cards.length;
        maxRound = 0;
        
        simulation(cards, coin);
        answer = maxRound;
        
        return answer;
    }
    
    static void simulation(int[] cards, int coin){
        Set<Integer> myCards = new HashSet<>();
        Set<Integer> candidateCards = new HashSet<>();
        int idx = 0, round = 1;
        
        for(; idx < N/3; idx++) myCards.add(cards[idx]);

        //남은 카드뭉치 없으면 종료
        while(idx < N){
            //라운드 시작 시 카드 2장 뽑기
            for(int i = 0; idx < N && i < 2; idx++, i++) {
                candidateCards.add(cards[idx]);
            }
            
            boolean flag = false;
            //카드 2장 합이 N+1이 되는지 확인 -> 이 경우가 여러가지라면? 코인을 가장 덜 쓰도록
            for(int i : myCards){
                //내가 가진 카드들로만 가능한지
                if(myCards.contains(N+1-i)){
                    myCards.remove(i);
                    myCards.remove(N+1-i);
                    flag = true;
                    break;
                //코인을 1개 썼다면 가능한지
                }else if(candidateCards.contains(N+1-i) && coin > 0){
                    myCards.remove(i);
                    candidateCards.remove(N+1-i);
                    coin--;
                    flag = true;
                    break;
                }
            }
            //코인 2개 쓰면 가능한지
            if(!flag && coin > 1){
                for(int i : candidateCards){
                    if(candidateCards.contains(N+1-i)){
                        candidateCards.remove(i);
                        candidateCards.remove(N+1-i);
                        coin -= 2;
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag) break;
            round++;
        }
        maxRound = Math.max(maxRound,round);
    }
}
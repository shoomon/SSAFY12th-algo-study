import java.util.*;
import java.io.*;

public class KAKAO_카드게임_mingyung {
	public int solution(int coin, int[] cards) {
        int answer = 0;
        
        int n = cards.length;
        Set<Integer> original = new HashSet<>();
        Set<Integer> additional = new HashSet<>();
        
        // 내가 가진 카드 입력
        int idx = n/3;
        for (int i=0; i<idx; i++) {
            original.add(cards[i]);
        }
        
        // 게임 진행
        int target = n+1; // 만들어야 하는 수
        while (true) {
            answer++;
            
            // 다 뽑을 경우 게임 끝내기
            if (idx >= n) break;
            
            // 카드 뽑기
            additional.add(cards[idx++]);
            additional.add(cards[idx++]);
            
            boolean flag = false;
            
            // 1. 최초 카드에서 해결할 수 있는지 확인
            for (int i : original) {
                // i와 target-i가 있는지 확인
                if (original.contains(target-i)) {
                    original.remove(i);
                    original.remove(target-i);
                    flag = true;
                    break;
                }
            }
            
            // 2. 최초 카드에서 해결이 안 되었다면
            //    추가한 카드와 기존에 가지고 있던 카드 체크
            if (!flag) {
                // coin 1개 이상 있어야 가능
                if (coin > 0) {
                    // 기존 카드 돌면서 추가한 카드와 비교
                    for (int i : original) {
                        if (additional.contains(target-i)) {
                            original.remove(i);
                            additional.remove(target-i);
                            coin--; // 추가한 카드 사용 위해 코인 사용
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            // 3. 그래도 해결 안됐으면 추가한 카드 중에서 확인
            if (!flag) {
                // 추가한 카드에서 두 장 뽑으려면 coin 2 이상 필요
                if (coin > 1) {
                    for (int i : additional) {
                        if (additional.contains(target-i)) {
                            additional.remove(i);
                            additional.remove(target-i);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            // 그럼에도 해결되지 않았다면 게임 종료
            if (!flag) break;
        }
        
        return answer;
    }
}

import java.util.*;
import java.io.*;

public class KAKAO_이모티콘할인행사_mingyung {
	public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        // 할인율 조합 뽑아서 계산해보기
        int[] sales = new int[emoticons.length];
        comb(0, sales, emoticons, users, answer);
        
        return answer;
    }
    
    public void comb(int idx, int[] sales, int[] emoticons, int[][] users, int[] answer) {
        // 할인율 다 담기면 계산하기
        if (idx == sales.length) {
            // 구독수와 판매액 세팅 
            int gudok = 0;
            int cost = 0;
            
            // 유저 돌면서 계산
            for (int[] user : users) {
                // 이모티콘 산 합
                int sum = 0;
                
                // 할인 배열 돌면서 원하는 할인 이상이면 사기
                for (int i=0; i<sales.length; i++) {
                    if (sales[i] >= user[0]) sum += emoticons[i] / 100 * (100-sales[i]);
                }
                
                // sum이 한계선보다 크면 구독 아니면 그냥 이모티콘 사기
                if (sum >= user[1]) gudok++;
                else cost += sum;
            }
            
            // 만약 구독수가 높으면 답 변경
            if (gudok > answer[0]) {
                answer[0] = gudok;
                answer[1] = cost;
            }
            
            // 구독수 같아도 이모티콘 판매금액 높으면 답변경
            else if (gudok == answer[0] && cost > answer[1]) answer[1] = cost;
            
            return;
        }
        
        // 할인율 뽑기
        for (int i=10; i<=40; i+=10) {
            sales[idx] = i;
            comb(idx+1, sales, emoticons, users, answer);
        }
    }
}

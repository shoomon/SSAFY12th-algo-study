
import java.util.*;
import java.io.*;

public class 연습_택배상자_mingyung {
	public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>(); // 보조컨테이너
        int idx = 0; // 주문 인덱스
        int boxN = 1; // 현재 박스 번호
        
        here:
        while (boxN <= order.length) {
            // 주문서와 같은 경우 담기
            if (boxN == order[idx]) {
                answer++;
                idx++;
                boxN++;
            }
            
            // 보조 컨테이너와 비교
            while (!stack.isEmpty()) {
                // 보조 컨테이너 벨트와 같으면 담기
                if (stack.peek() == order[idx]) {
                    stack.pop();
                    answer++;
                    idx++;
                }
                // 보조 컨테이너에 담긴 수보다 요청 상자 번호가 작으면 의미 없음
                else if (stack.peek() > order[idx]) {
                    break here;
                }
                // 위 아니면 while문 나가가
                else break;
            }
            
            stack.add(boxN++);
        }
        
        // 마지막 번호 갔을 때 보조 컨테이너와 비교
        while (!stack.isEmpty()) {
            // 보조 컨테이너 벨트와 같으면 담기
            if (idx<order.length && stack.peek() == order[idx]) {
                stack.pop();
                answer++;
                idx++;
            }
            // 아니면 종료
            else break;
        }
        
        return answer;
    }
}

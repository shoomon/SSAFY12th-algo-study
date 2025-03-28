import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        int orderIdx = 0;
        int answer = 0;

        for (int i = 1; i <= order.length; i++) {
            // 현재 박스를 트럭에 실는다.
            if (order[orderIdx] == i) {
                orderIdx++;
                answer++;
            } else {
                // 보조 컨테이너에 box를 넣는다.
                stack.push(i);
            }

            // 보조 컨테이너에서 뺄 수 있을만큼 순차적으로 빼기
            while (!stack.isEmpty() && stack.peek() == order[orderIdx]) {
                stack.pop();
                orderIdx++;
                answer++;
            }
        }
        return answer;
    }
}
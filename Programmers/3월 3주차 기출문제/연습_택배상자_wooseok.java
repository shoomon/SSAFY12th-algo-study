package codingTest;
import java.util.*;

public class 연습_택배상자_wooseok {
	public int solution(int[] order) {
		int answer = 0;
		Stack<Integer> stack = new Stack<>();
		int cur = 1; // 어차피 컨테이너 벨트에는 1 ~ n 순서로 들어있음
		int cnt = 0;
		while(cnt < order.length){
			int target = order[cnt++];
			if(cur <= target){
				while(cur != target){
					stack.push(cur++);
				}
				cur++;
				answer++;
			}
			else{ // cur > target 이라면 이미 컨테이너 벨트에서 보조 컨테이너 벨트로 이동 상태
				if(stack.isEmpty() || stack.peek() != target) break;
				else{
					answer++;
					stack.pop();
				}
			}
		}
		return answer;
	}
}

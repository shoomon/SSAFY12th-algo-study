package PRO_1월_3주차;

import java.util.*;

public class 연습_뒤에있는큰수찾기_hyunjin {
	public static void main(String[] args) {
		int[] numbers = { 9, 1, 5, 3, 6, 2 };
		연습_뒤에있는큰수찾기_hyunjin sol = new 연습_뒤에있는큰수찾기_hyunjin();
		System.out.println(Arrays.toString(sol.solution(numbers)));
	}

	public int[] solution(int[] numbers) {
		int N = numbers.length;
		int[] answer = new int[N];
		Arrays.fill(answer, -1); // 모든 값을 -1로 초기화

		Stack<Integer> stack = new Stack<>();

		stack.push(0); // 첫 번째 인덱스 스택에 넣기

		for (int i = 1; i < N; i++) {
			while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
				answer[stack.pop()] = numbers[i];
			}
			stack.add(i);
		}

		return answer;
	}
}

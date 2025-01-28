package codingTest;

import java.util.*;

public class 연습_뒤에있는큰수찾기_wooseok {
    public static void main(String[] args) {
        // 테스트 케이스: 단일 배열 입력으로 전달
        int[] numbers = {2, 3, 3, 5};
        int[] ans = solution(numbers);
        System.out.println(Arrays.toString(ans));
    }


    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1); // 결과 배열을 -1로 초기화
        Stack<Integer> stack = new Stack<>(); // 스택을 사용하여 인덱스를 추적

        // 배열의 숫자를 순차적으로 탐색
        for (int i = 0; i < numbers.length; i++) {
            // 현재 숫자가 스택의 최상단 숫자보다 큰 경우
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                int index = stack.pop(); // 스택에서 인덱스를 꺼냄
                answer[index] = numbers[i]; // 해당 위치의 결과를 현재 숫자로 설정
            }
            // 현재 인덱스를 스택에 추가
            stack.push(i);
        }

        // 스택에 남아 있는 인덱스는 더 큰 숫자가 없으므로 기본값 -1 유지
        return answer;
    }

}

package 프로그래머스;

import java.util.*;

class 연습_뒤에있는큰수찾기_mirim {
    public int[] solution(int[] numbers) {
        int n = numbers.length; // 배열의 길이 저장
        int[] answer = new int[n]; // 결과를 저장할 배열
        Arrays.fill(answer, -1); // 기본값 -1로 초기화 (뒷 큰수가 없는 경우)
        
        Stack<Integer> stack = new Stack<>(); // 인덱스를 저장할 스택
        
        for (int i = 0; i < n; i++) { // 배열을 처음부터 끝까지 순회
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) { // 스택의 top보다 현재 값이 크면
                answer[stack.pop()] = numbers[i]; // 스택의 인덱스 위치에 현재 값 저장
            }
            stack.push(i); // 현재 인덱스를 스택에 추가
        }
        
        return answer; // 결과 반환
    }
}

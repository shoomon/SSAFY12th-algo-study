import java.util.*;
import java.io.*;

public class 연습_뒤에있는큰수찾기_mingyung {
	public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        // 초기 세팅 -1로 하기
        // 큰 수 찾으면 그걸로 바꾸기
        Arrays.fill(answer, -1);
        // Stack에 쌓으면서 체크하기
        Stack<Integer> stack = new Stack<>();
        // i는 idx
        for (int i=0; i<numbers.length; i++) {
            // stack에 넣어져 있는 idx에 해당하는 값과 현재 인덱스 해당하는 값 비교
            // stack에 넣어져 있는 값이 작으면 그 곳에 해당 인덱스값 넣기
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            // 나보다 큰 수 없으면 stack에 넣기
            stack.push(i);
        }
        
        return answer;
    }
}

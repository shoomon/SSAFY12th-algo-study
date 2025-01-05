package codingTest;

import java.util.Arrays;

public class 연습_연속된부분수열의합_wooseok {
    public static int[] solution(int[] sequence, int k) {
        // 최소 길이를 저장할 변수 초기화
        int min = Integer.MAX_VALUE;
        int start = 0;   // 윈도우 시작 위치
        int end = 0;     // 윈도우 끝 위치
        long cur = 0;    // 현재 윈도우의 합
        int answer_st = -1; // 최소 길이 구간의 시작 인덱스
        int answer_en = -1; // 최소 길이 구간의 끝 인덱스

        // 윈도우를 확장하며 탐색
        while (end < sequence.length) {
            cur += sequence[end]; // 현재 값을 윈도우 합에 추가

            // 현재 합(cur)이 k를 초과하면 윈도우를 줄이며 조정
            while (cur > k && start <= end) {
                cur -= sequence[start]; // 시작 위치 값을 제외
                start++;                // 시작 위치를 오른쪽으로 이동
            }

            // 현재 합이 k와 같을 때 최소 길이를 갱신
            if (cur == k) {
                if (end - start + 1 < min) { // 최소 길이 조건 확인
                    min = end - start + 1;  // 최소 길이 갱신
                    answer_st = start;      // 구간 시작 인덱스 저장
                    answer_en = end;        // 구간 끝 인덱스 저장
                }
            }
            end++; // 윈도우 끝을 확장
        }

        // 최소 길이 구간의 시작과 끝 인덱스를 반환
        return new int[]{answer_st, answer_en};
    }

    public static void main(String[] args) {
        int[] sequence = {1, 2, 3, 4, 5};
        int k = 7;

        int[] answer = solution(sequence, k);

        System.out.println(Arrays.toString(answer));
    }

}

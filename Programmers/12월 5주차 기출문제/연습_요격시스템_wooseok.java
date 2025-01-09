package codingTest;

import java.util.Arrays;

public class 연습_요격시스템_wooseok {
    public static int solution(int[][] targets) {
        // 폭격 미사일을 종료 지점(e)을 기준으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0]; // 종료 지점이 같으면 시작 지점 기준으로 정렬
            return o1[1] - o2[1];
        });

        int ans = 0;  // 최소 요격 미사일 수
        double lastInterceptPosition = -1; // 마지막으로 발사한 요격 미사일의 x 좌표

        // 각 폭격 미사일의 구간을 순회
        for (int[] target : targets) {
            int start = target[0]; // 폭격 미사일의 시작 지점
            int end = target[1];   // 폭격 미사일의 종료 지점

            // 현재 폭격 미사일이 마지막 요격 위치 이후에 있다면 새로운 요격 미사일 발사
            if (lastInterceptPosition < start) {
                ans++; // 요격 미사일 개수 증가
                lastInterceptPosition = end - 0.5; // 현재 폭격 미사일 구간의 종료 지점 직전에 요격
            }
        }

        return ans; // 최소 요격 미사일 수 반환
    }

    public static void main(String[] args) {
        // 테스트 입력 데이터
        int[][] targets = {
                {4, 5},
                {4, 8},
                {10, 14},
                {11, 13},
                {5, 12},
                {3, 7},
                {1, 4}
        };

        // 결과 출력
        System.out.println(solution(targets)); // 기대 결과: 최소 요격 미사일 수
    }
}


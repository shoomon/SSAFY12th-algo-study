package codingTest;

import java.util.*;

public class 연습_인사고과_wooseok {

    public static int solution(int[][] scores) {
        int myScore1 = scores[0][0]; // 후보(완호)의 근무 태도 점수
        int myScore2 = scores[0][1]; // 후보(완호)의 동료 평가 점수
        int myTotal = myScore1 + myScore2; // 후보의 총점

        // 첫 번째 점수를 내림차순, 두 번째 점수를 오름차순 정렬
        Arrays.sort(scores, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);

        int rank = 1;  // 후보보다 높은 총점을 가진 사람 수 + 1 (등수)
        int maxSecond = 0;  // 지금까지 살아남은 지원자의 두 번째 점수 최댓값

        for (int[] score : scores) {
            int total = score[0] + score[1];

            // maxSecond보다 작은 동료 평가 점수라면 탈락
            if (score[1] < maxSecond) {
                // 만약 탈락자가 후보(완호)라면 -1 반환
                if (score[0] == myScore1 && score[1] == myScore2) return -1;
                continue;
            }

            // 현재 점수가 살아남았다면 maxSecond 갱신
            maxSecond = Math.max(maxSecond, score[1]);

            // 후보보다 총점이 높은 경우 등수 밀리기
            if (total > myTotal) rank++;
        }
        return rank;
    }

    public static void main(String[] args) {
        // 테스트 입력: scores [[2,2],[1,4],[3,2],[3,2],[2,1]]
        int[][] scores = {
                {2,2},
                {1,4},
                {3,2},
                {3,2},
                {2,1}
        };
        System.out.println(solution(scores)); // 결과: 2
    }
}

import java.util.*;
import java.io.*;

public class PerformanceEvaluation {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] scores = {{2,2},{1,4},{3,2},{3,2},{2,1}};
        int ans = sol.solution(scores);
        System.out.println(ans);
    }

    static class Solution {
        public int solution(int[][] scores) {
            int[] wanHo = scores[0];
            // 근무태도점수 내림차순으로 정렬. 태도 점수 동일 시, 평가점수 오름차순 정렬
            Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
            int answer = 1, maxScore = 0, wanHoSum = wanHo[0] + wanHo[1];
            for (int[] score : scores) {
                // 탈락 대상으로 선정되는 경우, (이미 근무 태도 점수가 낮은 상태에서, 동료 평가 점수가 낮은 경우)
                if (score[1] < maxScore) {
                    // 탈락대상에 완호가 포함되면 그 즉시 -1 반환
                    if (score.equals(wanHo))
                        return -1;
                } else {
                    // 탈락 대상을 식별하기 위한 동료 평가 max 점수 고려.
                    maxScore = Math.max(maxScore, score[1]);
                    // 2개의 점수 합이 완호보다 높으면 완호보다 앞에 있는 사람
                    if (score[0] + score[1] > wanHoSum)
                        answer++;
                }
            }
            return answer;
        }
    }
}

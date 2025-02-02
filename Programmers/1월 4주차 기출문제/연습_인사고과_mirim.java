package 프로그래머스;

import java.util.*;

class 연습_인사고과_mirim {
    public int solution(int[][] scores) {
        int[] wanho = scores[0]; // 완호의 점수
        int wanhoSum = wanho[0] + wanho[1]; // 완호의 두 점수 합
        
        // 1. 점수를 근무 태도 기준으로 내림차순, 근무 태도 동일하면 동료 평가 기준으로 오름차순 정렬
        Arrays.sort(scores, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);
        
        int maxPeerScore = 0; // 현재까지의 최고 동료 평가 점수
        int rank = 1; // 현재 석차
        int higherSumCount = 0; // 완호보다 높은 점수를 가진 사람 수
        
        for (int[] score : scores) {
            // 두 점수가 모두 낮은 경우 -> 인센티브 받을 수 없음
            if (score[1] < maxPeerScore) {
                if (score == wanho) return -1;
                continue;
            }
            
            maxPeerScore = Math.max(maxPeerScore, score[1]); // 최고 동료 평가 점수 갱신
            
            int sum = score[0] + score[1]; // 현재 사원의 점수 합 계산
            if (sum > wanhoSum) higherSumCount++; // 완호보다 높은 점수면 카운트 증가
        }
        
        return higherSumCount + 1; // 완호의 순위 반환
    }
}

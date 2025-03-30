package 프로그래머스;

public class KAKAO_양궁대회_mirim {

    class Solution {
        // 최대 점수 차이와 해당 배분 결과를 저장하는 전역 변수
        static int maxDiff;
        static int[] bestCandidate;

        public int[] solution(int n, int[] info) {
            maxDiff = 0;
            bestCandidate = new int[11];

            // 2^11 경우의 수를 bitmask로 탐색
            for (int mask = 0; mask < (1 << 11); mask++) {
                int[] candidate = new int[11]; // 현재 경우의 라이언 화살 배분
                int arrowsUsed = 0;            // 사용한 화살 수 누적

                // 각 점수(10점~0점)별로 라이언의 전략 결정
                for (int i = 0; i < 11; i++) {
                    // bit가 1이면, 해당 점수를 이기기 위해 어피치보다 한 발 더 쏨
                    if ((mask & (1 << i)) != 0) {
                        candidate[i] = info[i] + 1;
                        arrowsUsed += candidate[i];
                    }
                }
                // 사용한 화살이 제한을 넘으면 건너뜀
                if (arrowsUsed > n) continue;
                
                // 남은 화살은 0점(인덱스 10)에 모두 할당
                candidate[10] += (n - arrowsUsed);
                
                int ryanScore = 0, apeachScore = 0;
                // 라이언과 어피치의 점수를 계산
                for (int i = 0; i < 11; i++) {
                    if (candidate[i] == 0 && info[i] == 0) continue; // 둘 다 맞추지 않은 경우는 무시
                    if (candidate[i] > info[i]) ryanScore += (10 - i);  // 라이언 승리 시 해당 점수 획득
                    else apeachScore += (10 - i);                       // 그 외는 어피치 점수 획득
                }
                
                int diff = ryanScore - apeachScore;
                // 라이언이 이기는 경우에만 고려
                if (diff > 0) {
                    // 최대 점수 차이가 갱신되면 결과 저장
                    if (diff > maxDiff) {
                        maxDiff = diff;
                        bestCandidate = candidate.clone();
                    } 
                    // 점수 차이가 동일할 경우, 낮은 점수를 더 많이 맞힌 후보 우선
                    else if (diff == maxDiff && isBetter(candidate, bestCandidate)) {
                        bestCandidate = candidate.clone();
                    }
                }
            }
            // 라이언이 이길 방법이 없으면 -1 반환
            return maxDiff == 0 ? new int[]{-1} : bestCandidate;
        }

        // 두 배분 결과를 비교하여, 낮은 점수를 더 많이 맞춘 경우를 우선시
        private boolean isBetter(int[] candidate1, int[] candidate2) {
            for (int i = 10; i >= 0; i--) {
                if (candidate1[i] > candidate2[i]) return true;
                else if (candidate1[i] < candidate2[i]) return false;
            }
            return false;
        }
    }
}

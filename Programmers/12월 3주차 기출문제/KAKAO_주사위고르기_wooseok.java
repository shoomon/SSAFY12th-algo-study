package codingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KAKAO_주사위고르기_wooseok {
    private int[][] dice; // 주사위 배열
    private int[] combi; // 현재 조합을 저장하는 배열
    private ArrayList<int[]> diceCombination; // 주사위를 나누는 모든 조합 저장
    private ArrayList<Integer> score; // 점수 계산에 사용되는 임시 저장소

    public static void main(String[] args) {
        // 테스트 데이터: 주사위 배열
        int[][] dice = {
                {1, 2, 3, 4, 5, 6},
                {3, 3, 3, 3, 4, 4},
                {1, 3, 3, 4, 4, 4},
                {1, 1, 4, 4, 5, 5}
        };

        // 클래스 인스턴스 생성 및 결과 출력
        KAKAO_주사위고르기_wooseok solver = new KAKAO_주사위고르기_wooseok();
        int[] result = solver.solution(dice);

        System.out.println("Best Combination: " + Arrays.toString(result));
    }

    // 주사위 조합을 통해 가장 승률이 높은 조합을 계산하는 메서드
    public int[] solution(int[][] dice) {
        int[] answer = {}; // 최적의 조합을 저장할 배열
        int diceCnt = dice.length; // 주사위 개수
        int maxWinCnt = 0; // 최대 승리 횟수

        this.dice = dice;
        this.diceCombination = new ArrayList<>(); // 주사위 조합 초기화
        this.score = new ArrayList<>(); // 점수 계산에 사용되는 리스트 초기화

        combi = new int[diceCnt / 2]; // 주사위를 반으로 나누는 조합 초기화

        // 모든 가능한 주사위 조합 생성
        calculateCombination(0, 0, diceCnt / 2);

        // 각 조합을 통해 승리 횟수 계산
        for (int i = 0; i < diceCombination.size() / 2; i++) {
            int myIdx = i; // 나의 조합 인덱스
            int oppIdx = diceCombination.size() - i - 1; // 상대 조합 인덱스

            int[] myCombination = diceCombination.get(myIdx);
            int[] oppCombination = diceCombination.get(oppIdx);

            // 각 조합의 점수 분포 계산
            HashMap<Integer, Integer> myScoreCnt = calculateScoreCnt(myCombination, 0, diceCnt / 2);
            HashMap<Integer, Integer> oppScoreCnt = calculateScoreCnt(oppCombination, 0, diceCnt / 2);

            int winCnt = 0; // 승리 횟수
            int loseCnt = 0; // 패배 횟수

            // 각 점수에 대해 승리와 패배 계산
            for (int m : myScoreCnt.keySet()) {
                for (int o : oppScoreCnt.keySet()) {
                    if (m > o) {
                        winCnt += (myScoreCnt.get(m) * oppScoreCnt.get(o));
                    } else if (m < o) {
                        loseCnt += (myScoreCnt.get(m) * oppScoreCnt.get(o));
                    }
                }
            }

            // 최대 승리 횟수를 갱신하고 최적의 조합 저장
            if (maxWinCnt < winCnt) {
                maxWinCnt = winCnt;
                answer = myCombination;
            }
            if (maxWinCnt < loseCnt) {
                maxWinCnt = loseCnt;
                answer = oppCombination;
            }
        }

        // 최종적으로 인덱스를 1부터 시작하도록 수정
        for (int i = 0; i < answer.length; i++) {
            answer[i]++;
        }
        return answer;
    }

    // 주어진 깊이까지 모든 주사위 조합을 생성하는 메서드
    private void calculateCombination(int start, int curDepth, int maxDepth) {
        if (curDepth == maxDepth) {
            diceCombination.add(arrayDeepCopy(combi)); // 현재 조합 저장
            return;
        }
        for (int i = start; i < dice.length; i++) {
            combi[curDepth] = i; // 현재 인덱스를 조합에 추가
            calculateCombination(i + 1, curDepth + 1, maxDepth); // 다음 깊이 탐색
        }
    }

    // 배열을 깊은 복사하여 반환하는 메서드
    private int[] arrayDeepCopy(int[] arr) {
        return Arrays.stream(arr).toArray();
    }

    // 주어진 조합의 점수 분포를 계산하는 메서드
    private HashMap<Integer, Integer> calculateScoreCnt(int[] combi, int curDepth, int maxDepth) {
        if (curDepth == maxDepth) {
            HashMap<Integer, Integer> scoreCnt = new HashMap<>(); // 점수 분포 저장
            for (int s : score) {
                scoreCnt.put(s, scoreCnt.getOrDefault(s, 0) + 1); // 점수 빈도 계산
            }
            score.clear(); // 점수 리스트 초기화
            return scoreCnt;
        }
        int idx = combi[curDepth]; // 현재 주사위 인덱스

        // 점수 계산 로직
        if (score.isEmpty()) { // 첫 주사위 점수를 추가
            for (int i = 0; i < 6; i++) {
                score.add(dice[idx][i]);
            }
        } else {
            int size = score.size();
            for (int i = 0; i < size; i++) {
                int s = score.remove(0);
                for (int j = 0; j < 6; j++) {
                    score.add(dice[idx][j] + s); // 점수 누적 계산
                }
            }
        }
        return calculateScoreCnt(combi, curDepth + 1, maxDepth); // 다음 깊이 탐색
    }
}
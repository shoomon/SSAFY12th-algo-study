import java.util.*;
import java.io.*;

class Solution {
    int[][] diceSet;
    // A에게 선택된 주사위 = true, B에게 선택된 주사위 = false.
    boolean[] selected;

    // 승리 횟수가 많은 조합
    List<Integer> winComb = new ArrayList<>();
    int maxWinCnt = 0;

    public int[] solution(int[][] dice) {
        diceSet = dice;
        // 주사위 선택 배열 확인용
        selected = new boolean[dice.length];

        recur(0, 0);

        int[] answer = new int[winComb.size()];
        for (int i = 0; i < winComb.size(); i++)
            answer[i] = winComb.get(i);

        return answer;
    }

    public void recur(int idx, int cnt) {
        if (idx == selected.length) {
            // 정확히 절반만 사용한 경우 진행
            if (cnt == selected.length / 2) {
                List<Integer> aDices = new ArrayList<>();
                List<Integer> bDices = new ArrayList<>();
                for (int i = 0; i < selected.length; i++) {
                    if (selected[i]) aDices.add(i);
                    else bDices.add(i);
                }

                List<Integer> aSums = getSums(aDices);
                List<Integer> bSums = getSums(bDices);
                int winCnt = 0;
                for (int aSum : aSums) {
                    int left = 0;
                    int right = bSums.size() - 1;
                    int winIdx = 0;
                    while (left <= right) {
                        int mid = (left + right) / 2;
                        // 승리하는 경우
                        if (bSums.get(mid) < aSum) {
                            winIdx = mid;
                            left = mid + 1;
                        } else
                            right = mid - 1;
                    }
                    winCnt += winIdx + 1;
                }
                if (maxWinCnt < winCnt) {
                    winComb = aDices;
                    maxWinCnt = winCnt;
                }
            }
            return;
        }

        selected[idx] = true;
        recur(idx + 1, cnt + 1);
        selected[idx] = false;
        recur(idx + 1, cnt);
    }

    // 나올 수 있는 주사위 조합 구하기
    public List<Integer> getSums(List<Integer> dices) {
        List<Integer> sums = new ArrayList<Integer>();
        combineDices(dices, sums, 0, 0);
        Collections.sort(sums);
        return sums;
    }

    public void combineDices(List<Integer> dices, List<Integer> sums, int idx, int sum) {
        if (idx == dices.size()) {
            sums.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++)
            combineDices(dices, sums, idx + 1, sum + diceSet[dices.get(idx)][i]);
    }
}


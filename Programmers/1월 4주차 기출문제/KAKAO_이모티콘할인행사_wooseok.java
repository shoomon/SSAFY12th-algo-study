package codingTest;

import java.util.*;

public class KAKAO_이모티콘할인행사_wooseok {
    // 가능한 할인율 목록
    public static final int[] DISCOUNTS = {10, 20, 30, 40};

    //사용자 정보와 이모티콘 가격, 이모티콘 개수
    public static int[][] users;
    public static int[] emoticons;
    public static int n;

    // 최종 결과: 최대 플러스 가입자 수와 최대 매출액
    public static int maxSubscribers = 0;
    public static int maxRevenue = 0;

    public static int[] solution(int[][] usersInput, int[] emoticonsInput) {
        // 입력값 복사
        users = usersInput;
        emoticons = emoticonsInput;
        n = emoticons.length;

        // 백트래킹을 통한 할인 조합 탐색 시작
        backtracking(0, new int[n]);

        return new int[]{maxSubscribers, maxRevenue};
    }

    //재귀적으로 모든 할인 조합을 생성

    public static void backtracking(int idx, int[] discountCombination) {
        if (idx == n) {
            evaluate(discountCombination);
            return;
        }
        // 가능한 할인율 각각에 대해 재귀 호출
        for (int discount : DISCOUNTS) {
            discountCombination[idx] = discount;
            backtracking(idx + 1, discountCombination);
        }
    }

    //현재 할인 조합을 기준으로 각 사용자의 구매액 및 플러스 가입 여부를 평가
    public static void evaluate(int[] discountCombination) {
        int subscribers = 0; // 해당 할인 조합에서 플러스 가입한 사용자 수
        int revenue = 0;     // 해당 할인 조합에서 발생한 매출액

        // 모든 사용자에 대해 평가
        for (int[] user : users) {
            int userDiscountThreshold = user[0];  // 사용자가 요구하는 최소 할인율
            int spendingThreshold = user[1];        // 이 금액 이상 구매 시 플러스 가입
            int sum = 0;  // 해당 사용자의 이모티콘 구매 총액

            // 각 이모티콘 할인 적용 후 구매액 계산
            for (int i = 0; i < n; i++) {
                if (discountCombination[i] >= userDiscountThreshold) {
                    sum += emoticons[i] * (100 - discountCombination[i]) / 100;
                }
            }

            // 구매액이 임계치를 넘으면 플러스 가입, 그렇지 않으면 매출액에 반영
            if (sum >= spendingThreshold) {
                subscribers++;
            } else {
                revenue += sum;
            }
        }

        // 최종 결과 갱신: 가입자가 더 많거나 가입자가 같을 경우 매출액이 더 높으면 업데이트
        if (subscribers > maxSubscribers || (subscribers == maxSubscribers && revenue > maxRevenue)) {
            maxSubscribers = subscribers;
            maxRevenue = revenue;
        }
    }

    // 테스트용 main 메서드
    public static void main(String[] args) {
        int[][] users = { {40, 10000}, {25, 10000} };
        int[] emoticons = {7000, 9000};
        System.out.println(Arrays.toString(solution(users, emoticons)));
    }
}

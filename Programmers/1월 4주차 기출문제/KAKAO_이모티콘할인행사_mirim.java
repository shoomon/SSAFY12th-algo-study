package 프로그래머스;


import java.util.*;

// 그냥 공부함...

class SolutKAKAO_이모티콘할인행사_mirimion {
    // 사용할 수 있는 할인율 (10%, 20%, 30%, 40%)을 저장하는 배열
    private static final int[] DISCOUNT_RATES = {10, 20, 30, 40};
    
    // 최적의 결과를 저장할 변수들
    private int maxSubscribers = 0; // 최대한 많은 가입자를 저장하는 변수
    private int maxSales = 0; // 최대한 많은 매출액을 저장하는 변수
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 각 이모티콘에 대해 할인율을 저장할 배열 (초기화)
        int[] discounts = new int[emoticons.length];
        
        // 할인율을 설정하면서 최적의 할인 조합을 찾음
        findBestDiscounts(0, discounts, users, emoticons);
        
        // 최종적으로 최대 가입자 수와 최대 매출액을 배열로 반환
        return new int[]{maxSubscribers, maxSales};
    }
    
    private void findBestDiscounts(int index, int[] discounts, int[][] users, int[] emoticons) {
        // 모든 이모티콘에 대한 할인율이 결정된 경우
        if (index == emoticons.length) {
            // 현재 할인율 조합에 대해 가입자 수와 매출액을 계산
            evaluateDiscounts(discounts, users, emoticons);
            return;
        }
        
        // 현재 이모티콘(index)에 대해 가능한 할인율(10, 20, 30, 40%)을 설정해봄
        for (int discount : DISCOUNT_RATES) {
            discounts[index] = discount; // 현재 이모티콘에 할인율 적용
            findBestDiscounts(index + 1, discounts, users, emoticons); // 다음 이모티콘 할인율 설정으로 이동
        }
    }
    
    private void evaluateDiscounts(int[] discounts, int[][] users, int[] emoticons) {
        int subscribers = 0; // 현재 할인율 조합에서 이모티콘 플러스 가입자 수
        int totalSales = 0; // 현재 할인율 조합에서 이모티콘 판매액
        
        // 각 사용자에 대해 이모티콘 구매 또는 서비스 가입 여부를 판단
        for (int[] user : users) {
            int requiredDiscount = user[0]; // 사용자가 원하는 최소 할인율 (이 이상이면 구매 고려)
            int priceLimit = user[1]; // 사용자가 이 금액 이상을 지출하면 구독을 선택함
            int totalCost = 0; // 사용자가 구매할 이모티콘의 총 가격
            
            // 사용자가 할인 기준을 만족하는 이모티콘을 구매하는 과정
            for (int i = 0; i < emoticons.length; i++) {
                if (discounts[i] >= requiredDiscount) { // 현재 이모티콘 할인율이 사용자의 기준 이상인지 확인
                    totalCost += emoticons[i] * (100 - discounts[i]) / 100; // 할인된 가격으로 구매 비용 추가
                }
            }
            
            // 사용자가 구매 비용이 한도를 초과하면 이모티콘 플러스 서비스 가입
            if (totalCost >= priceLimit) {
                subscribers++; // 구독자 수 증가
            } else {
                totalSales += totalCost; // 이모티콘을 구매한 경우 매출 증가
            }
        }
        
        // 현재 계산된 가입자 수와 매출액이 기존 최대값보다 좋은 경우 갱신
        if (subscribers > maxSubscribers || (subscribers == maxSubscribers && totalSales > maxSales)) {
            maxSubscribers = subscribers; // 최대 가입자 수 갱신
            maxSales = totalSales; // 최대 매출액 갱신
        }
    }
}

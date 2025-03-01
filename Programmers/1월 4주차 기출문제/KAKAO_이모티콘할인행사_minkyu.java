import java.util.*;
import java.io.*;

public class EmoEvent {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] users = { {40, 10000}, {25, 10000} };
        int[] emoticons = {7000, 9000};
        int[] ans = sol.solution(users, emoticons);
        System.out.println(Arrays.toString(ans));
    }

    static class Solution {
        int[] discountPercent, maxVals, emoticonInfos;
        int[][] userInfos;
        public int[] solution(int[][] users, int[] emoticons) {
            int[] answer = new int[2];
            emoticonInfos = emoticons;
            userInfos = users;
            // 비교에 활용할 전체 이모티콘 할인율 적용량
            discountPercent = new int[emoticons.length];
            // 최대 값 갱신하면서 진행
            maxVals = new int[2];
            // 각 이모티콘 별 값을 정해놓고, 해당 값을 이용 전체 user가 어떻게 결정하는지 확인
            recur(0);
            return maxVals;
        }
        public void recur(int idx){
            // 마지막까지 이동한 경우
            if (idx == discountPercent.length){
                int subscribeCnt = 0, totalSum = 0;
                // 모든 회원의 구매 여부를 확인해서 진행
                for (int[] user : userInfos){
                    int sumPrice = 0;
                    for (int i = 0; i < emoticonInfos.length; i++){
                        if (user[0] > discountPercent[i]) continue;
                        int curPrice = emoticonInfos[i] /100 * (100 - discountPercent[i]);
                        sumPrice += curPrice;
                    }
                    if (user[1] <= sumPrice) subscribeCnt++;
                    else totalSum += sumPrice;
                }
                // 현재 선택된 정보가 최대의 값인지에 따라 변경 가능성을 판단함.
                if (maxVals[0] < subscribeCnt) {
                    maxVals[0] = subscribeCnt;
                    maxVals[1] = totalSum;
                }else if (maxVals[0] == subscribeCnt){
                    maxVals[1] = Math.max(maxVals[1], totalSum);
                }
                return;
            }

            // 각 이모티콘 별 할인율을 10에서 40까지 설정하는 과정
            for (int disc = 10; disc <= 40; disc+=10){
                discountPercent[idx] = disc;
                recur(idx+1);
            }
        }
    }
}

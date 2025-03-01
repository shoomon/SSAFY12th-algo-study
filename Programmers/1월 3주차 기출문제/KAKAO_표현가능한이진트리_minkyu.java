import java.util.*;
import java.io.*;

public class ExpressableBinaryTree {
    public static void main(String[] args) {
        Solution sol = new Solution();
        long[] numbers = {15, 42, 5};
        int[] ans = sol.solution(numbers);
        System.out.println(Arrays.toString(ans));
    }

    static class Solution {
        int[] binaryNum;
        boolean isPossible;
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];
            int idx = 0;
            for (long number : numbers){
                int cnt = 0;
                while(true){
                    if (Math.pow(2,cnt) - 1 < number) cnt++;
                    else break;
                }
                int level = 1;
                // 이진트리로 단계 검사
                while (true){
                    if ((int)Math.pow(2,level) - 1 < cnt) level++;
                    else break;
                }
                cnt = (int)Math.pow(2,level) - 1;

                binaryNum = new int[cnt];
                int pos = cnt - 1;
                // 이진 수로 나타내기
                int needCnt = 0;
                while (number > 1){
                    // 나머지 넣어주기.
                    binaryNum[pos--] = (int)(number % 2);
                    needCnt++;
                    number /= 2;
                    if (number == 1){
                        binaryNum[pos] = 1;
                        needCnt++;
                    }
                }

                isPossible = true;
                // 해당 위치가 제대로 된 것인지 체크할 것.
                checkOk(0, cnt-1, true);

                answer[idx++] = isPossible ? 1 : 0;
            }
            return answer;
        }

        public void checkOk(int left, int right, boolean isOk){
            if (!isPossible) return;
            int mid = (left + right) / 2;
            if (left >= right) {
                if (!isOk && binaryNum[mid] == 1) isPossible = false;
                return;
            }

            // 해당 위치에 노드가 있는 경우
            if (binaryNum[mid] == 1){
                // 전에 부모가 있었던 경우
                if (isOk){
                    checkOk (left , mid-1, true);
                    checkOk (mid+1, right, true);
                }else{
                    isPossible = false;
                    return;
                }
            // 해당 위치에 노드가 없는 경우    
            }else{
                checkOk (left, mid - 1, false);
                checkOk (mid + 1, right, false);
            }
        }
    }
}

import java.util.*;
import java.io.*;

public class BiggerNumber {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] numbers = {9, 1, 5, 3, 6, 2};
        int[] ans = sol.solution(numbers);
        System.out.println(Arrays.toString(ans));
    }

    static class Solution {
        public int[] solution(int[] numbers) {
            int[] answer = new int[numbers.length];
            Arrays.fill(answer, -1);
            Stack<int[]> sta = new Stack<>();
            sta.push(new int[]{numbers[0],0});
            for (int i = 1; i < numbers.length; i++){
                while (!sta.isEmpty()){
                    // 현재 위치의 값이 큰 경우 기존에 위치하던 것 꺼냄
                    if (sta.peek()[0] < numbers[i]){
                        int[] cur = sta.pop();
                        answer[cur[1]] = numbers[i];
                    }else{
                        break;
                    }
                }
                sta.push(new int[]{numbers[i],i});
            }

            return answer;
        }
    }
}

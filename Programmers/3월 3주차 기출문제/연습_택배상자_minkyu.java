import java.util.*;

public class DeliverBox {
    public static void main(String[] args) {

    }

    public static class Solution {
        public int solution(int[] order) {
            int answer = 0;
            int n = order.length;

            Stack<Integer> subBelt = new Stack<>();

            int curBox = 1;

            for (int i = 0; i < n; i++) {
                int tarBox = order[i];

                // 넣어야되는 박스보다 작은 경우
                while (curBox < tarBox) {
                    subBelt.push(curBox);
                    curBox++;
                }

                // 컨테이너 벨트와 순서가 맞는 경우
                if (curBox == tarBox) {
                    answer++;
                    curBox++;
                }
                // 보조 벨트가 안비었고, 제일 상단이 현재 위치와 동일한 경우
                else if (!subBelt.isEmpty() && subBelt.peek() == tarBox) {
                    subBelt.pop();
                    answer++;
                }
                else
                    break;
            }

            return answer;
        }
    }
}

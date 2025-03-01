import java.util.Stack;

public class DeliveryRetreive {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};
        long ans = sol.solution(4,5, deliveries, pickups);
        System.out.println(ans);
    }

    static class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            long answer = -1;
            Stack<int[]> deliverStack = new Stack<>();
            Stack<int[]> pickupStack = new Stack<>();
            for (int i = 0; i < deliveries.length; i++) deliverStack.push(new int[]{deliveries[i], i+1});
            for (int i = 0; i < pickups.length; i++) pickupStack.push(new int[]{pickups[i], i+1});

            answer = 0;
            // Stack이 빌때까지 왔다갔다 진행해야함.
            while (!deliverStack.isEmpty() || !pickupStack.isEmpty()) {
                int deliverDist = 0, pickupDist = 0;
                int left = cap;
                // 배송을 가면서 마지막 근처에 있는 배송지만 먼저 배송을 진행하는 방식
                while(left > 0 && !deliverStack.isEmpty()){
                    int[] tmp = deliverStack.pop();
                    // 마지막 위치 비교
                    if (tmp[0] > 0) deliverDist = Math.max(tmp[1], deliverDist);
                    // 배송지에 배달할 택배가 남아있지 않으면, 빼버릴 것.
                    if (tmp[0] <= left) left -= tmp[0];
                    // 아직 배송지에 배달할 택배가 남아있는 경우 넣어 놓을 것.
                    else{
                        tmp[0] -= left;
                        deliverStack.push(tmp);
                        break;
                    }
                }
                left = cap;
                // 배송을 마치고 돌아오면서 수거하는 과정을 거침
                while(left > 0 && !pickupStack.isEmpty()){
                    int[] tmp = pickupStack.pop();
                    // 마지막 위치 비교
                    if (tmp[0] > 0) pickupDist = Math.max(tmp[1], pickupDist);
                    // 수거지에 수거할 박스가 남아있으면, 빼버릴 것.
                    if (tmp[0] <= left) left -= tmp[0];
                    // 아직 수거지에 수거할 박스가 남아있는 경우 넣어 놓을 것.
                    else{
                        tmp[0] -= left;
                        pickupStack.push(tmp);
                        break;
                    }
                }
                answer += Math.max(deliverDist, pickupDist) * 2;
            }

            return answer;
        }
    }
}

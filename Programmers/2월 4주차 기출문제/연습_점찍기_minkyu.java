public class MakeDots {
    public static void main(String[] args) {
        Solution sol = new Solution();
        long ans = sol.solution(1,1);
        System.out.println(ans);
    }

    public static class Solution{
        public long solution(int k, int d){
            long answer = 0;
            long poweredDist = d * d;
            int limit = d / k;
            answer += limit + 1;
            long cnt = 0;
            for (int i = 1; i <= limit; i++){
                int left = 0;
                int right = limit;
                int max = 0;
                while (left <= right){
                    int mid = (left + right)/2;
                    if (getDist(mid * k, i * k) <= poweredDist){
                        max = mid;
                        left = mid + 1;
                    }else
                        right = mid - 1;
                }
                cnt += max;
            }
            answer += cnt;
            return answer;
        }

        public long getDist(int a, int b){
            return a * a + b * b;
        }
    }
}

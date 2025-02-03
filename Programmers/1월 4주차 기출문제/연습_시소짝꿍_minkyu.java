public class SeeSawPair {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] weights = {100,180,360,100,270};
        long ans = sol.solution(weights);
        System.out.println(ans);
    }

    static class Solution {
        public long solution(int[] weights) {
            long answer = 0;
            // 기존 몸무게 범위도 개수를 계산할 것.
            int[] originCnt = new int[1001];
            // 몸무게 범위가 1000까지이므로, 4를 곱한 4000까지의 개수를 셀 것.
            int[] weightCnt = new int[4001];
            for (int i = 0; i < weights.length; i++){
                originCnt[weights[i]]++;
                weightCnt[weights[i] * 2]++;
                weightCnt[weights[i] * 3]++;
                weightCnt[weights[i] * 4]++;
            }
            // 무게의 개수를 세서 처리 진행
            for (int i: weights){
                // 본인 것 까지 포함해서 더한 다음, 마지막에 제외시킬 것.
                // 완전 동일무게인 경우 2를 빼줘야 함 (한 번으로 쳐야 되기 때문에).
                answer += weightCnt[i * 2] + weightCnt[i*3] + weightCnt[i*4] - ((originCnt[i] - 1) * 2) - 3;
            }
            // 2번씩 비교되었기 때문에 2로 나누어 줄 것
            answer /= 2;
            return answer;
        }
    }
}

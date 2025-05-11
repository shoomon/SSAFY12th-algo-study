class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0;
        long right = 4_000_000_000_000_000L;
        long answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long totalGold = 0;
            long totalSilver = 0;
            long totalPossible = 0;

            for (int i = 0; i < g.length; i++) {
                long roundTrip = 2L * t[i]; 
                long trips = mid / roundTrip;
                long maxWeight = trips * w[i];

                if (mid % roundTrip >= t[i]) {
                    maxWeight += w[i];
                }

                long availableGold = Math.min(g[i], maxWeight);
                long availableSilver = Math.min(s[i], maxWeight);
                long availableTotal = Math.min(maxWeight, g[i] + s[i]);

                totalGold += availableGold;
                totalSilver += availableSilver;
                totalPossible += availableTotal;
            }

            if (totalGold >= a && totalSilver >= b && totalPossible >= a + b) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
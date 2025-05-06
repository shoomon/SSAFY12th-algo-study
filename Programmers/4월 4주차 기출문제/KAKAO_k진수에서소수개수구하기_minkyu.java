class Solution {
    public int solution(int n, int k) {
        String kBase = Integer.toString(n, k);
        
        String[] candidates = kBase.split("0+");
        
        int count = 0;
        for (String candidate : candidates) {
            if (candidate.isEmpty()) continue;

            long num = Long.parseLong(candidate);
            if (isPrime(num)) count++;
            
        }
        
        return count;
    }
    
    // 소수 판별
    private boolean isPrime(long num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        
        for (long i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
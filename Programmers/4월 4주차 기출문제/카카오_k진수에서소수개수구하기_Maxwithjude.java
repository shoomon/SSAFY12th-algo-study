class Solution {
    public int solution(int n, int k) {
        String converted = Integer.toString(n, k); // n을 k진수로 변환하는 함수
        String[] parts = converted.split("0");     // 0을 기준으로 나눔
        int count = 0;

        for (String part : parts) {
            if (part.isEmpty()) continue; // 빈 문자열 무시
            long num = Long.parseLong(part); // 크기 때문에 long 사용
            if (isPrime(num)) count++;
        }
        return count;
    }
    
    private boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; i++) { // 제곱근까지만 검사하기
            if (num % i == 0) return false;
        }
        return true;
    }
}

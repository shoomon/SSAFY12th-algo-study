class Solution {
    public int solution(int n, int k) {
        // 진수변환
        String transform = Integer.toString(n,k);
        // 0 기준으로 자르기
        String[] arr = transform.split("0");
        // 소수 개수
        int cnt = 0;
        // 0아닌 숫자만 뽑아서 소수 판별 
        for(String s : arr){
            //00, 000 같이 붙어있으면 자르면 공백("")이 나온다
            if("".equals(s)) continue; 
            // 소수면 cnt +1 증가
            if(isPrime(Double.parseDouble(s))) cnt++;
        }

        // 정답 리턴 
        return cnt;
    }
    // 소수 판별 
    public boolean isPrime(double n){
        // 1이하면, 소수아님 
        if(n<=1){
            return false;
        }
        // 2,3은 소수 
        else if(n<=3){
            return true;
        }
        // 2~제곱근(n)의 수로 나누어 지면 소수아님 
        for(double i=2; i<=Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        // 안나눠지면 소수 
        return true;
    }
}

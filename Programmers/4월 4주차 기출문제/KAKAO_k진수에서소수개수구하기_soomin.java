import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String num = convert(n,k);
        String[] nums = num.split("0+");
        
        for(String s : nums){
            if(isPrime(Long.parseLong(s))) answer++;
        }
        
        return answer;
    }
    
    static String convert(int n, int k){
        StringBuilder ret = new StringBuilder();
        
        while(n > 0){
            ret.append(n%k);
            n /= k;
        }
        
        return ret.reverse().toString();
    }
    
    static boolean isPrime(long num){
        if(num == 1) return false;
        
        for(long i = 2; i <= Math.sqrt(num); i++){
            if(num%i == 0) return false;
        }
        return true;
    }
}
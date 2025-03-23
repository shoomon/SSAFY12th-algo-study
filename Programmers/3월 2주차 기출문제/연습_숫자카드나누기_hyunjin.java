import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        // arrayA와 arrayB의 최대공약수 구하기
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        for(int i=1; i<arrayA.length; i++){
            gcdA = gcd(arrayA[i], gcdA);
            gcdB = gcd(arrayB[i], gcdB);
        }

        // System.out.println(gcdA);
        // System.out.println(gcdB);

        // 최대공약수로 상대 카드 중에서 나누어 떨어지는 수가 하나라도 있는지 체크
        if(!canDivide(arrayB, gcdA)){
            answer = Math.max(answer, gcdA);
        }

        if(!canDivide(arrayA, gcdB)){
            answer = Math.max(answer, gcdB);
        }


        return answer;
    }

    // 유클리드 호제법 -> 최대공약수 구하기
    public static int gcd (int a, int b){
        if(b == 0){
            return a;
        }else{
            return gcd(b, a%b);
        }
    }


    public static boolean canDivide(int[] array, int gcd){
        // 하나라도 나누어 떨어지는 수가 있는지 확인
        for(int num : array){
            if(num % gcd == 0) return true;
        }
        return false;
    }
}
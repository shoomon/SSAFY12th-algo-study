//각 배열의 최대공약수

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0, gcd1=arrayA[0], gcd2=arrayB[0];
        
        for(int i = 1; i < arrayA.length; i++){
            gcd1 = getGcd(gcd1,arrayA[i]);
            gcd2 = getGcd(gcd2,arrayB[i]);
        }
        
        System.out.println(gcd1+" "+gcd2);
        
        if(checkDivison(arrayA, gcd2) && answer < gcd2) answer = gcd2;
        if(checkDivison(arrayB, gcd1) && answer < gcd1) answer = gcd1;
        
        return answer;
    }
    
    static int getGcd(int a, int b){
        int r=a%b;
        
        while(true){
            if(a%b != 0){
                r=a%b;
                a = b;
                b = r;
            }else{
                return b;
            }
        }
    }
    
    static boolean checkDivison(int[] arr, int num){
        for(int i : arr) if(i%num == 0) return false;
        return true;
    }
}
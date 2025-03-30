import java.util.Arrays;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        
        //각 배열 최솟값과 최대공약수로 해야할듯
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        int answer1 = 0;
        int answer2 = 0;
        int min1 = arrayA[0];
        int min2 = arrayB[0];
        //약수 리스트를 어떻게 뽑을지 원소값 최대 1억  
        //에라토스테네스의 체가 생각남
        //sqrt 즉 제곱근 까지
        for(int k = 0; k < arrayA.length; k++){
               //가지치기 : 큰 값부터
            for(int i = min1; i >= Math.sqrt(min1); i--){
                //모든 arrayA의 배열이 나눠떨어지고 상대배열은 하나도 나눠x면
                if(arrayB[k] % i != 0 && arrayA[k] % i == 0){
                    answer1 = i;
                    break;
                    }
                } 
            }
        
        for(int k = 0; k < arrayB.length; k++){
               //가지치기 : 큰 값부터
            for(int i = min2; i >= Math.sqrt(min2); i--){
                //모든 arrayA의 배열이 나눠떨어지고 상대배열은 하나도 나눠x면
                if(arrayB[k] % i != 0 && arrayA[k] % i == 0){
                    answer2 = i;
                        break;
                }
        } 
            }
        return answer1 > answer2 ? answer1 : answer2;
    }
}

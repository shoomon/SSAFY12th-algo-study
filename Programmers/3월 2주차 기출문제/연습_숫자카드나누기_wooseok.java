import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // 철수 카드(arrayA), 영희 카드(arrayB) 오름차순 정렬
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        // 첫 번째 카드 중에서 더 큰 숫자를 저장
        // 즉, 양의 정수 a의 최대값
        int max = Math.max(arrayA[0],arrayB[0]);

        // max부터 -1씩 나누기 검사
        for(int i=max;i>1;i--){
            // arrayA로 전부 나눌 수 있고
            // arrayB로 전부 나눌 수 없으면
            if(isDivision(arrayA,i)){
                if(isNotDivision(arrayB,i)) return i;
            }
            // arrayB로 전부 나눌 수 있고
            // arrayA로 전부 나눌 수 없으면
            else if(isDivision(arrayB,i)){
                if(isNotDivision(arrayA,i)) return i;
            }
        }
        // 정답이 없으면 0 리턴
        return 0;
    }
    // 전부 나눌 수 있는지 판별
    public boolean isDivision(int[] arr, int n){
        for(int i=0;i<arr.length;i++){
            if(arr[i]%n!=0) return false;
        }
        return true;
    }
    // 전부 나눌수 없는지 판별
    public boolean isNotDivision(int[] arr, int n){
        for(int i=0;i<arr.length;i++){
            if(arr[i]%n==0) return false;
        }
        return true;
    }
}
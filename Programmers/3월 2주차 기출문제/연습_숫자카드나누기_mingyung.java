
import java.util.*;
import java.io.*;

public class 연습_숫자카드나누기_mingyung {
	// 전부 나눌 수 있는지 체크
    boolean isDivisible(int[] arr, int num) {
        for (int i=0; i<arr.length; i++) {
            if (arr[i] % num != 0) return false;
        }
        return true;
    }
    
    // 전부 나눌 수 없는지 체크
    boolean isNotDivisible(int[] arr, int num) {
        for (int i=0; i<arr.length; i++) {
            if (arr[i] % num == 0) return false;
        }
        return true;
    }
    
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        // 작은 수 앞으로 정렬해서 최솟값 찾기
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        int max = Math.max(arrayA[0], arrayB[0]);
        
        // 최솟값부터 뒤로 돌면서 확인하기
        for (int i=max; i>=1; i--) {
            if (isDivisible(arrayA, i) && isNotDivisible(arrayB, i)) {
                answer = i;
                break;
            } else if (isDivisible(arrayB, i) && isNotDivisible(arrayA, i)) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}

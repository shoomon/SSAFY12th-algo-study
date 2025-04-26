package PRO_4월_3주차;

import java.util.*;

class 연습_연속부분수열합의개수_hyunjin {
    public int solution(int[] elements) {
        int answer = 0;
        int n = elements.length;
        int[] doubleArr = new int[n * 2];

        // 원형 수열 구현을 위해 배열 크기를 2배로 만들기
        // [7,9,1,1,4,7,9,1,1,4]
        for(int i= 0; i < n; i++){
            doubleArr[i] = elements[i];
            doubleArr[i + n] = elements[i];
        }

        // 중복 제거를 위해 Set 사용
        Set<Integer> sumSet = new HashSet<>();

        // 길이 1부터 n까지의 연속 부분 수열 합 계산
        for(int len=1; len<=n; len++){ // 부분 수열의 길이
            for(int i = 0; i<n; i++){ // 시작하는 인덱스
                int sum = 0;
                for(int j=i; j<i+len; j++){ // 시작 인덱스로부터 ~ 부분수열 길이까지의 합
                    sum += doubleArr[j];
                }
                sumSet.add(sum); // Set에 sum 넣기
            }
        }
        
        answer = sumSet.size(); // Set에 추가된 길이 별 부분 수열의 합 갯수 구하기

        return answer;
    }
}

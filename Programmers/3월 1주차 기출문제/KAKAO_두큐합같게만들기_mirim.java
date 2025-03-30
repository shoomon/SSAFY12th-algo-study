package 프로그래머스;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
    	// 공부부
    	
        // 1. 각 큐의 합 구한 후, 전체 합(total)과 목표(target)를 계산
        long sum1 = 0;
        long sum2 = 0;
        int n = queue1.length;
        
        for (int num : queue1) {
            sum1 += num;
        }
        
        for (int num : queue2) {
            sum2 += num;
        }
        
        // 합
        long total = sum1 + sum2;
        
        
        // 합이 홀수라면 불가능 -1 반환
        if (total % 2 != 0) {
            return -1;
        }
        
        // 타겟 합
        long target = total / 2;
        
        
        // 2. 두 큐를 하나의 배열로 합침
        long[] arr = new long[2 * n];
        
        // 앞쪽 n개의 원소는 queue1, 뒤쪽 n개의 원소는 queue2
        for (int i = 0; i < n; i++) {
            arr[i] = queue1[i];
            arr[i + n] = queue2[i];
        }
        
        
        // 3. 투 포인터 기법을 위한 두 인덱스를 초기화
        //    i는 현재 queue1의 "앞"을 가리키고, j는 queue2의 "앞" (arr에서 n번째 인덱스)
        
        int i = 0, j = n;
        int count = 0;
        
        // 최대 작업 횟수를 제한
        int limit = 3 * n;
        
        // 4. 반복을 통해 필요한 작업을 수행
        while (count <= limit) {
            // queue1 = 목표 합
            if (sum1 == target) {
                return count;
            }
            
            // queue1 > 목표 합
            // queue1의 앞 원소(즉, arr[i])를 pop해서 queue2로 이동
            if (sum1 > target) {
                sum1 -= arr[i];  // queue1에서 원소를 제거하므로 sum1에서 해당 값을 뺀다
                i++;        
                
            } else { 
                // queue1 < 목표 합
                // queue2의 앞 원소(즉, arr[j])를 pop해서 queue1으로 이동
                sum1 += arr[j];  // queue1으로 원소가 들어오므로 sum1에 해당 값을 더한다
                j++;       
            }
            
            // 카운트 증가
            count++;
        }
        
        return -1;
    }
}

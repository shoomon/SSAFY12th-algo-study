// import java.util.*;
// class Solution {
//     public int solution(int[] topping) {
        
//         //총 길이 1이하면 
//         if(topping.length <= 1) return 0;
//         int answer = 0;
        
//         Deque<Integer> dq = new ArrayDeque<>();
//         //형 카운팅
//         Map<Integer, Integer> old = new HashMap<>();
//         //동생 카운팅 
//         Map<Integer, Integer> young = new HashMap<>();
        
//         //덱에 넣고
//         for(int i : topping){
//             dq.addLast(i);
//         }
//         //카운팅 시작
//         //형, 동생 빵 길이
//         int a = 0;
//         int b = 0;
        
        
//         // while(a+b != topping.length){
//         while(!dq.isEmpty()){
//             //앞뒤하나씩 카운팅 시작
//             int cnt = dq.pollFirst();
//             old.put(cnt, old.getOrDefault(cnt, 0) + 1);
//             a++;
            
//             //중간에 다 나눴을 경우 대비
//             if(!dq.isEmpty()){
//                 int count = dq.pollLast();
//                 young.put(count, young.getOrDefault(count, 0) + 1);
//                 b++;    
//             }
//         }
//         if(old.size() == young.size()){
//             answer = 1;
//         }
//         return answer;
//     }
// }
import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> young = new HashMap<>();
        Map<Integer, Integer> old = new HashMap<>();
        
        // 전체 토핑 개수 카운트 (동생 기준)
        for (int t : topping) {
            young.put(t, young.getOrDefault(t, 0) + 1);
        }

        // 형이 하나씩 가져가면서 비교
        for (int i = 0; i < topping.length; i++) {
            int t = topping[i];

            // 형이 토핑 추가
            old.put(t, old.getOrDefault(t, 0) + 1);

            // 동생이 해당 토핑 하나 제거
            if (young.get(t) == 1) {
                young.remove(t);  // 마지막 토핑이면 삭제
            } else {
                young.put(t, young.get(t) - 1);
            }

            // 형과 동생이 가진 토핑 종류 개수 비교
            if (old.size() == young.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}

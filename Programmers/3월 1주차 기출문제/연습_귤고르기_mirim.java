package 프로그래머스;

//해시맵 : 이 방법이 더 알맞음

import java.util.*;

class Solution {
 public int solution(int k, int[] tangerine) {
	 
	 // 공부우
	 
     // 귤 크기의 개수를 저장하기 위한 HashMap 선언
     HashMap<Integer, Integer> map = new HashMap<>();
     
     // 배열을 순회하며 귤의 크기에 따른 빈도수를 계산
     for (int size : tangerine) {
         // getOrDefault를 사용하여 현재 크기의 개수를 가져오고, 없으면 0으로 시작
    	 map.put(size, map.getOrDefault(size, 0) + 1);
     }
     
     // ArrayList에 저장 (각 크기의 귤이 몇 개씩 있는지)
     ArrayList<Integer> num = new ArrayList<>(map.values());
     
     // 4. 귤을 많이 포함하는 크기부터 선택할 수 있도록 내림차순으로 정렬
     num.sort((a, b) -> b - a);
     
     // 5. 최소한의 크기 종류를 선택하기 위해 k개를 채울 때까지 빈도수를 누적
     int typeNum = 0; // 선택한 귤 크기 종류의 수
     for (int tan : num) {
         k -= tan;       // 현재 크기의 귤 개수를 k에서 빼줌
         typeNum++;     // 한 종류 선택했으므로 카운트 증가
         
         if (k <= 0) {    // k개 이상 선택한 경우 종료
             break;
         }
     }
     
     // 6. 선택한 귤 크기 종류의 최소 개수를 반환
     return typeNum;
 }
}


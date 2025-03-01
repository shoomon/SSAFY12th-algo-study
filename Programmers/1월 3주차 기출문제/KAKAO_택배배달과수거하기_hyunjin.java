package PRO_1월_3주차;

public class KAKAO_택배배달과수거하기_hyunjin {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		
		int deliver = 0; // 현재까지 배달해야할 택배 총량 
		int pickup = 0; // 현재까지 픽업해야할 수거 총량 
		
		// 가장 먼 위치부터 시작하여 탐색 
		for (int i = n - 1; i >= 0; i--) {
			deliver += deliveries[i]; 
			pickup += pickups[i]; 
			
			while (deliver > 0 || pickup > 0) {
				// 현재 위치에서 가능한 만큼 cap만큼 배달 / 수거
				deliver -= cap;
				pickup -= cap;
				answer += (i + 1) * 2;
			}
		}
		return answer;
	}

}

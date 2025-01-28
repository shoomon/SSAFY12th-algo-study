
public class KAKAO_택배배달과수거하기_mingyung {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
        
		// 내 껄론 답이 안나와서 찾아본 코드
		// 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 return 
        int d = 0;
        int p = 0;
        for(int i=n-1; i>=0; i--){
        	// 일단 각 값을 빼봐
            d -= deliveries[i];
            p -= pickups[i];
            
            while(d < 0 || p < 0){
            	// 그라구 cap을 더해. 그러면 0보다 커질 것. 그 때 idx가 최대 거리
                d += cap;
                p += cap;
                // 최대 거리 왕복값 더해주기
                answer += (i+1)*2;
            }
        }
        
        return answer;
		
		/* 실패코드
		long answer = 0;

        int del = 0;
        int pick = 0;
        int idx = n-1; // 뒤에서부터 돌자
        while (idx > 0) {
            // 일단 끝에 집 먼저 가야해
            answer += (idx+1) * 2;
            System.out.println(answer);
            // 돌아오면서 갯수 체크하자
            while (del <= cap && pick <= cap && idx >= 0) {
                del += deliveries[idx];
                pick += pickups[idx];
                idx--;
            }
            del = 0;
            pick = 0;
            if (idx > 0) idx++;
            System.out.println("idx: "+idx);
        }
        
        return answer;
        */
    }
}

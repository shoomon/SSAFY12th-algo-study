package 프로그래머스;

// 아자아자 안 화이팅!

public class KAKAO_택배배달과수거하기_mirim {
	
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long totalDistance = 0; // 총 이동 거리 저장 변수
        
        int deliveryLoad = 0; // 현재 배달 가능한 택배 개수
        int pickupLoad = 0; // 현재 수거 가능한 택배 개수
        
        for (int i = n - 1; i >= 0; i--) { // 가장 먼 집부터 탐색
            int totalTrips = 0; // 해당 위치에서의 필요한 이동 횟수 계산
            
            while (deliveryLoad < deliveries[i] || pickupLoad < pickups[i]) { // 현재 트럭 적재량이 부족할 경우 반복
                deliveryLoad += cap; // 최대한 적재 (cap만큼)
                pickupLoad += cap;
                totalTrips++; // 한 번 이동할 때마다 추가
            }
            
            deliveryLoad -= deliveries[i]; // 해당 위치에서 배달 수행
            pickupLoad -= pickups[i]; // 해당 위치에서 수거 수행
            
            totalDistance += (i + 1) * 2 * totalTrips; // 왕복 거리 계산하여 추가
        }
        
        return totalDistance; // 최소 이동 거리 반환
    }
}

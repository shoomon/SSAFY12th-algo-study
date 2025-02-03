package codingTest;

public class KAKAO_택배배달과수거하기_wooseok {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        for (int i = deliveries.length - 1; i >= 0;) {
            // 배달 개수와 수거 개수가 존재하지 않을때 다음 index 탐색
            if (deliveries[i] == 0 && pickups[i] == 0) {
                i--;
                continue;
            }

            process(cap, deliveries, i);
            process(cap, pickups, i);

            // 배달과 수거가 같이 이루어 지므로 (이동 거리 * 2)가 총 이동 거리
            answer += (i + 1) * 2;
        }

        return answer;
    }

    private void process(int cap, int[] arr, int index) {
        // 배달 : 물류 창고에서 index의 집까지 가면서 총 배달 할 수 있는 개수
        // 수거 : index의 집에서 물류 창고까지 돌아오면서 총 수거 할 수 있는 개수
        while (index >= 0) {
            if (cap >= arr[index]) {
                cap -= arr[index];
                arr[index--] = 0;
            } else {
                arr[index] -= cap;
                break; // 더 이상 배달이나 수거가 불가능하면 멈춤
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11399_mingyung {
	// S4_11399_ATM
	// 결과 : 맞았습니다!!
	// 메모리 : 14,496 kb
	// 시간 : 108 ms
	
	// N명 사람이 ATM 앞에 서있음
	// 각 사람이 돈을 뽑는 데 걸리는 시간 주어짐
	// 기다리는 시간의 합이 최소가 되도록 하는 값 찾기
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] time = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		// 일단 정렬해야 제일 적게 기다릴 수 있음
		Arrays.sort(time);
		
		int[] totalT = new int[N]; // 각자 기다리는 시간 저장할 배열
		totalT[0] = time[0]; // 첫 번째 사람은 자기 시간만큼만 소요됨
		int ans = time[0]; // 총합 더해줄 변수
		for (int i=1; i<N; i++) {
			totalT[i] = totalT[i-1]+time[i];
			ans += totalT[i];
		}
		
		// 출력하기
		System.out.println(ans);
	}
}
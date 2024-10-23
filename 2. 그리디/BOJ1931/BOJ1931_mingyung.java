import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1931_mingyung {
	// S1_1931_회의실 배정
	// 결과 : 맞았습니다!!
	// 메모리 : 45,428 kb
	// 시간 : 512 ms
	
	// 한 개의 회의실 N개의 회의
	// 각 회의 시작, 끝 시간 주어짐. 겹치지 않게 회의실 사용
	// 사용할 수 있는 최대 회의 개수
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫 줄 N
		int N = Integer.parseInt(br.readLine());
		// 그 다음 줄부터 각 회의 시작시간, 끝시간
		int[][] meetings = new int[N][3];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meetings[i][0] = Integer.parseInt(st.nextToken());
			meetings[i][1] = Integer.parseInt(st.nextToken());
			meetings[i][2] = meetings[i][1]-meetings[i][0];			
		} // 입력 완료
		
		// 계산 위해 정렬 _ 람다식 활용하여 이차원배열 정렬
		// 회의 시작 시간, 걸리는 시간 기준 오름차순
		Arrays.sort(meetings, (o1, o2) -> {
			return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[2]-o2[2];
		});
		
		// 가능한 회의 Stack에 넣기.
		// 뒤 회의의 시간이 더 짧고 좋다면 빼고 다시 넣기
		Stack<int[]> stack = new Stack<int[]>();
		stack.add(meetings[0]);
		for (int i=1; i<N; i++) {
			int[] tmp = stack.pop();
			if (meetings[i][2]<tmp[2] && meetings[i][1]<tmp[1]) {
				stack.add(meetings[i]);
			} else if (tmp[1]<=meetings[i][0]) {
				stack.add(tmp);
				stack.add(meetings[i]);
			} else {
				stack.add(tmp);
			}
		}
		
		System.out.println(stack.size());
	} // main
}
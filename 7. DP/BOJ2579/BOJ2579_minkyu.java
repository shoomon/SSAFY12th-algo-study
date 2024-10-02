import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
계단 오르기

계단별로 점수가 있다고 가정할 때
1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
2. 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
3. 마지막 도착 계단은 반드시 밟아야 한다.
게임에서 얻을 수 있는 총 점수의 최댓값 구하기.

메모리 : 14064 KB
시간 : 100 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int stairCnt = Integer.parseInt(br.readLine());
		int[] stair = new int[stairCnt + 1];
		for (int i = 1; i <= stairCnt; i++)
			stair[i] = Integer.parseInt(br.readLine());
		
		// 0번째에는 최대 점수 값, 1번째에는 연속된 칸의 숫자
		int[] dp = new int[stairCnt + 1];
		dp[1] = stair[1];
		if (stairCnt >= 2)
			dp[2] = stair[1] + stair[2];
		if (stairCnt >= 3)
			dp[3] = Math.max(stair[1], stair[2]) + stair[3];
		for (int i = 4; i <= stairCnt; i++)
			dp[i] = Math.max(dp[i - 3] + stair[i - 1], dp[i - 2]) + stair[i];
		
		System.out.println(dp[stairCnt]);
	}
}
import java.util.*;

public class BOJ1929_soomin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int M, N;
		int[] nums;
		
		M = sc.nextInt();
		N = sc.nextInt();
		nums = new int[N+1];
		
		nums[0] = 1;
		nums[1] = 1;
		
		for(int i = 2; i <= Math.sqrt(N); i++) {
			int j = 2;
			while(i*j < N+1) {
				if(nums[i*j] == 0) {
					nums[i*j] = 1;
				}
				j++;
			}
		}
		
		for(int i = M; i < N+1; i++) {
			if(nums[i] == 0) {
				System.out.println(i);
			}
		}
	}
}

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1037_bmk {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] nums = new long[n];
		for (int i = 0; i < n; i++)
			nums[i] = sc.nextInt();
		
		Arrays.sort(nums);
		System.out.println(nums[0] * nums[n - 1]);
	}
}
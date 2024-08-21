package BOJ2309;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2309_dohee {
	static int[] arr, res;
	static int n, r;
	static boolean found;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = 9; r = 7;
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		res = new int[r];
		find_small(0,0);
	}
	
	private static void find_small(int cnt, int start) {
		if (found) return;
		
		if (cnt == r) {
			int small_sum = 0;
			for (int r : res) {
				small_sum += r;
			}
			if (small_sum == 100) {
				Arrays.sort(res);
				for (int r : res) {
				System.out.println(r);				
				}
				found = true;
			}
			return ;
		}
		
		for (int i = start; i < n; i++) {
			res[cnt] = arr[i];
			find_small(cnt+1, i+1);
		}
		
	}
}

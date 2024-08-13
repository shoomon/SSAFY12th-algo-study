package im_ps;

import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_2068 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		sc.nextLine();
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int max = Integer.MIN_VALUE;
			while (st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				if (max <= num) {
					max = num;
				}				
			}
			sb.append("#"+test_case).append(" "+max).append("\n");
		}
		System.out.println(sb.toString());
	}
}

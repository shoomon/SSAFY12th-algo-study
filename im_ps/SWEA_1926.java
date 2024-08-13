package im_ps;

import java.util.Scanner;

public class SWEA_1926 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i<N+1; i++) {
			char[] c_str = (i+"").toCharArray();
			int num=0;
			for (char c : c_str) {
				if (c == '3'|| c=='6' || c=='9') {
					num++;
				}
			}
			if(num==0) {
				sb.append(i+" ");
			} else {
				for (int j = 0; j<num; j++) {
				sb.append("-");
				}
				sb.append(" ");
			}
		}
		System.out.println(sb.toString());
	}
}

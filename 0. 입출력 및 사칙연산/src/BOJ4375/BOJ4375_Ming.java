

import java.util.Scanner;

public class BOJ4375_Ming {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
//			long x = 1L;
//			long ten = 1L;
//			int length = 1;
//			
			int n = sc.nextInt();
//			for (int i=0; !(x % n==0); i++) {
//				ten *= 10;
//				x += ten;
//				length++;				
//			}
//			System.out.println(length);
			
			int num = 0;
			for (int i=1; ; i++) {
				num = (num*10+1)%n;
				if (num == 0) {
					System.out.println(i);
					break;
				}
			}
		}
		sc.close();
	}
}

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int largerNum = 0;
		if (A < B)
			largerNum = B;
		else
			largerNum = A;
		
		int leastOne = 1;
		int largeOne = 1;
		
		for (int i = 0; i < largerNum; i++) {
			int curCntA = 0;
			while (A % (2 + i) == 0) {
				curCntA++;
				A /= (2 + i);
			}
			int curCntB = 0;
			while (B % (2 + i) == 0) {
				curCntB++;
				B /= (2 + i);
			}
			if (curCntA > curCntB) {
				leastOne *= Math.pow(2 + i, curCntB);
				largeOne *= Math.pow(2 + i, curCntA);
			}
			else {
				leastOne *= Math.pow(2 + i, curCntA);
				largeOne *= Math.pow(2 + i, curCntB);
			}
		}
		System.out.println(leastOne);
		System.out.println(largeOne);
	}
}

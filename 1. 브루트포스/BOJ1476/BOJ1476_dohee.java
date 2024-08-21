package BOJ1476;

import java.util.Scanner;

public class BOJ1476_dohee {
	static int cnt, res[], E, S, M, map[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		E = sc.nextInt();
		S = sc.nextInt();
		M = sc.nextInt();
		
		moveYears();
		System.out.println(cnt);
	}

	private static void moveYears() {
		resetMap();
		cnt = 0;
		while (true) {
			cnt++;
			if (map[0]==E && map[1]==S && map[2]==M) {
				break;
			}
			moveYear(0, 15);
			moveYear(1, 28);
			moveYear(2, 19);
//			System.out.println(Arrays.toString(map));
		}
		
	}
	
	private static void resetMap() {
		map = new int[3];
		map[0] = 1;
		map[1] = 1;
		map[2] = 1;
	}

	private static void moveYear(int i, int mod) {
		map[i] = map[i]+1;
		if (map[i] == mod+1) {
			map[i] = map[i] % mod;
		}
	}
	
	
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ2309_hyunjin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> list = new ArrayList<>();

		int sumAge = 0;
		for (int i = 0; i < 9; i++) {
			list.add(sc.nextInt());
			sumAge += list.get(i);
		}

		// 두 명의 나이를 더한 값이 전체 나이 - 100 만큼이 되는 두 int 제거하기
		int diff = sumAge - 100;

		int rm1 = 0;
		int rm2 = 0;
		out: for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				if ((list.get(i) + list.get(j)) == diff) {
					rm1 = i;
					rm2 = j;
					break out;
				}
				continue;
			}
		}

		list.remove(rm1);
		// 위에서 이미 한 번 지움으로써 index값이 전체적으로 -1 씩 줄었음.
		list.remove(rm2 - 1);

		Collections.sort(list);
		
		for(int a : list) {	
			System.out.println(a);			
		}

	}
}

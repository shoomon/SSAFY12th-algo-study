import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/* 난쟁이 문제
왕비를 피해 일곱 난쟁이들과 함께 평화롭게 생활하고 있던 백설공주에게 위기가 찾아왔다. 일과를 마치고 돌아온 난쟁이가 일곱 명이 아닌 아홉 명이었던 것이다.

아홉 명의 난쟁이는 모두 자신이 "백설 공주와 일곱 난쟁이"의 주인공이라고 주장했다. 뛰어난 수학적 직관력을 가지고 있던 백설공주는, 다행스럽게도 일곱 난쟁이의 키의 합이 100이 됨을 기억해 냈다.

아홉 난쟁이의 키가 주어졌을 때, 백설공주를 도와 일곱 난쟁이를 찾는 프로그램을 작성하시오.
*/
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] midgets = new int[9];
		for (int tc = 0; tc < 9; tc++)
			midgets[tc] = Integer.parseInt(br.readLine());
		
		int notMidgetIdx = 0;
		int notMidgetIdx1 = 0;
		main : for (int i = 0; i < (9-1); i++) {
			for (int j = i + 1; j < 9; j++) {
				int sum = 0;
				for (int k = 0; k < 9; k++) {
					if (k != i && k != j)
						sum += midgets[k];
				}
				if (sum == 100) {
					notMidgetIdx = i;
					notMidgetIdx1 = j;
					break main;
				}
			}
		}
		int[] trueMidgets = new int[7];
		int curIdx = 0;
		for (int i = 0; i < 9; i++) {
			if (i != notMidgetIdx && i!= notMidgetIdx1) {
				trueMidgets[curIdx] = midgets[i];
				curIdx++;
			}
		}
		
		Arrays.sort(trueMidgets);
		for (int i : trueMidgets)
			System.out.println(i);
	}
}
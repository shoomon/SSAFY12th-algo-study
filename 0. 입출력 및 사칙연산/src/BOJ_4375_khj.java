package BOJ_0_입출력및사칙연산;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_4375_khj {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//		Scanner sc = new Scanner(System.in);
//		while (sc.hasNext()) { // EOF(End Of File)인 경우 false를 반환하여 반복문이 종료된다.
		String s;

		while ((s = br.readLine()) != null) {

//			int n = sc.nextInt();
			int n = Integer.parseInt(s);

			long multipleNum = 1;
			int i = 1;
			
			

			while (multipleNum % n != 0) {
//				multipleNum += Math.pow(10, i); // 10의 거듭제곱 (= 10^i) => 시간 초과

				// 모듈러 함수 사용
				multipleNum = (multipleNum * 10 + 1) % n;
				i++;
//				System.out.println(multipleNum + ", " + i);
			}

//		System.out.println(i);
			bw.write(String.valueOf(i));
			bw.newLine();
			bw.flush();
		}

		br.close();
//		}
//		sc.close();
	}
}

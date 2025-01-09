package PRO_12월_5주차;

import java.util.*;

public class 연습문제_두원사이의정수쌍_hyunjin {

	class Solution {
		public long solution(int r1, int r2) {
			long answer = 0;

			for (int x = 1; x <= r2; x++) {
				// x=1 -> y^2 = r^2-x^2

				long minY = (long) Math.ceil(Math.sqrt(1.0 * r1 * r1 - 1.0 * x * x));
				long maxY = (long) Math.floor(Math.sqrt(1.0 * r2 * r2 - 1.0 * x * x));

				// System.out.println("minY : "+ minY);
				// System.out.println("maxY : "+ maxY);

				answer += (maxY - minY + 1);
			}

			return answer * 4;
		}
	}
}

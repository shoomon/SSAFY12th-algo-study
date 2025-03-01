package PRO_1월_4주차;

import java.util.*;

public class 연습_시소짝꿍_hyunjin {
	public long solution(int[] weights) {

		long answer = 0;

		Arrays.sort(weights);
		Map<Double, Integer> map = new HashMap<>();

		for (int w : weights) {
			double a = w * 1.0;
			double b = (w * 2.0) / 3.0;
			double c = (w * 3.0) / 4.0;
			double d = (w * 2.0) / 4.0;

			if (map.containsKey(a))
				answer += map.get(a);
			if (map.containsKey(b))
				answer += map.get(b);
			if (map.containsKey(c))
				answer += map.get(c);
			if (map.containsKey(d))
				answer += map.get(d);

			map.put((w * 1.0), map.getOrDefault((w * 1.0), 0) + 1);
		}
		return answer;
	}
}

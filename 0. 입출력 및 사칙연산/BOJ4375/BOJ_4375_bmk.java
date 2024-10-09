import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_4375_bmk {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> nList = new ArrayList<>();
		while(sc.hasNext())
			nList.add(Integer.parseInt(sc.nextLine()));
		
		String result = "";
		long cnt = 0;
		long oneNumber = 0;
		for (int i = 0; i < nList.size(); i++) {
			oneNumber = 1;
			cnt = 1;
			while(true) {
				if (oneNumber % nList.get(i) == 0) {
					result += cnt + "\n";
					break;
				}else {
					oneNumber = oneNumber * 10 + 1;
					oneNumber %= nList.get(i);
					cnt++;
				}
			}
		}
		System.out.println(result);
	}
}

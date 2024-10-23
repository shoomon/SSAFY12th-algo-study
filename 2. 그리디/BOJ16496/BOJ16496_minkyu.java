import java.util.;
import java.io.;

/*
큰 수 만들기

숫자를 서로 이어붙여서 최대 수가 되도록 작성하시오.

메모리 : 81776 KB
시간 : 216 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		ListString arr = new ArrayList();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i  N; i++) {
			String curString = st.nextToken();
			arr.add(curString);
		}

		StringBuilder sb = new StringBuilder();
		while (!arr.isEmpty()) {
			Collections.sort(arr, new ComparatorString() {
				@Override
				public int compare(String o1, String o2) {
					StringBuilder sb = new StringBuilder();
					String a = sb.append(o1).append(o2).toString();
					sb = new StringBuilder();
					String b = sb.append(o2).append(o1).toString();
					return b.compareTo(a);
				}
			});
			String curStr = arr.get(0);
			arr.remove(0);
			sb.append(curStr);
		}
		String curString = sb.toString();
		if (curString.charAt(0) == '0')
			System.out.println(0);
		else
			System.out.println(curString);
	}
}
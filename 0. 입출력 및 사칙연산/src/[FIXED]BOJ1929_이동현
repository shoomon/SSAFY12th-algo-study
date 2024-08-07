import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main4 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer> li = new ArrayList<>();
		for(int i = n; i <= m; i++) {
			if((i % 2 != 0 && i % 3 != 0 && i % 5 != 0) || i ==2 || i == 5 
					|| i == 3) {
				li.add(i);
			}
		}
		for(int i = 0; i <li.size(); i++) {
			System.out.println(li.get(i));
		}
		
}
}

ECLIPSE상에서 잘 되는데 백준에서 출력초과 오류가 발생한다. 

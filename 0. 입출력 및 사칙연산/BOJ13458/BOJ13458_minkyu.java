import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
시험 감독

각각의 시험장에 총감독관 1명씩 존재하고 부감독관 여러명 존재가능,
최소한 필요한 감독관의 수를 구하는 프로그램 작성


메모리 : 92224 KB
시간 : 416 ms
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int placeCnt = Integer.parseInt(br.readLine());
		int[] places = new int[placeCnt];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < placeCnt; i++)
			places[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int totalViewer = Integer.parseInt(st.nextToken());
		int viceViewer = Integer.parseInt(st.nextToken());

		long viewerCnt = 0;
		for (int i = 0; i < placeCnt; i++) {
			places[i] -= totalViewer;
			viewerCnt++;

			if (places[i] > 0) {
				viewerCnt += places[i] / viceViewer;
				if (places[i] % viceViewer > 0)
					viewerCnt++;
			}
		}

		System.out.println(viewerCnt);
	}
}
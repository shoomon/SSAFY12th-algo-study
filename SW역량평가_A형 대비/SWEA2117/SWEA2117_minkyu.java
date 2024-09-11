import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int citySize = Integer.parseInt(st.nextToken());
			int costPerHouse = Integer.parseInt(st.nextToken());
			List<int[]> houseInfo = new ArrayList<>();
			for (int i = 0; i < citySize; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < citySize; j++) {
					int curInfo = Integer.parseInt(st.nextToken());
					if (curInfo == 1)
						houseInfo.add(new int[] {i,j});
				}
			}
			
			int maxHouse = 0;
			for (int i = 0; i < citySize; i++) {
				for (int j = 0; j < citySize; j++) {
					int[] cnts = new int[citySize + citySize + 1];
					for (int k = 0; k < houseInfo.size(); k++) {
						int[] tmp = houseInfo.get(k);
						cnts[getDist(i,j,tmp[0],tmp[1])]++;
					}
					
					int curCnt = 0;
					int useCnt = 0;
					for (int k = 1; k < cnts.length; k++) {
						curCnt += cnts[k];
						if (curCnt * costPerHouse >= getCost(k))
							useCnt = curCnt;
					}
					
					if (maxHouse < useCnt)
						maxHouse = useCnt;
				}
			}
			
			System.out.printf("#%d %d\n",tc,maxHouse);
		}
	}
	
	public static int getCost(int size) {
		return size * size + (size - 1) * (size - 1);
	}
	
	public static int getDist(int r, int c, int r1, int c1) {
		return Math.abs(r1 - r) + Math.abs(c1 - c) + 1;
	}
}
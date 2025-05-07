package PRO_5월_1주차;

class 월간코드챌린지시즌3_n2배열자르기_hyunjin {
	public int[] solution(int n, long left, long right) {
		int len = (int) (right - left + 1);
		int[] answer = new int[len];

		for (int i = 0; i < len; i++) {
			long idx = i + left;
			long div = idx / n;
			long mod = idx % n;
			answer[i] = (int) (Math.max(div, mod) + 1);
		}

		return answer;
	}
}

// 0%4+1   1    2      3   4/4+1  5%4+1 6%4+1 7%4+1 8/4+1 9/4+1 10/4+1 11/4+1  12/4+1   13/4+1  13%4+1
// (0,0) (0,1) (0,2) (0,3) (1,0) (1,1) (1,2) (1,3) (2,0) (2,1) (2,2) (2,3)   (3,0)   (3,1)
//  1     2     3      4     2     2     3     4      3    3     3     4        4       4
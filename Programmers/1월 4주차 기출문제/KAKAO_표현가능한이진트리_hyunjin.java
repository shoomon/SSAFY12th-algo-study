package PRO_1월_4주차;

public class KAKAO_표현가능한이진트리_hyunjin {
	public int[] solution(long[] numbers) {

		int n = numbers.length;
		int[] answer = new int[n];

		for (int i = 0; i < n; i++) {
			String binary = Long.toBinaryString(numbers[i]); // 42(10) -> 101010(2)
			// System.out.println(binary);

			// 포화 이진트리로 만들기 위해 적절한 길이의 트리 노드 개수를 찾기
			int treeSize = getFullBinaryTreeSize(binary.length()); // 7
			// System.out.println(treeSize);

			// 앞에 더미 노드 추가 (포화 이진트리로 맞추기 위함)
			// "%" : format 시작
			// treeSize : 출력할 길이
			// "s" : 문자열(string)으로
			// 7자리 사이즈로 101010을 format 하면 첫 번째 공간 = 공백 -> 공백은 0으로 replace
			binary = String.format("%" + treeSize + "s", binary).replace(' ', '0'); // 0101010

			// System.out.println(binary);

			// 재귀적으로 유효한 이진트리인지 확인
			if (isValidTree(binary, 0, binary.length() - 1)) {
				answer[i] = 1;
			} else {
				answer[i] = 0;
			}
		}
		return answer;
	}

	// 포화 이진트리 크기 구하기 (2^k - 1)
	private int getFullBinaryTreeSize(int length) {
		int n = 1;
		while ((1 << n) - 1 < length) {
			n++;
		}
		return (1 << n) - 1;
	}

	// 재귀적으로 이진트리가 유효한지 확인
	private boolean isValidTree(String binary, int start, int end) {
		if (start > end)
			return true;

		int mid = (start + end) / 2;

		// 부모가 '0'일 때 자식 노드에 '1'이 있는지 확인
		if (binary.charAt(mid) == '0') {
			for (int i = start; i <= end; i++) {
				if (binary.charAt(i) == '1') {
					return false; // 유효하지 않은 트리
				}
			}
		}

		// 좌측 및 우측 서브트리 검사
		return isValidTree(binary, start, mid - 1) && isValidTree(binary, mid + 1, end);
	}
}

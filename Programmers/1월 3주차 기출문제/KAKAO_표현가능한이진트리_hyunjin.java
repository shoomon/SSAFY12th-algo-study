package PRO_1월_3주차;

import java.util.*;

public class KAKAO_표현가능한이진트리_hyunjin {
	public int[] solution(long[] numbers) {
		int n = numbers.length;
		int[] answer = new int[n];

		for (int i = 0; i < n; i++) {
			String binary = Long.toBinaryString(numbers[i]); // 10진수 -> 2진수 변환 

			// 포화 이진트리 크기 계산 및 노드 채우기
			int treeSize = getFullBinaryTreeSize(binary.length());
			
			StringBuilder sb = new StringBuilder();

			// 앞쪽에 더미 노드('0')를 추가하여 포화 이진트리로 맞춤
			for (int j = 0; j < treeSize - binary.length(); j++) {
				sb.append('0');
			}
			sb.append(binary); // 기존 이진수 추가

			// 유효한 이진트리인지 확인
			if (isValidTree(sb.toString())) {
				answer[i] = 1; // 유효하면 1
			} else {
				answer[i] = 0; // 유효하지 않으면 0
			}
		}
		return answer;
	}

	// 포화 이진트리 크기 계산 함수 (2^k - 1)
	private int getFullBinaryTreeSize(int length) {
		int n = 1;
		
		// 포화이진트리 크기 : 2^n - 1가 length보다 작을 때 반복 
		while ((1 << n) - 1 < length) { 
			n++;
		}
		return (1 << n) - 1; 
	}

	// 이진트리가 유효한지 확인하는 함수
	private boolean isValidTree(String binary) {
		return checkTree(binary, 0, binary.length() - 1); // 재귀적으로 확인
	}

	// 재귀적으로 유효성 검사
	private boolean checkTree(String binary, int start, int end) {
		if (start > end)
			return true;

		int mid = (start + end) / 2; 
		
		// 부모 노드가 '0'인데 자식 노드에 '1'이 있는 경우 유효하지 않음
		if (binary.charAt(mid) == '0') {
			for (int i = start; i <= end; i++) {
				if (binary.charAt(i) == '1') {
					return false;
				}
			}
		}

		// 좌측 및 우측 서브트리 각각 확인
		return checkTree(binary, start, mid - 1) && checkTree(binary, mid + 1, end);
	}

}

package 프로그래머스;

// 공부만이 살길이다~

import java.util.*;

class KAKAO_표현가능한이진트리_mirim {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length]; // 결과를 저장할 배열
        
        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]); // 숫자를 이진수 문자열로 변환 (ex: 7 -> "111")
            int treeSize = getFullBinaryTreeSize(binary.length()); // 포화 이진트리 크기 계산
            
            // 포화 이진트리 구조로 맞추기 위해 왼쪽에 '0'을 추가하여 패딩 (빈 노드 추가)
            String fullBinary = String.format("%" + treeSize + "s", binary).replace(' ', '0'); 
            
            // 포화 이진트리 형태에서 해당 이진트리를 유효한 트리로 표현할 수 있는지 확인
            if (canBeBinaryTree(fullBinary)) { // 유효하면 1 저장
                answer[i] = 1;
            } else { // 유효하지 않으면 0 저장
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
    // 포화 이진트리의 최소 크기 계산 (2^h - 1 형태 유지)
    private int getFullBinaryTreeSize(int length) {
        int size = 1; // 초기 크기 (루트 하나만 있는 트리)
        while (size - 1 < length) { // 현재 트리의 크기가 주어진 이진수 길이보다 작은 경우 확장
            size *= 2; // 포화 이진트리의 다음 크기로 증가 (2, 4, 8, 16...)
        }
        return size - 1; // 노드 개수 반환 (2^h - 1 형태)
    }
    
    // 주어진 포화 이진트리 표현이 유효한 이진트리인지 확인하는 함수
    private boolean canBeBinaryTree(String binary) {
        return checkTree(binary, 0, binary.length() - 1); // 전체 문자열을 확인
    }
    
    // 재귀적으로 트리의 유효성을 확인하는 함수
    private boolean checkTree(String binary, int left, int right) {
        if (left > right) return true; // 더 이상 확인할 노드가 없으면 유효한 트리
        
        int mid = (left + right) / 2; // 현재 서브트리의 루트 노드 위치 찾기
        
        // 루트 노드가 '0'인데 그 아래에 '1'이 있다면 트리로 표현할 수 없음
        if (binary.charAt(mid) == '0') { 
            for (int i = left; i <= right; i++) { 
                if (binary.charAt(i) == '1') return false; // 서브트리에 '1'이 있으면 잘못된 구조
            }
        }
        
        // 왼쪽 및 오른쪽 서브트리를 재귀적으로 확인 (DFS 방식)
        return checkTree(binary, left, mid - 1) && checkTree(binary, mid + 1, right);
    }
}

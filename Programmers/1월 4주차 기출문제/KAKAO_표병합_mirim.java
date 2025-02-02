package 프로그래머스;

import java.util.*;

// 이것도 공부했음미다아

class KAKAO_표병합_mirim {
    // 50x50 표를 표현하는 데이터 구조
    private String[][] table = new String[51][51]; // 각 셀의 값을 저장하는 2차원 배열
    private int[][] parent = new int[51][51]; // 병합 정보를 저장하는 배열 (각 셀의 부모를 가리킴)
    
    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>(); // "PRINT" 명령어 결과를 저장할 리스트
        
        // 초기화: 모든 셀을 자신을 부모로 설정 (자기 자신이 독립적인 셀임)
        for (int r = 1; r <= 50; r++) {
            for (int c = 1; c <= 50; c++) {
                parent[r][c] = r * 51 + c; // 고유한 부모값을 설정 (r * 51 + c)
            }
        }
        
        // 입력된 명령어 리스트를 하나씩 처리
        for (String command : commands) {
            String[] parts = command.split(" "); // 공백을 기준으로 명령어를 분리
            
            if (parts[0].equals("UPDATE")) { // "UPDATE" 명령어 처리
                if (parts.length == 4) { // "UPDATE r c value" 형태
                    int r = Integer.parseInt(parts[1]); // 행 위치
                    int c = Integer.parseInt(parts[2]); // 열 위치
                    String value = parts[3]; // 새로운 값
                    table[r][c] = value; // 해당 위치에 값 저장
                } else { // "UPDATE value1 value2" 형태
                    String value1 = parts[1]; // 기존 값
                    String value2 = parts[2]; // 변경할 값
                    for (int r = 1; r <= 50; r++) { // 모든 셀을 순회
                        for (int c = 1; c <= 50; c++) {
                            if (table[r][c] != null && table[r][c].equals(value1)) { // 값이 value1이면
                                table[r][c] = value2; // value2로 변경
                            }
                        }
                    }
                }
            } else if (parts[0].equals("MERGE")) { // "MERGE" 명령어 처리
                int r1 = Integer.parseInt(parts[1]); // 첫 번째 셀의 행 위치
                int c1 = Integer.parseInt(parts[2]); // 첫 번째 셀의 열 위치
                int r2 = Integer.parseInt(parts[3]); // 두 번째 셀의 행 위치
                int c2 = Integer.parseInt(parts[4]); // 두 번째 셀의 열 위치
                merge(r1, c1, r2, c2); // 병합 실행
            } else if (parts[0].equals("UNMERGE")) { // "UNMERGE" 명령어 처리
                int r = Integer.parseInt(parts[1]); // 해제할 셀의 행 위치
                int c = Integer.parseInt(parts[2]); // 해제할 셀의 열 위치
                unmerge(r, c); // 병합 해제 실행
            } else if (parts[0].equals("PRINT")) { // "PRINT" 명령어 처리
                int r = Integer.parseInt(parts[1]); // 출력할 셀의 행 위치
                int c = Integer.parseInt(parts[2]); // 출력할 셀의 열 위치
                result.add(table[r][c] != null ? table[r][c] : "EMPTY"); // 값이 없으면 "EMPTY" 추가
            }
        }
        
        return result.toArray(new String[0]); // 결과 리스트를 문자열 배열로 변환 후 반환
    }
    
    // 병합 함수 (단순한 로직으로, 실제 병합 정보 처리는 추후 추가 필요)
    private void merge(int r1, int c1, int r2, int c2) {
        int parent1 = parent[r1][c1]; // 첫 번째 셀의 부모 찾기
        int parent2 = parent[r2][c2]; // 두 번째 셀의 부모 찾기
        
        if (parent1 != parent2) { // 두 셀이 이미 병합되지 않은 경우에만 실행
            parent[r2][c2] = parent1; // 두 번째 셀의 부모를 첫 번째 셀로 변경하여 병합
        }
    }
    
    // 병합 해제 함수 (단순한 로직으로, 실제 상태 복구 로직 추가 필요)
    private void unmerge(int r, int c) {
        int originalParent = parent[r][c]; // 기존 부모 정보 저장
        parent[r][c] = r * 51 + c; // 병합을 해제하여 자기 자신을 부모로 설정
    }
}


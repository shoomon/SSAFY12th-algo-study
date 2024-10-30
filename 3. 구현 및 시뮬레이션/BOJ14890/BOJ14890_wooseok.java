package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890_wooseok {
    
    //지도의 크기 : N, 경사로의 길이 :L
    static int N, L;

    // map : 지도 데이터 저장(높이 정보)
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        //입력을 받기 위한 BufferedReader 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //지도에 대한 정보 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        //지도배열 생성
        map = new int[N][N];
        
        //지도 데이터를 입력받아 배열에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 지나갈 수 있는 길의 수
        int count = 0;
        
        //각 행과 열을 검사하여 경사로를 설치할 수 있는지 확인
        for (int i = 0; i < N; i++) {
            if (checkRow(i)) {
                count++;
            }
            if (checkCol(i)) {
                count++;
            }
        }

        System.out.println(count);        
        
    }


    // 주어진 행(row)이 지나갈 수 있는지 검사하는 함수
    static boolean checkRow(int row) {
        // 경사로가 설치되었는지 확인하기 위한 배열
        boolean[] slope = new boolean[N];
        // 행의 인접한 두 칸을 비교하면서 경사로 설치 가능 여부 확인
        for (int i = 0; i < N - 1; i++) {
            // 높이가 같으면 계속 진행
            if (map[row][i] == map[row][i + 1]) continue;
            // 높이 차이가 1을 초과하면 경사로 설치 불가
            if (Math.abs(map[row][i] - map[row][i + 1]) > 1) return false;

            // 현재 칸의 높이가 다음 칸보다 낮을 때 (오르막 경사로 설치)
            if (map[row][i] < map[row][i + 1]) {
                // 경사로의 길이만큼 뒤로 돌아가며 경사로 설치 가능 여부 확인
                for (int j = 0; j < L; j++) {
                    if (i - j < 0 || slope[i - j] || map[row][i] != map[row][i - j]) return false;
                    // 경사로가 설치된 칸을 기록
                    slope[i - j] = true;
                }
            }
            // 현재 칸의 높이가 다음 칸보다 높을 때 (내리막 경사로 설치)
            else {
                // 경사로의 길이만큼 앞으로 나가며 경사로 설치 가능 여부 확인
                for (int j = 1; j <= L; j++) {
                    if (i + j >= N || slope[i + j] || map[row][i + 1] != map[row][i + j]) return false;
                    // 경사로가 설치된 칸을 기록
                    slope[i + j] = true;
                }
            }
        }
        // 경사로 설치가 성공적으로 완료되면 true 반환
        return true;
    }

    //주어진 열(col)이 지나갈 수 있는지 검사하는 메서드
    static boolean checkCol(int col) {

        // 경사로가 설치되어있는지 확인하기 위한 배열
        boolean[] slope = new boolean[N];
        //열의 인접한 두 칸을 비교하면서 경사로 설치 가능 여부 확인
        for (int i = 0; i < N - 1; i++) {
            //높이가 같다면 그대로 이동 가능
            if (map[i][col] == map[i + 1][col]) continue;
            //놑이가 1이상 차이가 나면 경사로 설치 불가
            if (Math.abs(map[i][col] - map[i + 1][col]) > 1) return false;
            
            //현재 칸의 높이가 다음 칸보다 낮을 때 (오르막 경사로 설치)
            if (map[i][col] < map[i + 1][col]) {
                //경사로의 길이만큼 뒤로 돌아가서  경사로 설치 가능 여부 확인
                for (int j = 0; j < L; j++) {
                    if(i-1 <0 || slope[i-j]|| map[i][col] != map[i-j][col]) return false;
                    // 경사로가 설치된 칸을 기록
                    slope[i - j] = true;
                }
            }
        }
        
        //경사로 설치 완료됨
        return true;
    }
    
    
    
}

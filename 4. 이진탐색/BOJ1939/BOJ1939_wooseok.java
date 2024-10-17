import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1939_wooseok {
    static int result; // 결과를 저장할 변수
    static List<Bridge>[] graph; // 각 도시와 연결된 다리 정보를 저장하는 리스트
    static boolean[] visited; // 방문 여부를 체크하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int cities = Integer.parseInt(tokenizer.nextToken()); // 도시 개수
        int bridges = Integer.parseInt(tokenizer.nextToken()); // 다리 개수

        // 그래프 초기화
        graph = new ArrayList[cities + 1];
        for (int i = 1; i <= cities; i++) {
            graph[i] = new ArrayList<>();
        }

        int minWeight = 0; // 최소 중량
        int maxWeight = 0; // 최대 중량

        // 다리 정보 입력
        for (int i = 0; i < bridges; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()); // 시작 도시
            int end = Integer.parseInt(tokenizer.nextToken()); // 도착 도시
            int weightLimit = Integer.parseInt(tokenizer.nextToken()); // 다리 중량 제한

            graph[start].add(new Bridge(end, weightLimit)); // 양방향 그래프
            graph[end].add(new Bridge(start, weightLimit));
            maxWeight = Math.max(maxWeight, weightLimit); // 중량 제한 업데이트
        }

        // 출발 도시와 목표 도시 입력
        tokenizer = new StringTokenizer(reader.readLine());
        int source = Integer.parseInt(tokenizer.nextToken()); // 출발 도시
        int destination = Integer.parseInt(tokenizer.nextToken()); // 목표 도시

        // 이분 탐색으로 최대 중량 찾기
        while (minWeight <= maxWeight) {
            int midWeight = (minWeight + maxWeight) / 2; // 중간 중량
            result = -1; // 결과 초기화
            visited = new boolean[cities + 1]; // 방문 배열 초기화
            explore(source, destination, midWeight); // DFS로 탐색

            if (result != -1) { // 목표 도시에 도달 가능하면 중량 증가
                minWeight = midWeight + 1;
            } else { // 도달 불가능하면 중량 감소
                maxWeight = midWeight - 1;
            }
        }

        // 최대 중량 출력
        System.out.println(maxWeight);
    }

    // DFS 탐색
    static void explore(int current, int target, int weightLimit) {
        if (current == target) {
            result = current; // 목표 도달
            return;
        }

        visited[current] = true; // 현재 도시 방문 처리
        for (Bridge bridge : graph[current]) {
            // 아직 방문하지 않았고, 다리 중량이 조건을 충족하면 이동
            if (!visited[bridge.destination] && bridge.weight >= weightLimit) {
                explore(bridge.destination, target, weightLimit);
            }
        }
    }
}

// 다리 정보를 담는 클래스
class Bridge {
    int destination; // 목적지 도시
    int weight; // 다리 중량 제한

    public Bridge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

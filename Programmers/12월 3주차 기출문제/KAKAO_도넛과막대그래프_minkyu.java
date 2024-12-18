import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[][] edges) {
        // 인접 리스트 생성 - 나가는 것과 들어오는 것
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Map<Integer, List<Integer>> adjListIn = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            if (adjList.containsKey(edges[i][0]))
                adjList.get(edges[i][0]).add(edges[i][1]);
            else
                adjList.put(edges[i][0], new ArrayList<>(List.of(edges[i][1])));

            if (adjListIn.containsKey(edges[i][1]))
                adjListIn.get(edges[i][1]).add(edges[i][0]);
            else
                adjListIn.put(edges[i][1], new ArrayList<>(List.of(edges[i][0])));
        }

        // 새로 추가된 노드의 번호값
        int newNode = 0;
        List<Integer> newNodeTar = null;
        // 들어오는 것은 없고, 나가는 것이 2개 이상 존재하는 노드가 새로 추가된 노드 값이다.
        for (int i : adjList.keySet()) {
            if (adjList.get(i).size() >= 2 && !adjListIn.containsKey(i)) {
                newNode = i;
                newNodeTar = adjList.get(i);
                break;
            }
        }

        int donutCnt = 0;
        int barCnt = 0;
        int eightCnt = 0;
        // 새로 추가된 노드로부터 나가는 노드에 존재하는 그래프들의 종류 파악이 필요함.
        for (int i = 0; i < newNodeTar.size(); i++) {
            int startNode = newNodeTar.get(i);
            int beforeNode = startNode;
            while (true) {
                List<Integer> lines = adjList.get(beforeNode);
                if (lines == null) {    // 나가는 간선이 없는 경우 막대 그래프
                    barCnt++;
                    break;
                } else if (lines.size() > 1) {    // 2개 이상인 경우 8자 그래프
                    eightCnt++;
                    break;
                } else if (lines.get(0) == startNode) { // 다시 본인으로 돌아오면 도넛 그래프
                    donutCnt++;
                    break;
                }
                beforeNode = lines.get(0);
            }
        }

        int[] answer = {newNode, donutCnt, barCnt, eightCnt};
        return answer;
    }
}


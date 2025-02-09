package codingTest;

public class 연습_마법의엘리베이터_wooseok {
    public int solution(int storey) {
        int answer = 0;
        int size = 10;
        while(storey != 0) {
            if(storey % size != 0){
                if(storey % size < size / 2) {
                    answer += (storey % size) / (size/10);
                    storey -= (storey % size);
                } else if(storey % size == size / 2) {
                    int t = size*10;
                    if(storey % t <= t / 2) {
                        answer += (storey % size) / (size/10);
                        storey -= (storey % size);
                    } else {
                        answer += ((size - (storey % size)) / (size/10));
                        storey += (size - (storey % size));
                    }
                } else {
                    answer += ((size - (storey % size)) / (size/10));
                    storey += (size - (storey % size));
                }
            }
            size *= 10;
        }


        return answer;
    }
}

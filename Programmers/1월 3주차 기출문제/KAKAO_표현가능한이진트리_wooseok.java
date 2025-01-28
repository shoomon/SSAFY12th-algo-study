package codingTest;

import java.util.*;

public class KAKAO_표현가능한이진트리_wooseok {
    public List<Integer> solution(long[] numbers) {
        List<Integer> results = new ArrayList<>();

        for (long number : numbers) {
            String binary = Long.toBinaryString(number);
            int treeSize = getTreeSize(binary.length());
            char[] completeTree = createCompleteTree(binary, treeSize);

            if (isValidBinaryTree(0, treeSize - 1, completeTree)) {
                results.add(1);
            } else {
                results.add(0);
            }
        }

        return results;
    }

    private int getTreeSize(int length) {
        int size = 1;
        while (size - 1 < length) {
            size *= 2;
        }
        return size - 1;
    }

    private char[] createCompleteTree(String binary, int size) {
        char[] tree = new char[size];
        Arrays.fill(tree, '0');
        int offset = size - binary.length();

        for (int i = 0; i < binary.length(); i++) {
            tree[offset + i] = binary.charAt(i);
        }

        return tree;
    }

    private boolean isValidBinaryTree(int start, int end, char[] tree) {
        if (start > end) return true;

        int root = (start + end) / 2;
        if (tree[root] == '0') {
            for (int i = start; i < root; i++) {
                if (tree[i] == '1') return false;
            }
            for (int i = root + 1; i <= end; i++) {
                if (tree[i] == '1') return false;
            }
        }

        return isValidBinaryTree(start, root - 1, tree) && isValidBinaryTree(root + 1, end, tree);
    }
}

package 剑指offer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lzh
 * @date 2021/2/15 15:27
 */
public class _38_字符串的排列 {

    /**
     * 朴素回溯
     *
     * 44 ms 20.72%    42.8 MB  74.60%
     */
    public String[] permutation1(String s) {

        char[] arr = s.toCharArray();
        Set<String> result = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[arr.length];

        recursive(arr, result, sb, visited);

        return result.toArray(new String[0]);
    }

    private void recursive(char[] arr, Set<String> result, StringBuilder sb, boolean[] visited) {

        if(sb.length() == arr.length) {
            result.add(sb.toString());
            return;
        }

        for(int i = 0; i < arr.length; i++) {

            if(!visited[i]) {
                sb.append(arr[i]);
                visited[i] = true;
                recursive(arr, result, sb, visited);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }


    /**
     * 交换思想
     *
     * 8 ms  97.46%     42.4 MB  92.11%
     */
    public String[] permutation2(String s) {

        char[] arr = s.toCharArray();

        List<String> result = new ArrayList<>();

        recursive(arr, result, 0);

        return result.toArray(new String[0]);
    }

    private void recursive(char[] arr, List<String> result, int index) {

        if(index == arr.length - 1) {
            result.add(String.valueOf(arr));
            return;
        }

        Set<Character> set = new HashSet<>();

        for(int i = index; i < arr.length; i++) {

            if(set.contains(arr[i])) {
                continue;
            }

            set.add(arr[i]);

            swap(arr, i, index);

            recursive(arr, result, index + 1);

            swap(arr, i, index);

        }

    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

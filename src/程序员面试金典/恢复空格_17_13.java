package 程序员面试金典;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/7/9 16:35
 */
public class 恢复空格_17_13 {

    public int respace(String[] dictionary, String sentence) {

        //1. 将字典中的单词插入字典树（反向）
        Trie root = new Trie();
        for (String word : dictionary) {
            root.insert(word);
        }

        //2. 定义状态变量 dp，dp[i] 表示前 i 个字符中最少的未识别的字符数
        int senLen = sentence.length();
        int[] dp = new int[senLen + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        //3. 循环
        for (int i = 1; i < senLen + 1; i++) {

            // 先假设找不到匹配的词，则转态转移为 dp[i] = dp[i-1] + 1
            dp[i] = dp[i-1] + 1;

            // 寻找前 i 个是否有匹配的单词，从位置 i 往前寻找
            Trie curPos = root;
            for (int j = i; j >= 1; j--) {

                int idx = sentence.charAt(j-1) - 'a';
                if (curPos.next[idx] == null) {
                    break;
                } else if (curPos.next[idx].isEnd) {
                    dp[i] = Math.min(dp[j - 1], dp[i]);
                }

                curPos = curPos.next[idx];
            }
        }

        return dp[senLen];
    }


    /**
     * 字典树
     */
    private class Trie {

        /**
         * 存放下一节点的数组
         */
        private Trie[] next;

        /**
         * 当前节点是不是一个单词的结束节点（结束字符）
         */
        private boolean isEnd;

        Trie() {
            this.next = new Trie[26];
            this.isEnd = false;
        }

        /**
         * 将单词插入字典树
         * @param str 待插入的单词
         */
        void insert(String str) {

            Trie curPos = this;

            for (int i = str.length()-1; i >= 0; i--) {
                int idx = str.charAt(i) - 'a';
                if (curPos.next[idx] == null) {
                    curPos.next[idx] = new Trie();
                }
                curPos = curPos.next[idx];
            }
            curPos.isEnd = true;
        }
    }


    @Test
    public void test() {

        String[] dictionary = {"looked","just","like","her","brother"};
        String sentence = "jesslookedjustliketimherbrother";

        System.out.println(respace(dictionary, sentence));


    }

}

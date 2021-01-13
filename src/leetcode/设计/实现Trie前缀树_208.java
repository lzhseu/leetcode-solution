package leetcode.设计;

/**
 * @author lzh
 * @date 2021/1/3 19:32
 */
public class 实现Trie前缀树_208 {

    public static class Trie {

        private TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {

            if(word == null || word.length() == 0) {
                return;
            }

            TrieNode curr = root;

            for(char c : word.toCharArray()) {

                if(!curr.contains(c)) {
                    curr.putNext(c, new TrieNode());
                }

                curr = curr.getNext(c);
            }

            curr.setIsEnd(true);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {

            TrieNode curr = root;

            for(char c : word.toCharArray()) {
                if(!curr.contains(c)) {
                    return false;
                }
                curr = curr.getNext(c);
            }

            return curr.isEnd();
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {

            TrieNode curr = root;

            for(char c : prefix.toCharArray()) {
                if(!curr.contains(c)) {
                    return false;
                }

                curr = curr.getNext(c);
            }

            return true;
        }

        public static class TrieNode {

            private boolean isEnd;
            private TrieNode[] next;

            private static final int NEXT_SIZE = 26;

            private static final char FIRST_CHAR = 'a';

            public TrieNode() {
                isEnd = false;
                next = new TrieNode[NEXT_SIZE];
            }

            public boolean contains(char c) {
                return next[c - FIRST_CHAR] != null;
            }

            public TrieNode getNext(char c) {
                return next[c - FIRST_CHAR];
            }

            public void putNext(char c, TrieNode node) {
                next[c - FIRST_CHAR] = node;
            }

            public void setIsEnd(boolean isEnd) {
                this.isEnd = isEnd;
            }

            public boolean isEnd() {
                return this.isEnd;
            }
        }
    }
}

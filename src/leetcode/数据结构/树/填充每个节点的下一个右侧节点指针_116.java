package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/17 11:22
 */
public class 填充每个节点的下一个右侧节点指针_116 {

    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    void connectTwoNode(Node node1, Node node2) {

        if(node1 == null || node2 == null) {
            return;
        }

        node1.next = node2;

        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }
}

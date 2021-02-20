package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/16 14:51
 */
public class _33_二叉树的后序遍历序列 {


    public boolean verifyPostorder(int[] postorder) {

        if(postorder == null || postorder.length == 0) {
            return true;
        }

        return verifyPostorder2(postorder, 0, postorder.length - 1);
    }

    /**
     * 第一版编码
     */
    private boolean verifyPostorder1(int[] postorder, int begin, int end) {

        // base case
        if(begin == end) {
            return true;
        }

        boolean isValid = true;

        int rootVal = postorder[end];

        // 左子树应该比根节点的数值小，可以不存在左子树
        int leftBegin = begin, leftEnd = leftBegin - 1;
        for(int i = leftBegin; i < end; i++) {
            if(postorder[i] < rootVal) {
                leftEnd++;
            } else {
                break;
            }
        }

        // 如果存在左子树
        if(leftBegin <= leftEnd) {
            isValid = verifyPostorder1(postorder, leftBegin, leftEnd);
        }

        // 如果存在左子树且左子树无效，则直接返回 false
        if(!isValid) {
            return false;
        }

        // 若左子树是有效的，或者无左子树，此时 isValid 为 true，则继续看右子树

        // 如果存在右子树
        if(leftEnd < end - 1) {

            int rightBegin = leftBegin <= leftEnd ? leftEnd + 1 : begin;
            int rightEnd = rightBegin - 1;
            for(int i = rightBegin; i < end; i++) {
                if(postorder[i] > rootVal) {
                    rightEnd++;
                } else {
                    break;
                }
            }

            // 如果右子树的值没有都比跟的值大，则返回false；
            if(rightEnd < end - 1) {
                return false;
            }

            isValid = verifyPostorder1(postorder, rightBegin, rightEnd);
        }

        return isValid;
    }

    
    /**
     * 第二版编码：简洁了许多
     */
    private boolean verifyPostorder2(int[] postorder, int begin, int end) {

        // base case
        if(begin == end) {
            return true;
        }

        int rootVal = postorder[end];

        int i = begin;
        for(; i < end; i++) {
            if(postorder[i] > rootVal) {
                break;
            }
        }

        int j = i;
        for(; j < end; j++) {
            if(postorder[j] < rootVal) {
                return false;
            }
        }

        // 判断左子树是不是有效
        boolean left = true;
        if(i > begin) {
            // 存在左子树才进来
            left = verifyPostorder2(postorder, begin, i - 1);
        }

        // 判断右子树是不是有效
        boolean right = true;
        if(i < end - 1) {
            // 存在右子树才进来
            right = verifyPostorder2(postorder, i, end - 1);
        }

        return left && right;
    }
}

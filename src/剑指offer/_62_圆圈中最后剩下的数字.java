package 剑指offer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2021/2/20 11:40
 */
public class _62_圆圈中最后剩下的数字 {

    /*
     * 本题就是有名的「约瑟夫环问题」
     */


    /**
     * 做法一：辅助数组＋模拟。使用数组需要一直循环
     * 超出时间限制
     */
    public int lastRemaining1(int n, int m) {

        boolean[] assist = new boolean[n];
        Arrays.fill(assist, true);

        int p = 0;
        int count = 0;

        for(int i = 0; i < n - 1; i++) {

            while(true) {

                if(assist[p]) {
                    count++;
                }

                if(count == m) {
                    assist[p] = false;
                    count = 0;
                    break;
                }

                p++;

                if(p == n) {
                    p = 0;
                }
            }
        }

        for(int i = 0; i < assist.length; i++) {
            if(assist[i]) {
                return i;
            }
        }

        return -1;
    }


    /**
     * 做法二：链表＋模拟
     * 超出时间限制
     */
    public int lastRemaining2(int n, int m) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            linkedList.add(i);
        }

        Iterator<Integer> it = linkedList.iterator();
        it.next();

        while(linkedList.size() > 1) {

            for(int i = 1; i < m; i++) {
                if (!it.hasNext()) {
                    it = linkedList.iterator();
                }
                it.next();
            }

            it.remove();
            if (!it.hasNext()) {
                it = linkedList.iterator();

            }
            it.next();

        }

        return linkedList.peek();
    }


    /**
     * 做法三：数学规律，找到递推公式（难，自己想不出）
     *
     * 7 ms 99.91%    34.9 MB 98.53%
     */
    public int lastRemaining(int n, int m) {

        int pos = 0;
        for(int i = 2; i <= n; i++) {
            pos = (pos + m) % i;
        }

        return pos;
    }


    @Test
    public void test() {
        System.out.println(lastRemaining2(5, 7));
    }

    @Test
    public void test1() {

        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        Iterator<Integer> next = it;

        it.next();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());

        while (next.hasNext()) {
            System.out.println(next.next());
        }
    }
}

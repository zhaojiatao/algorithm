package list;


import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhaojiatao
 * @date 2018/6/24
 1. 数据查询的效率（Get的效率）
 ArrayList：使用数组，获取数据通过数组下标进行快速定位，性能为O(1)，数据顺序、随机获取的速度快。
 LinkedList：使用链表进行数据存储，查询数据需要从头或者从尾部遍历链表，性能O(n)，因此数组数据越多、查询位置越靠中间性能越差。
 2. 数据添加的效率（Add的效率）
 ArrayList：数组的数据添加，如果在头部或者中间性能最差，因为需要更改后面所有数据的位置和索引。如果在尾部性能则影响不大。
 LinkedList：插入的效率比ArrayList和Vector都高，因为只需要更改相应位置前后节点的指向就行。
 即，一般情况下数组的插入性能不如链表，但是，当ArrayList和LinkedList以add(int index, E element)方式，向中间插入10000条以上时，数组的性能则会大于链表的性能，
 3. 数据移除的效率（Remove的效率）
 数据移除同添加
 4. 数据替换的效率（Set的效率）
 ArrayList：数据替换由于不需要更改后续数组的位置和索引，因此效率比插入高。同时LinkedList需要定位位置，因此ArrayList也会比LinkedList快。
 LinkedList：需要定位替换的位置，效率较低。
 */
public class ArrayListAndLinkedListTest {


    /**
     * 1、测试在最前面插入的效率
     *
     结果:在ArrayList 最前面插入100000条数据，总共耗时 1026 毫秒
     在LinkedList 最前面插入100000条数据，总共耗时 14 毫秒
     原因:
     ArrayList的add(int index, E element)方法中，面临动态扩容的问题，此外还需要使用 System.arraycopy实现数组自我复制来实现
     LinkedList的add(int index, E element)方法，仅仅通过改变链表的指向来实现

     结论:在集合的前面插入元素，ArrayList不如LinkedList插入快
    */


    private static void insertFirst(List<Integer> l, String type) {
        int total = 1000 * 100;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(0, number);
        }
        long end = System.currentTimeMillis();
        System.out.printf("在%s 最前面插入%d条数据，总共耗时 %d 毫秒 %n", type, total, end - start);
    }

    @Test
    public void testInsertFirst(){
        List<Integer> l;
        l = new ArrayList<>();
        insertFirst(l, "ArrayList");

        l = new LinkedList<>();
        insertFirst(l, "LinkedList");
    }



    /**
     *2、定位数据
     * ArrayList总长度是100000，定位到第50000个数据，取出来，加1，再放回去
     重复100000遍，总共耗时 8 毫秒
     LinkedList总长度是100000，定位到第50000个数据，取出来，加1，再放回去
     重复100000遍，总共耗时 19017 毫秒
     原因：ArrayList直接是通过数据下标来完成定位和赋值；
     LinkedList是先判断index大于一半还是小于一半，然后从后往前或从前往后循环一遍。直到查到为止；其set(int index, E element)方法一样涉及到遍历一遍；
     LinkedList越靠近中间定位越慢

     结论:ArrayList比LinkedList定位快，LinkedList越靠近中间定位越慢
     */
    private static void modify(List<Integer> l, String type) {
        int total = 100 * 1000;
        int index = total/2;
        final int number = 5;
        //初始化
        for (int i = 0; i < total; i++) {
            l.add(number);
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            int n = l.get(index);
            n++;
            l.set(index, n);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%s总长度是%d，定位到第%d个数据，取出来，加1，再放回去%n 重复%d遍，总共耗时 %d 毫秒 %n", type,total, index,total, end - start);
        System.out.println();
    }


    @Test
    public void testModify(){
        List<Integer> l;
        l = new ArrayList<>();
        modify(l, "ArrayList");

        l = new LinkedList<>();
        modify(l, "LinkedList");
    }

    /**
     * 3、从后面插入十万条数据:
     * 在ArrayList 最后面插入100000条数据，总共耗时 6 毫秒
     在LinkedList 最后面插入100000条数据，总共耗时 9 毫秒
     原因:因为是插入在最后面，所以对于数组而言，并没有一个移动很多数据的需要，所以也非常的快；而链表本身就很快，无论插入在哪里

     结论:在后面插入，两者一样快
     */
    private static void insertLast(List<Integer> l, String type) {
        int total = 1000 * 100;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(number);
        }
        long end = System.currentTimeMillis();
        System.out.printf("在%s 最后面插入%d条数据，总共耗时 %d 毫秒 %n", type, total, end - start);
    }

    @Test
    public void testinsertLast(){
        List<Integer> l;
        l = new ArrayList<>();
        insertLast(l, "ArrayList");

        l = new LinkedList<>();
        insertLast(l, "LinkedList");
    }

    /**
     * 4、向中间插入
     * 在ArrayList 最中间插入100000条数据，总共耗时 282 毫秒
     在LinkedList 最中间插入100000条数据，总共耗时 5073 毫秒
     结论：在这个位置插入数据
     数组定位很快，插入数据比较慢
     链表定位很慢，插入数据比较快
     最后发现，当总数是10000条的时候，链表定位的总开支要比数组插入的总开支更多，所以最后整体表现，数组会更好。
     */
    private static void insertMiddle(List<Integer> l, String type) {
        int total = 1000 * 100;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            //把当前容器的一半的位置，插入数据
            l.add(l.size()/2,number);
        }
        long end = System.currentTimeMillis();
        System.out.printf("在%s 最中间插入%d条数据，总共耗时 %d 毫秒 %n", type, total, end - start);
    }
    @Test
    public void testInsertMiddle(){
        List<Integer> l;
        l = new ArrayList<>();
        insertMiddle(l, "ArrayList");

        l = new LinkedList<>();
        insertMiddle(l, "LinkedList");
    }


}

package datastructure;

/**
 * @Author sun
 * @Date 2020-06-03
 * Description:
 */
public class Zijie {
    /**
     * 输入一个链表，反转链表后，输出新链表的表头（链表反转）。
     *
     * @param head
     * @return
     */
    public Main.ListNode ReverseList(Main.ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        Main.ListNode pre = null;     //当前节点的前一个节点
        Main.ListNode next = null;   //当前节点的后一个节点
        while (head != null) {
            next = head.next;   //记录当前节点的下一个节点的位置
            head.next = pre;    //让当前节点指向前一个节点的位置，完成反转
            pre = head;         //pre向右走
            head = next;
        }

        return pre;
    }
}

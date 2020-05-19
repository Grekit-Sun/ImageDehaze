package datastructure;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Pattern;


public class Main {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    ViewGroup view;

    public static void main(String[] args) {


    }

    /**
     * 图片A和图片B的像素Byte分别存放在一维数组byte[] dest和byte[] src中，如何实现图片B拷贝到图片A中
     * @param W
     * @param H
     * @param m
     * @param n
     * @param x
     * @param y
     * @param dest
     * @param src
     */
    void copyBitmap(int W, int H, int m, int n, int x, int y, byte[] dest, byte[] src) {
    }

    /**
     * 打乱数组
     *
     * @param n
     * @return
     */
    public int[] random(int n) {
        int[] array = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        for (int a : array) {
            list.add(a);
        }
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 排序
     *
     * @param n
     */
    public int[] sort(int n) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[n];
        while (scanner.hasNext()) {
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 求和（递归）
     */
    public int getSum(int n) {
        if (n == 1) {
            return n;
        }
        return getSum(n - 1) + n;
    }

    public int getViewNum() {
        for (int i = 0; i < view.getChildCount(); i++) {
            viewNum(i);
            view = (ViewGroup) view.getChildAt(i);
        }
        return sum;
    }

    int sum = 0;

    private void viewNum(int pos) {
        sum += view.getChildCount();
    }

    /**
     * 回串文
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0 || len == 1)
            return s;
        int[][] dp = new int[len][len]; //定义二位数组存储值，dp值为1表示true，为0表示false
        int start = 0;  //回文串的开始位置
        int max = 1;   //回文串的最大长度
        for (int i = 0; i < len; i++) {  //初始化状态
            dp[i][i] = 1;
            if (i < len - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = 1;
                start = i;
                max = 2;
            }
        }

        for (int l = 3; l <= len; l++) {  //l表示检索的子串长度，等于3表示先检索长度为3的子串
            for (int i = 0; i + l - 1 < len; i++) {
                int j = l + i - 1;  //终止字符位置
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) {  //状态转移
                    dp[i][j] = 1;  //是一，不是字母l
                    start = i;
                    max = l;
                }
            }
        }
        return s.substring(start, start + max);   //获取最长回文子串

    }


    private void isHuan(int start, int end, String str, String out) {

        if (!(str.substring(start, start + 1)).equals(str.substring(end, end + 1))) {
            isHuan(start + 1, end, str, out);
        } else {
            isHuan(start + 1, end - 1, str, out);
        }
        //偶数

        //奇数
    }


    /**
     * 剪绳子
     * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。
     * 请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * 输入
     * 8
     * 输出
     * 18
     *
     * @param target
     * @return
     */
    public int cutRope(int target) {
        return cutRope(target, 0);
    }

    public int cutRope(int target, int max) {
        int maxValue = max;
        for (int i = 1; i < target; ++i) {
            maxValue = Math.max(maxValue, i * cutRope(target - i, target - i));
        }
        return maxValue;
    }


    /**
     * (序列化二叉树)
     * 请实现两个函数，分别用来序列化和反序列化二叉树
     * <p>
     * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
     * <p>
     * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
     * <p>
     * 例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
     *
     * @param root
     * @return
     */
    //String str = "";
    private int index = -1;

    String Serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        SerializeHelp(root, sb);
        return sb.toString();
    }

    TreeNode Deserialize(String str) {
        if (str == null || str == "") {
            return null;
        }
        String[] strs = str.split("!"); //利用结束符分割出每个节点的值便于处理。
        return DeserializeHelp(strs);
    }

    //主要实现前序遍历，然后加上#和！
    void SerializeHelp(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#!");
            return;
        }
        sb.append(root.val).append("!");
        SerializeHelp(root.left, sb);
        SerializeHelp(root.right, sb);
    }

    TreeNode DeserializeHelp(String[] strs) {
        index++;
        if (!strs[index].equals("#")) { //按顺序添加各节点。
            TreeNode node = new TreeNode(Integer.parseInt(strs[index]));
            node.left = DeserializeHelp(strs);
            node.right = DeserializeHelp(strs);
            return node;
        }
        return null;
    }

    /**
     * 数据流中的中位数
     * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值
     * ，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
     *
     * @param num
     */
    //PriorityQueue<Integer> left = new PriorityQueue<>((o1,o2)->o2-o1);
    //PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> left = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    PriorityQueue<Integer> right = new PriorityQueue<>();
    private int N;

    public void Insert(Integer num) {
        if (N % 2 == 0) {
            left.add(num);
            right.add(left.poll());
        } else {
            right.add(num);
            left.add(right.poll());
        }
        N++;
    }

    public Double GetMedian() {

        if (N % 2 == 0)
            return (left.peek() + right.peek()) / 2.0;
        else
            return (double) right.peek();
    }

    /**
     * 矩阵中的路径
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如
     * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int[] marks = new int[rows * cols];

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                Arrays.setAll(marks, i -> 0);
                if (isHasPath(matrix, marks, rows, cols, row, col, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isHasPath(char[] matrix, int[] marks, int rows, int cols, int row, int col, char[] str) {
        int currentIndex = 0;
        int count = 0;
        // LinkedList<Integer> visitedStack = new LinkedList<>();
        LinkedList<Integer> rowStack = new LinkedList<>();
        LinkedList<Integer> colStack = new LinkedList<>();
        rowStack.push(row);
        colStack.push(col);
        int[][] newIndexes = new int[][]{
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };
        while (!rowStack.isEmpty()) {
            count++;
            int topX = colStack.peek();
            int topY = rowStack.peek();
            int topIndex = topY * cols + topX;
            if (currentIndex == str.length) {
                return true;
            }
            if (marks[topIndex] == 0) {
                if (matrix[topIndex] == str[currentIndex]) {
                    for (int j = 0; j < newIndexes.length; ++j) {
                        int newX = topX + newIndexes[j][0];
                        int newY = topY + newIndexes[j][1];
                        if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
                            int newIndex = newY * cols + newX;
                            if (marks[newIndex] != 1) {//not visited.
                                colStack.push(newX);
                                rowStack.push(newY);
                            }
                        }
                    }
                    marks[topIndex] = 1;
                    //visitedStack.push(topIndex);
                    currentIndex++;
                } else {
                    colStack.pop();
                    rowStack.pop();
                }
            } else {
                //back to the visited
                marks[topIndex] = 0;
                colStack.pop();
                rowStack.pop();
//               if(visitedStack.size()>0)
//               visitedStack.pop();
                currentIndex--;
            }
        }
        String strlog = String.format("%d x %d: %d", cols, rows, count);
        System.out.println(strlog);
        return false;
    }


    /**
     * 正则表达式匹配
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     *
     * @param str
     * @param pattern
     * @return
     */
    public boolean match(char[] str, char[] pattern) {
        return matchStr(str, 0, pattern, 0);
    }

    public boolean matchStr(char[] str, int i, char[] pattern, int j) {

        // 边界
        if (i == str.length && j == pattern.length) { // 字符串和模式串都为空
            return true;
        } else if (j == pattern.length) { // 模式串为空
            return false;
        }

        boolean flag = false;
        boolean next = (j + 1 < pattern.length && pattern[j + 1] == '*'); // 模式串下一个字符是'*'
        if (next) {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) { // 要保证i<str.length，否则越界
                return matchStr(str, i, pattern, j + 2) || matchStr(str, i + 1, pattern, j);
            } else {
                return matchStr(str, i, pattern, j + 2);
            }
        } else {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) {
                return matchStr(str, i + 1, pattern, j + 1);
            } else {
                return false;
            }
        }
    }

    /**
     * ^ 和 美元符号框定正则表达式，它指引这个正则表达式对文本中的所有字符都进行匹配。如果省略这些标识，那么只要一个字符串中包含一个数字这个正则表达式就会进行匹配。如果仅包含 ^ ，它将匹配以一个数字开头的字符串。如果仅包含$ ，则匹配以一个数字结尾的字符串。
     * <p>
     * [-+]?
     * 正负号后面的 ? 后缀表示这个负号是可选的,表示有0到1个负号或者正号
     * <p>
     * \\d*
     * \d的含义和[0-9]一样。它匹配一个数字。后缀 * 指引它可匹配零个或者多个数字。
     * <p>
     * (?:\\.\\d*)?
     * (?: …)?表示一个可选的非捕获型分组。* 指引这个分组会匹配后面跟随的0个或者多个数字的小数点。
     * <p>
     * (?:[eE][+\\-]?\d+)?
     * 这是另外一个可选的非捕获型分组。它会匹配一个e(或E)、一个可选的正负号以及一个或多个数字。
     * <p>
     * (表示数值的字符串)
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     *
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        String pattern = "^[-+]?\\d*(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?$";
        String s = new String(str);
        return Pattern.matches(pattern, s);
    }

    /**
     * (机器人的运动范围)
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入
     * 行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        int[][] flag = new int[rows][cols];
        help(0, 0, threshold, rows, cols, flag);
        return count;
    }

    // 判断坐标是否符合要求
    public boolean isValid(int row, int col, int threshold) {
        int sum = 0;
        while (row > 0) {
            sum += row % 10;
            row = row / 10;
        }
        while (col > 0) {
            sum += col % 10;
            col = col / 10;
        }
        if (sum > threshold) return false;
        else return true;
    }

    //统计能够走到的次数
    public int count = 0;

    public void help(int i, int j, int threshold, int rows, int cols, int[][] flag) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) return;//如果坐标不符合则不计数
        if (flag[i][j] == 1) return;//如果曾经被访问过则不计数
        if (!isValid(i, j, threshold)) {
            flag[i][j] = 1;//如果不满足坐标有效性，则不计数并且标记是访问的
            return;
        }
        //无论是广度优先遍历还是深度优先遍历，我们一定要知道的时候遍历一定会有终止条件也就是要能够停止，
        //不然程序就会陷入死循环，这个一定是我们做此类题目必须要注意的地方
        flag[i][j] = 1;
        count++;
        help(i - 1, j, threshold, rows, cols, flag);//遍历上下左右节点
        help(i + 1, j, threshold, rows, cols, flag);
        help(i, j - 1, threshold, rows, cols, flag);
        help(i, j + 1, threshold, rows, cols, flag);
    }

    /**
     * 字符流中第一个不重复的字符
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     * 如果当前字符流没有存在出现一次的字符，返回#字符。
     *
     * @param ch
     */
    //Insert one char from stringstream
    String input = "";
    Map<Character, Integer> map = new HashMap<>();

    public void Insert(char ch) {
        if (!map.keySet().contains(ch)) {
            map.put(ch, 1);
        } else {
            map.put(ch, map.get(ch) + 1);
        }
        input += ch;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        int index = Integer.MAX_VALUE;
        char result = '#';
        for (Character c : map.keySet()) {
            if (map.get(c) == 1) {
                if (input.indexOf(c) < index) {
                    index = input.indexOf(c);
                    result = input.charAt(index);
                }
            }
        }
        return result;
    }

    /**
     * 滑动窗口的最大值
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
     * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     *
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size < 1 || num.length == 0) return res;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {

            while (!list.isEmpty() && num[list.peekLast()] < num[i]) {
                list.pollLast();
            }
            list.add(i);
            //判断左边是否失效
            if (list.peekFirst() <= i - size) {
                list.pollFirst();
            }
            if (i >= size - 1)
                res.add(num[list.peekFirst()]);


        }
        return res;
    }

    /**
     * 按之字形顺序打印二叉树
     * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     *
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> PrintZhi(TreeNode pRoot) {
        LinkedList<TreeNode> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        boolean rev = true;
        q.add(pRoot);
        while (!q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node == null) {
                    continue;
                }
                if (rev) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                q.offer(node.left);
                q.offer(node.right);
            }
            if (list.size() != 0) {
                res.add(list);
            }
            rev = !rev;
        }
        return res;
    }

    /**
     * 二叉搜索树的第k个结点
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
     *
     * @param pRoot
     * @param k
     * @return
     */
    ArrayList<TreeNode> list = new ArrayList();

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) return null;

        toLDR(pRoot, k);

        if (k >= 1 && list.size() >= k) return list.get(k - 1);

        return null;
    }

    public void toLDR(TreeNode node, int k) {
        if (node != null) {
            // 通过此处判断减少递归次数
            if (list.size() >= k) return;
            toLDR(node.left, k);
            list.add(node);
            toLDR(node.right, k);
        }
    }

    /**
     * (把二叉树打印成多行)
     * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     *
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> thelist = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) return thelist; //这里要求返回thelist而不是null
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            thelist.add(list);
        }
        return thelist;
    }

    /**
     * （扑克牌顺子）
     * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,
     * 如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,
     * J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
     * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
     *
     * @param numbers
     * @return
     */
    public boolean isContinuous(int[] numbers) {

        if (numbers == null || numbers.length == 0) return false;
        List<Integer> list = new ArrayList<>();
        int n = 0;
        for (int i : numbers) {
            if (i == 0) {
                n++;
            } else {
                list.add(i);
            }
        }
        Collections.sort(list);
        int m = list.get(list.size() - 1) - list.get(0);
        return m <= 4 && 4 - m <= n;
    }

    /**
     * （孩子们的游戏(圆圈中最后剩下的数)）
     * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
     * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
     * 然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,
     * 并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     * <p>
     * 如果没有小朋友，请返回-1
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        //构建list
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int cur = -1;
        while (list.size() > 1) {
            for (int i = 0; i < m; i++) {
                cur++;
                if (cur == list.size()) {
                    cur = 0;
                }
            }
            list.remove(cur);
            cur--;//cur--的原因，因为新的list中cur指向了下一个元素，为了保证移动m个准确性，所以cur向前移动一位。
        }
        return list.get(0);
    }

    /**
     * （构建乘积数组）
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]
     * *...*A[n-1]。不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
     *
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        //边界
        if (A == null || A.length <= 1) {
            return null;
        }
        //计算下三角
        //初始化第一行
        B[0] = 1;
        for (int i = 1; i < length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        //计算上三角
        //初始化最后一行
        int temp = 1;
        for (int i = length - 1; i >= 0; i--) {
            B[i] = temp * B[i];
            temp = A[i] * temp;
        }
        return B;
    }

    /**
     * 二叉树的下一个结点
     * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        // 1.
        if (pNode.right != null) {
            TreeLinkNode pRight = pNode.right;
            while (pRight.left != null) {
                pRight = pRight.left;
            }
            return pRight;
        }
        // 2.
        if (pNode.next != null && pNode.next.left == pNode) {
            return pNode.next;
        }
        // 3.
        if (pNode.next != null) {
            TreeLinkNode pNext = pNode.next;
            while (pNext.next != null && pNext.next.right == pNext) {
                pNext = pNext.next;
            }
            return pNext.next;
        }
        return null;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
     * (把字符串转换成整数)
     * 输入
     * +2147483647
     * 1a33
     * 输出
     * 2147483647
     * 0
     *
     * @param str
     * @return
     */
    public int StrToInt(String str) {

        //最优解
        if (str == null || "".equals(str.trim())) return 0;
        str = str.trim();
        char[] arr = str.toCharArray();
        int i = 0;
        int flag = 1;
        int res = 0;
        if (arr[i] == '-') {
            flag = -1;
        }
        if (arr[i] == '+' || arr[i] == '-') {
            i++;
        }
        while (i < arr.length) {
            //是数字
            if (isNum(arr[i])) {
                int cur = arr[i] - '0';
                if (flag == 1 && (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && cur > 7)) {
                    return 0;
                }
                if (flag == -1 && (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && cur > 8)) {
                    return 0;
                }
                res = res * 10 + cur;
                i++;
            } else {
                //不是数字
                return 0;
            }
        }
        return res * flag;

    }

    public static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     * 对称的二叉树
     *
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        return pRoot == null || jude(pRoot.left, pRoot.right);
    }

    public boolean jude(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.val != node2.val) {
            return false;
        } else {
            return jude(node1.left, node2.right) && jude(node1.right, node2.left);
        }
    }


    /**
     * 输入
     * 1,2,3,4,5,6,7,0
     * 输出
     * 7
     * (数组中的逆序对)
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
     *
     * @param array
     * @return
     */
    private int cnt;

    public int InversePairs(int[] array) {
        MergeSort(array, 0, array.length - 1);
        return cnt;
    }

    private void MergeSort(int[] array, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        MergeSort(array, start, mid);
        MergeSort(array, mid + 1, end);
        MergeOne(array, start, mid, end);
    }

    private void MergeOne(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int k = 0, i = start, j = mid + 1;
        while (i <= mid && j <= end) {
//如果前面的元素小于后面的不能构成逆序对
            if (array[i] <= array[j])
                temp[k++] = array[i++];
            else {
//如果前面的元素大于后面的，那么在前面元素之后的元素都能和后面的元素构成逆序对
                temp[k++] = array[j++];
                cnt = (cnt + (mid - i + 1)) % 1000000007;
            }
        }
        while (i <= mid)
            temp[k++] = array[i++];
        while (j <= end)
            temp[k++] = array[j++];
        for (int l = 0; l < k; l++) {
            array[start + l] = temp[l];
        }
    }

    /**
     * 链表中环的入口结点
     * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
     *
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }

        ListNode fast = pHead;
        ListNode slow = pHead;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode slow2 = pHead;
                while (slow2 != slow) {
                    slow2 = slow2.next;
                    slow = slow.next;
                }
                return slow2;
            }
        }
        return null;

    }

    /**
     * 不用加减乘除做加法
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     *
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        int result, ans;
        do {
            result = num1 ^ num2;       // 每一位相加
            ans = (num1 & num2) << 1;   // 进位
            num1 = result;
            num2 = ans;
        } while (ans != 0);
        return result;
    }

    /**
     * 和为S的连续正数序列
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多
     * 少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> resp = new ArrayList<>();
        if (sum <= 0) {
            return resp;
        }
        int leftP = 1;
        int rightP = 2;
        int sumVal = leftP + rightP;

        while (sum > rightP) {
            if (sumVal < sum) {
                rightP++;
                sumVal += rightP;
            } else if (sumVal > sum) {
                sumVal -= leftP;
                leftP++;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = leftP; i <= rightP; i++) {
                    list.add(i);
                }
                resp.add(list);

                rightP++;
                sumVal += rightP;
            }
        }

        return resp;
    }

    /**
     * 删除链表中重复的结点
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        // 自己构建辅助头结点
        ListNode head = new ListNode(Integer.MIN_VALUE);
        head.next = pHead;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.next != null && cur.next.val == cur.val) {
                // 相同结点一直前进
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                // 退出循环时，cur 指向重复值，也需要删除，而 cur.next 指向第一个不重复的值
                // cur 继续前进
                cur = cur.next;
                // pre 连接新结点
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }

    /**
     * 平衡二叉树
     * <p>
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * <p>
     * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
     *
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root) != -1;
    }

    public int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        if (left == -1) return -1; //如果发现子树不平衡之后就没有必要进行下面的高度的求解了
        int right = depth(root.right);
        if (right == -1) return -1;//如果发现子树不平衡之后就没有必要进行下面的高度的求解了
        if (left - right < (-1) || left - right > 1)
            return -1;
        else
            return 1 + (left > right ? left : right);
    }


    /**
     * （整数中1出现的次数（从1到n整数中1出现的次数））
     * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、
     * 11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
     *
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        // 循环 1->n
        for (int i = 1; i <= n; i++) {
            count += f(i);
        }
        return count;
    }

    // 判断有多少个1
    public int f(int n) {
        String str = n + "";
        char[] c = str.toCharArray();
        int count = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '1') {
                count++;
            }
        }
        return count;
    }

    /**
     * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
     *
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        //边界条件
        if (array == null || array.length <= 1) {
            return result;
        }
        int smallIndex = 0;
        int bigIndex = array.length - 1;
        while (smallIndex < bigIndex) {
            //如果相等就放进去
            if ((array[smallIndex] + array[bigIndex]) == sum) {
                result.add(array[smallIndex]);
                result.add(array[bigIndex]);
                //最外层的乘积最小，别被题目误导
                break;
            } else if ((array[smallIndex] + array[bigIndex]) < sum) {
                smallIndex++;
            } else {
                bigIndex--;
            }
        }
        return result;

    }

    /**
     * 丑数
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     *
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int p2 = 0, p3 = 0, p5 = 0;//初始化三个指向三个潜在成为最小丑数的位置
        int[] result = new int[index];
        result[0] = 1;//
        for (int i = 1; i < index; i++) {
            result[i] = Math.min(result[p2] * 2, Math.min(result[p3] * 3, result[p5] * 5));
            if (result[i] == result[p2] * 2) p2++;//为了防止重复需要三个if都能够走到
            if (result[i] == result[p3] * 3) p3++;//为了防止重复需要三个if都能够走到
            if (result[i] == result[p5] * 5) p5++;//为了防止重复需要三个if都能够走到


        }
        return result[index - 1];
    }

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * （二叉搜索树与双向链表）
     * 是用一个数组来存储中序遍历的节点，然后再从头到尾，建立节点前后的连接关系。
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null)
            return null;
        ArrayList<TreeNode> list = new ArrayList<>();
        Convert(list, pRootOfTree);
        return Convert(list);
    }

    public void Convert(ArrayList<TreeNode> list, TreeNode root) {
        if (root != null) {
            Convert(list, root.left);
            list.add(root);
            Convert(list, root.right);
        }
    }

    public TreeNode Convert(ArrayList<TreeNode> list) {
        TreeNode head = list.get(0);
        TreeNode cur = head;
        for (int i = 1; i < list.size(); ++i) {
            TreeNode node = list.get(i);
            node.left = cur;
            cur.right = node;
            cur = node;
        }
        return head;
    }

    /**
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节
     * 点），请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     * (复杂链表复制)
     * 复杂，随机
     *
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;

        // target 作为将要返回的头，记住要new的
        RandomListNode target = new RandomListNode(pHead.label);
        // cur 获取链表头
        RandomListNode cur = pHead;
        // p   获取新链表头
        RandomListNode p = target;

        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();

        // 由pHead将所有值存入map，每一个结点都要new的
        while (pHead != null) {
            map.put(pHead, new RandomListNode(pHead.label));
            pHead = pHead.next;
        }

        // target作为新链表的头，由cur，p移动来复制链表
        while (cur != null) {
            p.next = map.get(cur.next);
            p.random = map.get(cur.random);

            cur = cur.next;
            p = p.next;
        }

        return target;
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**---------------------------------------------------------------------------------------------**/
    /*************************************以上为答案并未去理解*******************************************/
    /**---------------------------------------------------------------------------------------------**/


    /**
     * 翻转单词顺序列
     * <p>
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，
     * 有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
     * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     *
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        StringBuilder builder = new StringBuilder();
        if (str == null || str.length() == 0) {
            return "";
        }
        String[] s = str.split(" ");
        if (s.length == 0) {
            return str;
        }

        for (int i = s.length - 1; i >= 0; i--) {
            builder.append(s[i] + " ");
        }
        return builder.toString().trim();
    }

    /**
     * （左旋转字符串）
     * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个
     * 给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
     *
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str, int n) {
        if (str == null || str.length() == 0) {
            return "";
        }
        return str.substring(n) + str.substring(0, n);    //subString左闭右开
    }

    /**
     * 全排序
     *
     * @param ss
     * @param i
     */
    public static void permutation(char[] ss, ArrayList<String> list, int i) {
        if (ss == null || i < 0 || i > ss.length) {//1
            return;
        }

        if (i == ss.length - 1) {//2
            System.out.println(new String(ss));
            //            if(!list.contains(String.valueOf(ss))){
//                list.add(String.valueOf(ss));
//            }
        } else {
            for (int j = i; j < ss.length; j++) {//3
                char temp = ss[j];//交换前缀,使之产生下一个前缀
                ss[j] = ss[i];
                ss[i] = temp;
                permutation(ss, list, i + 1);//4
                temp = ss[j]; //将前缀换回来,继续做上一个的前缀排列.//5
                ss[j] = ss[i];
                ss[i] = temp;
            }
        }
    }

    /**
     * 全排序
     *
     * @param arr
     * @param start
     * @param end
     */
    public void fullSort(int[] arr, int start, int end) {
        // 递归终止条件
        if (start == end) {
            for (int i : arr) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(arr, i, start);
            fullSort(arr, start + 1, end);
            swap(arr, i, start);
        }
    }

    /**
     * 全排序
     *
     * @param arr
     * @param start
     * @param end
     */
    public void fullSort(int[] arr, ArrayList<String> list, int start, int end) {
        // 递归终止条件
        if (start == end) {
            StringBuilder builder = new StringBuilder();
            for (int i : arr) {
                builder.append(i);
//                System.out.print(i);
            }
            if (!list.contains(builder.toString())) {
                list.add(builder.toString());
            }
//            System.out.println();
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(arr, i, start);
            fullSort(arr, list, start + 1, end);
            swap(arr, i, start);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     *
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int[] numbers) {
        ArrayList<String> list = new ArrayList<>();
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        fullSort(numbers, list, 0, numbers.length - 1);
        Collections.sort(list);
        return list.get(0);
    }

    public String PrintMinNumber0(int[] numbers) {

        String result = "";
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : numbers) list.add(num);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1 + "" + o2;
                String s2 = o2 + "" + o1;
                return s1.compareTo(s2);
            }
        });

        for (int i : list) result += i;

        return result;
    }

    /**
     * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。\
     * //num1,num2分别为长度为1的数组。传出参数
     * //将num1[0],num2[0]设置为返回结果
     *
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0) {
            return;
        }
        for (int a : array) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }
        num1[0] = list.get(0);
        num2[0] = list.get(1);
    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     *
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int[] array, int k) {
        int cnt = 0;
        for (int a : array) {
            if (a == k) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
     * <p>
     * 思路：两个链表拼接
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
            if (p1 != p2) {
                if (p1 == null) {
                    p1 = pHead2;
                }
                if (p2 == null) {
                    p2 = pHead1;
                }
            }
        }
        return p1;
    }

    /**
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     *
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {

        return ((int) Math.pow(n, 2) + n) >> 1;
    }

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     * 递归 （全排序）
     * 按字典序****
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (str == null || str.length() == 0)
            return arrayList;
        char[] c = str.toCharArray();
        perm(c, arrayList, 0);
        Collections.sort(arrayList);
        return arrayList;
    }

    // 递归方法一：运行时间：138ms     占用内存：12244k

    /**
     * 打乱
     *
     * @param c
     * @param arrayList
     * @param start
     */
    private void perm(char[] c, ArrayList<String> arrayList, int start) {
        if (c == null || start == c.length - 1)
            if (!arrayList.contains(String.valueOf(c)))
                arrayList.add(String.valueOf(c));
        for (int i = start; i < c.length; i++) {
            char temp = c[i];
            c[i] = c[start];
            c[start] = temp;
            perm(c, arrayList, start + 1);
            temp = c[i];
            c[i] = c[start];
            c[start] = temp;
        }
    }

    /**
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (numbers[i] == numbers[j]) {
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
     *
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {

        if (str == null || str.length() == 0) {
            return -1;
        }

        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == entry.getKey()) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);
        return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
    }

    /**
     * 深度优先遍历(先序遍历)
     *
     * @param nodeHead
     */
    public void depthFirstSearch(TreeNode nodeHead) {
        if (nodeHead == null) {
            return;
        }
        Stack<TreeNode> myStack = new Stack<>();
        myStack.add(nodeHead);
        while (!myStack.isEmpty()) {
            TreeNode node = myStack.pop();    //弹出栈顶元素
            System.out.print(node.val + " ");         //逐一输出结果
            if (node.right != null) {
                myStack.push(node.right);    //深度优先遍历，先遍历左边，后遍历右边,栈先进后出
            }
            if (node.left != null) {
                myStack.push(node.left);
            }
        }
    }

    /**
     * 广度优先遍历是使用队列实现的
     *
     * @param nodeHead
     */
    public void BroadFirstSearch(TreeNode nodeHead) {
        if (nodeHead == null) {
            return;
        }
        Queue<TreeNode> myQueue = new LinkedList<>();
        myQueue.add(nodeHead);
        while (!myQueue.isEmpty()) {
            TreeNode node = myQueue.poll();
            System.out.print(node.val + " ");       //逐一输出结果
            if (null != node.left) {
                myQueue.add(node.left);    //深度优先遍历，我们在这里采用每一行从左到右遍历
            }
            if (null != node.right) {
                myQueue.add(node.right);
            }
        }
    }

    /**
     * 深度优先遍历(先序遍历)
     * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     *
     * @param root
     * @param target
     * @return
     */
    private ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list1 = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return result;
        }
        list1.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<Integer>(list1));
        }
//因为在每一次的递归中，我们使用的是相同的result引用，所以其实左右子树递归得到的结果我们不需要关心，
//可以简写为FindPath(root.left, target)；FindPath(root.right, target)；
//但是为了大家能够看清楚递归的真相，此处我还是把递归的形式给大家展现了出来。
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);
        return result;
    }

    /**
     * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常
     * 需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
     * 并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
     * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
     *
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int max = -1;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, lianxuMax(Arrays.copyOfRange(array, i, array.length)));
        }
        return max;
    }

    private int lianxuMax(int[] array) {
        int[] num = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <= i; j++) {
                num[i] += array[j];
            }
        }
        Arrays.sort(num);
        return num[num.length - 1];
    }

    /**
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length) {
            return list;
        }
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    /**
     * 输入一个非空整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     * 例子： 2 4 3 6 8 7 5 这是一个正确的后序遍历
     * 这个例子的特点就是:最后一个元素是 5 ，首先遍历数组，当遍历到6的时候，6前面的值都小于5，如果在6后面的值有一个小于5就不是后序遍历，所以一旦在遍历的时候遇到比最后一个元素的值索引
     * ，那么之后的所有元素都必须大于5，否则就不是后序遍历序列。
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return isBST(sequence);
    }

    private boolean isBST(int[] sequence) {
        int mid = 0, len = sequence.length;
        if (len <= 1) {
            return true;
        }
        for (; sequence[mid] < sequence[len - 1]; mid++) ;       //空语句
        for (int i = mid; i < len - 1; i++) {
            if (sequence[i] < sequence[len - 1]) {
                return false;
            }
        }
        return isBST(Arrays.copyOfRange(sequence, 0, mid)) && isBST(Arrays.copyOfRange(sequence, mid, len - 1));
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     *
     * @param array
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int MoreThanHalfNum_Solution(int[] array) {

        if (array == null || array.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
        }

        int maxValue = -1;
        int maxKey = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        if (maxValue > array.length / 2) {
            return maxKey;
        } else {
            return 0;
        }
    }

    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
     *
     * @param node
     */
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();    //辅助栈

    public void push(int node) {
        stack.push(node);
        if (minStack.isEmpty()) {
            minStack.push(node);
        } else {
            if (minStack.peek() >= node) {
                minStack.push(node);
            }
        }
    }

    public void pop() {
        if (stack.peek() == minStack.peek()) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     * <p>
     * 思路：新建一个栈，将数组A压入栈中，当栈顶元素等于数组B时，就将其出栈，当循环结束时，判断栈是否为空，若为空则返回true.
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || pushA.length != popA.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[j]) {       //peek 不改变栈的值(不删除栈顶的值)，pop会把栈顶的值删除。
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     * <p>
     * //思路：此题实际为二叉树的广度遍历，广度遍历必须借助其他的数据结构才能进行，比如最常见的Queue
     * //(不能直接递归哦)
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode current = root;
        queue.offer(root);
        while (!queue.isEmpty()) {
            current = queue.poll();
            list.add(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return list;
    }

    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {

        if (root1 == null || root2 == null) {
            return false;
        }

        //如果找到相同的根值，则走判断方法
        if (root1.val == root2.val) {
            if (judge(root1, root2)) {
                return true;
            }

        }
        //遍历左孩子和右孩子
        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);

    }

    /**
     * 判断是否是子结构
     *
     * @param root
     * @param subtree
     * @return
     */
    public boolean judge(TreeNode root, TreeNode subtree) {
        //子结构已经循环完毕，代表全部匹配
        if (subtree == null) {
            return true;
        }
        //大树已经循环完毕，并未成功匹配
        if (root == null) {
            return false;
        }
        if (root.val == subtree.val) {
            return judge(root.left, subtree.left) && judge(root.right, subtree.right);
        }
        return false;
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     *
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) {
            //最上面一层
            for (int col = left; col <= right; col++) {
                list.add(matrix[up][col]);
            }
            //向下逼近
            up++;
            //判断是否越界
            if (up > down) {
                break;
            }
            //最右边
            for (int row = up; row <= down; row++) {
                list.add(matrix[row][right]);
            }
            //向左逼近
            right--;
            //判断是否越界
            if (right < left) {
                break;
            }
            //最下边
            for (int col = right; col >= left; col--) {
                list.add(matrix[down][col]);
            }
            //向上逼近
            down--;
            //判断是否越界
            if (down < up) {
                break;
            }
            //最右边
            for (int row = down; row >= up; row--) {
                list.add(matrix[row][left]);
            }
            //向右逼近
            left++;
            //判断是否越界
            if (left > right) {
                break;
            }
        }
        return list;
    }

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * 递归
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            if (list1.val <= list2.val) {
                list1.next = Merge(list1.next, list2);
                return list1;
            } else {
                list2.next = Merge(list1, list2.next);
                return list2;
            }
        }
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge1(ListNode list1, ListNode list2) {
        ListNode h = new ListNode(-1);
        ListNode head = h;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        if (list1 == null) {
            head.next = list2;
        }
        if (list2 == null) {
            head.next = list1;
        }
        return h.next;
    }

    /**
     * 输入一个链表，反转链表后，输出新链表的表头。
     *
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;     //当前节点的前一个节点
        ListNode next = null;   //当前节点的后一个节点
        while (head != null) {
            next = head.next;   //记录当前节点的下一个节点的位置
            head.next = pre;    //让当前节点指向前一个节点的位置，完成反转
            pre = head;         //pre向右走
            head = next;
        }

        return pre;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {

        int size = 1;
        ListNode temp = head;

        if (temp == null || temp.next == null) {
            return head;
        }

        while (temp.next != null) {
            temp = temp.next;
            size++;
        }

        if (size - k < 0) {
            return null;
        }

        for (int i = size - k; i > 0; i--) {
            head = head.next;
        }

        return head;

    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     *
     * @param array
     */
    public void reOrderArray1(int[] array) {
        Queue<Integer> queue_J = new LinkedList<>();
        Queue<Integer> queue_O = new LinkedList<>();
        for (int a : array) {
            if (a % 2 == 0) {
                queue_O.add(a);
            } else {
                queue_J.add(a);
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (!queue_J.isEmpty()) {
                array[i] = queue_J.poll();
            } else if (!queue_O.isEmpty()) {
                array[i] = queue_O.poll();
            }
        }
    }

    public void reOrderArray2(int[] array) {
        List<Integer> list_l = new ArrayList<>();
        List<Integer> list_r = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int a : array) {
            if (a % 2 == 0) {
                list_r.add(a);
            } else {
                list_l.add(a);
            }
        }
        list.addAll(list_l);
        list.addAll(list_r);
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
    }

    /**
     * substring左开右闭
     *
     * @param array
     */
    public void reOrderArray3(int[] array) {

        StringBuilder builder_l = new StringBuilder();
        StringBuilder builder_r = new StringBuilder();

        for (int a : array) {
            if (a % 2 == 0) {
                builder_r.append(a);
            } else {
                builder_l.append(a);
            }
        }
        String string = builder_l.append(builder_r).toString();
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.valueOf(string.substring(i, i + 1));
        }
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * 保证base和exponent不同时为0
     *
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {

        return Math.pow(base, exponent);

    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        String string = Integer.toBinaryString(n);
        char[] chars = string.toCharArray();
        int cnt = 0;
        for (char a : chars) {
            if (a == '1') {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * 比如n=3时，2*3的矩形块有3种覆盖方法：
     *
     * @param target
     * @return
     */
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        return RectCover(target - 1) + RectCover(target - 2);
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *
     * @param target
     * @return f(n) = 2f(n-1)
     */
    public int JumpFloorII1(int target) {

        if (target == 1) {
            return 1;
        }
        return 2 * JumpFloorII1(target - 1);

    }

    /**
     * 等比
     *
     * @param target
     * @return
     */
    public int JumpFloorII2(int target) {

        return target == 1 ? 1 : 1 << target - 1;

    }


    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     *
     * @param target
     * @return
     */
    public int JumpFloor(int target) {

        if (target <= 2) {
            return target;
        }
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }


    /**
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {

        if (n <= 1) {
            return n;
        }

        return Fibonacci(n - 1) + Fibonacci(n - 2);

    }


    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int[] array) {
        Arrays.sort(array);
        return array[0];
    }

    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    public void push1(int node) {
        stack1.push(node);
    }

    public int pop1() {
        if (stack2.isEmpty()) {      //一定判断stack2为空
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }


    /**
     * Definition for binary tree
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                //左子树  copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                //右子树
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }

    /**
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        Collections.reverse(list);
        return list;
    }


    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        StringBuilder builder = new StringBuilder();
        char[] chars = str.toString().toCharArray();
        for (char a : chars) {
            if (a == ' ') {
                builder.append("%20");
            } else {
                builder.append(a);
            }
        }
        return builder.toString();
    }

    /**
     * q:在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param target 目标数
     * @param array  是否含有此数
     * @return
     */
    public boolean Find(int target, int[][] array) {
        for (int[] a : array) {
            for (int i : a) {
                if (target == i) {
                    return true;
                }
            }
        }
        return false;

    }


    /**
     * 顺序查找
     *
     * @param array
     * @param value 待查找的值
     * @return 有则返回true否则false
     */
    public static boolean sequentialSearch(int[] array, int value) {
        for (int a : array) {
            if (a == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * 二分查找（前提：1.先排序；2.动态查找效率低）注：静态或者动态都是针对查找表而言的。动态表指查找表中有删除和插入操作的表。
     *
     * @param array
     * @param value 待查找的值
     * @return 有则返回true否则false
     */
    public static boolean binarySearch1(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (array[mid] == value) {
                return true;
            } else if (value < array[mid]) {
                high = mid - 1;
            } else if (value > array[mid]) {
                low = mid + 1;
            }
        }
        return false;
    }

    /**
     * 递归二分查找
     *
     * @param array
     * @param value
     * @return
     */
    public static boolean binarySearch2(int[] array, int value, int low, int high) {
        int mid;
        if (low <= high) {
            mid = (low + high) / 2;
            if (array[mid] == value) {
                return true;
            } else if (value < array[mid]) {
                return binarySearch2(array, value, low, mid - 1);
            } else if (value > array[mid]) {
                return binarySearch2(array, value, mid + 1, high);
            }
        }
        return false;
    }

    /**
     * 递归插值查找
     *
     * @param array
     * @param value
     * @return
     */
    public static boolean interpolatationSearch(int[] array, int value, int low, int high) {
        int mid;
        if (low <= high) {
            mid = (low + high) / 2;
            if (array[mid] == value) {
                return true;
            } else if (value < array[mid]) {
                return binarySearch2(array, value, low, mid - 1);
            } else if (value > array[mid]) {
                return binarySearch2(array, value, mid + 1, high);
            }
        }
        return false;
    }

    /**==========================================================================================**/
    /**====================================以上为剑指offer=========================================**/
    /**==========================================================================================**/

    /**
     * 动态规划（购物单）
     */
    public static void dynamicDecided() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int N = scanner.nextInt() / 10;
            int m = scanner.nextInt();
            int[] v = new int[m + 1];   //价格
            int[] p = new int[m + 1];   //重要度
            int[] q = new int[m + 1];   //主附件编号

            int[] zj_pri = new int[m + 1];
            int[] zj_imp = new int[m + 1];

            int[] fj_pri1 = new int[m + 1];
            int[] fj_imp1 = new int[m + 1];
            int[] fj_pri2 = new int[m + 1];
            int[] fj_imp2 = new int[m + 1];

            int[] dp = new int[N + 1];  //花N买最高价值*重要度

            for (int i = 1; i < m + 1; i++) {
                v[i] = scanner.nextInt() / 10;
                p[i] = scanner.nextInt();
                q[i] = scanner.nextInt();
                if (q[i] == 0) {    //主键
                    zj_pri[i] = v[i];
                    zj_imp[i] = v[i] * p[i];
                } else if (fj_pri1[q[i]] == 0) {    //附件1未赋值时
                    fj_pri1[q[i]] = v[i];
                    fj_imp1[q[i]] = v[i] * p[i];
                } else {
                    fj_pri2[q[i]] = v[i];
                    fj_imp2[q[i]] = v[i] * p[i];
                }
            }
            for (int i = 1; i < m + 1; i++) {
                for (int money = N; money > 0; money--) {
                    if (money >= zj_pri[i]) {
                        dp[money] = Math.max(dp[money], dp[money - zj_pri[i]] + zj_imp[i]);
                    }
                    if (money >= zj_pri[i] + fj_pri1[i]) {
                        dp[money] = Math.max(dp[money], dp[money - zj_pri[i] - fj_pri1[i]] + zj_imp[i] + fj_imp1[i]);
                    }
                    if (money >= zj_pri[i] + fj_pri2[i]) {
                        dp[money] = Math.max(dp[money], dp[money - zj_pri[i] - fj_pri2[i]] + zj_imp[i] + fj_imp2[i]);
                    }
                    if (money >= zj_pri[i] + fj_pri1[i] + fj_pri2[i]) {
                        dp[money] = Math.max(dp[money], dp[money - zj_pri[i] - fj_pri1[i] - fj_pri2[i]] + zj_imp[i] + fj_imp1[i] + fj_imp1[i]);
                    }
                }
            }
            System.out.println(dp[N] * 10);
        }
    }

    /**
     * 递归
     *
     * @param j       第j件物品
     * @param jp      第j件拿的价值
     * @param zf_code 主附件的编号 0 - 为主键
     */
    public static void funShoplist(int j, int jp, int zf_code) {


    }

    /**
     * 修复bug
     */
    public static void repairBug() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();  //总bug
            int k = scanner.nextInt();  //总天
            double x = n * (1 - 1 / k) / Math.pow(1 - 1 / k, k);
            System.out.println(Math.round(x));
        }
    }

    /**
     * 带头冲锋
     */
    public static void chongFeng() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            int[] chufa = new int[N];
            int[] daoda = new int[N];
            for (int i = 0; i < N; i++) {
                chufa[i] = scanner.nextInt();
            }
            for (int i = 0; i < N; i++) {
                daoda[i] = scanner.nextInt();
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (chufa[i] == daoda[j]) {
                        daoda[j] = i;
                    }
                }
            }
            int cnt = 0;
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (daoda[i] < daoda[j]) {
                        cnt++;
                        break;
                    }
                }
            }
            System.out.println(N - cnt + "");
        }
    }


    /**
     * 时间回溯
     */
    public static void timeBack() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int libai = scanner.nextInt();
            String time = scanner.next().replace(":", "");
            int hour = Integer.valueOf(time.substring(0, 1));
            int min = Integer.valueOf(time.substring(2));
            int minForword = scanner.nextInt();
            int nowTime = (libai - 1) * 24 * 60 + (hour * 60 + min);
            int outTime = nowTime - minForword;
            int day = (outTime / (24 * 60)) + 1;
            int outMin = outTime % (24 * 60);
            int outHour = outMin / 60;
            outMin = outMin % 60;
            String outH = "";
            String outM = "";
            if (outHour < 10) {
                outH = "0" + outHour;
            } else {
                outH = outHour + "";
            }
            if (outMin < 10) {
                outM = "0" + outMin;
            } else {
                outM = outMin + "";
            }
            System.out.println(day + "");
            System.out.println(outH + ":" + outM);
        }
    }

    /**
     * 蛇形矩阵
     */
    public static void snackMatri() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            int start = 1;
            int diff = 2;
            int pre = 0;
            for (int i = 0; i < N; i++) {
                start += i;
                System.out.print(start + " ");
                pre = start;
                for (int j = 0; j < N - i - 1; j++) {
                    System.out.print(pre + diff + j + " ");
                    pre = pre + diff + j;
                }
                diff++;
                System.out.println();
            }
        }
    }

    /**
     * 统计出包含英文字母、空格、数字和其它字符的个数。
     */
    public static void staticNum() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            char[] chars = str.toCharArray();
            int englishCharCount = getEnglishCharCount(chars);
            int blankCharCount = getBlankCharCount(chars);
            int numberCharCount = getNumberCharCount(chars);
            System.out.println(englishCharCount);
            System.out.println(blankCharCount);
            System.out.println(numberCharCount);
            System.out.println(chars.length - englishCharCount - blankCharCount - numberCharCount);
        }
    }

    /**
     * 统计出英文字母字符的个数。
     *
     * @return 英文字母的个数
     */
    public static int getEnglishCharCount(char[] chars) {
        int cnt = 0;
        for (char a : chars) {
            if (isLetter(a)) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 统计出空格字符的个数。
     *
     * @return 空格的个数
     */
    public static int getBlankCharCount(char[] chars) {
        int cnt = 0;
        for (char a : chars) {
            if (' ' == a) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 统计出数字字符的个数。
     *
     * @return 英文字母的个数
     */
    public static int getNumberCharCount(char[] chars) {
        int cnt = 0;
        for (char a : chars) {
            if ('0' <= a && a <= '9') {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 字符串加解密
     */
    public static void encryStr() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String needEncry = scanner.next();
            String needUnEncry = scanner.next();
            encryption(needEncry);  //加密
            unEncryption(needUnEncry);  //解密
        }

    }

    /**
     * 加密
     */
    public static void encryption(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (chars[i] >= 'a' && chars[i] < 'z')
                chars[i] = (char) (chars[i] + 1 - 32);
            else if (chars[i] == 'z')
                chars[i] = 'A';
            else if (chars[i] >= 'A' && chars[i] < 'Z')
                chars[i] = (char) (chars[i] + 1 + 32);
            else if (chars[i] == 'Z')
                chars[i] = 'a';
            else if (chars[i] >= '0' && chars[i] < '9')
                chars[i] = (char) (chars[i] + 1);
            else if (chars[i] == '9')
                chars[i] = '0';
        }
        StringBuilder builder = new StringBuilder();
        for (char a : chars) {
            builder.append(a);
        }
        System.out.println(builder.toString());
    }

    /**
     * 解密
     */
    public static void unEncryption(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (chars[i] > 'a' && chars[i] <= 'z')
                chars[i] = (char) (chars[i] - 1 - 32);
            else if (chars[i] == 'a')
                chars[i] = 'Z';
            else if (chars[i] > 'A' && chars[i] <= 'Z')
                chars[i] = (char) (chars[i] - 1 + 32);
            else if (chars[i] == 'A')
                chars[i] = 'z';
            else if (chars[i] > '0' && chars[i] <= '9')
                chars[i] = (char) (chars[i] - 1);
            else if (chars[i] == '0')
                chars[i] = '9';
        }
        StringBuilder builder = new StringBuilder();
        for (char a : chars) {
            builder.append(a);
        }
        System.out.println(builder.toString());
    }

    /**
     * 图片整理（ascii大小）
     */
    public static void imageSort() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] > chars[j]) {
                        char temp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = temp;
                    }
                }
            }

            for (char a : chars) {
                System.out.print(a);
            }
            System.out.print("\n");
        }
    }

    /**
     * 图片整理（ascii大小）
     */
    public static void imageArraysSort() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            for (char a : chars) {
                System.out.print(a);
            }
            System.out.print("\n");
        }
    }

    /**
     * 斐波那契数列（兔子数列）
     */
    public static void fibonacci() {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int N = input.nextInt();    //月份
            System.out.println(getMonth(N) + "");
        }
    }

    public static int getMonth(int N) {
        if (N == 1 || N == 2) {
            return 1;
        }
        return getMonth(N - 1) + getMonth(N - 2);
    }

    /**
     * 求解立方跟（牛顿迭代法x(k+1)= x(k)-f(x)/f`(x)）
     */
    public static void lifangen() {
        Scanner input = new Scanner(System.in);
        while (input.hasNextDouble()) {
            double num = input.nextDouble();
            double x = 1.0;
            for (; Math.abs(Math.pow(x, 3) - num) > 1e-2; x = x - ((Math.pow(x, 3) - num) / (3 * Math.pow(x, 2))))
                ;  //1e-5精度
            System.out.println(String.format("%.1f", x));
        }
    }

    /**
     * 字符逆序
     */
    public static void charRev() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] strings = str.trim().split(" ");    //\\s表示 空格,回车,换行等空白符;
            for (int i = strings.length - 1; i >= 0; i--) {
                char[] chars = strings[i].toCharArray();
                for (int j = chars.length - 1; j >= 0; j--) {
                    System.out.print(chars[j]);
                }
                if (i != 0) {
                    System.out.print(" ");
                }
            }
        }
    }

    /**
     * 单词倒排
     */
    public static void wordRev() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            str = str.replaceAll("[^a-zA-Z]", " ");
            String[] strings = str.trim().split("\\s+");    //\\s表示 空格,回车,换行等空白符;
            for (int i = strings.length - 1; i >= 0; i--) {
                System.out.print(strings[i]);
                if (i != 0) {
                    System.out.print(" ");
                }
            }
        }
    }

    /**
     * 单词倒排
     */
    public static void wordReveser() {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        List<String> out = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.next());
        }
        for (int i = 0; i < list.size() - 1; i++) {
            String s = list.get(i);
            StringBuilder sb = new StringBuilder();
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (isLetter(chars[j])) {
                    sb.append(chars[j]);
                }
            }
            out.add(sb.toString());
        }
        for (int i = 0; i < out.size(); i++) {
            System.out.print(out.get(out.size() - i) + " ");
        }
    }

    /**
     * 最小公倍数
     */
    public static void minBeiShu() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(a * b / zuidagongyueshu(a, b));
    }

    /**
     * 最大公约数
     *
     * @param int1
     * @param int2
     * @return
     */
    public static int zuidagongyueshu(int int1, int int2) {
        if (int1 == int2) {
            return int2;
        }
        if (int1 > int2) {
            int differ = int1 - int2;
            return zuidagongyueshu(int2, differ);
        } else {
            int differ = int2 - int1;
            return zuidagongyueshu(int1, differ);
        }
    }


    /**
     * 求质数
     *
     * @return
     */
    public static void priNum(int n) {
        int j;
        for (int i = 2; i <= n; i++) {
            j = 2;
            while (i % j != 0) {
                j++;    // 测试2至i的数字是否能被i整除，如不能就自加
            }
            if (j == i) { // 当有被整除的数字时，判断它是不是自身
                System.out.println(i); // 如果是就打印出数字
            }

        }
    }

    /**
     * 字符串排序
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void strSort() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            List<Character> list = new ArrayList<>();
            String line = scanner.nextLine();
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (isLetter(chars[i])) {
                    list.add(chars[i]);
                }
            }
            //排序
            list.sort(new Comparator<Character>() {
                @Override
                public int compare(Character c1, Character c2) {
                    return Character.toLowerCase(c1) - Character.toLowerCase(c2);
                }
            });

            int index = 0;
            StringBuilder sb = new StringBuilder();
            for (char temp : chars) {
                if (isLetter(temp) && index < list.size()) {
                    sb.append(list.get(index));
                    index++;
                } else {
                    sb.append(temp);
                }
            }
            System.out.println(sb);
        }
    }

    /**
     * 是否为字母
     *
     * @param ch
     * @return
     */
    public static boolean isLetter(char ch) {
        return (ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122);
//        return (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z');
    }

    /**
     * 合唱团
     */
    public static void singSong() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] queue = new int[N];
        int[] back_queue = new int[N];
        int[] sum = new int[N];
        for (int i = 0; i < N; i++) {
            queue[i] = scanner.nextInt();
            back_queue[N - 1 - i] = queue[i];
        }
        int[] location = location(queue);
        int[] back_location = location(back_queue);
        for (int i = 0; i < N; i++) {
            sum[i] = location[i] + back_location[N - 1 - i];
        }
        int max = -1;
        for (int i = 0; i < N; i++) {
            if (max < sum[i]) {
                max = sum[i];
            }
        }
        System.out.println(N - max - 1);
    }

    /**
     * 递增子序列的位置
     *
     * @param array
     * @return
     */
    public static int[] location(int[] array) {
        int N = array.length;
        int[] d = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && d[j] >= d[i]) {
                    d[i] = d[j] + 1;
                }
            }
        }
        return d;
    }

    /**
     * 数组反转
     *
     * @param array
     * @return
     */
    public static int[] reverseArray(int[] array) {
        int N = array.length;
        int[] backArray = new int[N];
        for (int i = 0; i < N; i++) {
            backArray[N - 1 - i] = array[i];
        }
        return backArray;
    }

    /**
     * 最大递增子序列
     */
    public static int maxSon(int[] array) {
        int N = array.length;
        int[] d = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && d[j] >= d[i]) {
                    d[i] = d[j] + 1;
                }
            }
        }
        int max = -1;
        for (int i = 0; i < N; i++) {
            if (max < d[i]) {
                max = d[i];
            }
        }
        return max;
    }

    /**
     * 删除字符串中出现字符最少的字符
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void deleteChar() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Map<Character, Integer> map = new LinkedHashMap<>();        //LinkedHashMap ：保存了记录的插入顺序，遍历慢；
            char[] chars = scanner.nextLine().toCharArray();
            for (int i = 0; i < chars.length; i++) {
                map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
            }
            int tempValue = 0;
            int minValue = 0;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (tempValue > entry.getValue()) {
                    minValue = entry.getValue();
                } else {
                    minValue = tempValue;
                }
                tempValue = entry.getValue();
            }

            for (int i = 0; i < chars.length; i++) {
                int cnt = 0;
                for (int j = 0; j < chars.length; j++) {
                    if (chars[i] == chars[j]) {
                        cnt++;
                    }
                }
                if (cnt != minValue) {
                    System.out.print(chars[i]);
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * 汽水瓶（找规律）
     */
    public static void sodaBottle() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if (n != 0) {
                System.out.println(n / 2);
            }
        }
    }

    /**
     * 简单密码
     */
    public static void simpleSecret() {
        Scanner scanner = new Scanner(System.in);
        char[] chars = scanner.nextLine().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('a' <= chars[i] && chars[i] <= 'z') {
                //小写
                if ('a' <= chars[i] && chars[i] <= 'c') {
                    chars[i] = '2';
                }
                if ('d' <= chars[i] && chars[i] <= 'f') {
                    chars[i] = '3';
                }
                if ('g' <= chars[i] && chars[i] <= 'i') {
                    chars[i] = '4';
                }
                if ('j' <= chars[i] && chars[i] <= 'l') {
                    chars[i] = '5';
                }
                if ('m' <= chars[i] && chars[i] <= 'o') {
                    chars[i] = '6';
                }
                if ('p' <= chars[i] && chars[i] <= 's') {
                    chars[i] = '7';
                }
                if ('t' <= chars[i] && chars[i] <= 'v') {
                    chars[i] = '8';
                }
                if ('w' <= chars[i] && chars[i] <= 'z') {
                    chars[i] = '9';
                }
            }
            //大写
            if ('A' <= chars[i] && chars[i] <= 'Z') {
                if (chars[i] == 'Z') {
                    chars[i] = 'a';
                } else {
                    chars[i] = (char) (chars[i] + 33);
                }
            }
            System.out.print(chars[i]);
        }
    }

    /**
     * 密码验证
     */
    public static void secretCode() {
        Scanner scanner = new Scanner(System.in);
        Set<Character> set1 = new HashSet<>();
        set1.add('$');
        set1.add('%');
        set1.add('#');
        set1.add('@');
        set1.add('-');
        set1.add('~');
        set1.add('!');
        set1.add('^');
        set1.add('&');
        set1.add('*');
        set1.add('(');
        set1.add(')');
        set1.add('+');
        //有人直接把set1去掉了，我认为这才符合一般情况，密码一般允许‘（’吧
        while (scanner.hasNext()) {
            int sign = 0;
            String str = scanner.nextLine();
            if (str.length() <= 8) {
                System.out.println("NG");
                //sign = 1;
                continue;
            }
            char[] cha = str.toCharArray();
            int numsign = 0;
            int uppersign = 0;
            int lowersign = 0;
            int othersign = 0;
            for (int i = 0; i < str.length(); i++) {
                if (numsign == 0 && (cha[i] >= '0' && cha[i] <= '9')) {
                    numsign = 1;
                }
                if (uppersign == 0 && (cha[i] >= 'a' && cha[i] <= 'z')) {
                    uppersign = 1;
                }
                if (lowersign == 0 && (cha[i] >= 'A' && cha[i] <= 'Z')) {
                    lowersign = 1;
                }
                if (othersign == 0 && set1.contains(cha[i])) {
                    othersign = 1;
                }
                if (uppersign + lowersign + numsign + othersign == 3) {
                    break;
                }
            }
            if (uppersign + lowersign + numsign + othersign < 3) {
                System.out.println("NG");
                //sign = 1;
                continue;
            }
            Set<String> set = new HashSet<>();
            for (int j = 0; j < str.length() - 2; j++) {
                StringBuilder sb = new StringBuilder();
                String str1 = sb.append(cha[j]).append(cha[j + 1]).append(cha[j + 2]).toString();
                if (set.contains(str1)) {
                    System.out.println("NG");
                    sign = 1;
                    break;
                } else {
                    set.add(str1);
                }
            }
            if (sign == 0) {
                System.out.println("OK");
            }
        }
    }

    /**
     * 简单的错误记录
     */
    public static void simpleErrorRecord() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> map = new LinkedHashMap<>();
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] error = str.split("\\s+");
            String fname = error[0].substring(error[0].lastIndexOf("\\") + 1);
            fname = fname.substring(Math.max(fname.length() - 16, 0)) + " " + error[1];
            Integer temp = map.get(fname);
            if (temp == null) {
                map.put(fname, 1);
            } else {
                map.put(fname, temp + 1);
            }
        }
        int cnt = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (map.size() - cnt <= 8) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            cnt++;
        }
    }

    /**
     * 坐标移动（正则+hashmap）
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void coordMove() {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        while (scanner.hasNext()) {
            int x = 0, y = 0;
            String[] s = scanner.nextLine().split(";");
            String reg = "[ASDW]\\d\\d?";
            for (int i = 0; i < s.length; i++) {
                if (s[i].matches(reg)) {
                    map.put(s[i].charAt(0), map.getOrDefault(s[i].charAt(0), 0) + Integer.valueOf(s[i].substring(1)));
                }
            }
            x = x - map.get('A') + map.get('D');
            y = y - map.get('S') + map.get('W');
            System.out.println(x + "," + y);
            map.clear();
        }
    }

    /**
     * 01背包问题(购物单)
     *
     * @return 总钱数*重要度
     */
    public static void shopList() {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int N = Integer.valueOf(s[0]) / 10;
        int m = Integer.valueOf(s[1]);

        int[] zj_pri = new int[m];
        int[] zj_imp = new int[m];
        int[] fj1_pri = new int[m];
        int[] fj1_imp = new int[m];
        int[] fj2_pri = new int[m];
        int[] fj2_imp = new int[m];
        int[] dp = new int[N];

        int[] v = new int[m];
        int[] p = new int[m];
        int[] q = new int[m];
        while (scanner.hasNext()) {
            for (int i = 0; i < m; i++) {
                v[i] = scanner.nextInt() / 10;
                p[i] = scanner.nextInt();
                q[i] = scanner.nextInt();
                if (q[i] == 0) {    //主键
                    zj_pri[i] = v[i];
                    zj_imp[i] = v[i] * p[i];
                } else if (fj1_pri[q[i]] == 0) {
                    fj1_pri[i] = v[i];
                    fj1_imp[i] = v[i] * p[i];
                } else {
                    fj2_pri[i] = v[i];
                    fj2_imp[i] = v[i] * p[i];
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = N; j > 0; j--) {

                if (j >= zj_pri[i])
                    dp[j] = Math.max(dp[j], dp[j - zj_pri[i]] + zj_imp[i]);
                if (j >= zj_pri[i] + fj1_pri[i])
                    dp[j] = Math.max(dp[j], dp[j - zj_pri[i] - fj1_pri[i]] + zj_imp[i] + fj1_imp[i]);
                if (j >= zj_pri[i] + fj2_pri[i])
                    dp[j] = Math.max(dp[j], dp[j - zj_pri[i] - fj2_pri[i]] + zj_imp[i] + fj2_imp[i]);
                if (j >= zj_pri[i] + fj1_pri[i] + fj2_pri[i])
                    dp[j] = Math.max(dp[j], dp[j - zj_pri[i] - fj1_pri[i] - fj2_pri[i]] + zj_imp[i] + fj1_imp[i] + fj2_imp[i]);
            }
        }
        System.out.println(dp[N] * 10);
    }


    /**
     * 获取整数在内存中1的个数
     */
    public static void getNum1() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String str = Integer.toBinaryString(num);
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 1) {
                count++;
            }
        }
        System.out.println(count);
    }


    /**
     * 字符串连接成路径查找
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void searchMost() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < num; i++) {
            String s = scanner.next();
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                System.out.println(entry.getKey());
            }
        }

    }

    /**
     * 句子逆序
     */
    public static void senReserve() {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.next());
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            list1.add(list.get(i));
//            list.set(list.size() - 1 - i, list.get(i));
        }
        String out = "";
        for (int j = 0; j < list1.size(); j++) {
            if (j != list1.size() - 1) {
                out = out + list1.get(j) + " ";
            } else {
                out = out + list1.get(j);
            }

        }
        System.out.println(out);
    }

    /**
     * 字符串反转
     */
    public static void strReserve() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        String out = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            out += chars[i];
        }
        System.out.println(out);
    }

    /**
     * 数字颠倒
     */
    public static void numConser() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        char[] chars = (num + "").toCharArray();
        String str = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            str += chars[i];
        }
        System.out.println(str);
    }

    /**
     * 字符个数统计
     */
    public static void statisChar() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Map<Character, Object> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (0 < c && c < 127) {
                map.put(c, 1);
            }
        }
        System.out.println(map.keySet().size());
    }

    /**
     * 提取不重复的整数
     */
    public static void getUniqueInt() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        char[] chars = (num + "").toCharArray();
        String str = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            if (!str.contains(chars[i] + "")) {
                str += chars[i];
            }
        }
        System.out.println(Integer.valueOf(str));
    }

    /**
     * 合并表记录
     */
    public static void mergeTable() {
        Scanner scanner = new Scanner(System.in);
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (scanner.hasNext()) {
            int next = scanner.nextInt();
            for (int i = 0; i < next; i++) {
                int key = scanner.nextInt();
                int value = scanner.nextInt();
                if (treeMap.containsKey(key)) {
                    treeMap.put(key, treeMap.get(key) + value);
                } else {
                    treeMap.put(key, value);
                }
            }
            for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }

    /**
     * 取近似值
     */
    public static void nearNum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println((int) Math.round(scanner.nextDouble()));
    }

    /**
     * 质数因子
     */
    public static void primFactor() {
        Scanner scanner = new Scanner(System.in);
        long num = Long.parseLong(scanner.next());
        getPrime(num);
    }

    /**
     * 获得质数因子
     */
    public static void getPrime(long num) {
        for (int i = 2; i <= num; i++) {
            if (num % i == 0) {
                System.out.print(i + " ");
                getPrime(num / i);
                break;
            }
            if (i == num) {
                System.out.print(i + "");
            }
        }
    }


    /**
     * 进制转换
     */
    public static void baseConver() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            System.out.println(Integer.valueOf(s.substring(2), 16).toString());
        }
    }

    /**
     * 字符串分隔
     */
    public static void strSplit() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            StringBuilder stringBuilder = new StringBuilder();
            String s = scanner.nextLine();
            stringBuilder.append(s);
            int size = stringBuilder.length();
            int add0 = 8 - size % 8;
            if (add0 > 0 && add0 < 8) {
                while (add0 > 0) {
                    stringBuilder.append(0);
                    add0--;
                }
            }

            String str = stringBuilder.toString();
            while (str.length() > 0) {
                System.out.println(str.substring(0, 8));
                str = str.substring(8);     //输出从第八个开始
            }

        }
    }
}

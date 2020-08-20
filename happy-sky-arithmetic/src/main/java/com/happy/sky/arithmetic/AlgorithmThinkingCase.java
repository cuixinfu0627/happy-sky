package com.happy.sky.arithmetic;

/**
 * @name: RealCaseDemo <tb>
 * @title: 16 | 真题案例（一）：算法思维训练 <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/7 15:41 <tb>
 */
public class AlgorithmThinkingCase {
    public static void main(String[] args) {
        // 案例1 递归
        int x = 20;
        System.out.println(fun(x));

        // 案例2 递归二&分查找法
        int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 7;
        System.out.println(bs(arr, target, 0, arr.length-1));

        // 案例3 求解最大公共子串  动态规划
        String a = "13452439";
        String b = "123456";
        getCommenStr(a, b);
    }

    /**
     * @title: 例题 1：斐波那契数列 <tb>
     * @params: a  <tb>
     */
    private static int fun(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return fun(n - 1) + fun(n - 2);
    }

    /**
     * @title: 例题2：判断一个数组中是否存在某个数 <tb>
     * @date: 2020/8/7 15:54<tb>
     */
    private static int bs(int[] arr, int target, int begin, int end) {
        if (begin == end) {
            if (target == arr[begin]){
                return begin;
            }
            else{
                return -1;
            }
        }
        int middle = (begin + end)/2;
        if (target == arr[middle]) {
            return middle;
        }
        if (arr[begin] <= arr[middle-1]){
            if (arr[begin] <= target && target <= arr[middle-1]) {
                return bs(arr,target, begin,middle-1);
            } else {
                return bs(arr,target, middle+1,end);
            }
        } else {
            if (arr[middle+1] <= target && target <= arr[end]) {
                return bs(arr,target, middle+1,end);
            } else {
                return bs(arr,target, begin,middle-1);
            }
        }
    }


    /**
     * @title: 例题3：求解最大公共子串 <tb>
     * @date: 2020/8/7 15:54<tb>
     */
    public static void getCommenStr(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        int[][] m = new int[c2.length+1][c1.length+1];
        for (int i = 1; i <= c2.length; i++) {
            for (int j = 1; j <= c1.length; j++) {
                if (c2[i - 1] == c1[j - 1])
                    m[i][j] = m[i - 1][j - 1] + 1;
            }
        }
        int max = 0;
        int index = 0;
        for (int i = 0; i <= c2.length; i++) {
            for (int j = 0; j <= c1.length; j++) {
                if (m[i][j] > max) {
                    max = m[i][j];
                    index = i;
                }
            }
        }
        String s = "";
        for (int i = index - max; i < index; i++)
            s += b.charAt(i);
        System.out.println(s);
    }

}

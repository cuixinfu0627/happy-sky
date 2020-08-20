package com.happy.sky.arithmetic;

import java.util.Arrays;

/**
 * @name: SortDemo <tb>
 * @title: 排序算法  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/4 10:13 <tb>
 */
public class SortDemo {

    public static void main(String[] args) {
        bubble();
        insertion();
        merging();
        s1_3();
    }

    /**
     * @title: 冒泡排序 <tb>
     * @author: cuixinfu@51play.com <tb>
     * @date: 2020/8/4 10:16<tb>
     */
    public static void bubble(){
        int[] arr = { 1, 0, 3, 4, 5, -6, 7, 8, 9, 10 };
        System.out.println("原始数据: " + Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序: " + Arrays.toString(arr));
    }

    /**
     * @title: 插入排序 <tb>
     * @author: cuixinfu@51play.com <tb>
     * @date: 2020/8/4 10:20<tb>
     */
    public static void insertion(){
        int[] arr = { 2, 3, 5, 1, 23, 6, 78, 34 };
        System.out.println("原始数据: " + Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
        System.out.println("插入排序: " + Arrays.toString(arr));
    }

    /**
     * @title: 归并排序 <tb>
     * @author: cuixinfu@51play.com <tb>
     * @date: 2020/8/4 10:29<tb>
     */
    public static void merging(){
        int[] arr = { 49, 38, 65, 97, 76, 13, 27, 50 };
        int[] tmp = new int[arr.length];
        System.out.println("原始数据: " + Arrays.toString(arr));
        customMergeSort(arr, tmp, 0, arr.length - 1);
        System.out.println("归并排序: " + Arrays.toString(arr));
    }

    public static void customMergeSort(int[] a, int[] tmp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            // 对左侧子序列进行递归排序
            customMergeSort(a, tmp, start, mid);
            // 对右侧子序列进行递归排序
            customMergeSort(a, tmp,mid + 1, end);
            // 合并
            customDoubleMerge(a, tmp, start, mid, end);
        }
    }

    public static void customDoubleMerge(int[] a, int[] tmp, int left, int mid, int right) {
        int p1 = left, p2 = mid + 1, k = left;
        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }
        while (p1 <= mid)
            tmp[k++] = a[p1++];
        while (p2 <= right)
            tmp[k++] = a[p2++];
        // 复制回原素组
        for (int i = left; i <= right; i++)
            a[i] = tmp[i];
    }

    public static void s1_3() {
        int a[] = { 1, 4, 3 };
        int max_val = -1;
        int max_inx = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max_val) {
                max_val = a[i];
                max_inx = i;
            }
        }
        System.out.println(max_val);
    }
}

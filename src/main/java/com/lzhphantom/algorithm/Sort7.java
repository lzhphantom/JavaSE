package com.lzhphantom.algorithm;

/**
 * 常见7大排序方式
 *
 * @author lzhphantom
 * @create 2/20/2023
 */
public class Sort7 {

    /**
     * 直接插入排序
     *
     * 时间复杂度：
     * 最好情况：（有序且为正序）O(n)
     * 最坏情况：（逆序）O(n^2）
     * 平均情况：O(n2)
     *
     * 空间复杂度：O(1)
     * 具有稳定性
     *
     * @param array
     */
    public void insert(int[] array){
        //每拿到一个元素就为它找到合适的位置
        for(int i=0;i<array.length;i++){
            int j=i-1;
            int key=array[i];
            //从后向前找，如果大于等于目前在找的前一个值j，就说明j+1是合适的位置了
            //有序区间[0,i)
            //无序区间[i,array.length)
            for(;j>=0&&array[j]>key;j--){
                //说明需要继续前进，同时将有序区间的值后移
                array[j+1]=array[j];
            }
            //跳出循环，说明>=最后一个值了，直接放在j+1位置即可
            array[j+1]=key;
        }
    }
    public static void shellSort(int[] array){
        int gap=array.length;//10
        //每隔gap分为一组，直到gap=1
        while(gap>1){
            //当gap>1都是分组预排，最后一次gap==1时已经接近整体有序，降低了最坏情况的时间复杂度
            gap=(gap/3)+1;//4，2，1
            //分组插排
            inserWithGap(array,gap);
        }
    }

    /**
     *
     * 希尔排序
     *
     * 时间复杂度：
     * 最好情况（正序）：O（n）
     * 平均情况：O(n1.3–n2)
     * 最坏情况（逆序）：O(n2)
     *
     * 空间复杂度：O(1)
     * 不具有稳定性的排序
     *
     * @param array
     * @param gap
     */
    private static void inserWithGap(int[] array, int gap) {
        for(int i=0;i<array.length;i++){
            int j=i-gap;
            int key=array[i];
            for(;j>=0&&array[j]>key;j-=gap){
                //向后搬移
                array[j+gap]=array[j];
            }
            array[j+gap]=key;
        }
    }

    /**
     * 直接选择排序
     *
     * 时间复杂度：
     *
     * 最好情况：O(n2)
     * 平均情况：O(n2)
     * 最坏情况：O(n2)
     * 空间复杂度：O（1）
     * 稳定性：不稳定
     *
     * @param array
     */
    public static void selectSort(int[] array){
//        //选出无序区间最大的元素，放到有序区间的最后一个
//        for(int i=0;i<array.length;i++){
//            int max=0;
//            //无序区间：[0,array.length-i)
//            //有序区间:[array.length-i,array.length)
//            for(int j=1;j<array.length-i;j++){
//                if(array[j]>array[max]){
//                    max=j;
//                }
//            }
//            swap(array,max,array.length-1-i);
//        }


        //在无序区间选出最小的，放到有序区间的第一个
        for(int i=0;i<array.length;i++){
            int min=i;
            //有序区间[0,i)
            //无序区间[i,array.length)
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[min]){
                    min=j;
                }
            }
            swap(array,min,i);
        }
    }

    private static void swap(int[] array, int max, int i) {
        int t=array[max];
        array[max]=array[i];
        array[i]=t;
    }

    /**
     * 冒泡排序
     *
     * 时间复杂度：
     * 最好情况（正序）：O（n）
     * 最坏情况（逆序）：O(n2)
     * 平均情况：O(n2)
     *
     * 空间复杂度：O(1)
     * 稳定性：稳定
     *
     * @param array
     */
    public static void bubbleSort(int[] array){
        //把最小的冒泡到最前面
        boolean isSort=false;
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[j-1]){
                    swap(array,j,j-1);
                    isSort=true;
                }
            }
            if(!isSort){
                break;
            }
        }

    }

    private static void heapify(int[] array, int size, int index) {
        // 1. 判断 index 是不是叶子
        while (2 * index + 1 < size) {
            // 2. 找到最大的孩子的下标
            int max = 2 * index + 1;
            if (max + 1 < size && array[max + 1] > array[max]) {
                max = 2 * index + 2;
            }

            // 3. 判断最大的孩子和根的值
            if (array[index] < array[max]) {
                swap(array, index, max);

                index = max;
            } else {
                // 4. 根的值比较大，可以直接结束了
                // 不交换，也不继续往下走了
                break;
            }
        }
    }

    private static void createHeap(int[] array) {
        // [从最后一个非叶子结点的下标, 根] 向下调整
        // [(array.length - 2) / 2, 0]

        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            heapify(array, array.length, i);
        }
    }

    /**
     * 堆排序
     * 时间复杂度：
     * 最好情况：O（n*logn）
     * 平均情况：O（n*logn）
     * 最坏情况：O（n*logn）
     *
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     *
     * @param array
     */
    public static void heapSort(int[] array)
    {
        // 建堆   大堆
        createHeap(array);

        // 减治处理
        for (int i = 0; i< array.length; i++) {
            // 无序 [0, length - i - 1]
            // 有序 [length - i, length)
            // 最大的数在 [0], 最大的数应该放到的下标是
            // [length - i  - 1]
            swap(array, 0, array.length - 1 - i);
            // 处理 [0] , 无序剩余部分满足堆的性质
            // 无序 [0, length - i - 2]
            // 有序 [length - i - 1, length)
            // size 剩余无序部分的长度
            heapify(array, array.length - 1 - i, 0);
        }
    }

    /**
     * 快速排序
     *
     * 时间复杂度：
     * 最好情况：O(n*long(n)) （区间分割，组成形式可以考虑为二叉树形式，最好情况就成了单支树，分治算法变成了减治算法）
     * 平均情况：O(n*long(n))
     * 最差情况：O（n2）
     *
     * 空间复杂度：O(log(n))—O（n）
     * 稳定性：不稳定
     *
     * @param array
     */
    public static void quickSort(int[] array){
        quickSortInner(array,0,array.length-1);
    }

    private static void quickSortInner(int[] array, int left, int right) {

        if(left>=right){
            return;
        }

        //1.找基准值，array[right]
        int originIndex=midOfThreeNum(array,left,right);
        //把基准值放到最右
        swap(array,originIndex,right);
        //2.遍历整个区间，把区间分为三部分
        int pirvotIndex = partition(array, left, right);
        //分别对左右两个区间做相同的处理
        quickSortInner(array,left,pirvotIndex-1);
        quickSortInner(array,pirvotIndex+1,right);
        //直到区间中只有一个数或者没有数为止

    }
    private static int partation2(int[] array, int left, int right) {
        //挖坑法：把end下标处当作一个临时的坑，有需要移动值时，借助这个坑交换值，
        // 把坑挪到正确的位置了之后，最后把基准值填入坑
        int begin=left;
        int end=right;
        int pirvot=array[right];
        while (begin<end){
            while (begin<end&&array[begin]<=pirvot){
                begin++;
            }
            //停下来了array[begin]>基准值
            array[end]=array[begin];
            while (begin<end&&array[end]>=pirvot){
                end--;
            }
            //相当于一直在调整基准值的位置
            array[begin]=array[end];
        }
        array[end]=pirvot;
        return begin;
    }
    private static int partation(int[] array, int left, int right) {
        //hover：左边区间向前逼近，不满足条件了就走右边下标，
        //左右都不满足条件了，就说明他们的需求正好相反（该大的小 该小的大）
        // 交换两个值，又必定有一个能继续前进了
        int begin=left;
        int end=right;
        int pirvot=array[right];
        while (begin<end){
            while (begin<end&&array[begin]<=pirvot){
                begin++;
            }
            while (begin<end&&array[end]>=pirvot){
                end--;
            }
            //说明停下了，则交换值
            swap(array,begin,end);
        }
        //退出循环 说明分离完成
        swap(array,begin,right);
        return begin;
    }
    private static int partition(int[] array, int left, int right) {
        //前后下标
        /*
        pivot=array[right]
        i向前遍历，d最终指向的是比基准值大的值，
        i找出比基准值小的值就会把大的交换到后面

        * 前后两个下标，i，d如果array[i]>=pivot{i++}
        *               如果array[i]<pivot{sway(array,i,d);i++;d++}
        * */
        int pivot = array[right];
        int d=left;
        for(int i=left;i<right;i++){
            if (array[i]<pivot){
                swap(array,i,d);
                d++;
            }
        }
        swap(array,right,d);
        return d;
    }
    private static int midOfThreeNum(int[] array, int left, int right) {
        int mid=left+(right-left)/2;
        //      right left
        if(array[left]>array[right]){
            if(array[mid]<array[right]){
                return right;
            }else  if(array[left]<array[mid]){
                return left;
            }
            else {
                return mid;
            }
        }else{
            //left right
            if(array[left]>array[mid]){
                return left;
            }else if(array[mid]>array[right]){
                return right;
            } else {
                return mid;
            }
        }
    }

    /**
     * 归并排序
     *
     * 时间复杂度：
     * 最好情况：O（n*log(n)）
     *
     * 空间复杂度：O（n）
     * 稳定性：稳定
     * 需要做外部排序的情况：
     * 1.内存放不下了，切成小块，将每一小块排序，然后合并n个有序数组
     *
     * @param array
     */
    public static void mergrSort(int[] array){
        int[] extra=new int[array.length];
        mergrSortInner(array,0,array.length,extra);

    }

    private static void mergrSortInner(int[] array, int low, int high, int[] extra) {
        if(low==high-1){
            //区间中只有一个数就不用再分治了
            return;
        }
        if(low>=high){
            //区间中没有数了，不用再分了
            return;
        }
        int mid=low+(high-low)/2;
        mergrSortInner(array,low,mid,extra);
        mergrSortInner(array,mid,high,extra);
        //分治完成后，对两个数组进行归并
        merge(array,low,mid,high,extra);
    }

    private static void merge(int[] array, int low, int mid, int high, int[] extra) {
        int left=low;
        int right=mid;
        int x=0;
        //保证区间中还有值
        while (left<mid&&right<high) {
            if(array[left]<array[right]){
                extra[x++]=array[left++];
            }else{
                extra[x++]=array[right++];
            }
        }
        //若两个数组的长度不相等，有一个数组有剩余
        while (left<mid){
            extra[x++]=extra[left++];
        }
        while (right<high){
            extra[x++]=extra[right++];
        }
        //拷贝回原数组
        for(int i=low;i<high;i++){
            array[i]=extra[i-low];
        }

    }

}

package com.lzhphantom.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzhphantom
 * @create 2/20/2023
 */
public class SearchMethod {

    /**
     * 二分查找
     * @description 也称为折半查找，属于有序查找算法。用给定值value先与中间结点的关键字比较，
     * 中间结点把线形表分成两个子表，若相等，则查找成功；若不相等，再根据K与该中间结点关键字的比较结果确定下一步查找哪个子表，
     * 这样递归进行，直到查找到或查找结束发现表中没有这样的结点。
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while (true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp--;
            }
            resIndexlist.add(mid);
            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while (true){
                if(temp > arr.length - 1 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp++;
            }
            return resIndexlist;
        }
    }


    /**
     *
     * 插值查找
     *
     * 基于二分查找算法，将查找点的选择改进为自适应选择，可以提升查找算法的平均性能比折半查找要好的多。
     *
     * 如果数组中分布不均匀，那么插值查找未必是很合适的选择。
     * 插值查找算法和二分查找算法对比
     * （1）对于数据量较大，数值分布比较均匀的数组来说，使用插值查找算法，速度更快一些；
     *
     * （2）但是对于数值分布不均匀的数组来说，建议使用二分查找算法；
     *
     * 在二分查找中：
     *
     * mid=(left+right)/2；
     *
     * 在插值查找中：
     *
     * mid=left + (right - left)*(findVal - arr[left])/(arr[right]-arr[left])；
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal){
        if(left>right || findVal<arr[left] || findVal>arr[right]){
            return -1;
        }

        int mid = left + (right - left)*(findVal - arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if(findVal>midVal){
            return insertValueSearch(arr,mid + 1,right,findVal);
        }else if(findVal>midVal){
            return insertValueSearch(arr,left,mid - 1,findVal);
        }else {
            return mid;
        }
    }


    //因为后面我们mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fibonacci(){
        int[] fibonacci = new int[20];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < fibonacci.length - 1; i++) {
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        return fibonacci;
    }

    /**
     * 斐波那契查找算法
     *
     * 斐波那契的性能对比优于二分查找，实验数据表明，斐波那契查找算法大约较二分查找算法快17%，
     * 但是这个归功于一个原因，斐波那契查找算法在分割时只有加、减运算，而没有乘法运算。
     *
     * 这样虽然运算简化了，实则复杂化了，看代码就知道了。
     *
     * @param arr
     * @param findVal
     * @return
     */
    public static int fibonacciSearch(int[] arr,int findVal){
        int left = 0;
        int right = arr.length - 1;
        int f = 0;//表示斐波那契分割数值的下标
        int mid = 0;
        int[] fibonacci = fibonacci();
        //获取到斐波那契分割数值的下标
        while (right > fibonacci[f] - 1){
            f++;
        }

        //因为 f[f] 值 可能大于 arr 的 长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr,fibonacci[f]);
        //实际上需求使用arr数组最后的数填充 temp
        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        // 使用while来循环处理，找到我们的数 findVal
        while (left <= right) {
            //fibonacci寻找mid固定写法
            mid = left + fibonacci[f - 1] - 1;
            if (findVal < temp[mid]) { //我们应该继续向数组的前面查找(左边)
                right = mid - 1;
                //为甚是 f--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. fibonacci[f] = fibonacci[f-1] + fibonacci[f-2]
                //因为 前面有 fibonacci[f-1]个元素,所以可以继续拆分 fibonacci[f-1] = fibonacci[f-2] + fibonacci[f-3]
                //即 在 fibonacci[f-1] 的前面继续查找 f--
                //即下次循环 mid = fibonacci[f-1-1]-1
                f--;
            } else if (findVal > temp[mid]) { // 我们应该继续向数组的后面查找(右边)
                left = mid + 1;
                //为什么是f -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. fibonacci[f] = fibonacci[f-1] + fibonacci[f-2]
                //3. 因为后面我们有f[f-2] 所以可以继续拆分 fibonacci[f-2] = fibonacci[f-3] + fibonacci[f-4]
                //4. 即在fibonacci[f-2] 的前面进行查找 f -=2
                //5. 即下次循环 mid = fibonacci[f - 1 - 2] - 1
                f -= 2;
            }else { //找到
                //需要确定，返回的是哪个下标
                if(mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }

}

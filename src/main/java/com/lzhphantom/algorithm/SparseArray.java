package sg.com.ncs.luozhihui.algorithm;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 当一个数组中部分元素是0，或者为同一个值的数组，可以使用稀疏数组来保存该数据。
 * <p>
 * 稀疏数组的处理方法：
 * <p>
 * 记录数组一共有几行几列，有多少个不同的值；
 * 把具有不同值的元素的行列和值记录在一个小规模的数组中，从而缩小程序的规模。
 *
 * @author lzhphantom
 * @create 2/17/2023
 */
public class SparseArray {
    public static void sparseArrayToIO(int[][] sparseArr) throws Exception {
        System.out.println("将稀疏数组存入磁盘");
        File file = new File("D:/sparseArr.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        for (int[] ints : sparseArr) {
            for (int j = 0; j < 3; j++) {
                writer.write(ints[j]);
            }
        }
        writer.flush();
        writer.close();
    }

    //从磁盘中读取稀疏数组
    public static int[][] sparseArrFromIO(int lines) throws Exception {
        System.out.println("从磁盘中读取稀疏数组");
        int[][] sparseArray = new int[lines][3];
        try(FileReader reader = new FileReader("D:/sparseArr.txt")){
            int getNum = 0;
            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < 3; j++) {
                    getNum = reader.read();
                    sparseArray[i][j] = getNum;
                }
            }
        }
        return sparseArray;
    }

    public static void ArrSparseArrTransfer() {
        //先创建一个原始的二维数组
        //0表示没有棋子，1表示黑子，2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组转稀疏数组的思路
        //1、先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2、创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //3、稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0;//count 用来记录第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println("稀疏数组");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        int[][] sparseArr2 = new int[sum + 1][3];
        try {

            sparseArrayToIO(sparseArr);
            sparseArr2 = sparseArrFromIO(3);
            for (int[] row : sparseArr2) {
                for (int data : row) {
                    System.out.printf("%d\t", data);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //稀疏数组恢复成原始的二维数组
        //1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        //2、再读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组即可
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }


        //恢复后的二维数组
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrSparseArrTransfer();
    }

}

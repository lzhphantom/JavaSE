package sg.com.ncs.luozhihui.instance;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * FileUtils StringUtils CollectionUtils ArrayUtils
 *
 * @author lzhphantom
 * @create 2/15/2023
 */
public class UtilsTest {
    private static void fileUtilsTest() throws IOException, ParseException {
        //读取文件内容
        String readFileToString = FileUtils.readFileToString(new File("D:\\guor\\data\\test20211022000000.txt"));
        System.out.println(readFileToString);
        //删除文件夹
        FileUtils.deleteDirectory(new File("D:\\guor1\\data1"));
        FileUtils.deleteQuietly(new File("D:\\guor\\data"));
        //清空文件夹
        FileUtils.cleanDirectory(new File("D:\\guor\\data"));
        //判断文件内容是否一致
        boolean contentEquals = FileUtils.contentEquals(new File("D:\\guor\\data\\test20211022000000.txt"), new File("D:\\guor\\data\\testNew20211021000000.txt"));
        boolean contentEquals2 = FileUtils.contentEquals(new File("D:\\guor\\data\\中文20211021100000.txt"), new File("D:\\guor\\data\\中文New20211022100000.txt"));
        System.out.println(contentEquals + "," + contentEquals2);
        //拷贝特定类型的文件
        FileUtils.copyDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data2"), pathname -> pathname.getName().startsWith("test"));
        FileUtils.moveDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data3"));
        FileUtils.moveFileToDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data3"), true);
        FileUtils.moveToDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data4"), true);
        boolean directoryContains = FileUtils.directoryContains(new File("D:\\guor\\data"), new File("D:\\guor\\data\\test20211022000000.txt"));
        System.out.println(directoryContains);
        directoryContains = FileUtils.directoryContains(new File("D:\\guor\\data"), new File("*.txt"));
        System.out.println(directoryContains);
        //获取某文件夹下特定格式文件
        File[] listFiles = new File("D:\\guor\\data").listFiles((dir, name) -> name.startsWith("test"));

        System.out.println(Arrays.toString(listFiles));
        //获取系统temp文件夹路径
        File tempDirectory = FileUtils.getTempDirectory();
        System.out.println(tempDirectory);
        //获取系统用户文件夹路径
        File userDirectory = FileUtils.getUserDirectory();
        System.out.println(userDirectory);

        //查看是否是新建的文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = sdf.parse("20210206134900");
        boolean fileNewer = FileUtils.isFileNewer(new File("D:\\guor\\data"), parse);
        System.out.println(fileNewer);
        //更新文件修改时间，如果不存在，则新建；
        FileUtils.touch(new File("D:\\guor\\data\\test20211022000000.txt"));
        //延迟查看文件是否存在
        boolean waitFor = FileUtils.waitFor(new File("D:\\guor\\data\\testNew20211021000000.txt"), 5);
        System.out.println(waitFor);
    }

    //根据文件修改时间排序
    public static void test02() {
        String dir = "D:\\data";
        File[] listFiles = new File(dir).listFiles();
        List<File> asList = Arrays.asList(listFiles);
        for (File file : listFiles) {
            System.out.println(file);
        }
        asList.sort(Comparator.comparingLong(File::lastModified));
        System.out.println("sort...");
        for (File file : listFiles) {
            System.out.println(file + "," + file.lastModified());
        }
    }

    // 根据文件修改时间排序
    public static File[] orderByDate(File[] files) {
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        return files;
    }

    private static void stringUtilsTest() {
        String str = "hello world";
        //将第一个字符大写
        String capitalize = StringUtils.capitalize(str);
        System.out.println(capitalize);//Hello
        //两边空格补齐
        String center = StringUtils.center(str, 7);
        System.out.println(center);// hello
        String center1 = StringUtils.center(str, 7, "*");
        System.out.println(center1);//*hello*
        //chomp剔除字符串最后一个是\n或\r\或\r\n
        String chomp = StringUtils.chomp(str);
        System.out.println(chomp);
        //chop剔除字符串最后一个字符（如果最后一个是\n或\r\或\r\n也剔除掉）
        String chop = StringUtils.chop(str);
        System.out.println(chop);//hello worl
        //是否包含任意字符
        boolean containsAny = StringUtils.containsAny(str, "W");
        System.out.println(containsAny);
        //是否包含任意字符，忽略大小写
        containsAny = StringUtils.containsIgnoreCase(str, "W");
        System.out.println(containsAny);

        //有意思
        int indexOfAnyBut = StringUtils.indexOfAnyBut(str, "hello orld");
        System.out.println(indexOfAnyBut);

        //两个字符串从第几位开始不同
        int indexOfDifference = StringUtils.indexOfDifference(str, str);
        System.out.println(indexOfDifference);
        indexOfDifference = StringUtils.indexOfDifference(str, str + "zs");
        System.out.println(indexOfDifference);
        indexOfDifference = StringUtils.indexOfDifference(str, "he llo");
        System.out.println(indexOfDifference);

        //判断是否是正整数
        boolean isNumeric = StringUtils.isNumeric(str);
        System.out.println(isNumeric);//false
        isNumeric = StringUtils.isNumeric("1234dd");
        System.out.println("1234dd:" + isNumeric);//false
        isNumeric = StringUtils.isNumeric("1234 ");
        System.out.println("1234 :" + isNumeric);//false
        isNumeric = StringUtils.isNumeric("1.234");
        System.out.println("1.234:" + isNumeric);//false
        isNumeric = StringUtils.isNumeric("1234");
        System.out.println("1234:" + isNumeric);//true
        isNumeric = StringUtils.isNumeric("-1234");
        System.out.println("-1234:" + isNumeric);//false
        //判断是否是正整数或空格
        boolean isNumeric1 = StringUtils.isNumericSpace(str);

        //将数组变为字符串，并添加字符进行分割
        Object[] array = new Object[]{"zs", 18, "男", 666, 777};
        String join = StringUtils.join(array);
        System.out.println(Arrays.toString(array));
        join = StringUtils.join(array, "*");
        System.out.println(join);
        Object[] array1 = new Object[]{"zs", 18, "男", 666, 777};
        String join1 = StringUtils.join(array1, ";", 2, 5);
        System.out.println(join);

        String remove = StringUtils.remove(str, "hello");
        System.out.println(remove);// world
        String repeat = StringUtils.repeat(str, 3);
        System.out.println(repeat);//hello worldhello worldhello world
        repeat = StringUtils.repeat(str, ",", 3);
        System.out.println(repeat);//hello world,hello world,hello world

        //分别替换
        String[] list = new String[]{"hello", "girl"};
        String[] replacementList = new String[]{"say", "woman"};
        String str2 = "hello world,hello boy,hello girl.";
        String replaceEach = StringUtils.replaceEach(str2, list, replacementList);
        System.out.println(replaceEach);//say world,say boy,say woman.

        //只替换第一个匹配的字符串
        String replaceOnce = StringUtils.replaceOnce("hello world,hello boy,hello girl.", "hello", "say");
        System.out.println(replaceOnce);//say world,hello boy,hello girl.

        //截取某字符后面的部分
        String substringAfterLast = StringUtils.substringAfterLast("hello world,hello boy,hello girl.", " ");
        System.out.println(substringAfterLast);//girl.

        String substringBeforeLast = StringUtils.substringBeforeLast("hello world,hello boy,hello girl.", " ");
        System.out.println(substringBeforeLast);//hello world,hello boy,hello
    }

    private static void collectionUtilsTest() {
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("dalian");
        list.add("gateway");
        list.add("gateway");
        list.add("28");
        list.add("35");
        //查找第一个符合条件的元素
        Object find = CollectionUtils.find(list, arg0 -> !StringUtils.isNumeric(arg0.toString()));

        //排除不符合条件的元素
        Collection select = CollectionUtils.select(list, arg0 -> !StringUtils.isNumeric(arg0.toString()));
        System.out.println(list);//[zhangsan, dalian, 28]
        System.out.println(find);//zhangsan
        System.out.println(select);//[zhangsan, dalian]


        List<String> list2 = new ArrayList<>();
        list2.add("zhangsan");
        list2.add("dalian2");
        list2.add("282");
        //查看两个集合中是否有相同的元素
        boolean containsAny = CollectionUtils.containsAny(list, list2);
        System.out.println(containsAny);//true

        //查找集合中某元素的个数
        int countMatches = CollectionUtils.countMatches(list, arg0 -> StringUtils.isNumeric(arg0.toString()));
        System.out.println(countMatches);

        //查找集合中是否存在某元素
        boolean exists = CollectionUtils.exists(list, arg0 -> StringUtils.isNumeric(arg0.toString()));

        System.out.println(exists);

        //删除集合中的某些元素
        System.out.println(list);//[zhangsan, dalian, 打法, 28, 35]
        CollectionUtils.filter(list, arg0 -> !StringUtils.isNumeric(arg0.toString()));

        System.out.println(list);//[zhangsan, dalian, 打法]

        //查找集合中符合条件的第一个元素
        find = CollectionUtils.find(list, arg0 -> !StringUtils.isNumeric(arg0.toString()));
        System.out.println(find);//zhangsan

        //闭包回调函数
        System.out.println(list);
        CollectionUtils.forAllDo(list, arg0 -> {
            if (StringUtils.isNumeric(arg0.toString())) {
                work(arg0);
            }
        });
        System.out.println(list);

        //查找集合中元素的个数
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]
        Map cardinalityMap = CollectionUtils.getCardinalityMap(list);
        System.out.println(cardinalityMap);//{35=1, 28=1, zhangsan=1, dalian=1, gateway=2}

        //查找两个集合中的相同元素
        list2 = new ArrayList<>();
        list2.add("lisi");
        list2.add("beijing");
        list2.add("gateway");
        list.add("19");
        Collection intersection = CollectionUtils.intersection(list, list2);
        System.out.println(intersection);

        //获取两个List<Map<String, Object>>中相同的部分，可以用于对比两个库中的相同表数据是否相等
        List<Map<String, Object>> list3 = new ArrayList<>();
        List<Map<String, Object>> list4 = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "zhangsan");
        map1.put("age", 18);
        map1.put("address", "dalian");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "张三");
        map2.put("age", "十八");
        map2.put("address", "大连");
        list3.add(map1);
        list3.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "李四");
        map3.put("age", "十八");
        map3.put("address", "大连");
        list4.add(map2);
        list4.add(map1);
        list3.add(map3);
        System.out.println(list3);
        System.out.println(list4);
        intersection = CollectionUtils.intersection(list3, list4);
        System.out.println(intersection);//[{address=dalian, name=zhangsan, age=18}]

        //判断两个集合是否相同
        boolean equalCollection = CollectionUtils.isEqualCollection(list3, list4);
        System.out.println(equalCollection);

        //判断param2是否包含param1，且不能完全相同
        System.out.println(list3);//[{address=dalian, name=zhangsan, age=18}, {address=大连, name=张三, age=十八}, {address=大连, name=李四, age=十八}]
        System.out.println(list4);//[{address=大连, name=张三, age=十八}, {address=dalian, name=zhangsan, age=18}]
        boolean properSubCollection = CollectionUtils.isProperSubCollection(list4, list3);
        System.out.println(properSubCollection);//true

        //断定某集合是否含有某元素，如果包含则抛异常（我觉得应该少用为妙）
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]
        Collection predicatedCollection = CollectionUtils.predicatedCollection(list, object -> !StringUtils.isNumeric(object.toString()));
        System.out.println(predicatedCollection);//Exception in thread "main" java.lang.IllegalArgumentException: Cannot add Object '28' - Predicate rejected it
        list2 = new ArrayList<>();
        list2.add("gateway");
        System.out.println(list);
        System.out.println(list2);
        //删除list1中的list2
        Collection removeAll = CollectionUtils.removeAll(list, list2);
        System.out.println(removeAll);

        //反转数组
        String[] array = new String[]{"zs", "ls", "ww"};
        System.out.println(StringUtils.join(array, ","));//zs,ls,ww
        CollectionUtils.reverseArray(array);
        System.out.println(StringUtils.join(array, ","));//ww,ls,zs

        //删除list1中的list2
        list2 = new ArrayList<>();
        list2.add("zhangsan");
        list2.add("dalian");
        Collection subtract = CollectionUtils.subtract(list, list2);
        System.out.println(subtract);

        //更改集合中的特定值
        Collection collect = CollectionUtils.collect(list, arg0 -> {
            if (StringUtils.isNumeric(arg0.toString())) {
                return Integer.valueOf(15);
            }
            return arg0;
        });
        System.out.println(list);//[zhangsan, dalian, 28, 35]
        System.out.println(collect);//[zhangsan, dalian, 15, 15]

        //更改集合中的特定值，改变集合本身
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]
        CollectionUtils.transform(list, input -> {
            if (StringUtils.isNumeric(input.toString())) {
                return Integer.valueOf(15);
            }
            return input;
        });
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 15, 15]
        list2 = new ArrayList<>();
        list2.add("docker");
        list2.add("zhangsan");
        System.out.println(list);
        System.out.println(list2);
        //将list和list2中的元素合并，但去除list2中的重复元素
        Collection union = CollectionUtils.union(list, list2);
        System.out.println(union);
    }

    private static void work(Object obj) {
        System.out.println("闭包回调函数:我是数字，" + obj);
    }

    private static void arrayUtilsTest() {
        String[] array = new String[]{"zs", "ls"};

        //增加元素
        Object[] add = ArrayUtils.add(array, "ww");
        System.out.println(StringUtils.join(add, ","));//zs,ls,ww

        String[] array1 = new String[]{"zs", "ls"};
        //合并数组
        String[] array2 = new String[]{"ww", "ss"};
        Object[] addAll = ArrayUtils.addAll(array1, array2);
        System.out.println(StringUtils.join(addAll, ","));//zs,ls,ww,ss

        //克隆数组
        Object[] clone = ArrayUtils.clone(array);
        System.out.println(StringUtils.join(clone, ","));//zs,ls


        boolean contains = ArrayUtils.contains(array, "ww");
        boolean contains1 = ArrayUtils.contains(array, "zs");
        System.out.println(contains + "," + contains1);//false,true
    }
}

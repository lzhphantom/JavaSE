package com.lzhphantom.design.proxy;

/**
 * @author lzhphantom
 * @create 2/22/2023
 */
public class Movie implements IMovie{
    @Override
    public void play(String movieName) {
        System.out.println("您正在观看电影《"+movieName+"》");
    }

    @Override
    public void advertising(Boolean isBoforMovie, String txt) {
        if(isBoforMovie){
            System.out.println("影片马上开始,"+txt);
        }else{
            System.out.println("影片正片已经结束,马上彩蛋环节,不要离开哦,"+txt);
        }
    }
}

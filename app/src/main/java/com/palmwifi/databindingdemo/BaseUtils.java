package com.palmwifi.databindingdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import java.io.InputStream;

/**
 * Created by Kiki on 2017/5/17.
 */

public class BaseUtils {
    public static BaseUtils.Rect screenRect = null;


    /**
     * px转换成dip工具
     */
    public static float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }

    /**
     * dip转px工具
     *
     * @param context  上下文
     * @param dipValue DIP大小
     * @return px值
     */
    public static float dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }

    /**
     * 根据默认图片设置列表图片大小
     *
     * @param activity           : 页面对象
     * @param defaultImgResource : 默认图片
     * @param scale              : 图片宽度相对于屏幕宽度的比例
     */
    public static Rect calculateLeftRect(Activity activity, int defaultImgResource, float scale) {
        //获取默认图的大小
        InputStream is = activity.getResources().openRawResource(defaultImgResource);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        int displayWidth = getScreen(activity).getWidth();
        //设定图片宽高
        float scaleWidth = scale * displayWidth;
        float scaleHeight = (height / width) * scaleWidth;
        Rect rect = new Rect((int) scaleWidth, (int) scaleHeight);
        return rect;
    }

    /***
     * 获取
     *
     * @param scale  比例
     * @param margin 边距
     * @param count  图片个数
     * @return
     */
    public static Rect calculateImgSize(float scale, int margin, int count, Activity activity) {
        if (screenRect == null) {
            screenRect = getScreen(activity);
        }
        int width = (screenRect.getWidth() - margin * (count + 1)) / count;
        Rect rect = new Rect(width, (int) (width / scale));
        return rect;
    }


    public static Rect calculateImgSize(float scale, int width) {
        Rect rect = new Rect(width, (int) (width / scale));
        return rect;
    }


    /**
     * 根据左侧布局获得右边布局大小
     *
     * @param activity  : 页面对象
     * @param leftRect  : 左侧布局矩形
     * @param widthLess : 屏幕宽度-左边布局宽度-?=宽度
     * @param height    : 右边布局高度
     */
    public static Rect calculateRightRectByLeftRect(Activity activity, Rect leftRect, int widthLess, int height) {
        int displayWidth = getScreen(activity).getWidth();
        Rect rect = new Rect((displayWidth - leftRect.getWidth() - (int) dip2px(activity, widthLess)), height);
        return rect;
    }

    /**
     * 获取分辨率
     */
    public static Rect getScreen(Activity activity) {
        if (screenRect == null) {
            int displayWidth = 0, displayHeight = 0;
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            displayWidth = dm.widthPixels;
            displayHeight = dm.heightPixels;
            screenRect = new Rect(displayWidth, displayHeight);
        }
        return screenRect;
    }

    /**
     * 一行排列按比例计算宽度与高度
     *
     * @param activity    : 页面对象
     * @param linePicNums : 一行有多少张图片
     * @param margin      : 有多少间距
     * @param defaultpic  : 默认图片
     */
    public static Rect calculateByBili(Activity activity, int linePicNums, int margin, int defaultpic) {
        Rect sr = getScreen(activity);
        int marginpx = (int) dip2px(activity, margin);
        //获取默认图的大小
        InputStream is = activity.getResources().openRawResource(defaultpic);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        //设定图片宽高
        float scaleWidth = (float) (sr.getWidth() - marginpx) / linePicNums;
        float scaleHeight = (height / width) * scaleWidth;
        Rect rect = new Rect((int) scaleWidth, (int) scaleHeight);
        return rect;
    }


    /**
     * 矩形(长.宽)
     */
    public static class Rect {
        private int width;
        private int height;

        public Rect() {
        }

        public Rect(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}

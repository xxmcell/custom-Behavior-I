package com.it.jason.circleimageview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jason on 2017/7/5.
 */

public class circleBehavior extends CoordinatorLayout.Behavior<ImageView>{
    public circleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        if(dependency.getId()==R.id.toolbar){
            return true;
        }
        return false;
    }
    //定好,到最后图片的大小
    private float finalCircleSize=100;
    //图片本来的尺寸
    private float originalCircleSize;
    private float startCircleX;
    private float startCircleY;
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        if(startCircleX==0){
            //当开始的坐标等于0时,就是控件的坐标
            startCircleX=child.getX();
        }
        if(startCircleY==0){
            //在y轴上,开始的坐标是被观察者的y轴位置
            startCircleY=dependency.getY();
        }
        if(originalCircleSize==0){
            //子控件的尺寸
            originalCircleSize=child.getHeight();
        }
        //观察者和被观察者的y轴一样
        child.setY(dependency.getY());
        //计算被观察者的移动百分比
        float percent=dependency.getY()/startCircleY;
        //x轴和y轴移动的百分比是一样的,实时x坐标=起始点x坐标-已经移动的x距离
        float x= startCircleX-(startCircleX-(dependency.getWidth()/2-finalCircleSize/2)*(1-percent));
        child.setX(x);
        CoordinatorLayout.LayoutParams params= (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        params.width= (int) (finalCircleSize+(originalCircleSize-finalCircleSize)*percent);
        params.height=(int) (finalCircleSize+(originalCircleSize-finalCircleSize)*percent);
        child.setLayoutParams(params);
        return true;
    }
}

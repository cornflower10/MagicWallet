package com.qingmang.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qingmang.R;
import com.qingmang.baselibrary.utils.SizeUtils;

/**
 * Created by xiejingbao on 2018/4/19.
 */

public class LevelView extends LinearLayout {
    private int count ;

    private int resDrawable;
    private int defultRes;

    public LevelView(Context context) {
        this(context,null);
    }

    public LevelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LevelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LevelView);

        count = typedArray.getInt(R.styleable.LevelView_leavelCount,5);
        resDrawable = typedArray.getResourceId(R.styleable.LevelView_image,R.drawable.ic_mood_red_24dp);
        defultRes = typedArray.getResourceId(R.styleable.LevelView_image_choose,R.drawable.ic_mood_black_24dp);
       int margin = SizeUtils.dp2px(4,context);
        setOrientation(HORIZONTAL);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(context);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(margin,0,0,0);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundResource(defultRes);
            addView(imageView);
        }


    }

    public void setLevel(int level){

      if(level>getChildCount()){
          level = getChildCount();
      }
        for (int i = 0; i < level; i++) {
           ImageView imageView = (ImageView) getChildAt(i);
            imageView.setBackgroundResource(resDrawable);
        }
        invalidate();

    }
}

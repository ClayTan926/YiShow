package cn.tzl.yishow.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Tanzl on 2018/3/13.
 */

public class ToolbarAnimation {

    /**
     * 隐藏toolbar
     */
    public static void hideToolbar(final Toolbar toolbar){

        Animator animator=ObjectAnimator.ofFloat(toolbar, View.TRANSLATION_Y,0,-toolbar.getHeight());
        animator.setDuration(350);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                toolbar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    /**
     * 显示toolbar
     */
    public static void showToolbar(final Toolbar toolbar){
        ObjectAnimator animator=ObjectAnimator.ofFloat(toolbar, View.TRANSLATION_Y,-toolbar.getHeight(),0);
        animator.setDuration(350);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                toolbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }


}

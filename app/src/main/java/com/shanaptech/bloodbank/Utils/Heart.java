package com.shanaptech.bloodbank.Utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class Heart {

    ImageView likeImage , dislikeImage ;


    DecelerateInterpolator decelerate_Interpolator = new DecelerateInterpolator();
    AccelerateInterpolator accelerate_Interpolator = new AccelerateInterpolator();


    public Heart(ImageView likeImage, ImageView dislikeImage) {
        this.likeImage = likeImage;
        this.dislikeImage = dislikeImage;
    }

    public void liek()
    {
        AnimatorSet anime = new AnimatorSet();

        if(likeImage.getVisibility() == View.VISIBLE)
        {
            likeImage.setScaleX(0.1f);
            likeImage.setScaleY(0.1f);

            ObjectAnimator animator = ObjectAnimator.ofFloat(likeImage , "scaleY" , 1f , 0f);
            animator.setDuration(200);
            animator.setInterpolator(accelerate_Interpolator);



            ObjectAnimator animator2 = ObjectAnimator.ofFloat(likeImage , "scaleX" , 1f , 0f);
            animator2.setDuration(200);
            animator2.setInterpolator(accelerate_Interpolator);

            likeImage.setVisibility(View.GONE);
            dislikeImage.setVisibility(View.VISIBLE);


            anime.playTogether(animator , animator2);
        }
        else if(likeImage.getVisibility() == View.GONE)
        {
            likeImage.setScaleX(0.1f);
            likeImage.setScaleY(0.1f);

            ObjectAnimator animator = ObjectAnimator.ofFloat(likeImage , "scaleY" , 0.1f , 1f);
            animator.setDuration(200);
            animator.setInterpolator(decelerate_Interpolator);

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(likeImage , "scaleX" , 0.1f , 1f);
            animator2.setDuration(200);
            animator2.setInterpolator(decelerate_Interpolator);

            likeImage.setVisibility(View.VISIBLE);
            dislikeImage.setVisibility(View.GONE);


            anime.playTogether(animator , animator2);
        }
        anime.start();
    }


}

package site.petrumugurel.animations;

import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mugurel on 11-Feb-16.
 * Kanged from https://github.com/GeMoschen/Stundenrechner/blob/master/src/de/gemo/stunden/units/OnSwipeTouchListener.java, By GeMoschen
 */
public class CoolAnimatedEffects  {
    public enum DIRECTIONS { RIGHT, LEFT }

    private final ImageView mWorkedOnView;
    private int mTranslationX = 2000;

    private long mDownSwipeStartTime = System.nanoTime();
    private long mUpSwipeStartTime   = System.nanoTime();

    private int mSwypeDownCounter;
    private int mSwypeUpCounter;

    public CoolAnimatedEffects(View view) {
        mWorkedOnView = (ImageView) view;
    }

    public void rotateOnXInPlace(int swipesNumber, int swipesBlockSeconds,
                                 float degrees, int duration) {
        if (TimeUnit.SECONDS.convert(System.nanoTime() - mDownSwipeStartTime,
                                     TimeUnit.NANOSECONDS) > swipesBlockSeconds) {
            mDownSwipeStartTime = System.nanoTime();
            mSwypeUpCounter = mSwypeDownCounter = 0;
        }
        else if (++mSwypeDownCounter == swipesNumber) {
            mSwypeUpCounter = mSwypeDownCounter = 0;
            mWorkedOnView.animate().rotationXBy(degrees).setDuration(duration);
        }
        else if (++mSwypeUpCounter == swipesNumber) {
            mSwypeUpCounter = mSwypeDownCounter = 0;
            mWorkedOnView.animate().rotationXBy(degrees).setDuration(duration);
        }
    }

    public void fadeInRotatingImage(ImageView nextImage, final DIRECTIONS fadeInFrom,
                                    float rotationDegrees, int durationIn, int durationOut) {

        if (fadeInFrom == DIRECTIONS.RIGHT) {
            mTranslationX = -mTranslationX;
        }

        nextImage.setVisibility(View.VISIBLE);
        nextImage.setTranslationX(mTranslationX);
        nextImage.animate().alpha(0);
        nextImage.setRotation(0);
        nextImage.setScaleX(0);
        nextImage.setScaleY(0);

        nextImage.animate().alpha(1).translationXBy(-mTranslationX).rotation(rotationDegrees)
                     .scaleX(1).scaleY(1).setDuration(durationIn);

        mWorkedOnView.animate().alpha(0).setDuration(durationOut);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (fadeInFrom == DIRECTIONS.RIGHT) {
                    mWorkedOnView.animate().translationX(mTranslationX).scaleY(0).scaleX(0);
                }
                else {
                    mWorkedOnView.animate().translationX(-mTranslationX).scaleY(0).scaleX(0);
                }
            }
        }, durationOut/2);


        // restore the original value of mTranslationX
        if (fadeInFrom == DIRECTIONS.RIGHT) {
            mTranslationX = -mTranslationX;
        }
    }


    public void fadeInImage(ImageView nextImage, final DIRECTIONS fadeInFrom,
                            int durationIn, final int durationOut) {

        if (fadeInFrom == DIRECTIONS.RIGHT) {
            mTranslationX = -mTranslationX;
        }


        nextImage.setVisibility(View.VISIBLE);
        nextImage.setTranslationX(mTranslationX);
        nextImage.setRotationX(0);
        nextImage.setScaleX(0);
        nextImage.setScaleY(0);
        nextImage.animate().alpha(0).setDuration(1);


        nextImage.animate().alpha(1).translationXBy(-mTranslationX)
                     .scaleX(1).scaleY(1).setDuration(durationIn);

        mWorkedOnView.animate().alpha(0).setDuration(durationOut);

        // restore the original value of mTranslationX
        if (fadeInFrom == DIRECTIONS.RIGHT) {
            mTranslationX = -mTranslationX;
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (fadeInFrom == DIRECTIONS.RIGHT) {
                    mWorkedOnView.animate().translationX(mTranslationX).scaleY(0).scaleX(0);
                }
                else {
                    mWorkedOnView.animate().translationX(-mTranslationX).scaleY(0).scaleX(0);
                }
            }
        }, durationOut/2);
    }
}
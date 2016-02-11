package site.petrumugurel.animations;

import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mugurel on 11-Feb-16.
 * Kanged from https://github.com/GeMoschen/Stundenrechner/blob/master/src/de/gemo/stunden/units/OnSwipeTouchListener.java, By GeMoschen
 */
public class CoolAnimatedEffects  {
    private final View    mWorkedOnView;

    private long mDownSwipeStartTime = System.nanoTime();
    private long mUpSwipeStartTime = System.nanoTime();

    private int mSwypeDownCounter;
    private int mSwypeUpCounter;

    public CoolAnimatedEffects(View view) {
        mWorkedOnView = view;
    }

    public void rotateOnXInPlace(float degrees, int duration) {
        if (TimeUnit.SECONDS.convert(System.nanoTime() - mDownSwipeStartTime,
                                     TimeUnit.NANOSECONDS) > 5) {
            mDownSwipeStartTime = System.nanoTime();
            mSwypeDownCounter = 0;
        }
        else {
            if (++mSwypeDownCounter == 4) {
                mSwypeDownCounter = 0;
                mWorkedOnView.animate().rotationXBy(degrees).setDuration(duration);
            }
        }
    }

    public void fadeInRotatingImage(ImageView nextImage, ImageView previousImage,
                                    float rotationDegrees, int durationIn, int durationOut) {
        nextImage.setTranslationX(-2000);
        nextImage.animate().alpha(0);
        nextImage.setRotation(0);
        nextImage.setScaleX(0);
        nextImage.setScaleY(0);

        nextImage.animate().alpha(1).translationXBy(2000).rotation(rotationDegrees)
                 .scaleX(1).scaleY(1).setDuration(durationIn);

        previousImage.animate().alpha(0).setDuration(durationOut);
    }
}
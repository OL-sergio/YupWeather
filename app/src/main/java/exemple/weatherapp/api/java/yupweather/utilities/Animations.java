package exemple.weatherapp.api.java.yupweather.utilities;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

public class Animations {

    public Animation getMovementAnimation() {
        Animation animationsSlideMovement = new TranslateAnimation(100,0,0,0);
        animationsSlideMovement.setDuration(3000);
        animationsSlideMovement.setFillAfter(true);
        animationsSlideMovement.setInterpolator( new LinearInterpolator());
        return animationsSlideMovement;
    }

    public RotateAnimation getRotateAnim () {
        RotateAnimation animIconRotation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animIconRotation.setDuration(15000);
        animIconRotation.setInterpolator(new LinearInterpolator());
        return animIconRotation;
    }

    public Animation getAnimFade() {
        Animation animIconFade = new AlphaAnimation(0.0f, 1.0f);
        animIconFade.setDuration(3000);
        animIconFade.setStartOffset(500);
        animIconFade.setInterpolator(new LinearInterpolator());
        return animIconFade;
    }

}

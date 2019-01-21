package com.example.keyframeanimations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private ConstraintSet constraintSetStart;
    private ConstraintSet constraintSetHalf;
    private ConstraintSet constraintSetEnd;

    private ConstraintLayout mainContainer;

    // Animation Transitions
    private ChangeBounds changeBounds;

    private int animationNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_animation_start);

        mainContainer = findViewById(R.id.main_container);

        changeBounds = new ChangeBounds();

        changeBounds.setInterpolator(
//                new AnticipateInterpolator(10.0f)
//                new DecelerateInterpolator(10.0f)
                new AnticipateOvershootInterpolator(1.0f)
        );

        constraintSetStart = new ConstraintSet();
        constraintSetStart.clone(this, R.layout.activity_main_animation_start);

        constraintSetHalf = new ConstraintSet();
        constraintSetHalf.clone(this, R.layout.activity_main_animation_half);

        constraintSetEnd = new ConstraintSet();
        constraintSetEnd.clone(this, R.layout.activity_main_animation_end);


        applyAnimations();

    }

    private void applyAnimations() {


        mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Standard animation (Linear)
//                TransitionManager.beginDelayedTransition(mainContainer);

                // Custom Animation
//                TransitionManager.beginDelayedTransition(mainContainer, changeBounds);

                if(animationNumber == 0) {
                    constraintSetHalf.applyTo(mainContainer);
                } else if(animationNumber == 1) {
                    constraintSetEnd.applyTo(mainContainer);
                } else {
                    constraintSetStart.applyTo(mainContainer);
                }

                animationNumber++;

                // Reset
                if (animationNumber >2) {
                    animationNumber = 0;
                }
            }
        });



    }


}

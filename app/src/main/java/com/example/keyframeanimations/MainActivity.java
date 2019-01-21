package com.example.keyframeanimations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ConstraintSet constraintSetStart;
    private ConstraintSet constraintSetEnd;

    private ConstraintLayout mainContainer;

    private Boolean isToggled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_animation_start);

        mainContainer = findViewById(R.id.main_container);

        constraintSetStart = new ConstraintSet();
        constraintSetStart.clone(this, R.layout.activity_main_animation_start);

        constraintSetEnd = new ConstraintSet();
        constraintSetEnd.clone(this, R.layout.activity_main_animation_end);


        applyAnimations();

    }

    private void applyAnimations() {


        mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(mainContainer);

                if(!isToggled) {
                    constraintSetEnd.applyTo(mainContainer);
                } else {
                    constraintSetStart.applyTo(mainContainer);
                }
                isToggled = !isToggled;
            }
        });



    }


}

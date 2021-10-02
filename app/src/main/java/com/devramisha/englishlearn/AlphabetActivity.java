package com.devramisha.englishlearn;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;


public class AlphabetActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Intent intent;
    private View background;


    public void buttonTapped(View view)
    {
        //below 2 line code is for stoping audio if it is playing already
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();

        int id = view.getId();
        String ourId = "";
        ourId = view.getResources().getResourceEntryName(id);
        //button = id;
        // Log.i("buttonTapped",ourId);
        int resourceId = getResources().getIdentifier(ourId,"raw","com.devramisha.englishlearn");

        //This mediaPlayer will play newly tapped button audio.
        mediaPlayer = MediaPlayer.create(this,resourceId);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move,R.anim.do_not_move);
        setContentView(R.layout.activity_alphabet);


        background = findViewById(R.id.background);

        if (savedInstanceState == null) {
            background.setVisibility(View.INVISIBLE);

            final ViewTreeObserver viewTreeObserver = background.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                });
            }

        }

    }


    private void circularRevealActivity() {
        int cx = background.getRight() - getDips(100) ;
        int cy = background.getBottom() - getDips(500) ;

        float finalRadius = Math.max(background.getWidth(), background.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                background,
                cx,
                cy,
                0,
                finalRadius);

        circularReveal.setDuration(800);
        background.setVisibility(View.VISIBLE);
        circularReveal.start();

    }

    private int getDips(int dps) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                resources.getDisplayMetrics());
    }

    @Override
    public void onBackPressed() {
        int cx = background.getWidth() - getDips(100);
        int cy = background.getBottom() - getDips(500);

        float finalRadius = Math.max(background.getWidth(), background.getHeight());
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(background, cx, cy, finalRadius, 0);

        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                background.setVisibility(View.INVISIBLE);

                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        circularReveal.setDuration(800);
        circularReveal.start();
    }

    public void previousScreen(View view)
    {
        intent = new Intent(this,HomeActivity.class);
       // onEnterAnimationComplete(intent);
        startActivity(intent);
        finish();


    }

    public void nextScreen(View view)
    {
        intent = new Intent(this,WordActivity.class);
        startActivity(intent);
        finish();


    }
/*
    @Override
    public void onBackPressed() {
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();

    }

 */

}

package com.devramisha.englishlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WordActivity extends AppCompatActivity {

    String [] names = {"Hi Children, Let Us start to Learn English ", "A for Apple \n(सेब , سیب)", "B for Ball \n (गेंद , گیند)", "C for Cow \n(गाय , گائے)", "D for Dog \n(कुत्ता , کتا) ",
            "E for Elephant \n (हाथी , ہاتھی)", "F for Flag \n(झंडा , پرچم) ", "G for Goat \n(बकरा , بکرا) ", "H for Hen \n(मुर्गी , مرغی) ", "I for Ice-Cream \n(कुल्फी , قلفی) ",
            "J for Juice \n(रस , رس) ", "K for king\n(राजा , بادشاہ)", "L for Lion\n(शेर , شیر)", "M for Mango\n(आम , آم)", "N for Nest\n(घोंसला , گھوںسلا)", "O for Orange\n(संतरा , سنگترہ )",
            "P for Parrot \n(तोता , طوطا) ", "Q for Queen \n(रानी , ملکہ) ", "R for River \n(नदी , دریا) ", "S for Sun \n(सूरज , سورج) ", "T for Tiger \n(बाघ , چیتا) ",
             "U for Umbrella \n(छाता , چھتری)", "V for Van \n(बंद गाड़ी , )", "W for Wolf \n(भेड़िया , بھیڑیا)", "X for Xylophone \n(सिलाफ़न , چوبی باجَہ)",
            "Y for Yak \n(एक जाति का बैल , جَنگَلی بيل)", "Z for Zebra \n( बनैला गधा , گورخَر )"};

    String [] spells = {"Learn English ", "Apple", "Ball", "Cow", "Dog",
            "Elephant", "Flag", "Goat", "Hen", "Ice-Cream",
            "Juice", "king", "Lion", "Mango", "Nest", "Orange",
            "Parrot", "Queen", "River", "Sun", "Tiger",
            "Umbrella", "Van", "Wolf", "Xylophone",
            "Yak", "Zebra"};
    ImageButton prev, next;
    ImageView mypic;
    TextView myStatusId;
    int currentImage = 0;
    MediaPlayer mediaPlayer;
    int resourceId;
    // 0-1-2-3-4-0-1-2-3-4
    private View background;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move,R.anim.do_not_move);
        setContentView(R.layout.activity_word);

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
        int cx = background.getRight() - getDips(300) ;
        int cy = background.getBottom() - getDips(300) ;

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
        int cx = background.getWidth() - getDips(300);
        int cy = background.getBottom() - getDips(300);

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

    public void prev(View v){
        //below 2 line code is for stoping audio if it is playing already
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();

        myStatusId = findViewById(R.id.myStatusId);
        String idX = "mypic" + currentImage;
        int x = this.getResources().getIdentifier(idX, "id", getPackageName());
        mypic = findViewById(x);
        mypic.setAlpha(0f);

        currentImage = (27 + currentImage - 1) % 27;
        String idY = "mypic" + currentImage;
        int y = this.getResources().getIdentifier(idY, "id", getPackageName());
        mypic = findViewById(y);
        mypic.setAlpha(1f);

         resourceId = getResources().getIdentifier(idY,"raw","com.devramisha.englishlearn");
        mediaPlayer = MediaPlayer.create(this,resourceId);
        mediaPlayer.start();

        myStatusId.setText(names[currentImage]);

    }

    public void next(View v){
        //below 2 line code is for stoping audio if it is playing already
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();

        myStatusId = findViewById(R.id.myStatusId);
        String idX = "mypic" + currentImage;
        int x = this.getResources().getIdentifier(idX, "id", getPackageName());
        mypic = findViewById(x);
        mypic.setAlpha(0f);

        currentImage = (currentImage + 1) % 27;
        String idY = "mypic" + currentImage;
        int y = this.getResources().getIdentifier(idY, "id", getPackageName());
        mypic = findViewById(y);
        mypic.setAlpha(1f);

         resourceId = getResources().getIdentifier(idY,"raw","com.devramisha.englishlearn");
        mediaPlayer = MediaPlayer.create(this,resourceId);
        mediaPlayer.start();

        myStatusId.setText(names[currentImage]);

    }

    public void again(View view)
    {
        //below 2 line code is for stoping audio if it is playing already
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();

        mediaPlayer = MediaPlayer.create(this,resourceId);
        mediaPlayer.start();
    }


    public void spelling(View view)
    {
        //below 2 line code is for stoping audio if it is playing already
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();

        String idY = "myspell" + currentImage;
        resourceId = getResources().getIdentifier(idY,"raw","com.devramisha.englishlearn");
        mediaPlayer = MediaPlayer.create(this,resourceId);
        mediaPlayer.start();

        /*
        final Handler handler = new Handler();
        //final int i=0;
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                //Log.i("sec", "run: 3 sec have passed");
                Toast.makeText(WordActivity.this, spells[currentImage], Toast.LENGTH_SHORT).show();
                handler.postDelayed(this,1000);
            }
        };

       // for (int i=0;i<spells[currentImage].length();i++)
       // handler.post(mRunnable);
        mRunnable.run();

         */
//        myStatusId.setText(names[currentImage]);

        int i=0;
        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(WordActivity.this, spells[currentImage], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    public void previousScreen(View view)
    {
        intent = new Intent(this,AlphabetActivity.class);
        startActivity(intent);
        finish();


    }
    public void nextScreen(View view)
    {
        intent = new Intent(this,QuizActivity.class);
        startActivity(intent);
        finish();


    }

    /*
    @Override
    public void onBackPressed() {
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        // super.onBackPressed();
    }
    */

}

package site.petrumugurel.animations;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView           mBartImageView;
    private ImageView           mHomerImageView;
    private Button              mBartBTN;
    private Button              mHomerBTN;
    private CoolAnimatedEffects mHomerAnimator;
    private CoolAnimatedEffects mBartAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBartImageView = (ImageView) findViewById(R.id.mainA_RL_IV_bart);
//        mBartImageView.setVisibility(View.INVISIBLE);
        mBartImageView.animate().alpha(0);
        mBartAnimator = new CoolAnimatedEffects(mBartImageView);
        mBartImageView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public boolean onSwipeDown() {
                mBartAnimator.rotateOnXInPlace(2, 5, -2160, 2000);
                return  true;
            }

            @Override
            public boolean onSwipeUp() {
                mBartAnimator.rotateOnXInPlace(2, 5, 2160, 2000);
                return true;
            }

            @Override
            public boolean onSwipeRight() {
                mBartAnimator.fadeInRotatingImage(mHomerImageView,
                                                  CoolAnimatedEffects.DIRECTIONS.RIGHT,
                                                  360, 2000, 2000);
                return true;
            }

            @Override
            public boolean onSwipeLeft() {
                showToastOnePicture();
                return true;
            }
        });

        mHomerImageView = (ImageView) findViewById(R.id.mainA_RL_IV_homer);
        mHomerAnimator = new CoolAnimatedEffects(mHomerImageView);
        mHomerImageView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public boolean onSwipeDown() {
                mHomerAnimator.rotateOnXInPlace(2, 5, -2160, 2000);
                return true;
            }

            @Override
            public boolean onSwipeUp() {
                mHomerAnimator.rotateOnXInPlace(2, 5, 2160, 2000);
                return true;
            }

            @Override
            public boolean onSwipeLeft() {
                mHomerAnimator.fadeInRotatingImage(mBartImageView,
                                                   CoolAnimatedEffects.DIRECTIONS.LEFT,
                                                   360, 2000, 2000);
                return true;
            }

            @Override
            public boolean onSwipeRight() {
                showToastOnePicture();
                return true;
            }
        });

        mHomerBTN = (Button) findViewById(R.id.mainA_RL_LL_btn_homer);
        mBartBTN = (Button) findViewById(R.id.mainA_RL_LL_btn_bart);

        mHomerBTN.setOnClickListener(this);
        mBartBTN.setOnClickListener(this);
    }

    private void showToastOnePicture() {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content),
                      "Only one picture available.\nYou don't like it?",
                      Snackbar.LENGTH_SHORT)
                .setAction("No", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,
                                       "Then Fuck You Too!",
                                       Toast.LENGTH_SHORT)
                             .show();
                        finish();   // exit the app
                    }
                });
        snackbar.setActionTextColor(Color.RED);

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.mainM_I_settyings) {
            Toast.makeText(MainActivity.this, "No available settyings atm.",
                           Toast.LENGTH_SHORT).show();
        }

        return true;        // signal that we dealt with the menuItem
    }

    @Override
    public void onClick(View v) {
        int buttonId = v.getId();
        if (buttonId == mBartBTN.getId()) {
            if (mBartImageView.getAlpha() < 1) {
                mHomerAnimator.fadeInImage(mBartImageView,
                                           CoolAnimatedEffects.DIRECTIONS.LEFT, 2000, 2000);
            }
            else {
                showToastOnePicture();
            }
        }
        else if (buttonId == mHomerBTN.getId()) {
            if (mHomerImageView.getAlpha() < 1) {
                mBartAnimator.fadeInImage(mHomerImageView,
                                          CoolAnimatedEffects.DIRECTIONS.RIGHT, 2000, 2000);
            }
            else {
                showToastOnePicture();
            }
        }
    }
}

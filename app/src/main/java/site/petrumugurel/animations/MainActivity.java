package site.petrumugurel.animations;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBartImageView;
    private ImageView mHomerImageView;
    private Button    mBartBTN;
    private Button    mHomerBTN;

    int  mSwypeDownCounter   = 0;
    long mDownSwipeStartTime = System.nanoTime();
    int  mSwypeUpCounter     = 0;
    long mUpSwipeStartTime   = System.nanoTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBartImageView = (ImageView) findViewById(R.id.mainA_RL_IV_bart);
        mBartImageView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public boolean onSwipeDown() {
//                Toast.makeText(MainActivity.this, "Ai dat jos", Toast.LENGTH_SHORT).show();
                if (TimeUnit.SECONDS.convert(System.nanoTime() - mDownSwipeStartTime,
                                             TimeUnit.NANOSECONDS) > 5) {
                    mDownSwipeStartTime = System.nanoTime();
                    mSwypeDownCounter = 0;
                }
                else {
                    if (++mSwypeDownCounter == 4) {
                        mSwypeDownCounter = 0;
                        rotateInPlace(mBartImageView);
                    }
                }

                return  true;
            }

            @Override
            public boolean onSwipeUp() {
                Toast.makeText(MainActivity.this, "Ai dat sus", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mHomerImageView = (ImageView) findViewById(R.id.mainA_RL_IV_homer);


//        mBartImageView.setVisibility(View.GONE);
        mBartImageView.animate().alpha(0);  // his dad we'll be on top
//        mBartImageView.animate().translationX(2000);

        mHomerBTN = (Button) findViewById(R.id.mainA_RL_LL_btn_homer);
        mBartBTN = (Button) findViewById(R.id.mainA_RL_LL_btn_bart);

        mHomerBTN.setOnClickListener(this);
        mBartBTN.setOnClickListener(this);
    }


    /**
     * Receives another View to fade in to display in our ImageView.
     * @param newImageToDisplay will fade in, and previous image will fade out.
     */
    private void fadeInImage(View newImageToDisplay) {
        if ((newImageToDisplay).getId() == mHomerImageView.getId()) {
            mHomerImageView.setTranslationX(-2000);
            mHomerImageView.animate().alpha(0);
            mHomerImageView.setRotation(0);
            mHomerImageView.setScaleX(0);
            mHomerImageView.setScaleY(0);

            mHomerImageView.animate().alpha(1).translationXBy(2000)
                           .rotation(360).scaleX(1).scaleY(1)
                           .setDuration(2500);

            mBartImageView.animate().alpha(0).setDuration(2000);
        }
        else if (( newImageToDisplay).getId() == mBartImageView.getId()) {
            mBartImageView.setTranslationX(2000f);
            mBartImageView.animate().alpha(0);
            mBartImageView.setRotation(0);
            mBartImageView.setScaleX(0);
            mBartImageView.setScaleY(0);

            mBartImageView.animate().alpha(1).translationXBy(-2000)
                          .rotation(360).scaleX(1).scaleY(1)
                          .setDuration(2500);

            mHomerImageView.animate().alpha(0).setDuration(2000);
        }
    }

    private void showToastOnePicture() {
/*        Toast.makeText(MainActivity.this,
                       "Only one picture available.\nYou don't like it?",
                       Toast.LENGTH_SHORT).show();*/

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
//        Log.e("pressedButton: ", Integer.toString(buttonId));
//        Log.e("bartBtnID: ", Integer.toString(mBartBTN.getId()));
//        Log.e("homerBrnID: ", Integer.toString(mHomerBTN.getId()));
        if (buttonId == mBartBTN.getId()) {
            if (mBartImageView.getAlpha() < 1) {
                fadeInImage(mBartImageView);
            }
            else {
                showToastOnePicture();
            }
        }
        else if (buttonId == mHomerBTN.getId()) {
            if (mHomerImageView.getAlpha() < 1) {
                fadeInImage(mHomerImageView);
            }
            else {
                showToastOnePicture();
            }
        }
    }

    private void rotateInPlace(View v) {
        Log.e("rotateInPlace ", "called");
        v.setRotationY(0);
        v.animate().rotationXBy(-3600).setDuration(2000);
    }
}

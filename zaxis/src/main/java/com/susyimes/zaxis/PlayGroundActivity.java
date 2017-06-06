package com.susyimes.zaxis;

import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.concurrent.TimeUnit;

import no.agens.depth.lib.DepthLayout;
import no.agens.depth.lib.DepthRendrer;
import no.agens.depth.lib.MaterialMenuDrawable;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class PlayGroundActivity extends Activity {

    private DepthLayout depthView,depthView2;
    private DepthRendrer parent;
    private static final float MAX_ROTATION_X = 90;
    private static final float MAX_ROTATION_Y = 90;
    private static final float MAX_ROTATION_Z = 360;

    private static final float MAX_ELEVATION = 50;
    private static final float MAX_DEPTH = 20;
    private static final float CAMERA_DISTANCE =6000f;
    private static final float CAMERA_DISTANCE2 = 6000f;
    private int seekbarColor;
    private Subscription subscription,subscription2;
    private boolean play=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_playground);
        seekbarColor = getResources().getColor(R.color.fab);
        depthView = (DepthLayout) findViewById(R.id.depth_view);
        depthView2= (DepthLayout) findViewById(R.id.depth_view2);
        parent= (DepthRendrer) findViewById(R.id.parent);
        depthView.setCameraDistance((CAMERA_DISTANCE * getResources().getDisplayMetrics().density));
        depthView2.setCameraDistance((CAMERA_DISTANCE2 * getResources().getDisplayMetrics().density));
        depthView.setDepth(60);
        depthView2.setDepth(60);
        depthView.setCustomShadowElevation(25);
        depthView2.setCustomShadowElevation(25);
        setupParent();
        setupSeekBars();
        makeAppFullscreen();
        setupMenuButton();
    }

    private void setupParent() {
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!play) {

                     Observable.interval(5, TimeUnit.MILLISECONDS).take(91).subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                                        /*depthView.animate().rotationY(270).setDuration(1300);
                                        depthView2.animate().rotationY(270).setDuration(1300);*/
                            depthView.setRotationY(aLong);
                            depthView2.setRotationY(aLong);
                            depthView.setTranslationX(-aLong);
                            depthView2.setTranslationX(aLong);


                        }
                    });
                    play=true;
                }else {

                     Observable.interval(5, TimeUnit.MILLISECONDS).take(91).subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                                        /*depthView.animate().rotationY(270).setDuration(1300);
                                        depthView2.animate().rotationY(270).setDuration(1300);*/
                            depthView.setRotationY(90-aLong);
                            depthView2.setRotationY(90-aLong);
                            depthView.setTranslationX(-90+aLong);
                            depthView2.setTranslationX(90-aLong);


                        }
                    });

                    play=false;
                }
               /* depthView.animate().translationX(-(-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * (1/2))).setDuration(1300);
                depthView2.animate().translationX((-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * (1/2))).setDuration(1300);
                depthView.setRotationY(-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * (1/2));
                depthView2.setRotationY(-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * (1/2));*/
              /*  depthView.setTranslationX( -(-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * (1/2)));
                depthView2.setTranslationX( (-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * (1/2)));*/

            }
        });
        parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:{


                        break;
                    }
                    case MotionEvent.ACTION_MOVE:{
                        Log.i("getY",motionEvent.getY()+"");

                        //depthView2.setRotationY(-MAX_ROTATION_X + (MAX_ROTATION_X * 2f) * (motionEvent.getY()));
                        break;
                    }
                    case MotionEvent.ACTION_UP:{


                        break;
                    }



                }
                return false;
            }
        });
    }

    private void setupMenuButton() {
       /* ImageView menu = (ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
    */
    }
    private void setupSeekBars() {
        /*setupRotationXSeekbar();
        setupRotationYSeekbar();
        setupRotationZSeekbar();
        setupElevationSeekbar();
        setupDepthSeekbar();*/

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void makeAppFullscreen() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

  /*  private void setupDepthSeekbar() {
        final SeekBar depth = (SeekBar) findViewById(R.id.depth_seekbar);
        //WindFragment.setProgressBarColor(depth, seekbarColor);
        depth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                depthView.setDepth(MAX_DEPTH * getResources().getDisplayMetrics().density * ((float) progress / (float) seekBar.getMax()));
                depthView2.setDepth(MAX_DEPTH * getResources().getDisplayMetrics().density * ((float) progress / (float) seekBar.getMax()));
             *//*   depthView.getMatrix().postTranslate(100,100);
                float[]src= new float[8];
                float []dst = new float[]{0, 0, depthView.getWidth(), 0, 0, depthView.getHeight(), depthView.getWidth(), depthView.getHeight()};
                depthView.getMatrix().mapPoints(src, dst);
                parent.invalidate();*//*

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        depth.setProgress((int) (depth.getMax() * 0.1f));
    }

    private SeekBar setupRotationXSeekbar() {
        SeekBar rotationX = (SeekBar) findViewById(R.id.rotation_x_seekbar);
        //WindFragment.setProgressBarColor(rotationX, seekbarColor);
        rotationX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                depthView.setRotationX(-MAX_ROTATION_X + (MAX_ROTATION_X * 2f) * ((float) progress / (float) seekBar.getMax()));
                depthView2.setRotationX(-MAX_ROTATION_X + (MAX_ROTATION_X * 2f) * ((float) progress / (float) seekBar.getMax()));
                depthView2.setTranslationY(-((-MAX_ROTATION_X + (MAX_ROTATION_X * 2f) * ((float) progress / (float) seekBar.getMax()))*2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rotationX.setProgress((int) (rotationX.getMax() * 0.1f));
        return rotationX;

    }

    private void setupRotationYSeekbar() {
        SeekBar rotationY = (SeekBar) findViewById(R.id.rotation_y_seekbar);
        rotationY.setProgress((int) (rotationY.getMax() * 0.5f));
        //WindFragment.setProgressBarColor(rotationY, seekbarColor);
        rotationY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                depthView.setRotationY(-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * ((float) progress / (float) seekBar.getMax()));
                depthView2.setRotationY(-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * ((float) progress / (float) seekBar.getMax()));
                depthView.setTranslationX( -(-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * ((float) progress / (float) seekBar.getMax())));
                depthView2.setTranslationX( (-MAX_ROTATION_Y + (MAX_ROTATION_Y * 2f) * ((float) progress / (float) seekBar.getMax())));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setupRotationZSeekbar() {
        SeekBar rotation = (SeekBar) findViewById(R.id.rotation_z_seekbar);
        rotation.setProgress(0);
        //WindFragment.setProgressBarColor(rotation, seekbarColor);
        rotation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                depthView.setRotation(-MAX_ROTATION_Z * ((float) progress / (float) seekBar.getMax()));
                depthView2.setRotation(-MAX_ROTATION_Z * ((float) progress / (float) seekBar.getMax()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void setupElevationSeekbar() {
        SeekBar elevation = (SeekBar) findViewById(R.id.elevation_seekbar);
        elevation.setProgress(0);
        //WindFragment.setProgressBarColor(elevation, seekbarColor);
        elevation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                depthView.setCustomShadowElevation((MAX_ELEVATION * ((float) progress / (float) seekBar.getMax())) * getResources().getDisplayMetrics().density);
                depthView2.setCustomShadowElevation((MAX_ELEVATION * ((float) progress / (float) seekBar.getMax())) * getResources().getDisplayMetrics().density);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        elevation.setProgress((int) (elevation.getMax() * 0.5f));
    }*/
}

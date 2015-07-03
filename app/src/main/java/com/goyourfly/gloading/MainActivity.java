package com.goyourfly.gloading;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.goyourfly.loading.GLoading;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.checkbox_under_actionbar)
    CheckBox checkboxUnderActionbar;
    @InjectView(R.id.checkbox_with_show_animation)
    CheckBox checkboxWithShowAnimation;
    @InjectView(R.id.checkbox_with_error_animation)
    CheckBox checkboxWithErrorAnimation;
    @InjectView(R.id.checkbox_with_dismiss_animation)
    CheckBox checkboxWithDismissAnimation;
    @InjectView(R.id.checkbox_custom_loading_view)
    CheckBox checkboxCustomLoadingView;
    @InjectView(R.id.checkbox_custom_error_view)
    CheckBox checkboxCustomErrorView;
    @InjectView(R.id.checkbox_custom_root_view)
    CheckBox checkboxCustomRootView;
    @InjectView(R.id.container)
    FrameLayout container;

    private GLoading loading;
    private Animation animationEnter;
    private Animation animationExit;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        animationEnter = new AlphaAnimation(0, 1);
        animationEnter.setDuration(500);

        animationExit = new AlphaAnimation(1, 0);
        animationExit.setDuration(500);
    }

    private Runnable errorRunnable = new Runnable() {
        @Override
        public void run() {
            if (checkboxWithErrorAnimation.isChecked()) {
                loading.error(animationEnter);
            } else {
                loading.error();
            }
        }
    };

    public void onStart(View view) {

        if (checkboxCustomRootView.isChecked()) {
            loading = GLoading.with(this, container);
        } else {
            loading = GLoading.with(this);
        }


        if (checkboxUnderActionbar.isChecked()) {
            loading.underActionbar();
        }

        if (checkboxCustomLoadingView.isChecked()) {
            loading.customView(R.layout.custom_loading);
        }

        if (checkboxCustomErrorView.isChecked()) {
            loading.customError(R.layout.custom_error);
        }

        if (checkboxWithShowAnimation.isChecked()) {
            loading.show(animationEnter);
        } else {
            loading.show();
        }

        loading.onErrorClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.show();
                handler.removeCallbacks(errorRunnable);
                handler.postDelayed(errorRunnable, 3000);
            }
        });
        handler.removeCallbacks(errorRunnable);
        handler.postDelayed(errorRunnable, 3000);
    }

    @Override
    public void onBackPressed() {

        if (loading != null) {
            if (loading.isShowing()) {
                handler.removeCallbacks(errorRunnable);
                if (checkboxWithDismissAnimation.isChecked()) {
                    loading.dismiss(animationExit);
                } else {
                    loading.dismiss();
                }
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}

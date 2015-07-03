package com.goyourfly.loading;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by gaoyufei on 15/7/3.
 */
public abstract class GLoading {
    public static GLoading with(Activity activity) {
        return new GLoadingImpl(activity, (ViewGroup) activity.getWindow().getDecorView());
    }

    public static GLoading with(Context context, ViewGroup view) {
        return new GLoadingImpl(context, view);
    }

    public abstract GLoading underActionbar();

    public abstract GLoading customView(View view);

    public abstract GLoading customView(int id);

    public abstract GLoading customError(View view);

    public abstract GLoading customError(int id);

    public abstract GLoading show();

    public abstract GLoading show(Animation anim);

    public abstract boolean isShowing();

    public abstract GLoading dismiss();

    public abstract GLoading dismiss(Animation anim);

    public abstract GLoading error();

    public abstract GLoading error(Animation anim);

    public abstract GLoading onErrorClick(View.OnClickListener listener);
}

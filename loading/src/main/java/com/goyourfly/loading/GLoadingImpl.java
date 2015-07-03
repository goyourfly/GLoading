package com.goyourfly.loading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;

/**
 * Created by gaoyufei on 15/7/3.
 */
public class GLoadingImpl extends GLoading {
    public static final int STATE_IDLE = 0x00;
    public static final int STATE_LOADING = 0x01;
    public static final int STATE_ERROR = 0x02;

    private Context mContext;
    private ViewGroup mRootView;
    private View mLoadingView;
    private View mErrorView;
    private int mCurrentState;
    private View.OnClickListener mErrorListener;

    protected GLoadingImpl(Context context, ViewGroup root) {
        mContext = context;
        mRootView = root;
    }

    @Override
    public GLoading underActionbar() {
        View view = mRootView.findViewById(android.R.id.content);
        if (view != null)
            mRootView = (ViewGroup) view;
        return this;
    }

    @Override
    public GLoading customView(View view) {
        mLoadingView = view;
        return this;
    }

    @Override
    public GLoading customView(int id) {
        mLoadingView = LayoutInflater.from(mContext).inflate(id, null);
        return this;
    }

    @Override
    public GLoading customError(View view) {
        mErrorView = view;
        return this;
    }

    @Override
    public GLoading customError(int id) {
        return customError(LayoutInflater.from(mContext).inflate(id, null));
    }

    @Override
    public GLoading show() {
        if (mLoadingView == null)
            mLoadingView = LayoutInflater.from(mContext).inflate(R.layout.loading, null);
        if (mCurrentState == STATE_ERROR) {
            mRootView.removeView(mErrorView);
        }
        ViewGroup.LayoutParams params
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootView.removeView(mLoadingView);
        mRootView.addView(mLoadingView, params);
        mCurrentState = STATE_LOADING;
        return this;
    }

    @Override
    public GLoading show(Animation anim) {
        show();
        mLoadingView.startAnimation(anim);
        return this;
    }

    @Override
    public boolean isShowing() {
        return mCurrentState == STATE_LOADING || mCurrentState == STATE_ERROR;
    }


    @Override
    public GLoading dismiss() {
        mCurrentState = STATE_IDLE;
        mRootView.removeView(mLoadingView);
        mRootView.removeView(mErrorView);
        return this;
    }

    @Override
    public GLoading dismiss(Animation anim) {
        if (mCurrentState == STATE_LOADING) {
            mLoadingView.startAnimation(anim);
        } else if (mCurrentState == STATE_ERROR) {
            mErrorView.startAnimation(anim);
        }
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return this;
    }

    @Override
    public GLoading error() {
        if (mErrorView == null)
            mErrorView = LayoutInflater.from(mContext).inflate(R.layout.error, null);
        mRootView.removeView(mLoadingView);
        ViewGroup.LayoutParams params
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootView.removeView(mErrorView);
        mRootView.addView(mErrorView, params);
        mErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mErrorListener != null)
                    mErrorListener.onClick(v);
            }
        });

        mCurrentState = STATE_ERROR;
        return this;
    }

    @Override
    public GLoading error(Animation anim) {
        error();
        mErrorView.startAnimation(anim);
        return this;
    }

    @Override
    public GLoading onErrorClick(View.OnClickListener listener) {
        mErrorListener = listener;
        return this;
    }


}

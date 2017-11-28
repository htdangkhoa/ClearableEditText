package com.github.htdangkhoa.clearableedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dangkhoa on 11/28/17.
 */

public class ClearableEditText extends AppCompatEditText implements View.OnTouchListener, TextWatcher {
    private Drawable DRAWABLE;
    int MODE;

    public ClearableEditText(Context context) {
        super(context);
        initialize(context, null);
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClearableEditText);
            DRAWABLE = typedArray.getDrawable(R.styleable.ClearableEditText_cet_src);
            MODE = typedArray.getInt(R.styleable.ClearableEditText_cet_visible_mode, 1);

            if (DRAWABLE != null) {
                DrawableCompat.setTint(DRAWABLE, getCurrentHintTextColor());
                DRAWABLE.setBounds(0, 0, DRAWABLE.getIntrinsicHeight(), DRAWABLE.getIntrinsicHeight());

                switch (MODE) {
                    case 0:
                        setClearIconVisible(false);
                    case 1:
                        setClearIconVisible(this.getText().length() > 0);
                        break;
                    case 2:
                        setClearIconVisible(true);
                        break;
                }

                super.setOnTouchListener(this);
                super.addTextChangedListener(this);
            }
        }
    }

    private void setClearIconVisible(boolean visible) {
        if (DRAWABLE != null) {
            DRAWABLE.setVisible(visible, false);
            Drawable[] compoundDrawables = getCompoundDrawables();
            setCompoundDrawables(
                    compoundDrawables[0],
                    compoundDrawables[1],
                    visible ? DRAWABLE : null,
                    compoundDrawables[3]);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int x = (int) event.getX();
        if (DRAWABLE.isVisible() && x > getWidth() - getPaddingRight() - DRAWABLE.getIntrinsicWidth()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                setText("");
            }
            return true;
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isFocused() && MODE == 1) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

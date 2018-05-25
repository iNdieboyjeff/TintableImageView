package util.android.tintableimageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;


/**
 * An ImageView that supports tinting.
 *
 * It's possible that this is no longer needed as the AppCompatImageView now supports tints,
 * but that's the way life is!
 *
 * The attribute for using this has been renamed from 'tint' to 'cTint' as there were conflicts
 * with the AppCompatImageView attribute of the same name.
 *
 */
public class TintableImageView extends android.support.v7.widget.AppCompatImageView {

    private ColorStateList tint;

    public TintableImageView(Context context) {
        super(context);
    }

    public TintableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TintableImageView, defStyle, 0);
        tint = a.getColorStateList(R.styleable.TintableImageView_cTint);
        a.recycle();
    }

    public TintableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public void setColorFilter(ColorStateList tint) {
        this.tint = tint;
        super.setColorFilter(tint.getColorForState(getDrawableState(), 0));
    }

    public void setTintList(ColorStateList colorList) {
        if (colorList != null) {
            tint = colorList;
            drawableStateChanged();
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (tint != null && tint.isStateful())
            updateTintColor();
    }

    private void updateTintColor() {
        int color = tint.getColorForState(getDrawableState(), 0);
        setColorFilter(color);
    }


}

package com.example.money_tracker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.DropBoxManager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class Category extends View {
	private String categoryName;
	private int categoryColor;
	private float iconDimension = 0; // TODO: use a default from R.dimen...
	private Drawable drawable;
	
	private float mTextWidth;
	private float mTextHeight;
	
	private TextPaint mTextPaint;
	
	public Category(Context context) {
		super(context);
		iconDimension = 70f;
		init(null, 0);
	}

	public Category(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public Category(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		final TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.Category, defStyle, 0);

		categoryName = a.getString(R.styleable.Category_exampleString);
		categoryColor = a.getColor(R.styleable.Category_exampleColor,
				categoryColor);
		// Use getDimensionPixelSize or getDimensionPixelOffset when dealing
		// with
		// values that should fall on pixel boundaries.
		iconDimension = a.getDimension(
				R.styleable.Category_exampleDimension, iconDimension);

		if (a.hasValue(R.styleable.Category_exampleDrawable)) {
			drawable = a
					.getDrawable(R.styleable.Category_exampleDrawable);
			drawable.setCallback(this);
		}

		a.recycle();

		// Set up a default TextPaint object
		mTextPaint = new TextPaint();
		mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextAlign(Paint.Align.LEFT);

		// Update TextPaint and text measurements from attributes
		invalidateTextPaintAndMeasurements();
	}

	private void invalidateTextPaintAndMeasurements() {
		mTextPaint.setTextSize(iconDimension);
		mTextPaint.setColor(categoryColor);
		mTextWidth = mTextPaint.measureText(categoryName);

		Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
		mTextHeight = fontMetrics.bottom;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// TODO: consider storing these as member variables to reduce
		// allocations per draw cycle.
		int paddingLeft = getPaddingLeft();
		int paddingTop = getPaddingTop();
		int paddingRight = getPaddingRight();
		int paddingBottom = getPaddingBottom();

		int contentWidth = getWidth() - paddingLeft - paddingRight;
		int contentHeight = getHeight() - paddingTop - paddingBottom;

		// Draw the text.
		canvas.drawText(categoryName, paddingLeft
				+ (contentWidth - mTextWidth) / 2, paddingTop
				+ (contentHeight + mTextHeight) / 2, mTextPaint);

		// Draw the example drawable on top of the text.
		if (drawable != null) {
			drawable.setBounds(paddingLeft, paddingTop, paddingLeft
					+ contentWidth, paddingTop + contentHeight);
			drawable.draw(canvas);
		}
	}

	/**
	 * Gets the example string attribute value.
	 * 
	 * @return The example string attribute value.
	 */
	public String getExampleString() {
		return categoryName;
	}

	/**
	 * Sets the view's example string attribute value. In the example view, this
	 * string is the text to draw.
	 * 
	 * @param exampleString
	 *            The example string attribute value to use.
	 */
	public void setExampleString(String exampleString) {
		categoryName = exampleString;
		invalidateTextPaintAndMeasurements();
	}

	/**
	 * Gets the example color attribute value.
	 * 
	 * @return The example color attribute value.
	 */
	public int getExampleColor() {
		return categoryColor;
	}

	/**
	 * Sets the view's example color attribute value. In the example view, this
	 * color is the font color.
	 * 
	 * @param exampleColor
	 *            The example color attribute value to use.
	 */
	public void setExampleColor(int exampleColor) {
		categoryColor = exampleColor;
		invalidateTextPaintAndMeasurements();
	}

	/**
	 * Gets the example dimension attribute value.
	 * 
	 * @return The example dimension attribute value.
	 */
	public float getExampleDimension() {
		return iconDimension;
	}

	/**
	 * Sets the view's example dimension attribute value. In the example view,
	 * this dimension is the font size.
	 * 
	 * @param exampleDimension
	 *            The example dimension attribute value to use.
	 */
	public void setExampleDimension(float exampleDimension) {
		iconDimension = exampleDimension;
		invalidateTextPaintAndMeasurements();
	}

	/**
	 * Gets the example drawable attribute value.
	 * 
	 * @return The example drawable attribute value.
	 */
	public Drawable getExampleDrawable() {
		return drawable;
	}

	/**
	 * Sets the view's example drawable attribute value. In the example view,
	 * this drawable is drawn above the text.
	 * 
	 * @param exampleDrawable
	 *            The example drawable attribute value to use.
	 */
	public void setExampleDrawable(Drawable exampleDrawable) {
		drawable = exampleDrawable;
	}
}

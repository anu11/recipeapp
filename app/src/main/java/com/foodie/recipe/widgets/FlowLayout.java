package com.foodie.recipe.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.foodie.recipe.R;

public class FlowLayout extends ViewGroup {
	public int mHorizontalSpacing = 10;
	public int mVerticalSpacing = 10;
	private Paint mPaint;

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
		try {
			mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontalSpacing, 0);
			mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_verticalSpacing, 0);
		} finally {
			a.recycle();
		}
		
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(0x00ff0000);
		mPaint.setStrokeWidth(2.0f);
	}

	public void setSpacing(int horizontalSpacing, int verticalSpacing) {
		mHorizontalSpacing = horizontalSpacing;
		mVerticalSpacing = verticalSpacing;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSizeBase = MeasureSpec.getSize(widthMeasureSpec)
				- getPaddingRight() - getPaddingLeft() - 2 * mHorizontalSpacing - 8;
		int widthSize = widthSizeBase;
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		boolean growHeight = widthMode != MeasureSpec.UNSPECIFIED;

		int width = 0;
		int height = getPaddingTop();

		int currentWidth = getPaddingLeft();
		int currentHeight = 0;

		boolean breakLine = false;
		int spacing = 0;
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() != VISIBLE) {
				continue;
			}

			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			LayoutParams lp = (LayoutParams) child.getLayoutParams();
		    spacing = mHorizontalSpacing;
			if (lp.horizontalSpacing >= 0) {
				spacing = lp.horizontalSpacing;
			}

			if (growHeight && (breakLine || currentWidth + child.getMeasuredWidth() > widthSize)) {
				height += currentHeight + mVerticalSpacing;
				currentHeight = 0;
				width = Math.max(width, currentWidth - spacing);
				currentWidth = getPaddingLeft();
				widthSize = widthSizeBase;
			}

			widthSize += mHorizontalSpacing;

			lp.x = currentWidth;
			lp.y = height;

			currentWidth += child.getMeasuredWidth() + spacing;
			currentHeight = Math.max(currentHeight, child.getMeasuredHeight());
			
			breakLine = lp.breakLine;
		}

		height += currentHeight;
		width = Math.max(width, currentWidth - spacing);

		width += getPaddingRight();
		height += getPaddingBottom();

		setMeasuredDimension(resolveSize(width, widthMeasureSpec),
				resolveSize(height, heightMeasureSpec));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			LayoutParams lp = (LayoutParams) child.getLayoutParams();
			child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
		}
	}

	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
		return p instanceof LayoutParams;
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new LayoutParams(getContext(), attrs);
	}
	
	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		return new LayoutParams(p.width, p.height);
	}

	public static class LayoutParams extends ViewGroup.LayoutParams {
		int x;
		int y;
		
		public int horizontalSpacing = 10;
		public boolean breakLine;

		public LayoutParams(Context context, AttributeSet attrs) {
			super(context, attrs);
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_LayoutParams);
			try {
				horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_LayoutParams_layout_horizontalSpacing, -1);
				breakLine = a.getBoolean(R.styleable.FlowLayout_LayoutParams_layout_breakLine, false);
			} finally {
				a.recycle();
			}
		}

		public LayoutParams(int w, int h) {
			super(w, h);
		}
	}
}

package com.kevin.douban250movie;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 描述: TODO
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-08-15 16:36
 */
public class Mydecoration extends RecyclerView.ItemDecoration{

	public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

	public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
	private int mOrientation;

	private  final int[] attrs = new int[]{
			android.R.attr.listDivider
	};
	private Drawable mdrawable;

	public Mydecoration(Context context, int orientation) {
		TypedArray type = context.obtainStyledAttributes(attrs);
		mdrawable = type.getDrawable(0);
		type.recycle();
		setOrientation(orientation);
	}

	public void setOrientation(int orientation) {
		if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
			throw new IllegalArgumentException("invalid orientation");
		}
		mOrientation = orientation;
	}


	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		if (mOrientation == VERTICAL_LIST) {
			drawVertical(c, parent);
		} else {
			drawHorizontal(c, parent);
		}
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		if (mOrientation == VERTICAL_LIST) {
			outRect.set(0, 0, 0, mdrawable.getIntrinsicHeight());
		} else {
			outRect.set(0, 0, mdrawable.getIntrinsicWidth(), 0);
		}
	}


	/**
	 * 画垂直的分割线
	 * @param c
	 * @param parent
	 */
	public void drawVertical(Canvas c, RecyclerView parent) {
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int top = child.getBottom() + params.bottomMargin;
			final int bottom = top + mdrawable.getIntrinsicHeight();
			mdrawable.setBounds(left, top, right, bottom);
			mdrawable.draw(c);
		}
	}

	/**
	 * 画水平分割线
	 * @param c
	 * @param parent
	 */
	public void drawHorizontal(Canvas c, RecyclerView parent) {
		final int top = parent.getPaddingTop();
		final int bottom = parent.getHeight() - parent.getPaddingBottom();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int left = child.getRight() + params.rightMargin;
			final int right = left + mdrawable.getIntrinsicHeight();
			mdrawable.setBounds(left, top, right, bottom);
			mdrawable.draw(c);
		}
	}
}

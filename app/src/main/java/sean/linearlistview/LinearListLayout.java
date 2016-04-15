/**
 * Copyright 2016 Sean Lee
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sean.linearlistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * Created by Sean Lee on 28/1/16.
 */
public class LinearListLayout extends LinearLayout implements View.OnClickListener {

    public interface OnItemClickListener {
        void onItemClick(View view, Object itemObject, int position);
    }

    private LayoutParams layoutParams;
    private OnItemClickListener onItemClickListener;
    private int dividerHeight = 0;

    public LinearListLayout(Context context) {
        this(context, null);
        init();
    }

    public LinearListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public LinearListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinearListLayout);
            dividerHeight = typedArray.getDimensionPixelSize(R.styleable.LinearListLayout_dividerHeight, 0);

            typedArray.recycle();
        }

        init();
    }

    private void init() {

        setOrientation(VERTICAL);

        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, dividerHeight);
    }

    public void setAdapter(BaseAdapter adapter) {

        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, this);
            view.setOnClickListener(this);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.position = i;
            viewHolder.itemObject = adapter.getItem(i);
            view.setTag(viewHolder);

            if (i >= adapter.getCount() - 1) {
                addView(view);
            } else {
                addView(view, layoutParams);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(v, ((ViewHolder) v.getTag()).itemObject, ((ViewHolder) v.getTag()).position);
    }

    private class ViewHolder {
        int position;
        Object itemObject;
    }
}

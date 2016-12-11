package edu.gisi.magic.thesismanagement.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.entity.Thesis;
import edu.gisi.magic.thesismanagement.entity.ThesisResult;


/**
 * Created by AlexXu on 2016/12/10.
 */

public class ThesisListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<ThesisResult> mData = new ArrayList<>();

    public ThesisListViewAdapter(Context context) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        mData.clear();
    }

    public void appendAndNotify(List<ThesisResult> data) {
        mData.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ThesisHolder viewHolder;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_hotel, parent, false);
            viewHolder = new ThesisHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ThesisHolder) view.getTag();
        }
        final ThesisResult thesisResult = mData.get(position);

        //设置item点击事件
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转到详情页
                Toast.makeText(mContext, thesisResult.getName() + " & " + thesisResult.getHouse(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.mTitle.setText(thesisResult.getName());
        viewHolder.mType.setText(mContext.getString(R.string.TYPE, thesisResult.getHouse()));
        viewHolder.mOrigin.setText(mContext.getString(R.string.ORIGIN, String.valueOf(thesisResult.getSize())));
        viewHolder.mTeacher.setText(mContext.getString(R.string.TEACHER, String.valueOf(thesisResult.getPrice())));

        return view;
    }

    class ThesisHolder {

        private View container;
        private TextView mTitle;
        private TextView mType;
        private TextView mOrigin;
        private TextView mTeacher;
        private TextView mNumber;
        private TextView mDepartment;

        private ThesisHolder(View view) {
            container = view.findViewById(R.id.container);
            mTitle = (TextView) view.findViewById(R.id.titleTextView);
            mType = (TextView) view.findViewById(R.id.houseTextView);
            mOrigin = (TextView) view.findViewById(R.id.sizeTextView);
            mTeacher = (TextView) view.findViewById(R.id.priceTextView);
        }
    }

}


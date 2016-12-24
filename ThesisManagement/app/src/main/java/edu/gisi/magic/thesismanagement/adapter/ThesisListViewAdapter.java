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
import edu.gisi.magic.thesismanagement.activity.ThesisDetailActivity;
import edu.gisi.magic.thesismanagement.entity.ThesisResult;
import edu.gisi.magic.thesismanagement.fragment.TypeFragment;


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
            view = layoutInflater.inflate(R.layout.item_thesis, parent, false);
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
                if (TypeFragment.getFragTag() == 0) {
                    ThesisDetailActivity.startAc(mContext,String.valueOf(thesisResult.getId()),false);
                } else if (TypeFragment.getFragTag() == 1) {
                    ThesisDetailActivity.startAc(mContext,String.valueOf(thesisResult.getId()),true);
                } else {
                    return;
                }
            }
        });
        viewHolder.mTitle.setText(thesisResult.getTitle());
        viewHolder.mType.setText(thesisResult.getType());
        viewHolder.mOrigin.setText(thesisResult.getOrigin());
        viewHolder.mTeacher.setText(thesisResult.getTeacher().getName());
        viewHolder.mNumber.setText(thesisResult.getNumbers());
        viewHolder.mDepartment.setText(thesisResult.getDep().getName());
        viewHolder.mStatus.setText(thesisResult.getVerifyState());

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
        private TextView mStatus;

        private ThesisHolder(View view) {
            container = view.findViewById(R.id.container);
            mTitle = (TextView) view.findViewById(R.id.titleTextView);
            mType = (TextView) view.findViewById(R.id.typeTextView);
            mOrigin = (TextView) view.findViewById(R.id.originTextView);
            mTeacher = (TextView) view.findViewById(R.id.teacherTextView);
            mNumber = (TextView) view.findViewById(R.id.numberTextView);
            mDepartment = (TextView) view.findViewById(R.id.departmentTextView);
            mStatus = (TextView) view.findViewById(R.id.statusTextView);
        }
    }

}


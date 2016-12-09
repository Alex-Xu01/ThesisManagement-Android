package edu.gisi.magic.thesismanagement.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.activity.CreateOrderActivity;
import edu.gisi.magic.thesismanagement.activity.HotelDetailActivity;
import edu.gisi.magic.thesismanagement.activity.InActivityActivity;
import edu.gisi.magic.thesismanagement.activity.MainActivity;

public class HomepageFragment extends Fragment {

	private ImageView topImageView;
	private Button typeButton;
	private Button orderButton;
	private Button activityButton;
	private Button introduceButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_homepage, null);
		this.initView(view);
		return view;
	}

	@SuppressLint("NewApi")
	public void initView(View view) {
		this.topImageView = (ImageView) view.findViewById(R.id.topImageView);
		this.typeButton = (Button) view.findViewById(R.id.typeButton);
		this.orderButton = (Button) view.findViewById(R.id.orderButton);
		this.activityButton = (Button) view.findViewById(R.id.activityButton);
		this.introduceButton = (Button) view.findViewById(R.id.introduceButton);

		this.topImageView.setImageDrawable(getActivity().getDrawable(R.drawable.hotel0));
		this.typeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				((MainActivity) getActivity()).bottomRadio.check(R.id.main_bottom_icon_2);// 跳到分类选项卡
			}
		});
		this.activityButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), InActivityActivity.class);
				startActivity(intent);
			}
		});
		this.orderButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), CreateOrderActivity.class);
				startActivity(intent);
			}
		});
		this.introduceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), HotelDetailActivity.class);
				startActivity(intent);
			}
		});
	}
}

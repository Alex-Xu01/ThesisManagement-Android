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
import android.widget.TextView;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.activity.LoginActivity;
import edu.gisi.magic.thesismanagement.tools.CacheTool;

public class UserCenterFragment extends Fragment {

	private ImageView topImageView;
	private TextView usernameTextView;
	private Button logoutButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_center, null);
		this.initView(view);
		return view;
	}

	@SuppressLint("NewApi")
	public void initView(View view) {
		this.topImageView = (ImageView) view.findViewById(R.id.topImageView);
		this.usernameTextView = (TextView) view.findViewById(R.id.usernameTextView);
		this.logoutButton = (Button) view.findViewById(R.id.logoutButton);

		this.topImageView.setImageDrawable(getActivity().getDrawable(R.drawable.hotel0));
		this.usernameTextView.setText("尊敬的酒店会员 " + CacheTool.get("username") + " , 欢迎您！");
		this.logoutButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), LoginActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
	}

}

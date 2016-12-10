package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.fragment.FragmentFactory;

public class MainActivity extends Activity {

	private FragmentManager fragmentManager;
	private Fragment fragment;
	public RadioGroup bottomRadio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getFragmentManager();
		bottomRadio = (RadioGroup) findViewById(R.id.main_rg_tab);
		bottomRadio.check(R.id.main_bottom_icon_1);
		bottomRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {// 选项卡切换
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				pageChange(checkedId);
			}
		});
		pageChange(R.id.main_bottom_icon_1);
	}

	private void pageChange(int checkedId) {
		FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
		fragment = FragmentFactory.createFragmentById(checkedId);
		beginTransaction.replace(R.id.main_fl_content, fragment);
		beginTransaction.commit();
	}

}

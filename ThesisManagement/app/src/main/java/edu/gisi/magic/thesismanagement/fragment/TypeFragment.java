package edu.gisi.magic.thesismanagement.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.activity.MainActivity;


/**
 * 论文管理
 * */
public class TypeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

	private RadioGroup typeGroup;

	private FragmentManager fragmentManager;
	private MyThesisFragment myThesisFragment;
	private ThesisManagerFragment thesisManagerFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_type, container,
				false);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view) {

		//init product type group
		typeGroup = (RadioGroup) view.findViewById(R.id.product_type_group);
		typeGroup.setOnCheckedChangeListener(this);
		((RadioButton) typeGroup.getChildAt(0)).setChecked(true);
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		switch (checkedId) {
			case R.id.mythesis_radio:
				if (myThesisFragment == null) {
					myThesisFragment = new MyThesisFragment();
					transaction.add(R.id.layout_container_linear, myThesisFragment);
				} else {
					transaction.show(myThesisFragment);
				}
				if (thesisManagerFragment != null) {
					transaction.hide(thesisManagerFragment);
				}
				((RadioButton) typeGroup.getChildAt(0)).setChecked(true);
				break;
			case R.id.thesismanager_radio:
				if (thesisManagerFragment == null) {
					thesisManagerFragment = new ThesisManagerFragment();
					transaction.add(R.id.layout_container_linear, thesisManagerFragment);
				} else {
					transaction.show(thesisManagerFragment);
				}
				if (myThesisFragment != null) {
					transaction.hide(myThesisFragment);
				}
				((RadioButton) typeGroup.getChildAt(1)).setChecked(true);
				break;
		}
		transaction.commit();
	}
}

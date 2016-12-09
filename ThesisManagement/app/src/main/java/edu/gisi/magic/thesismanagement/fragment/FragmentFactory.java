package edu.gisi.magic.thesismanagement.fragment;

import android.app.Fragment;

import edu.gisi.magic.thesismanagement.R;

public class FragmentFactory {

	public static Fragment createFragmentById(int id) {

		Fragment fragment = null;
		switch (id) {
		case R.id.main_bottom_icon_1:
			fragment = new HomepageFragment();
			break;
		case R.id.main_bottom_icon_2:
			fragment = new TypeFragment();
			break;
		case R.id.main_bottom_icon_3:
			fragment = new UserCenterFragment();
			break;
		}
		return fragment;
	}

}

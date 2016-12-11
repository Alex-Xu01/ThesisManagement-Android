package edu.gisi.magic.thesismanagement.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import java.util.List;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

	private List<Fragment> fragmentlist;

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragmentlist = fragments;
	}
	
	@Override
	public Fragment getItem(int arg0) {
		return fragmentlist.get(arg0);
	}

	@Override
	public int getCount() {
		if(fragmentlist==null){
			return 0;
		}else{
			return fragmentlist.size();
		}
	}
	
	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

}

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
import edu.gisi.magic.thesismanagement.tools.CacheTool;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends Activity {

	private FragmentManager fragmentManager;
	private Fragment fragment;
	public RadioGroup bottomRadio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CacheTool.setContext(this);
		initImageLoader();
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

	public void initImageLoader() {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.mipmap.ic_launcher).showImageOnFail(R.mipmap.ic_launcher)
				.cacheInMemory(true).cacheOnDisc(true).build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions).discCacheSize(50 * 1024 * 1024)//
				.discCacheFileCount(100)// 缓存一百张图片
				.writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
	}

}

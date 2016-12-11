package edu.gisi.magic.thesismanagement.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.adapter.MyFragmentPagerAdapter;
import edu.gisi.magic.thesismanagement.fragment.HomepageFragment;
import edu.gisi.magic.thesismanagement.fragment.TypeFragment;
import edu.gisi.magic.thesismanagement.fragment.UserCenterFragment;

public class MainActivity extends FragmentActivity {

    private FragmentManager fragmentManager;
    private HomepageFragment homepageFragment;
    private TypeFragment typeFragment;
    private UserCenterFragment userCenterFragment;
    private List<Fragment> fragmentList;
    private MyFragmentPagerAdapter viewAdapter;
    private ViewPager viewPager;

    public RadioGroup bottomRadio;
    private RadioButton[] radiobuttons = new RadioButton[3];
    private long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        initFragment();
    }

    public void findview() {
        bottomRadio = (RadioGroup) findViewById(R.id.main_rg_tab);
        radiobuttons[0]=(RadioButton) findViewById(R.id.main_bottom_icon_1);
        radiobuttons[1]=(RadioButton) findViewById(R.id.main_bottom_icon_2);
        radiobuttons[2]=(RadioButton) findViewById(R.id.main_bottom_icon_3);

        bottomRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {// 选项卡切换
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                pageChange(checkedId);
            }
        });
        viewPager = (ViewPager) findViewById(R.id.main_fl_content);
    }

    private void initFragment() {
        fragmentManager = this.getFragmentManager();
        fragmentList = new ArrayList<>();

        homepageFragment = new HomepageFragment();
        typeFragment = new TypeFragment();
        userCenterFragment = new UserCenterFragment();

        fragmentList.add(homepageFragment);
        fragmentList.add(typeFragment);
        fragmentList.add(userCenterFragment);

        setAdapter();
    }

    public void setAdapter() {
        viewAdapter = new MyFragmentPagerAdapter(fragmentManager, fragmentList);
        viewPager.setAdapter(viewAdapter);
//        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void pageChange(int index) {
        switch (index) {
            case 0:
            case R.id.main_bottom_icon_1:
                radiobuttons[0].setChecked(true);
                viewPager.setCurrentItem(0);
                break;
            case 1:
            case R.id.main_bottom_icon_2:
                radiobuttons[1].setChecked(true);
                viewPager.setCurrentItem(1);
                break;
            case 2:
            case R.id.main_bottom_icon_3:
                radiobuttons[2].setChecked(true);
                viewPager.setCurrentItem(2);
                break;
            default:
                viewPager.setCurrentItem(0);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        long endTime = System.currentTimeMillis();
        if (endTime - startTime < 2000) {
            finish();
            System.exit(0);
        } else {
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            startTime = endTime;
        }
    }
}

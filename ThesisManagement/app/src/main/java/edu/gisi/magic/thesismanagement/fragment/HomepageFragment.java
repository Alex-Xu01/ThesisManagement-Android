package edu.gisi.magic.thesismanagement.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.view.RingView;

/**
 * 首页内容
 */
public class HomepageFragment extends Fragment {

    private RingView mRingView;
    private TextView passedPaper;
    private TextView canBeChoosePaper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, null);
        this.initView(view);
        this.setContent();
        return view;
    }

    public void initView(View view) {
        mRingView = (RingView) view.findViewById(R.id.RingPecent);
        passedPaper = (TextView) view.findViewById(R.id.tv_passedPaper);
        canBeChoosePaper = (TextView) view.findViewById(R.id.tv_canBeChoosePaper);
    }

    public void setContent() {
        float pecent;
        int passed, canBeChoose;
        passed = 20;
        canBeChoose = 14;
        pecent = canBeChoose / (float) 1.0 / passed * 100;
        mRingView.setAngle(pecent);
        mRingView.setText(pecent+" %");
        passedPaper.setText(getString(R.string.font_papers, String.valueOf(passed)));
        canBeChoosePaper.setText(getString(R.string.font_papers, String.valueOf(canBeChoose)));
    }
}

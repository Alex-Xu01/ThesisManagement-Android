package edu.gisi.magic.thesismanagement.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.config.Urls;
import edu.gisi.magic.thesismanagement.entity.GeneralData;
import edu.gisi.magic.thesismanagement.view.RingView;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;

/**
 * 首页内容
 */
public class HomepageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RingView mRingView;
    private TextView totalPaper;
    private TextView passedPaper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;

    public static final String TAG = "HomepageFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, null);
        initView(view);
        setContent();
        return view;
    }

    public void initView(View view) {
        totalPaper = (TextView) view.findViewById(R.id.tv_totalPaper);
        passedPaper = (TextView) view.findViewById(R.id.tv_passedPaper);
        mRingView = (RingView) view.findViewById(R.id.RingPecent);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.homepage_container);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void setContent() {
        final Map<String, String> map = new HashMap<>();
        map.put("android", "1");
        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_GENERAL, GeneralData.class,
                new Response.Listener<GeneralData>() {
                    @Override
                    public void onResponse(GeneralData data) {
                        swipeRefreshLayout.setRefreshing(false);
                        float pecent;
                        int Passed, Total;
                        Passed = data.getPassNumber();
                        Total = data.getFailedNumber() + data.getPassNumber();
                        pecent = Passed / (float) 1.0 / Total;
                        mRingView.setAngle(pecent);
                        mRingView.setText(getString(R.string.font_pecent, pecent * 100));
                        mRingView.invalidate();
                        totalPaper.setText(getString(R.string.font_papers, String.valueOf(Total)));
                        passedPaper.setText(getString(R.string.font_papers, String.valueOf(Passed)));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onRefresh() {
        setContent();
    }

}

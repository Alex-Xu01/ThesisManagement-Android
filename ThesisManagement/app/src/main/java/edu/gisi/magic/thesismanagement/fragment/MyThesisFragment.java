package edu.gisi.magic.thesismanagement.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.activity.MainActivity;
import edu.gisi.magic.thesismanagement.adapter.ThesisListViewAdapter;
import edu.gisi.magic.thesismanagement.config.Urls;
import edu.gisi.magic.thesismanagement.entity.Thesis;
import edu.gisi.magic.thesismanagement.volley.CacheTool;
import edu.gisi.magic.thesismanagement.volley.VolleyManager;

/**
 * 我的论文
 */

public class MyThesisFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private MainActivity activity;
    private View fragment;
    private ListView listView;
    private ThesisListViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static final String TAG = "MyThesisFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragment = inflater.inflate(R.layout.fragment_type_thesis, container, false);
        activity = (MainActivity) this.getActivity();
        initView(fragment);
        requestList();
        return fragment;
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.list_view_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new ThesisListViewAdapter(activity);
        listView.setAdapter(adapter);
        listView.setDivider(null);

    }

    private void requestList() {
        Map<String, String> map = new HashMap<>();
        //TODO 已对应的用户id去查论文列表
        map.put("username", CacheTool.get("username"));
        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.URL_TYPE_QUERY_ALL_ACTIVITY, Thesis.class,
                new Response.Listener<Thesis>() {
                    @Override
                    public void onResponse(Thesis thesis) {
                        swipeRefreshLayout.setRefreshing(false);
                        if (thesis != null) {
                            if ((thesis.getResult().size() != 0) || (thesis.getResult() != null)) {
                                adapter.clear();
                                adapter.appendAndNotify(thesis.getResult());
                            } else {
                                adapter.clear();
                                adapter.notifyDataSetChanged();
                                Toast.makeText(activity, "您还未选择论文", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.clear();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(activity, "论文列表获取失败，无法连接到服务器", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onRefresh() {
        requestList();
    }

}

package edu.gisi.magic.thesismanagement.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.tools.CacheTool;
import edu.gisi.magic.thesismanagement.tools.NetTool;
import edu.gisi.magic.thesismanagement.tools.UrlTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CarFragment extends Fragment {

	private ListView rootListView;
	private JSONArray orderData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_car, null);
		this.initView(view);
		return view;
	}

	public void initView(View view) {
		this.rootListView = (ListView) view.findViewById(R.id.rootListView);
	}

	@Override
	public void onResume() {
		super.onResume();
		Map<String, String> data = new HashMap<String, String>();
		data.put("userId", CacheTool.get("userId"));
		NetTool.post(UrlTool.URL_ORDER_QUERY_BY_USERNAME, new Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				try {
					orderData = new JSONObject(arg0).getJSONArray("result");
					rootListView.setAdapter(new MyAdapter());
				} catch (JSONException e) {
					Toast.makeText(getActivity(), "获取信息失败，服务器返回数据异常", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(getActivity(), "获取信息失败，无法连接到服务器", Toast.LENGTH_LONG).show();
			}
		}, data);
	}

	public class MyAdapter extends BaseAdapter {

		View[] items;

		public MyAdapter() throws JSONException {
			items = new View[orderData.length()];
			for (int i = 0; i < orderData.length(); i++) {
				items[i] = makeItemView(orderData.getJSONObject(i));
			}
		}

		@Override
		public int getCount() {
			return orderData.length();
		}

		@Override
		public Object getItem(int arg0) {
			return items[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			if (arg1 == null)
				return items[arg0];
			return arg1;
		}

		private View makeItemView(JSONObject obj) throws JSONException {
			LayoutInflater inflater = (LayoutInflater) CacheTool.context
					.getSystemService(CacheTool.context.LAYOUT_INFLATER_SERVICE);
			View itemView = inflater.inflate(R.layout.item_order, null);
			TextView titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
			titleTextView.setText(obj.getString("name"));
			TextView dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
			dateTextView.setText("预订日期：" + obj.getString("time"));
			TextView phoneTextView = (TextView) itemView.findViewById(R.id.phoneTextView);
			phoneTextView.setText("预订电话：" + obj.getString("phone"));
			return itemView;
		}

	}

}

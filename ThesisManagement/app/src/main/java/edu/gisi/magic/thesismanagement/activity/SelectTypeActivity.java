package edu.gisi.magic.thesismanagement.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SelectTypeActivity extends Activity {

	private JSONArray typeData;
	private ListView rootListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_type);
		this.rootListView = (ListView) this.findViewById(R.id.rootListView);
		this.rootListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				try {
					CreateOrderActivity.selected_hosue_name = typeData.getJSONObject(position).getString("name");
					CreateOrderActivity.selected_house_id = typeData.getJSONObject(position).getLong("id") + "";
					CreateOrderActivity.selected_house_money = typeData.getJSONObject(position).getInt("price") + "";
					finish();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		NetTool.post(UrlTool.URL_TYPE_QUERY_ALL, new Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				try {
					typeData = new JSONObject(arg0).getJSONArray("result");
					rootListView.setAdapter(new MyAdapter());
				} catch (JSONException e) {
					Toast.makeText(SelectTypeActivity.this, "获取信息失败，服务器返回数据异常", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(SelectTypeActivity.this, "获取信息失败，无法连接到服务器", Toast.LENGTH_LONG).show();
			}
		}, new HashMap<String, String>());
	}

	public class MyAdapter extends BaseAdapter {

		View[] items;

		public MyAdapter() throws JSONException {
			items = new View[typeData.length()];
			for (int i = 0; i < typeData.length(); i++) {
				items[i] = makeItemView(typeData.getJSONObject(i));
			}
		}

		@Override
		public int getCount() {
			return typeData.length();
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
			View itemView = inflater.inflate(R.layout.item_hotel, null);
			ImageView image = (ImageView) itemView.findViewById(R.id.imageView);
			String url = UrlTool.createImageUrl(obj.getString("images").split(",")[0]);
			ImageLoader.getInstance().displayImage(url, image);
			TextView titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
			titleTextView.setText(obj.getString("name"));
			TextView sizeTextView = (TextView) itemView.findViewById(R.id.sizeTextView);
			sizeTextView.setText("面积：" + obj.getInt("size") + "㎡");
			TextView houseTextView = (TextView) itemView.findViewById(R.id.houseTextView);
			houseTextView.setText("户型：" + obj.getString("house"));
			TextView priceTextView = (TextView) itemView.findViewById(R.id.priceTextView);
			priceTextView.setText("价格：" + obj.getInt("price") + "");
			return itemView;
		}

	}

}

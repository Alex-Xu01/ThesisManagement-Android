package edu.gisi.magic.thesismanagement.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import edu.gisi.magic.thesismanagement.R;
import edu.gisi.magic.thesismanagement.tools.CacheTool;
import edu.gisi.magic.thesismanagement.tools.NetTool;
import edu.gisi.magic.thesismanagement.tools.UrlTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("NewApi")
public class CreateOrderActivity extends Activity {

	private ImageView topImageView;
	private Button dateButton;
	private EditText phoneEditText;
	private EditText nameEditText;
	private Button typeButton;
	private TextView priceTextView;
	private Button submitButton;

	private String selected_date;

	public static String selected_hosue_name;
	public static String selected_house_id;
	public static String selected_house_money;

	@Override
	protected void onResume() {
		super.onResume();
		this.typeButton.setText(this.selected_hosue_name == null ? "请选择房型" : selected_hosue_name);
		this.priceTextView
				.setText(this.selected_house_money == null ? "人民币 ： ¥ 0 元" : "人民币 ： ¥ " + selected_house_money + " 元");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_order);

		this.topImageView = (ImageView) findViewById(R.id.topImageView);
		this.dateButton = (Button) findViewById(R.id.dateButton);
		this.phoneEditText = (EditText) findViewById(R.id.phoneEditText);
		this.nameEditText = (EditText) findViewById(R.id.nameEditText);
		this.typeButton = (Button) findViewById(R.id.typeButton);
		this.priceTextView = (TextView) findViewById(R.id.priceTextView);
		this.submitButton = (Button) findViewById(R.id.submitButton);

		this.topImageView.setImageDrawable(getDrawable(R.drawable.hotel1));
		this.dateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.date_picker, null);
				final DatePicker datePicker = (DatePicker) layout.findViewById(R.id.datePicker);
				new AlertDialog.Builder(CreateOrderActivity.this).setTitle("请选择日期和数量").setView(layout)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								selected_date = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-"
										+ datePicker.getDayOfMonth();
								dateButton.setText(selected_date);
							}
						}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
							}
						}).show();
			}
		});
		this.typeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(CreateOrderActivity.this, SelectTypeActivity.class);
				startActivity(intent);
			}
		});

		this.submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String, String> data = new HashMap<String, String>();
				data.put("name", nameEditText.getText().toString());
				data.put("type", selected_house_id);
				data.put("time", dateButton.getText().toString());
				data.put("phone", phoneEditText.getText().toString());
				data.put("user", CacheTool.get("userId"));
				NetTool.post(UrlTool.URL_ORDER_ADD, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						try {
							if (new JSONObject(arg0).getBoolean("result")) {
								Toast.makeText(CreateOrderActivity.this, "酒店预订成功！", Toast.LENGTH_LONG).show();
								finish();
							} else
								Toast.makeText(CreateOrderActivity.this, "酒店预订失败，您的订单未填写完整", Toast.LENGTH_LONG).show();
						} catch (JSONException e) {
							Toast.makeText(CreateOrderActivity.this, "酒店预订失败，服务器返回的数据异常", Toast.LENGTH_LONG).show();
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						Toast.makeText(CreateOrderActivity.this, "酒店预订失败，无法连接到服务器", Toast.LENGTH_LONG).show();
					}
				}, data);
			}
		});
	}
}

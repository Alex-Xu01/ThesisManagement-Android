package edu.gisi.magic.thesismanagement.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import edu.gisi.magic.thesismanagement.R;


@SuppressLint("NewApi")
public class HotelDetailActivity extends Activity {

	private ImageView topImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_detail);
		this.topImageView = (ImageView) findViewById(R.id.topImageView);
		this.topImageView.setImageDrawable(getDrawable(R.drawable.hotel0));
	}

}

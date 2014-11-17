package com.yahoo.sunnyinstagramviewer;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
	public InstagramPhotosAdapter(Context context, List<InstagramPhoto> photos) {
		super(context, R.layout.item_photo, photos);
	}

	// Take a data item at a position, converts it to a raw in the listview
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// take the data source (i.e. 0)
		// get the data item
		InstagramPhoto photo = getItem(position);
		// check if we are using a recycled view
		if (convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
		}	
		// lookup the subview in the template
		TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
		ImageView imgPhoto = (ImageView)convertView.findViewById(R.id.imgPhoto);
		TextView tvUsername = (TextView)convertView.findViewById(R.id.tvUsername);
		// populate the subviews (textfield, imageview) with the correct data
		tvCaption.setText(photo.caption);
		tvUsername.setText(photo.username);
		// set the image height before loading
		imgPhoto.getLayoutParams().height = photo.imageHeight;
		// Reset the image from the recycle view
		imgPhoto.setImageResource(0);
		// Ask for the photo to be added into imageview based on the photo url
		// Background: send a network request to the url , download the image bytes, convert into bitmap, resizing the image, insert bitmap into the imageview
		Picasso.with(getContext()).load(photo.imageUrl).into(imgPhoto);
		// return the view for that data item
		return convertView;
	}

	// getView method (int position)
	// Default, takes the model (InstagramPhoto)

}

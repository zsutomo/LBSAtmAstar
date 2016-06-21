/**
 * 
 */
package id.tugasakhir.zakaria.lbsatmastar.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import id.tugasakhir.zakaria.lbsatmastar.DetailActivity;
import id.tugasakhir.zakaria.lbsatmastar.R;
import id.tugasakhir.zakaria.lbsatmastar.model.Atm;

/**
 * @author zsuto_000
 *
 */
public class DaftarAtmAdapter extends BaseAdapter {
	private Context mContext;
	private List<Atm> mAtmList;
	private ArrayList<Atm> arrayAtmlist;
	private LayoutInflater inflater;

	public  DaftarAtmAdapter(Context mcontext, List<Atm> mAtmList ) {
		this.mContext = mcontext;
		this.mAtmList = mAtmList;
		inflater = LayoutInflater.from(mcontext);
		this.arrayAtmlist = new ArrayList<Atm>();
		this.arrayAtmlist.addAll(mAtmList);
	}

	public class ViewHolder {
		ImageView imgView;
		TextView nama;
		TextView alamat;
		TextView informasi;
		TextView latitude;
		TextView longitude;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAtmList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mAtmList.get(position) ;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mAtmList.get(position).getId();
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.item_listview, null);

			holder.imgView = (ImageView) view.findViewById(R.id.icon);
			holder.nama = (TextView) view.findViewById(R.id.tv_namaAtm);
			holder.alamat = (TextView) view.findViewById(R.id.tv_alamatAtm);
			holder.informasi = (TextView) view.findViewById(R.id.tv_informasi);
			holder.latitude = (TextView) view.findViewById(R.id.tv_latitude);
			holder.longitude = (TextView) view.findViewById(R.id.tv_longitude);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		holder.nama.setText(mAtmList.get(position).getNama());
		holder.alamat.setText(mAtmList.get(position).getAlamat());
		holder.informasi.setText(mAtmList.get(position).getInformasi());
		holder.latitude.setText("Latitude :" +String.valueOf(mAtmList.get(position).getLatitude()));
		holder.longitude.setText("Longitude :" +String.valueOf(mAtmList.get(position).getLongitude()));

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, DetailActivity.class);
				intent.putExtra("nama", (mAtmList.get(position)).getNama());
				intent.putExtra("alamat", (mAtmList.get(position)).getAlamat());
				intent.putExtra("informasi", (mAtmList.get(position)).getInformasi());
				intent.putExtra("latitude", (mAtmList.get(position)).getLatitude());
				intent.putExtra("longitude", (mAtmList.get(position)).getLongitude());
				mContext.startActivity(intent);
			}
		});
		return view;

	}


	public void filter(String text) {
		text = text.toLowerCase(Locale.getDefault());
		mAtmList.clear();
		if(text.length() == 0) {
			mAtmList.addAll(arrayAtmlist);
		} else {
			for (Atm atm : arrayAtmlist) {
				if (atm.getNama().toLowerCase(Locale.getDefault()).contains(text)) {
					mAtmList.add(atm);
				}
			}
		}
		notifyDataSetChanged();
	}
}

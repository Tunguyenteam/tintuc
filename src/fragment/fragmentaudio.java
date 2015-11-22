package fragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.tin.MainActivity;
import com.example.tin.R;

public class fragmentaudio extends Fragment {
	LinearLayout layoutcontrol;
	public static MediaPlayer mediaPlayer = new MediaPlayer();
	public ListView lvaudio;
	String urlmp3, getlinkmp3, gettitleaudio;
	public ListAdapter adtlvaudio;
	String[] arlinkmp3, artitle;
	int arg2;
	int index;
	Timer timer = new Timer();
	ArrayList<HashMap<String, String>> arrlvaudio = new ArrayList<HashMap<String, String>>();
	public static ArrayList<String> arrlinkmp3 = new ArrayList<String>();
	public static ArrayList<String> arrtitle = new ArrayList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_layoutaudio, container,
				false);
		lvaudio = (ListView) view.findViewById(R.id.listViewtabaudio);
		String icon = "icon", title = "title";
		String from[] = { icon, title };
		int to[] = { R.id.iconlvfragment, R.id.titlelvfragment };
		adtlvaudio = new SimpleAdapter(getActivity(),
				arrlvaudio, R.layout.costumlvfragment, from, to);
		final String linkjsonaudio = MainActivity.linkjsonaudio;
		new redjson().execute(linkjsonaudio);
	
		lvaudio.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView tv = (TextView) arg1
						.findViewById(R.id.titlelvfragment);
				tv.setTextColor(Color.RED);
				arlinkmp3 = arrlinkmp3.get(arg2).toString().split("\t");
				for (int i = 0; i < arlinkmp3.length; i++) {
					getlinkmp3 = arlinkmp3[i];

				}
				artitle = arrtitle.get(arg2).toString().split("\t");
				for (int i = 0; i < artitle.length; i++) {
					gettitleaudio = artitle[i];

				}
				index = arg2;
				MainActivity.titletin.setText(gettitleaudio);
				audioplay();
				mediacontrol();

			}
		});

		return view;
	}

	public void mediacontrol() {

		MainActivity.back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				index--;
				if (index > -1) {
					arlinkmp3 = arrlinkmp3.get(index).toString().split("\t");
					for (int i = 0; i < arlinkmp3.length; i++) {
						getlinkmp3 = arlinkmp3[i];
					}
					artitle = arrtitle.get(index).toString().split("\t");
					for (int i = 0; i < artitle.length; i++) {
						gettitleaudio = artitle[i];
					}

				}
				if (index < 0) {
					index++;
				}
				audioplay();
				MainActivity.titletin.setText(gettitleaudio);

			}
		});
		MainActivity.next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				index++;
				if (index < arrlinkmp3.size()) {
					arlinkmp3 = arrlinkmp3.get(index).toString().split("\t");
					for (int i = 0; i < arlinkmp3.length; i++) {
						getlinkmp3 = arlinkmp3[i];
					}
					artitle = arrtitle.get(index).toString().split("\t");
					for (int i = 0; i < artitle.length; i++) {
						gettitleaudio = artitle[i];
					}

				}
				if (index > arrlinkmp3.size()) {
					index--;
				}
				audioplay();
				MainActivity.titletin.setText(gettitleaudio);

			}
		});

	}

	public void audioplay() {
		mediaPlayer.reset();
		String urlmp3 = getlinkmp3 + "";
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaPlayer.setDataSource(urlmp3);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediaPlayer.start();
		MainActivity.layoutcontrol.setVisibility(layoutcontrol.VISIBLE);
		MainActivity.playpause.setBackgroundResource(R.drawable.iconpause);
		MainActivity.playpause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.pause();
					MainActivity.playpause
							.setBackgroundResource(R.drawable.iconplay);
				} else {
					mediaPlayer.start();
					MainActivity.playpause
							.setBackgroundResource(R.drawable.iconpause);
				}

			}
		});
	}

	class redjson extends AsyncTask<String, Integer, String> {
		

		@Override
		protected String doInBackground(String... params) {
			String chuoi = getxmlfromurl(params[0]);
			return chuoi;
		}

		@Override
		protected void onPostExecute(String result) {
			try {
				arrlinkmp3.clear();
				arrtitle.clear();
				JSONObject root = new JSONObject(result);
				JSONArray arrroot = root.getJSONArray("tinmoi");
				for (int i = 0; i < arrroot.length(); i++) {
					JSONObject son = arrroot.getJSONObject(i);
					HashMap<String, String> hm = new HashMap<String, String>();
					hm.put("icon", String.valueOf(R.drawable.headphone));
					hm.put("title", son.getString("title"));
					arrlinkmp3.add(son.getString("link"));
					arrtitle.add(son.getString("title"));
					arrlvaudio.add(hm);
					lvaudio.setAdapter(adtlvaudio);

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onPostExecute(result);
		}

	}

	private String getxmlfromurl(String urlString) {
		String xml = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(urlString);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			xml = EntityUtils.toString(httpEntity, HTTP.UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xml;
	}

}

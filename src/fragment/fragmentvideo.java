package fragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

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

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.tin.MainActivity;
import com.example.tin.R;
import com.example.tin.activitydoc;
import com.example.tin.activityvideo;





public class fragmentvideo extends Fragment{
	public ListView lvvideo;
	public ListAdapter adtlvread;
	String[] arlinkvideo, artitlevideo;
	activitydoc activitydoc = new activitydoc();
	String urlvideo, getlinkvideo,gettitlevideo;
	fragmentaudio fragmentaudio = new fragmentaudio();
	ArrayList<String> arrlinkvideo = new ArrayList<String>();
	ArrayList<String> arrtitlevideo = new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_layoutvideo, container, false);
		String linkjsonvideo = MainActivity.linkjsonvideo;
		new redjson().execute(linkjsonvideo);
		lvvideo = (ListView) view.findViewById(R.id.listViewtabvideo);
		lvvideo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView tv = (TextView)arg1.findViewById(R.id.titlelvfragment);
				tv.setTextColor(Color.RED);
				arlinkvideo = arrlinkvideo.get(arg2).toString().split("\t");
				for (int i = 0; i < arlinkvideo.length; i++) {
					getlinkvideo= arlinkvideo[i];

				}
				artitlevideo = arrtitlevideo.get(arg2).toString().split("\t");
				for (int i = 0; i < artitlevideo.length; i++) {
					gettitlevideo = artitlevideo[i];

				}
				fragmentaudio.mediaPlayer.pause();
				MainActivity.playpause.setBackgroundResource(R.drawable.iconplay);
				Intent intentvideo = new Intent(getActivity(),activityvideo.class);
				intentvideo.putExtra("title", gettitlevideo);
				intentvideo.putExtra("linkvideo", getlinkvideo);
				startActivity(intentvideo);

			}
		});
		return view;
	}
	class redjson extends AsyncTask<String, Integer, String> {
		String icon = "icon", title = "title";
		String from[] = { icon, title };
		int to[] = { R.id.iconlvfragment, R.id.titlelvfragment };
		ArrayList<HashMap<String, String>> arrlvvideo = new ArrayList<HashMap<String, String>>();
		public SimpleAdapter adtlvvideo = new SimpleAdapter(getActivity(),
				arrlvvideo, R.layout.costumlvfragment, from, to);

		@Override
		protected String doInBackground(String... params) {
			String chuoi = getxmlfromurl(params[0]);
			return chuoi;
		}

		@Override
		protected void onPostExecute(String result) {
			try {
				arrlinkvideo.clear();
				arrtitlevideo.clear();
				JSONObject root = new JSONObject(result);
				JSONArray arrroot = root.getJSONArray("tinmoi");
				for (int i = 0; i < arrroot.length(); i++) {
					JSONObject son = arrroot.getJSONObject(i);
					HashMap<String, String> hm = new HashMap<String, String>();
					hm.put("icon", String.valueOf(R.drawable.video));
					hm.put("title", son.getString("title"));
					arrtitlevideo.add(son.getString("title"));
					arrlinkvideo.add(son.getString("linkmp4"));
					arrlvvideo.add(hm);
					lvvideo.setAdapter(adtlvvideo);

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
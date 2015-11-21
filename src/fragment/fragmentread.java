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
import android.widget.Toast;

import com.example.tin.MainActivity;
import com.example.tin.R;
import com.example.tin.activitydoc;




public class fragmentread extends Fragment{
	public ListView lvread;
	String urlread, getcontentread,gettitleread;
	public ListAdapter adtlvread;
	String[] arcontent, artitle;
	activitydoc activitydoc = new activitydoc();
	ArrayList<String> arrcontentread = new ArrayList<String>();
	ArrayList<String> arrtitleread = new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_layoutread, container, false);
		String linkjsonread = MainActivity.linkjsonread;
		new redjson().execute(linkjsonread);
		lvread = (ListView) view.findViewById(R.id.listViewtabread);
		lvread.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView tv = (TextView)arg1.findViewById(R.id.titlelvfragment);
				tv.setTextColor(Color.RED);
				arcontent = arrcontentread.get(arg2).toString().split("\t");
				for (int i = 0; i < arcontent.length; i++) {
					getcontentread = arcontent[i];

				}
				artitle = arrtitleread.get(arg2).toString().split("\t");
				for (int i = 0; i < artitle.length; i++) {
					gettitleread = artitle[i];

				}
				
				Intent intentread = new Intent(getActivity(),activitydoc.class);
				intentread.putExtra("title", gettitleread);
				intentread.putExtra("content", getcontentread);
				startActivity(intentread);

			}
		});
		return view;
	}
	class redjson extends AsyncTask<String, Integer, String> {
		String icon = "icon", title = "title";
		String from[] = { icon, title };
		int to[] = { R.id.iconlvfragment, R.id.titlelvfragment };
		ArrayList<HashMap<String, String>> arrlvread = new ArrayList<HashMap<String, String>>();
		public SimpleAdapter adtlvread = new SimpleAdapter(getActivity(),
				arrlvread, R.layout.costumlvfragment, from, to);

		@Override
		protected String doInBackground(String... params) {
			String chuoi = getxmlfromurl(params[0]);
			return chuoi;
		}

		@Override
		protected void onPostExecute(String result) {
			try {
				arrcontentread.clear();
				arrtitleread.clear();
				JSONObject root = new JSONObject(result);
				JSONArray arrroot = root.getJSONArray("tinmoi");
				for (int i = 0; i < arrroot.length(); i++) {
					JSONObject son = arrroot.getJSONObject(i);
					HashMap<String, String> hm = new HashMap<String, String>();
					hm.put("icon", String.valueOf(R.drawable.read));
					hm.put(title, son.getString("title"));
					arrtitleread.add(son.getString("title"));
					arrcontentread.add(son.getString("content"));
					arrlvread.add(hm);
					lvread.setAdapter(adtlvread);

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

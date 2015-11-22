package com.example.tin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import service.mediaservice;
import service.updateservice;

import android.R.color;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import fragment.fragmentaudio;
import fragment.fragmentread;
import fragment.fragmentvideo;
import fragmentadapter.fragmentadapter;



public class MainActivity extends FragmentActivity {
	int current;
	ViewPager viewPager;
	public static String titleactionbar;
	public static String linkjsonaudio = "http://nghetruyenapp.16mb.com/tinmoi.json";
	public static String linkjsonread = "http://nghetruyenapp.16mb.com/tamsu.json";
	public static String linkjsonvideo = "http://nghetruyenapp.16mb.com/giaitri.json";
	public static LinearLayout layouttab,layoutcontrol;
	DrawerLayout drawerLayout;
	public static ImageView back,playpause,next;
	boolean ckclickactionbar = false;
	public static TextView tvaudio, tvread, tvvideo, titletin;
	ActionBarDrawerToggle actionBarDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(MainActivity.this,updateservice.class);
		startService(intent);
		playpause = (ImageView)findViewById(R.id.buttonplaypause);
		back = (ImageView)findViewById(R.id.buttonback);
		next = (ImageView)findViewById(R.id.buttonnext);
		titletin = (TextView)findViewById(R.id.textViewtitletin);
		layoutcontrol = (LinearLayout)findViewById(R.id.layoutcontrol);
		
		// khởi tạo viewpager
		viewPager = (ViewPager) findViewById(R.id.pager);
		layouttab = (LinearLayout) findViewById(R.id.layouttab);
		tvaudio = (TextView) layouttab.findViewById(R.id.textViewaudio);
		tvread = (TextView) layouttab.findViewById(R.id.textViewread);
		tvvideo = (TextView) layouttab.findViewById(R.id.textViewvideo);
		// khởi tạo ListFragment
		List<Fragment> listFragments = new ArrayList<Fragment>();
		// add fragment vào list
		listFragments.add(new fragmentaudio());
		listFragments.add(new fragmentread());
		listFragments.add(new fragmentvideo());
		// khởi tạo adapter fragment
		fragmentadapter fragmentadapter = new fragmentadapter(
				getSupportFragmentManager(), listFragments);
		viewPager.setAdapter(fragmentadapter);

		drawermenuevent();
		menudrawer();
		selecttab();
		
	}

	public void menudrawer() {
		int icondrawer[] = { R.drawable.society, R.drawable.law,
				R.drawable.business, R.drawable.military, R.drawable.sports,
				R.drawable.entertain, R.drawable.heart,
				R.drawable.strange, R.drawable.smile };
		String titledrawer[] = { "Tin Thời Sự", "Tin Pháp Luật",
				"Tin Kinh Doanh", "Tin Quân Sự", "Thể Thao", "Giải Trí", "Tâm Sự", "Chuyện Lạ", "Cười" };
		String titlelvmenu = "titlelvmenu", iconlvmenu = "iconlvmenu";
		// khởi tạo Arraylist cho listview menu
		ArrayList<HashMap<String, String>> arrlvmenu = new ArrayList<HashMap<String, String>>();
		// khởi tạo adapter
		SimpleAdapter adtlvmenu = new SimpleAdapter(this, arrlvmenu,
				R.layout.costumlvmenu,
				new String[] { iconlvmenu, titlelvmenu }, new int[] {
						R.id.imageViewiconmenu, R.id.textViewlvmenu });
		ListView lvmenu = (ListView) findViewById(R.id.lvdrawermenu);
		// khởi tạo hashmap và icon,text vào arrlist
		for (int i = 0; i < titledrawer.length; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(iconlvmenu, String.valueOf(icondrawer[i]));
			hm.put(titlelvmenu, titledrawer[i]);
			arrlvmenu.add(hm);
		}
		lvmenu.setAdapter(adtlvmenu);
		lvmenu.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					linkjsonaudio ="http://nghetruyenapp.16mb.com/tinmoi.json";
					titleactionbar = "Tin Xã Hội";
					current=0;
					break;
				case 1:
					linkjsonaudio = "http://nghetruyenapp.16mb.com/phapluat.json";
					titleactionbar = "Tin Pháp Luật";
					current=0;
					break;
				case 2:
					linkjsonaudio = "http://nghetruyenapp.16mb.com/kinhdoanh.json";
					titleactionbar = "Tin Kinh Doanh";
					current=0;
					break;
				case 3:
					linkjsonaudio = "http://nghetruyenapp.16mb.com/quansu.json";
					titleactionbar = "Tin Quân Sự";
					current=0;
					break;
				case 4:
					linkjsonread = "http://nghetruyenapp.16mb.com/thethao.json";
					titleactionbar = "Thể Thao";
					current=1;
					break;
				case 5:
					linkjsonvideo = "http://nghetruyenapp.16mb.com/giaitri.json";
					titleactionbar = "Giải Trí";
					current=2;
					break;
				case 6:
					linkjsonread = "http://nghetruyenapp.16mb.com/tamsu.json";
					titleactionbar = "Tâm Sự";
					current=1;
					break;
				case 7:
					linkjsonvideo = "http://nghetruyenapp.16mb.com/chuyenla.json";
					titleactionbar = "Chuyện Lạ";
					current=2;
					break;
				case 8:
					linkjsonvideo = "http://nghetruyenapp.16mb.com/cuoi.json";
					titleactionbar = "Cười";
					current=2;
					break;

				default:
					break;
				}
				
				List<Fragment> listFragments = new ArrayList<Fragment>();
				// add fragment vào list
				listFragments.add(new fragmentaudio());
				listFragments.add(new fragmentread());
				listFragments.add(new fragmentvideo());
				// khởi tạo adapter fragment
				fragmentadapter fragmentadapter = new fragmentadapter(
						getSupportFragmentManager(), listFragments);
				viewPager.setAdapter(fragmentadapter);
				linkjsonaudio = linkjsonaudio;
				linkjsonread = linkjsonread;
				linkjsonvideo = linkjsonvideo;
				getActionBar().setTitle(titleactionbar);
				drawerLayout.closeDrawers();
				viewPager.setCurrentItem(current);
				if(current==0){
					selecttab();
				}
			}

		});
	}

	public void selecttab() {
		tvaudio.setBackgroundResource(R.drawable.costumtab);
		tvread.setBackgroundColor(color.holo_green_light);
		tvvideo.setBackgroundColor(color.holo_green_light);
		tvaudio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(0);

			}
		});
		tvread.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(1);

			}
		});
		tvvideo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(2);

			}
		});
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
						switch (position) {
						case 0:
							tvaudio.setBackgroundResource(R.drawable.costumtab);
							tvread.setBackgroundColor(color.holo_green_light);
							tvvideo.setBackgroundColor(color.holo_green_light);
							break;
						case 1:
							tvaudio.setBackgroundColor(color.holo_green_light);
							tvread.setBackgroundResource(R.drawable.costumtab);
							tvvideo.setBackgroundColor(color.holo_green_light);
							break;
						case 2:
							tvaudio.setBackgroundColor(color.holo_green_light);
							tvread.setBackgroundColor(color.holo_green_light);
							tvvideo.setBackgroundResource(R.drawable.costumtab);
							break;

						default:
							break;
						}
					}
				});
	}

	public void drawermenuevent() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawermenu);
		getActionBar().setIcon(R.drawable.menu);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayUseLogoEnabled(true);
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.dot, 0, 0) {

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setIcon(R.drawable.backmenu);
				super.onDrawerOpened(drawerView);
				ckclickactionbar = true;
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				getActionBar().setIcon(R.drawable.menu);
				super.onDrawerClosed(drawerView);
				ckclickactionbar = false;
			}
		};
		actionBarDrawerToggle.syncState();
		drawerLayout.setDrawerListener(actionBarDrawerToggle);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			break;

		default:
			if (ckclickactionbar == false) {
				drawerLayout.openDrawer(Gravity.LEFT);
				getActionBar().setIcon(R.drawable.backmenu);
				getActionBar().setTitle("Danh Mục");
				ckclickactionbar = true;
			} else {
				drawerLayout.closeDrawer(Gravity.LEFT);
				getActionBar().setIcon(R.drawable.menu);
				ckclickactionbar = false;
			}
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
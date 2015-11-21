package fragmentadapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class fragmentadapter extends FragmentPagerAdapter{
	List<Fragment> listFragments;
	public fragmentadapter(FragmentManager fm,List<Fragment> listFragments) {
		super(fm);
		this.listFragments = listFragments;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return listFragments.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listFragments.size();
	}

}

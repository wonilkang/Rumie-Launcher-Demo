package com.jsdev.ruime.adapters;

import java.util.ArrayList;
import java.util.List;

import com.jsdev.ruime.ListHelper;
import com.jsdev.ruime.R;
import com.jsdev.ruime.fragments.AdminGrid;
import com.jsdev.ruime.fragments.BasicGrid;
import com.jsdev.ruime.fragments.GameGrid;
import com.jsdev.ruime.structures.AppInfo;
import com.jsdev.rumie.utility.PrefsHelper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class LauncherAdapter extends FragmentPagerAdapter{
	
	private Context context;

	public LauncherAdapter(FragmentManager fm, Context context) {
		super(fm);
		
		this.context = context;
		
		System.out.println("Size: " + PrefsHelper.getPackages(context).size());
	}

	@Override
	public Fragment getItem(int position) {
		System.out.println("Selecting Item");
		switch (position) {
		case 0:
			return BasicGrid.createFragment(filterEducation());
		case 1:
			return GameGrid.createFragment(filterGames());
		case 2:
			return AdminGrid.createFragment(filterAdmin(), context.getResources().getDrawable(R.drawable.keyicon));
		}
		
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return context.getString(R.string.learn);
			case 1:
				return context.getString(R.string.play);
			case 2:
				return context.getString(R.string.admin);
		}
		
		return null;
	}
	
	private List<AppInfo> filterAdmin() {
		List<AppInfo> apps = new ArrayList<AppInfo>();
		List<AppInfo> packs = PrefsHelper.getPackages(context);
		
		for (int i = 0; i < packs.size(); i++)  {
			if (ListHelper.containsPackage(context, packs.get(i).getPackageName(), ListHelper.LIST_ADMIN)) {
				apps.add(packs.get(i));
				System.out.println(packs.get(i).getPackageName());
			}
		}
		
		return apps;
	}
	
	private List<AppInfo> filterEducation() {
		List<AppInfo> apps = new ArrayList<AppInfo>();
		List<AppInfo> packs = PrefsHelper.getPackages(context);

		for (int i = 0; i < packs.size(); i++)  {
			if (ListHelper.containsPackage(context, packs.get(i).getPackageName(), ListHelper.LIST_EDUCATION)) {
				apps.add(packs.get(i));
				System.out.println(packs.get(i).getPackageName());
			}
		}
		return apps;
	}
	
	private List<AppInfo> filterGames() {
		List<AppInfo> apps = new ArrayList<AppInfo>();
		List<AppInfo> packs = PrefsHelper.getPackages(context);

		for (int i = 0; i < packs.size(); i++)  {
			if (ListHelper.containsPackage(context, packs.get(i).getPackageName(), ListHelper.LIST_GAMES)) {
				System.out.println(packs.get(i).getPackageName());
				apps.add(packs.get(i));
			}
		}
		return apps;
	}
}

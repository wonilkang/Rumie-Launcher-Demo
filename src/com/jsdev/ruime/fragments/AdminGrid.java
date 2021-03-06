package com.jsdev.ruime.fragments;

import java.util.List;

import com.jsdev.ruime.CodeGenerator;
import com.jsdev.ruime.MainActivity;
import com.jsdev.ruime.R;
import com.jsdev.ruime.adapters.GridAdapter;
import com.jsdev.ruime.structures.AppInfo;
import com.jsdev.rumie.utility.PrefsHelper;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

public class AdminGrid extends Fragment{

	private GridAdapter gridAdapter;
	
	private List<AppInfo> appList;
	
	private RelativeLayout adminOverlay;
	private EditText adminPass;
	private Button adminSubmit;
	
	private boolean loggedIn = false;
	
	public static AdminGrid createFragment(List<AppInfo> apps, Drawable icon) {
		AppInfo codeApp = new AppInfo("Code Generator", "com.jsdev.ruime.CodeGenerator", icon);
		if (icon == null)
			System.out.println("Icon is null");
		apps.add(codeApp);

		AdminGrid fragment = new AdminGrid();
		fragment.setApps(apps);
		
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.grid_admin, null);
		
		if (appList == null) {
			appList = PrefsHelper.getPackages(getActivity());
		}
		else {
			System.out.println("Admin size: " + appList.size());
			
			GridView grid = (GridView) view.findViewById(R.id.adminGrid);
			gridAdapter = new GridAdapter(getActivity(), appList);
			
			adminPass = (EditText) view.findViewById(R.id.adminEdit);
			adminOverlay = (RelativeLayout) view.findViewById(R.id.adminOverlay);
			adminSubmit = (Button) view.findViewById(R.id.adminSubmit);
			
			grid.setAdapter(gridAdapter);
			
			adminSubmit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String pass = adminPass.getText().toString().trim();
					System.out.println("Pass: " + pass);
					
					if (pass.equals("rumie1984")) {
						loggedIn = true;
						adminOverlay.setVisibility(View.GONE);
						adminPass.setText("");
					}
					else {
						Toast.makeText(getActivity(), "Incorrect password.", Toast.LENGTH_LONG).show();
						adminPass.setText("");
					}
				}
			});
			
			grid.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
					if (loggedIn) {
						if (pos != gridAdapter.getCount() - 1) {
							AppInfo app = (AppInfo) gridAdapter.getItem(pos);
							
							Intent startApp = new Intent();
							ComponentName component = new ComponentName(app.getPackageName(), app.getClassName());
							startApp.setComponent(component);
							startApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startApp.setAction(Intent.ACTION_MAIN);
							
							startActivity(startApp);
						}
						else {
							Intent startCode = new Intent(getActivity(), CodeGenerator.class);
							startCode.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
							startActivity(startCode);
						}
					}
				}
			});
		}
		
		return view;
	}
	
	protected void setApps(List<AppInfo> apps) {
		appList = apps;
	}
}

package com.example.prototypeunddialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;


//ist anzugeben in der Manifest als attribut android:name im application-tag
public class AppGlobal extends Application{

	public final static int ACT_MAIN = 1;
	public final static int ACT_SECOND = 2;
	public final static int ACT_SETTINGS = 3;
	
	
	List <CDArtikel> liArtikel = new ArrayList<CDArtikel>();
	public int iSelection = -1;
	
	
	
	int iLastActivity = -1;
	int iActiveActivity = -1;
	List <Activity> liAct = new ArrayList<Activity>();
	
	protected void register(Activity act) {
		liAct.add(act);
		iLastActivity = iActiveActivity;
	}
	
	public AppGlobal() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void finishAll () {
		for (Activity act : liAct) {
			try {
				act.finish();
				act = null;
			}catch (Exception e) {
				
			}
			System.exit(0);
		}
	}
	
}

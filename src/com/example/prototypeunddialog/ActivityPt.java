package com.example.prototypeunddialog;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class ActivityPt extends ActionBarActivity {

	AppGlobal myApp;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = (AppGlobal) getApplication();
        myApp.register(this);
    }
	
    protected void onCreate(Bundle savedInstanceState, int iActType) {
        super.onCreate(savedInstanceState);
        myApp = (AppGlobal) getApplication();
        myApp.register(this);
        myApp.iActiveActivity = iActType;
    }
	
}

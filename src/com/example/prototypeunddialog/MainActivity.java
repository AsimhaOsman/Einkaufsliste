package com.example.prototypeunddialog;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActivityPt implements OnItemClickListener {

	ListView lvEKList;
	AdaptArtikel arradp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, myApp.ACT_MAIN);
		setContentView(R.layout.activity_main);

		lvEKList = (ListView) findViewById(R.id.lvEKList);
		doDummy();
		
	}
	
	void doDummy() {
		myApp.liArtikel.add(new CDArtikel("Gurken", "2", "kg"));
		myApp.liArtikel.add(new CDArtikel("Kartoffeln", "1", "kg"));
		myApp.liArtikel.add(new CDArtikel("Teebeutel", "10", "stk"));
		myApp.liArtikel.add(new CDArtikel("Fisch", "2", "kg"));
		myApp.liArtikel.add(new CDArtikel("Tanne", "1", "stk"));
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		fillEinkaufsliste();
	}

	void fillEinkaufsliste() {
//		arradp = new ArrayAdapter(this, //context
//				android.R.layout.simple_list_item_1, //layouttyp für die listeneinträge
//				myApp.liArtikel);			//liste
		arradp = new AdaptArtikel(this, //context
				R.layout.data_shopping, //wunschlayout
				myApp.liArtikel);		//datenquelle (hier: liste)
		lvEKList.setAdapter(arradp);
		lvEKList.setItemsCanFocus(true);
		lvEKList.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		myApp.iSelection = position;
		buildListClickDialog();
	}	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// myApp.finishAll();
			buildExitDialog();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void buildListClickDialog() {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		alertDialogBuilder.setTitle("Eintrag ausgewählt");
		alertDialogBuilder
				.setMessage("Was soll mit diesem Eintrag geschehen?");
		alertDialogBuilder.setCancelable(false); // damit dlg nicht per zurückbt
													// beendet werden kann
		alertDialogBuilder.setIcon(R.drawable.ic_launcher);

		alertDialogBuilder.setPositiveButton("Gekauft",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//todo: statusänderunge statt löschen
						myApp.liArtikel.get(myApp.iSelection).setbGekauft(true); //als gekauft markieren
//						myApp.liArtikel.remove(myApp.iSelection); //aus liste löschen
						arradp.notifyDataSetChanged(); //adapter über änderung informieren
						myApp.iSelection = -1;
					}
				});
		alertDialogBuilder.setNeutralButton("Editieren",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(MainActivity.this, ActSecond.class);
						startActivity(intent);
					}
				});

		alertDialogBuilder.setNegativeButton("Abbrechen",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						myApp.iSelection = -1;
						;
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}
	private void buildExitDialog() {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		alertDialogBuilder.setTitle("Wirklich beenden?");
		alertDialogBuilder
				.setMessage("Möchten Sie diese wunderbare App wirklich beenden?");
		alertDialogBuilder.setCancelable(false); // damit dlg nicht per zurückbt
													// beendet werden kann
		alertDialogBuilder.setIcon(R.drawable.ic_launcher);

		alertDialogBuilder.setPositiveButton("Ja",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finishApp();
					}
				});
		alertDialogBuilder.setNegativeButton("Nein",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// App nicht beenden
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	private void finishApp() {
		myApp.finishAll();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			return true;
		}

		if (id == R.id.action_mainfn1) {
			Intent intent = new Intent(MainActivity.this, ActSecond.class);
			startActivity(intent);
		}
		if (id == R.id.action_mainfn2) {
			Toast.makeText(this, "Fn 2 gewählt", Toast.LENGTH_LONG).show();
		}
		if (id == R.id.action_mainfn3) {
			Toast.makeText(this, "Fn 3 gewählt", Toast.LENGTH_LONG).show();
		}

		if (id == R.id.action_exit) {
			this.buildExitDialog();
		}
		return super.onOptionsItemSelected(item);
	}

}

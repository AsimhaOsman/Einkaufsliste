package com.example.prototypeunddialog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ActSecond extends ActivityPt implements OnClickListener {

	Button btSave, btCancel;
	EditText etMenge, etArtikel;
	CheckBox cbGekauft;
	RadioButton rbLtr, rbKg, rbPkg, rbSt;
	RadioGroup rgEinheiten;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, myApp.ACT_SECOND);
		setContentView(R.layout.activity_act_second);

		btSave = (Button) findViewById(R.id.btSave);
		btCancel = (Button) findViewById(R.id.btCancel);

		etMenge = (EditText) findViewById(R.id.etMenge);
		etArtikel = (EditText) findViewById(R.id.etArtikelName);
		
		cbGekauft = (CheckBox) findViewById(R.id.cbGekauftEdit);
		//checkbox via setChecked setzen und isChecked auswerten

		rbLtr = (RadioButton) findViewById(R.id.rbLtr);
		rbKg = (RadioButton) findViewById(R.id.rbKG);
		rbSt = (RadioButton) findViewById(R.id.rbSt);
		rbPkg = (RadioButton) findViewById(R.id.rbPkg);
		
		rgEinheiten = (RadioGroup) findViewById(R.id.radioMasseinheit);
//		int selected = rgEinheiten.getCheckedRadioButtonId();
//		RadioButton rbSel = (RadioButton) findViewById(selected);
//		rbSel.getText();
//		if (selected == R.id.rbKG) {/*dann hier kilogramm*/}
//		if (selected == R.id.rbLtr) {/*dann hier liter*/}

		
		btSave.setOnClickListener(this);
		btCancel.setOnClickListener(this);
		
		if (myApp.iSelection >= 0) {
			//ein objekt wurde zum editieren ausgewählt
			etMenge.setText(myApp.liArtikel.get(myApp.iSelection).getsMenge());
			etArtikel.setText(myApp.liArtikel.get(myApp.iSelection).getsArtikel());
			cbGekauft.setChecked(myApp.liArtikel.get(myApp.iSelection).getbGekauft()); //cb setzen
			String sEinheit = myApp.liArtikel.get(myApp.iSelection).getsEinheit(); //einheit lesen
			if (sEinheit.compareTo("kg") == 0) {
				rbKg.setChecked(true);
			}
			if (sEinheit.compareTo("ltr") == 0) {
				rbLtr.setChecked(true);
			}			
			if (sEinheit.compareTo("stk") == 0) {
				rbSt.setChecked(true);
			}
			if (sEinheit.compareTo("pkg") == 0) {
				rbPkg.setChecked(true);
			}
			
		}
	}
	
	@Override
	public void onClick(View v) {
		if (v == btSave) {
			String sMenge = etMenge.getText().toString();
			String sArtikel= etArtikel.getText().toString();
			etMenge.setText("");
			etArtikel.setText("");
			
			String sEinheit = "-";
			if (rbLtr.isChecked()) {sEinheit = "ltr";}
			if (rbKg.isChecked()) {sEinheit = "kg";}
			if (rbSt.isChecked()) {sEinheit = "stk";}
			if (rbPkg.isChecked()) {sEinheit = "pkg";}
			if (myApp.iSelection >= 0) {
				//editieren
				myApp.liArtikel.get(myApp.iSelection).setsMenge(sMenge);
				myApp.liArtikel.get(myApp.iSelection).setsArtikel(sArtikel);
				myApp.liArtikel.get(myApp.iSelection).setsEinheit(sEinheit);
				myApp.liArtikel.get(myApp.iSelection).setbGekauft(cbGekauft.isChecked()); //cb auslesen
				myApp.iSelection = -1; //editieren fertig
				this.finish();
			} else {
				//neuen Artikel zur Einkaufsliste hinzu fügen
				myApp.liArtikel.add(new CDArtikel(sArtikel, sMenge, sEinheit));
			}
		}
		if (v == btCancel) {
			this.finish(); //diese act schliessen und somit zurück zu der im stack darunter liegenden
		}
	}
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

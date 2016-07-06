p0ackage com.example.prototypeunddialog;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AdaptArtikel extends ArrayAdapter {

	Context ctx;
	int layout;
	List<CDArtikel> lData;

	public AdaptArtikel(Context context, int layout, List<CDArtikel> datenquelle) {
		super(context, layout, datenquelle);
		this.ctx = context;
		this.layout = layout;
		this.lData = datenquelle;
	}

	////////////////////////////////////////////////
	// Variante mit ViewHolder-Pattern
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View zeilenView = convertView;

		if (zeilenView == null) { //wenn zeilenView null
			// layout-erstellungswerkzeug
			LayoutInflater inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// nun eine Layout-Instanz beschaffen
			zeilenView = inflater.inflate(layout, parent, false);

			ViewHolder viewHolder =new ViewHolder();

			// die Felder verfügbar machen & den ViewHolder konfigurieren
			viewHolder.tvMenge = (TextView) zeilenView.findViewById(R.id.tvMenge);
			viewHolder.tvEinheit = (TextView) zeilenView.findViewById(R.id.tvEinheit);
			viewHolder.tvArtikel = (TextView) zeilenView.findViewById(R.id.tvArtikel);
			viewHolder.cbGekauft = (CheckBox) zeilenView.findViewById(R.id.cbGekauft);
			
			zeilenView.setTag(viewHolder);
		} else { //wenn bereits eine entsprechende View existiert und wiederverwendet werden kann:
			//dann do nothing
		}
		
		// /////// Daten in Felder
		// dan ViewHolder in den Zugriff nehmen
		ViewHolder holder = (ViewHolder) zeilenView.getTag();

		// Datensatz zu "position" lesen
		CDArtikel tArt = lData.get(position); // per get(position) die Referenz
												// zum Datensatz beschaffen

		// Werte des Datensatzes in Layoutelemente schreiben
		holder.tvMenge.setText(tArt.getsMenge());
		holder.tvArtikel.setText(tArt.getsArtikel());
		holder.tvEinheit.setText(tArt.getsEinheit());
		holder.cbGekauft.setChecked(tArt.getbGekauft()); //haken oder nicht haken
//
//		wenn checkbox und onitemclick verwendet werden sollen dann bitte im layout-xml
//        android:focusable="false"
//        android:focusableInTouchMode="false"
//        in das checkbox-tag einfügen
		
		return zeilenView;
	}
	//View Holder Pattern
	static class ViewHolder {					//in diesem Fall:  AdaptArtikel.ViewHolder 
		public TextView tvMenge;
		public TextView tvEinheit;
		public TextView tvArtikel;
		public CheckBox cbGekauft;
	}

	// //ohne holder pattern
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// //layout-erstellungswerkzeug
	// LayoutInflater inflater =
	// (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// //nun eine Layout-Instanz beschaffen
	// View zeilenView = inflater.inflate(layout, parent, false);
	//
	// //die Felder verfügbar machen
	// TextView tvMenge = (TextView) zeilenView.findViewById(R.id.tvMenge);
	// TextView tvEinheit=(TextView) zeilenView.findViewById(R.id.tvEinheit);
	// TextView tvArtikel=(TextView) zeilenView.findViewById(R.id.tvArtikel);
	//
	// //Datensatz zu "position" lesen
	// CDArtikel tArt = lData.get(position); //per get(position) die Referenz
	// zum Datensatz beschaffen
	//
	// //Werte des Datensatzes in Layoutelemente schreiben
	// tvMenge.setText(tArt.getsMenge());
	// tvArtikel.setText(tArt.getsArtikel());
	//
	// return zeilenView;
	// }

}

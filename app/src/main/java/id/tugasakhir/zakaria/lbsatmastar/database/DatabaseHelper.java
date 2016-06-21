/**
 * 
 */
package id.tugasakhir.zakaria.lbsatmastar.database;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.tugasakhir.zakaria.lbsatmastar.model.Atm;

/**
 * @author zsuto_000
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	 public static final String DBNAME = "dbatm.sqlite";
	 public static final String DBLOCATION = "/data/data/id.tugasakhir.zakaria.lbsatmastar/databases/";
	private static String TABLE_NAME;
	private Context mContext;
	 private SQLiteDatabase mDatabase;
	 
	public DatabaseHelper(Context context) {
		super(context, DBNAME, null, 1);
		this.mContext = context;
	}
	
	public void openDatabase(){
		String dbPath = mContext.getDatabasePath(DBNAME).getPath();
		if (mDatabase != null && mDatabase.isOpen()) {
			return;
		}
		mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	public void closeDatabase() {
		if (mDatabase != null) {
			mDatabase.close();
		}
	}
	
	public List<Atm> getAtmList() {
		Atm atm = null;
		List<Atm>  atmList = new ArrayList<Atm>();
		openDatabase();
		Cursor cursor = mDatabase.rawQuery("SELECT * FROM nodeatm", null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			atm = new Atm(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4), cursor.getDouble(5));
			atmList.add(atm);
			cursor.moveToNext();
		}
		cursor.close();
		closeDatabase();
		return atmList;
		
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}

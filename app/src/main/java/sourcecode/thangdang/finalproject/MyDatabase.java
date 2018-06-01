package sourcecode.thangdang.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29-May-18.
 */

public class MyDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Ranking_Manager";

    private static final String TABLE_NAME = "Ranking";

    private static final String ID = "Ranking_Id";
    private static final String NAME = "Ranking_Name";
    private static final String TIME = "Ranking_Time";

    public MyDatabase(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String script = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                TIME + " TEXT, " + ")";
        sqLiteDatabase.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public int addContact(Ranking ranking){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, ranking.getmName());
        values.put(TIME, ranking.getmTime());

        db.insert(TABLE_NAME, null, values);

        db = this.getReadableDatabase();
        String id = "SELECT * FROM " + TABLE_NAME +" ORDER BY " + ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(id, null);
        if (cursor != null)
            cursor.moveToFirst();
        db.close();
        return Integer.parseInt(cursor.getString(0));
    }

    public Ranking getRanking(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{ ID, NAME, TIME},
                ID + " =?", new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        Ranking ranking = new Ranking(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

        return ranking;
    }

    public ArrayList<Ranking> getAllRankings(){

        ArrayList<Ranking> rankingList = new ArrayList<Ranking>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Ranking ranking = new Ranking();
                ranking.setmId(Integer.parseInt(cursor.getString(0)));
                ranking.setmName(cursor.getString(1));
                ranking.setmTime(cursor.getString(2));
                rankingList.add(ranking);
            } while (cursor.moveToNext());
        }
        return rankingList;
    }

    public int getNotesCount(){

        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int updateContact(Ranking ranking){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(NAME, ranking.getmName());
        values.put(TIME, ranking.getmTime());

        return  db.update(TABLE_NAME, values, NAME + " =?", new String[]{ranking.getmName()});

    }
}

package tw.adouble.app.helloworld.mydbtest170813002;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyDBHelper myDBHelper;
    private SQLiteDatabase db;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);

        myDBHelper = new MyDBHelper(this, "brad", null, 1);
        //public MyDBHelper(Context context, String name(DB name), SQLiteDatabase.CursorFactory factory, int version)
        db = myDBHelper.getReadableDatabase();
        //https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html#getReadableDatabase()
        query(null);

    }

    public void insert(View view){
        //insert into cust(cname, tel, birthday) values('', '', '')
        ContentValues values = new ContentValues();
        values.put("cname", "Brad");
        values.put("tel", "0932");
        values.put("birthday", "1990-01-02");
        db.insert("cust", null, values);
        query(null);

    }

    public void delete(View view){
        //delete from cust where _id = 3 and cname = 'Brad'
        db.delete("cust", "_id = ? and cname =?", new String[]{"4", "Brad"});
        query(null);
    }

    public void update(View view){
        //update cust set XXX=xxx, ...where _id=5;
        ContentValues values = new ContentValues();
        values.put("cname", "Eric");
        values.put("tel", "0928");
        values.put("birthday", "2000-01-02");
        db.update("cust", values, "_id=?", new String[]{"5"});
        query(null);

    }

    public void query(View view){
        //select * from cust
        tv.setText("");
        Cursor cursor = db.query("cust", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            String cname = cursor.getString(cursor.getColumnIndex("cname"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            tv.append(id+":"+cname+":"+tel+":"+birthday + "\n");

        }


    }

}

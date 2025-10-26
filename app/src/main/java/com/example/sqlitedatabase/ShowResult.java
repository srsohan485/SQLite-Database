package com.example.sqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShowResult extends AppCompatActivity {

    TextView tvDisplay;
    SearchView searchView;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_result);

        searchView=findViewById(R.id.searchView);
        tvDisplay=findViewById(R.id.tvDisplay);
        dbHelper=new DataBaseHelper(ShowResult.this);

        showAllData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                showSearchData(newText);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                showSearchData(query);
                return false;
            }
        });


//        Cursor cursor =dbHelper.getAllData();              //data dakhay
//        Cursor cursor=dbHelper.searchDatabyName("Sohan");    //nirdisto data dakhay(id Number)
//        tvDisplay.setText("Total Data: "+cursor.getCount()); //count data index
//
//        if (cursor!=null && cursor.getCount()>0){
//            while (cursor.moveToNext()){                        //getData
//                int id=cursor.getInt(0);             //table index kotote ace
//                String name=cursor.getString(1);
//                String number=cursor.getString(2);
//                tvDisplay.append("\nID:"+id+" Name: "+name+" Number: "+number);
//            }
//        }else {
//            tvDisplay.setText("No Data");
//        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showAllData() {
        Cursor cursor = dbHelper.getAllData();
        displayData(cursor);
    }

    private void showSearchData(String Keyword){
        Cursor cursor;
        if(Keyword.isEmpty()){
            cursor=dbHelper.getAllData();
        }else {
            cursor=dbHelper.searchDatabyName(Keyword);
        }
        displayData(cursor);
    }

    private void displayData(Cursor cursor){
        tvDisplay.setText("");
        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){                        //getData
                int id=cursor.getInt(0);             //table index kotote ace
                String name=cursor.getString(1);
                String number=cursor.getString(2);
//                tvDisplay.append("\nID:"+id+" Name: "+name+" Number: "+number);
                tvDisplay.append("ID: " + id + " | Name: " + name + " | Number: " + number + "\n");
            }
        }else {
            tvDisplay.setText("No Data");
        }
    }

}
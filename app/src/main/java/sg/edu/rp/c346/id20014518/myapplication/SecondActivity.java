package sg.edu.rp.c346.id20014518.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Hobby> hobbyList;
    //ArrayAdapter<Song> adapter;
    Button btn5Stars;
    CustomAdapter adapter;
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        hobbyList.clear();
        hobbyList.addAll(dbh.getAllHobbies());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);

        DBHelper dbh = new DBHelper(this);
        hobbyList = dbh.getAllHobbies();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, hobbyList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("hobby", hobbyList.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                hobbyList.clear();
                hobbyList.addAll(dbh.getAllHobbiesByStars(5));
                adapter.notifyDataSetChanged();
            }
        });
    }
}

package sg.edu.rp.c346.id20014518.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etDescription;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        rb = findViewById(R.id.ratingbarStars);

        Intent i = getIntent();
        final Hobby currentHobby = (Hobby) i.getSerializableExtra("hobby");

        etID.setText(currentHobby.getId()+"");
        etTitle.setText(currentHobby.getTitle());
        etDescription.setText(currentHobby.getDescription());


        rb.setRating(currentHobby.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentHobby.setTitle(etTitle.getText().toString().trim());
                currentHobby.setDescription(etDescription.getText().toString().trim());

                currentHobby.setStars((int) rb.getRating());
                int result = dbh.updateHobby(currentHobby);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                int result = dbh.deleteHobby(currentHobby.getId());
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Hobby deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
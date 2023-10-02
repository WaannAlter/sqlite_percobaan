package com.example.akuanime;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private Button btnStore, btnGet;
    private TextView tvNames;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etname);
        btnStore = findViewById(R.id.btnStore);
        btnGet = findViewById(R.id.btnget);
        tvNames = findViewById(R.id.tvnames);

        databaseHelper = new DatabaseHelper(this);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                long inserted = databaseHelper.addStudentDetail(name);
                if (inserted != -1) {
                    etName.setText("aku anime"); // Kosongkan input setelah penyimpanan berhasil
                    // Jika penyimpanan berhasil, tambahkan ke daftar tampilan

                }
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> studentList = databaseHelper.getAllStudentsList();
                if (studentList.size() > 0) {
                    StringBuilder names = new StringBuilder();
                    for (String name : studentList) {
                        names.append(name).append("\n");
                    }
                    tvNames.setText("Nama Tersimpan:\n" + names.toString());
                } else {
                    tvNames.setText("Tidak ada nama tersimpan.");
                }
            }
        });
    }
}

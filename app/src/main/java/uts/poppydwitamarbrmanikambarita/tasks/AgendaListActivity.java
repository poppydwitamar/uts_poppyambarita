package uts.poppydwitamarbrmanikambarita.tasks;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AgendaListActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    ListView listView;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_list);

        myDb = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        listItem = new ArrayList<>();

        viewData();
    }

    private void viewData() {
        Cursor cursor = myDb.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(1)); // Tambahkan nama agenda ke daftar
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            listView.setAdapter(adapter);
        }
        Button btnTambahAgenda = findViewById(R.id.btnTambahAgenda);

        btnTambahAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendaListActivity.this, AddAgendaActivity.class);
                startActivity(intent);
            }
        });

    }
}




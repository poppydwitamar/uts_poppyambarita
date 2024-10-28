package uts.poppydwitamarbrmanikambarita.tasks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAgendaActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editNama, editDeskripsi;
    Spinner spinnerStatus;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);

        myDb = new DatabaseHelper(this);

        editNama = findViewById(R.id.editnama);
        editDeskripsi = findViewById(R.id.editDeskripsi);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = editNama.getText().toString();
                String deskripsi = editDeskripsi.getText().toString();
                String status = spinnerStatus.getSelectedItem().toString();

                if (!nama.isEmpty() && !deskripsi.isEmpty()) {
                    boolean isInserted = myDb.insertData(nama, deskripsi, status);

                    if (isInserted) {
                        Toast.makeText(AddAgendaActivity.this, "Data agenda berhasil disimpan", Toast.LENGTH_SHORT).show();
                        // Kembali ke daftar agenda
                        finish();
                    } else {
                        Toast.makeText(AddAgendaActivity.this, "Gagal menyimpan agenda", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddAgendaActivity.this, "Nama dan Deskripsi harus diisi!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


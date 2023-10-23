package fr.caensup.ldev.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class ModifyContactActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etFirstName;

    private EditText etDate;
    private DatePicker dpBirthDate;
    private Button btnOk;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_contact_layout);

        etName = findViewById( R.id.etName );
        etFirstName = findViewById( R.id.etFirstName );
        etDate = findViewById(R.id.etDate);
        dpBirthDate = findViewById( R.id.dpBirthDate );
        btnOk = findViewById( R.id.btnOk);
        btnCancel = findViewById( R.id.btnCancel );

        Intent intent = getIntent();
        if ( intent != null ) {
            etName.setText(intent.getStringExtra("nom"));
            etFirstName.setText(intent.getStringExtra("prenom"));
            etDate.setText( intent.getStringExtra("date"));
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndTransferData();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAndCancel();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dpBirthDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    etDate.setText( datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear());
                }
            });
        }

    }

    private void finishAndCancel() {
        setResult( RESULT_CANCELED );
        finish();
    }

    private void finishAndTransferData() {
        Intent data = new Intent();
        data.putExtra("nom" , etName.getText().toString());
        data.putExtra("prenom" , etFirstName.getText().toString());
        data.putExtra("date" , etDate.getText().toString());
        setResult( RESULT_OK , data );
        finish();
    }
}
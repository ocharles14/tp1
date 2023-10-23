package fr.caensup.ldev.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContactActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvFirstName;
    private TextView tvBirthdate;

    private static final int MODIFY_CODE=1;

    private Button btnModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contact_layout);

        tvName = findViewById( R.id.name );
        tvFirstName = findViewById( R.id.firstName);
        tvBirthdate = findViewById( R.id.birthdate );
        btnModify = findViewById( R.id.btnModify );

        // Ecouteur clic sur bouton modifier
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchModifyContactActivity();
            }
        });
    }

    private void launchModifyContactActivity() {
        // Création d'une intention explicite
        Intent intent = new Intent( this , ModifyContactActivity.class );
        // Cette intention porte des paramètres : Nom, Prénom et la date
        intent.putExtra("nom", tvName.getText());
        intent.putExtra("prenom", tvFirstName.getText());
        intent.putExtra("date", tvBirthdate.getText());
        startActivityForResult( intent , MODIFY_CODE );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data   ) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == MODIFY_CODE ) {
            if ( resultCode == RESULT_OK ) {
                tvName.setText( data.getStringExtra("nom"));
                tvFirstName.setText( data.getStringExtra("prenom"));
                tvBirthdate.setText( data.getStringExtra("date"));
            }
            else if ( resultCode == RESULT_CANCELED ) {
                Toast.makeText(ViewContactActivity.this,"Modification annulée",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
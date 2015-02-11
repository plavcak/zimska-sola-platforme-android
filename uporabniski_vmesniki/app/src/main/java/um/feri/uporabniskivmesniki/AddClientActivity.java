package um.feri.uporabniskivmesniki;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import um.feri.uporabniskivmesniki.db.ClientDAO;
import um.feri.uporabniskivmesniki.db.Database;
import um.feri.uporabniskivmesniki.model.Client;

public class AddClientActivity extends ActionBarActivity implements View.OnClickListener {

    private Button button;
    private EditText etFirstName;
    private EditText etlastName;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        button = (Button) findViewById(R.id.btnAdd);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etlastName = (EditText) findViewById(R.id.etLastName);

        button.setOnClickListener(this);

        database = new Database(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    private void addClient() {
        if(etFirstName.getText().length() == 0 || etlastName.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Manjkajo podatki", Toast.LENGTH_SHORT).show();
            return;
        }

        Client client = new Client();
        client.setFirstName(etFirstName.getText().toString());
        client.setLastName(etlastName.getText().toString());

        SQLiteDatabase db = database.getWritableDatabase();
        new ClientDAO().insert(client, db);
        db.close();

        Toast.makeText(getApplicationContext(), "Klient uspešno dodan!", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public void onClick(View v) {

        if(v.equals(button)) {
            addClient();
        }
    }
}
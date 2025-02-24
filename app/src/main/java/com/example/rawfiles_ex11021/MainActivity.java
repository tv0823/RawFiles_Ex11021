package com.example.rawfiles_ex11021;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The type Main activity.
 *
 * @author      Tal Weintraub <tv0823@bs.amalnet.k12.il>
 * @version	    1
 * @since		24/2/2025
 * short description:
 *      read text from file and put it in a textView or get text from the user and displays it.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The textTv.
     */
    TextView textTv;

    /**
     * The textEt.
     */
    EditText textEt;

    /**
     * The FILENAME.
     */
    private final String FILENAME = "rawtest.txt";

    /**
     * The fileName.
     */
    String fileName = FILENAME.substring(0, FILENAME.length() - 4);

    /**
     * The resourceId.
     */
    int resourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTv = findViewById(R.id.textTv);
        textEt = findViewById(R.id.textEt);

        resourceId = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());
    }

    /**
     * Gets text from raw file and displays it in a textView.
     *
     * @param view the view
     */
    public void rawFileBtn(View view) {
        try {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();

            while (line != null) {
                sB.append(line + '\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            textTv.setText(sB.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets text from EditText and displays it in a textView.
     *
     * @param view the view
     */
    public void textBtn(View view) {
        String text = textEt.getText().toString();
        textTv.setText(text);
    }

    /**
     * Creates the options menu on screen
     *
     * @param menu the menu
     * @return ture
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Checks if the clicked menuItem is R.id.menuCred
     *
     * @param item a menuItem
     * @return ture
     */
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menuCred) {
            Intent si = new Intent(this, CreditsActivity.class);
            startActivity(si);
        }
        return true;
    }
}
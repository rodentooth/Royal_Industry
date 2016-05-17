package com.frozensparks.royalindustry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Emanuel Graf on 16.05.2016.
 */
public class hangartut extends MainActivity {
Button hangar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hangartut);
        hangar = (Button) findViewById(R.id.werkstatt);
        hangar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {




        int id = v.getId();
        if (id == R.id.werkstatt) {
            Intent intent = new Intent(this, multiplayer.class);
            startActivity(intent);

        }
    }
}

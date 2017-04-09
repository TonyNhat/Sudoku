package com.example.dvid.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

/**
 * Created by Dvid on 17/03/2017.
 */

public class HuongDan extends Activity {
    LinearLayout manhinhHD;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huongdan);

        manhinhHD = (LinearLayout) findViewById(R.id.layout_HD);
        manhinhHD.setBackgroundResource(R.drawable.nen);
    }
}

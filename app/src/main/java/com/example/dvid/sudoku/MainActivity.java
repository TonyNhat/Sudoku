package com.example.dvid.sudoku;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnBatDau, btnTiepTuc, btnHuongDan, btnThoat;
    LinearLayout manHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //sự kiện click cho các Button
        btnBatDau = (Button) findViewById(R.id.btn_batdau);
        btnBatDau.setOnClickListener(this);
        btnTiepTuc = (Button) findViewById(R.id.btn_tieptuc);
        btnTiepTuc.setOnClickListener(this);
        btnHuongDan = (Button) findViewById(R.id.btn_huongdan);
        btnHuongDan.setOnClickListener(this);
        btnThoat = (Button) findViewById(R.id.btn_thoat);
        btnThoat.setOnClickListener(this);

        manHinh = (LinearLayout) findViewById(R.id.man_hinh);
        manHinh.setBackgroundResource(R.drawable.nen);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_huongdan:
                Intent i = new Intent(this, HuongDan.class);
                startActivity(i);
                break;
            case R.id.btn_tieptuc:
                startGame(Game.DIFFICULTY_CONTINUE);
                break;
            case R.id.btn_thoat:
                finish();
                break;
            case R.id.btn_batdau:
                openNewGameDialog();
                break;
        }
    }
     /*@Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            MenuInflater inflater = this.getMenuInflater();
            inflater.inflate(2131296256, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch(item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, Prefs.class));
                return true;
            default:
                return false;
            }
        }
     */
    public void openNewGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_game_title)
                .setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                startGame(i);
            }
        });
        builder.create().show();
    }
    //phương thức khởi động game
    private void startGame(int i) {
        Intent intent = new Intent(MainActivity.this, Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);
    }

    /*
    @Override
    protected void onPause() {
        super.onPause();
        Music.play(this,R.raw.main)
    }
     @Override
    protected void onResume() {
        super.onResume();
        Music.play(this,R.raw.main)
        }
    }

*/

}

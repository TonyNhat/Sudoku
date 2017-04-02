package com.example.dvid.sudoku;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnBatDau, btnTiepTuc, btnHuongDan, btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnBatDau = (Button) findViewById(R.id.btn_batdau);
        btnBatDau.setOnClickListener(this);
        btnTiepTuc = (Button) findViewById(R.id.btn_tieptuc);
        btnTiepTuc.setOnClickListener(this);
        btnHuongDan = (Button) findViewById(R.id.btn_huongdan);
        btnHuongDan.setOnClickListener(this);
        btnThoat = (Button) findViewById(R.id.btn_thoat);
        btnThoat.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_huongdan:
                Intent i = new Intent(this, HuongDan.class);
                startActivity(i);
                break;
            case R.id.btn_thoat:
                finish();
                break;
            case R.id.btn_batdau:
                openNewGameDialog();
                break;
        }
    }

    public void openNewGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_game_title).setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                startGame(i);
            }
        });
        builder.create().show();
    }

    private void startGame(int i) {
        Intent intent = new Intent(MainActivity.this, Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);
    }
}

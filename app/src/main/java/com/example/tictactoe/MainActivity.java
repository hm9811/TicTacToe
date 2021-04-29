package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean xTurn = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String ButtonID = "button_" + i + j;
                int resultID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                buttons[i][j] = findViewById(resultID);
                buttons[i][j].setOnClickListener(this);

            }
        }



        Button newGame = findViewById(R.id.button_NewGame);
        newGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        buttons[i][j].setText("");
                    }
                }
                TextView tv1 = (TextView)findViewById(R.id.textViewTurn);
                tv1.setText("Player X's turn");
            }
        });
    }

    public void onClick(View v){
        TextView tv1 = (TextView)findViewById(R.id.textViewTurn);
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(xTurn){
            ((Button) v).setText("X");
            xTurn = false;
            tv1.setText("Player O's turn");
        }
        else{
            ((Button) v).setText("O");
            xTurn = true;
            tv1.setText("Player X's turn");
        }

    }
}
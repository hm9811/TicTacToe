package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean xTurn = true;

    private int counts;


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
                TextView tv1 = findViewById(R.id.textViewTurn);
                tv1.setText("Player X's turn");
                xTurn = true;
                counts = 0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        String ButtonID = "button_" + i + j;
                        int resultID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                        buttons[i][j] = findViewById(resultID);
                        buttons[i][j].setEnabled(true);

                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v){
        TextView tv1 = findViewById(R.id.textViewTurn);
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

        counts++;



        if(checkForWin()){
            if(!xTurn){
                xWin();
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        String ButtonID = "button_" + i + j;
                        int resultID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                        buttons[i][j] = findViewById(resultID);
                        buttons[i][j].setEnabled(false);

                    }
                }
            }
            else{
                oWin();
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        String ButtonID = "button_" + i + j;
                        int resultID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                        buttons[i][j] = findViewById(resultID);
                        buttons[i][j].setEnabled(false);

                    }
                }
            }
        }
        else if(counts == 9){
            draw();
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    String ButtonID = "button_" + i + j;
                    int resultID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                    buttons[i][j] = findViewById(resultID);
                    buttons[i][j].setEnabled(false);

                }
            }
        }

    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i = 0; i < 3; i++){
            if(field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                && !field[i][0].equals("")){
                return true;
            }
        }

        for(int i = 0; i < 3; i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][2])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void xWin(){
        TextView tv1 = findViewById(R.id.textViewTurn);
        tv1.setText("X Wins!");

    }

    private void oWin(){
        TextView tv1 = findViewById(R.id.textViewTurn);
        tv1.setText("O Wins!");
    }

    private void draw(){
        TextView tv1 = findViewById(R.id.textViewTurn);
        tv1.setText("Draw!");
    }
}
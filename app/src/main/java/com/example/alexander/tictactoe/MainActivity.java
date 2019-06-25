package com.example.alexander.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int c[][];
    int i, j, k = 0;
    Button b[][];
    TextView textView;
    int player = 2;
    private Button newGame;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame = (Button) findViewById(R.id.newGame);

        setBoard();
    }

    public void newGame(View view)
    {
        setBoard();
    }

    // Set up the game board.
    private void setBoard() {
        b = new Button[4][4];
        c = new int[4][4];

        textView = (TextView) findViewById(R.id.dialogue);

        b[1][3] = (Button) findViewById(R.id.one);
        b[1][2] = (Button) findViewById(R.id.two);
        b[1][1] = (Button) findViewById(R.id.three);

        b[2][3] = (Button) findViewById(R.id.four);
        b[2][2] = (Button) findViewById(R.id.five);
        b[2][1] = (Button) findViewById(R.id.six);

        b[3][3] = (Button) findViewById(R.id.seven);
        b[3][2] = (Button) findViewById(R.id.eight);
        b[3][1] = (Button) findViewById(R.id.nine);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }

        textView.setText("Player 1 time to play.");

        // add the click listeners for each button
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if(!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }

    class MyClickListener implements View.OnClickListener {
        int x;
        int y;

        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {

            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                if (player % 2 == 0) {
                    b[x][y].setText("O");
                    c[x][y] = 0;
                    player++;
                    textView.setText(" Player 2 turn");
                    checkBoard();
                }else {
                        b[x][y].setText("X");
                        c[x][y] = 1;
                        player++;
                        textView.setText("Player 1 turn");
                        checkBoard();
                }
            }
        }
    }

    // check the board to see if someone has won
    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            textView.setText("Player 1 Wins!");
            gameOver = true;
            player = 2;
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            textView.setText("Player 2 Wins!");
            gameOver = true;
            player = 2;
        } else {
            boolean empty = false;
            for(i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOver = true;
                player = 2;
                textView.setText("0 wins!");
            }
        }
        return gameOver;
    }
}

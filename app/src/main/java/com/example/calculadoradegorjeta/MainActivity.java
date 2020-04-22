package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText valorConta;
    private TextView percentual;
    private SeekBar percentualSeekBar;
    private TextView valorGorjeta;
    private TextView valorTotal;

    private double percentualParaCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorConta = findViewById(R.id.valorConta);
        percentual = findViewById(R.id.percentual);
        percentualSeekBar = findViewById(R.id.percentualSeekBar);
        valorGorjeta = findViewById(R.id.valorGorjeta);
        valorTotal = findViewById(R.id.valorTotal);

        //Adicionar listener para seekBar
        percentualSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Executa algo quando se movimenta a barra
                percentual.setText(progress + "%");
                percentualParaCalculo = progress;
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Executa algo apenas no clique
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Executa algo apenas quando solta o clique
            }
        });

    }

    public void calcular(){
        String valorDaConta = valorConta.getText().toString();
        if(valorDaConta == null || valorDaConta.equals("")){
            Toast.makeText(getApplicationContext(), "Digite um valor!", Toast.LENGTH_LONG).show();
        } else{
            //Converter String para Double
            double valorContaDecimal = Double.parseDouble(valorDaConta);
            //Cálculo da gorjeta e total
            double gorjeta = valorContaDecimal * (percentualParaCalculo/100);
            double valorFinal = valorContaDecimal + gorjeta;
            DecimalFormat df = new DecimalFormat("#.##");
            gorjeta = Double.valueOf(df.format(gorjeta));
            valorFinal = Double.valueOf(df.format(valorFinal));
            //Exibir gorjeta e total
            valorGorjeta.setText("R$" + gorjeta);
            valorTotal.setText("R$" + valorFinal);
        }
    }
}

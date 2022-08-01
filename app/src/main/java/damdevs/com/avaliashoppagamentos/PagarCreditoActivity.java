package damdevs.com.avaliashoppagamentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;

import damdevs.com.avaliashoppagamentos.metodos_pagamento.PagarCredito;

public class PagarCreditoActivity extends AppCompatActivity {

    private float valor;
    TextView continuar;
    private ProgressBar progressBar;

    private TextView nmrParcelasTXT;

    //teclas do teclado
    private TextView bt1;
    private TextView bt2;
    private TextView bt3;
    private TextView bt4;
    private TextView bt5;
    private TextView bt6;
    private TextView bt7;
    private TextView bt8;
    private TextView bt9;
    private TextView bt10;
    private TextView bt11;
    private TextView bt12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar_credito);
        //colocando a setinha de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        continuar = findViewById(R.id.continuar);
        progressBar = findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0c336a"), PorterDuff.Mode.SRC_IN);

        //pegando o valor a ser pago
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            valor = extras.getFloat("valor");
        }

        TextView valorParcelas = findViewById(R.id.valor_por_parcela);
        nmrParcelasTXT = findViewById(R.id.n_parcelas);
        try {
            valorParcelas.setText("de R$" + valor/Integer.parseInt(nmrParcelasTXT.getText().toString()) );
        }catch (Exception e){}

        nmrParcelasTXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                float valorParcela = valor/Integer.parseInt(s.toString());

                DecimalFormat df = new DecimalFormat("0.00");

                //System.out.println(df.format(teste));
                try {
                    valorParcelas.setText("de R$" + df.format(valorParcela) );
                }catch (Exception e){}

            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                continuar.setVisibility(View.INVISIBLE);

                int nmrParcelas = Integer.parseInt(nmrParcelasTXT.getText().toString());
                if (nmrParcelas ==0 || nmrParcelas >12){
                    AlertDialog dialog = new AlertDialog.Builder(PagarCreditoActivity.this)
                            .setMessage(PagarCreditoActivity.this.getString(R.string.nmr_parc_ivalid))
                            .setPositiveButton("Ok", null)
                            .create();
                    dialog.show();
                }else {
                    //new PagarCredito(valor, nmrParcelas, PagarCreditoActivity.this);
                    Intent intent = new Intent(PagarCreditoActivity.this, ActivityAvalienos.class);
                    intent.putExtra("valor", valor);
                    intent.putExtra("tipopag", getString(R.string.credito));
                    intent.putExtra("nmrparcelas", nmrParcelas);
                    startActivity(intent);

                }
            }
        });

        configuraTeclado();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return true;
        }
    }
    private void configuraTeclado() {

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        bt10 = findViewById(R.id.bt10);
        bt11 = findViewById(R.id.bt11);
        bt12 = findViewById(R.id.bt12);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("1");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("2");
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("3");
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("4");
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("5");
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("6");
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("7");
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("8");
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmrParcelasTXT.setText("9");
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nmrParcelasTXT.getText().toString().contains(".")){

                }else {

                    nmrParcelasTXT.setText("10");
                }
            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nmrParcelasTXT.getText().toString().contains(".")){

                }else {

                    nmrParcelasTXT.setText("11");
                }
            }
        });
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nmrParcelasTXT.getText().toString().contains(".")){

                }else {

                    nmrParcelasTXT.setText("12");
                }
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        progressBar.setVisibility(View.INVISIBLE);
        finish();
    }
}
package damdevs.com.avaliashoppagamentos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cielo.sdk.order.payment.PaymentCode;
import damdevs.com.avaliashoppagamentos.metodos_pagamento.PagarVoucher;

public class PagarVoucherActivity extends AppCompatActivity {

    private ProgressBar progresAlim, progresRef;
    private TextView vcAlim, vcRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar_voucher);
        //colocando a setinha de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progresAlim = findViewById(R.id.progressBar_alim);
        progresRef = findViewById(R.id.progressBar_refei);
        vcAlim = findViewById(R.id.vc_alimentação);
        vcRef = findViewById(R.id.vc_refeicao);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.i("passou1", "teste");
            float valor = extras.getFloat("valor");

            vcAlim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progresAlim.setVisibility(View.VISIBLE);
                    vcAlim.setVisibility(View.INVISIBLE);

                    //new PagarVoucher(valor, PaymentCode.VOUCHER_ALIMENTACAO,PagarVoucherActivity.this);

                    Intent intent = new Intent(PagarVoucherActivity.this, ActivityAvalienos.class);
                    intent.putExtra("valor", valor);
                    intent.putExtra("tipopag", getString(R.string.vc_alimentacao));
                    startActivity(intent);
                }
            });
            vcRef.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progresRef.setVisibility(View.VISIBLE);
                    vcRef.setVisibility(View.INVISIBLE);
                    //new PagarVoucher(valor, PaymentCode.VOUCHER_REFEICAO,PagarVoucherActivity.this);
                    Intent intent = new Intent(PagarVoucherActivity.this, ActivityAvalienos.class);
                    intent.putExtra("valor", valor);
                    intent.putExtra("tipopag", getString(R.string.vc_refeicao));
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        progresAlim.setVisibility(View.INVISIBLE);
        vcAlim.setVisibility(View.VISIBLE);
        progresRef.setVisibility(View.INVISIBLE);
        vcRef.setVisibility(View.VISIBLE);
        finish();
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
}
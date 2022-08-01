package damdevs.com.avaliashoppagamentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FormasPagActivity extends AppCompatActivity {

    private float valorPagar = 0;

    private ProgressBar progressBarC,progressBarD, progressBarV;
    private TextView creditoTxt, debitoTxt, voucherTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formas_pag);
        //colocando a setinha de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBarC = findViewById(R.id.progressBarC);
        progressBarD = findViewById(R.id.progressBarD);
        progressBarV = findViewById(R.id.progressBarV);
        creditoTxt= findViewById(R.id.credito);
        debitoTxt = findViewById(R.id.debito);
        voucherTxt = findViewById(R.id.voucher);

        progressBarC.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0c336a"), PorterDuff.Mode.SRC_IN);
        progressBarD.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0c336a"), PorterDuff.Mode.SRC_IN);
        progressBarV.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0c336a"), PorterDuff.Mode.SRC_IN);

        //home
        findViewById(R.id.home_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //pegndo o avlor a ser pago
        if (getIntent().getExtras() != null){
            valorPagar = Float.parseFloat(getIntent().getExtras().getString("valor"));
            if (valorPagar == 0){
                finish();
            }
        }

        //indo pra tela de informar parcelas se clicar em crédito
        creditoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarC.setVisibility(View.VISIBLE);
                creditoTxt.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(FormasPagActivity.this, PagarCreditoActivity.class);
                intent.putExtra("valor", valorPagar);
                startActivity(intent);
            }
        });

        //pagamento no débito
        debitoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarD.setVisibility(View.VISIBLE);
                debitoTxt.setVisibility(View.INVISIBLE);

                //new PagarDebito(valorPagar, FormasPagActivity.this);
                Intent intent = new Intent(FormasPagActivity.this, ActivityAvalienos.class);
                intent.putExtra("valor", valorPagar);
                intent.putExtra("tipopag", getString(R.string.debito));
                startActivity(intent);
            }
        });

        //indo pra tela de pagar com voucher
        findViewById(R.id.voucher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarV.setVisibility(View.VISIBLE);
                voucherTxt.setVisibility(View.INVISIBLE);


                Intent intent = new Intent(FormasPagActivity.this, PagarVoucherActivity.class);
                intent.putExtra("valor", valorPagar);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        progressBarC.setVisibility(View.INVISIBLE);
        creditoTxt.setVisibility(View.VISIBLE);
        progressBarD.setVisibility(View.VISIBLE);
        debitoTxt.setVisibility(View.INVISIBLE);
        progressBarV.setVisibility(View.VISIBLE);
        voucherTxt.setVisibility(View.INVISIBLE);

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
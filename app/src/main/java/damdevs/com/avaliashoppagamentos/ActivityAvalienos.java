package damdevs.com.avaliashoppagamentos;

import static damdevs.com.avaliashoppagamentos.util.Constantes.jáAvaliou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import cielo.sdk.order.payment.PaymentCode;
import damdevs.com.avaliashoppagamentos.metodos_pagamento.PagarCredito;
import damdevs.com.avaliashoppagamentos.metodos_pagamento.PagarDebito;
import damdevs.com.avaliashoppagamentos.metodos_pagamento.PagarVoucher;

public class ActivityAvalienos extends AppCompatActivity {
    private TextView porFavor;
    private TextView aAvaliar;
    private TextView nivelSatisfacao;


    private Button pagar;
    private LinearLayout llteclado;

    //teclas do teclado
    private TextView indicaria;
    private TextView bt0;
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

    private ProgressBar progressBar;

    String tipopag;
    float valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avalienos);
        //colocando a setinha de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //pegando as referencias das views
        porFavor = findViewById(R.id.por_favor);
        aAvaliar = findViewById(R.id.a_avaliar);
        nivelSatisfacao = findViewById(R.id.txt_nivel_satisfacao);
        pagar = findViewById(R.id.pagar);
        indicaria = findViewById(R.id.edt_quanto);
        llteclado = findViewById(R.id.teclado);
        progressBar = findViewById(R.id.progressBar);

        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0c336a"), PorterDuff.Mode.SRC_IN);

        //setando o q será avaliado
        setAvaliar();

        //pegando os dados passados por extra
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            valor = getIntent().getExtras().getFloat("valor");
            tipopag = getIntent().getExtras().getString("tipopag");
        }else {
            finish();
        }

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Envie as avaliações para algum banco de dados
                 * ...
                 * */
                progressBar.setVisibility(View.VISIBLE);
                pagar.setVisibility(View.INVISIBLE);

                if (tipopag.equals(getString(R.string.credito))){
                    int nmrParcelas = extras.getInt("nmrparcelas");
                    new PagarCredito(valor, nmrParcelas, ActivityAvalienos.this);
                }else if (tipopag.equals(getString(R.string.debito))){
                    new PagarDebito(valor, ActivityAvalienos.this);
                }else if (tipopag.equals(getString(R.string.vc_alimentacao))){
                    new PagarVoucher(valor, PaymentCode.VOUCHER_ALIMENTACAO,ActivityAvalienos.this);
                }else if (tipopag.equals(getString(R.string.vc_refeicao))){
                    new PagarVoucher(valor, PaymentCode.VOUCHER_REFEICAO,ActivityAvalienos.this);
                }

            }
        });
    }

    private void setAvaliar() {
        Random random = new Random();
        int i = random.nextInt(5);
            if (i==0){
                aAvaliar.setText(getString(R.string.preco));
            }else if (i==1){
                aAvaliar.setText(getString(R.string.produto));
            }else if (i==2){
                aAvaliar.setText(getString(R.string.atendimento));
            }else if (i==3){
                aAvaliar.setText(getString(R.string.ambiente));
            }else if (i==4){
                porFavor.setText(getString(R.string.o_quanto));
                aAvaliar.setText(getString(R.string.recomendaria));
                configuraTeclado();

                @SuppressLint("WrongViewCast")
                LinearLayout lStar5 = findViewById(R.id.star_5);
                set_Altura_Largura_0(lStar5);
                lStar5.setVisibility(View.INVISIBLE);
                set_Altura_Largura_0(nivelSatisfacao);


            };
            if (i != 4) {
                set_Altura_Largura_0(indicaria);
                set_Altura_Largura_0(llteclado);

                ImageView star1 = findViewById(R.id.star1);
                ImageView star2 = findViewById(R.id.star2);
                ImageView star3 = findViewById(R.id.star3);
                ImageView star4 = findViewById(R.id.star4);
                ImageView star5 = findViewById(R.id.star5);

                star1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nivelSatisfacao.setText(R.string.pessimo);

                        star1.setImageResource(R.drawable.ic_baseline_star_24);
                        star2.setImageResource(R.drawable.ic_baseline_star_cinza24);
                        star3.setImageResource(R.drawable.ic_baseline_star_cinza24);
                        star4.setImageResource(R.drawable.ic_baseline_star_cinza24);
                        star5.setImageResource(R.drawable.ic_baseline_star_cinza24);

                    }
                });
                star2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nivelSatisfacao.setText(R.string.ruim);

                        star1.setImageResource(R.drawable.ic_baseline_star_24);
                        star2.setImageResource(R.drawable.ic_baseline_star_24);
                        star3.setImageResource(R.drawable.ic_baseline_star_cinza24);
                        star4.setImageResource(R.drawable.ic_baseline_star_cinza24);
                        star5.setImageResource(R.drawable.ic_baseline_star_cinza24);

                    }
                });
                star3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nivelSatisfacao.setText(R.string.regular);

                        star1.setImageResource(R.drawable.ic_baseline_star_24);
                        star2.setImageResource(R.drawable.ic_baseline_star_24);
                        star3.setImageResource(R.drawable.ic_baseline_star_24);
                        star4.setImageResource(R.drawable.ic_baseline_star_cinza24);
                        star5.setImageResource(R.drawable.ic_baseline_star_cinza24);

                    }
                });
                star4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nivelSatisfacao.setText(R.string.bom);

                        star1.setImageResource(R.drawable.ic_baseline_star_24);
                        star2.setImageResource(R.drawable.ic_baseline_star_24);
                        star3.setImageResource(R.drawable.ic_baseline_star_24);
                        star4.setImageResource(R.drawable.ic_baseline_star_24);
                        star5.setImageResource(R.drawable.ic_baseline_star_cinza24);

                    }
                });
                star5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nivelSatisfacao.setText(R.string.otimo);

                        star1.setImageResource(R.drawable.ic_baseline_star_24);
                        star2.setImageResource(R.drawable.ic_baseline_star_24);
                        star3.setImageResource(R.drawable.ic_baseline_star_24);
                        star4.setImageResource(R.drawable.ic_baseline_star_24);
                        star5.setImageResource(R.drawable.ic_baseline_star_24);

                    }
                });
            }

    }

    private void configuraTeclado() {

         bt0 = findViewById(R.id.bt0);
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

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("0");
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("1");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("2");
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("3");
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("4");
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("5");
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("6");
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("7");
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("8");
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicaria.setText("9");
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indicaria.getText().toString().contains(".")){

                }else {

                    indicaria.setText("10");
                }
            }
        });



    }

    //seta altura e largura sendo 0
    public static void set_Altura_Largura_0(View view){
        ViewGroup.LayoutParams lpnborda = view.getLayoutParams();
        if (lpnborda instanceof ViewGroup.MarginLayoutParams) {//se lb é um ViewGroup.MarginLayoutParams
            //instanceog == 'é um'
            lpnborda.height = 0;
            lpnborda.width = 0;

            //lpnborda.width = largura;
            view.requestLayout();//aplica as modificações
        }

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

    @Override
    protected void onRestart() {
        super.onRestart();
        progressBar.setVisibility(View.INVISIBLE);
    }
}
package damdevs.com.avaliashoppagamentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import cielo.orders.domain.Credentials;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.ServiceBindListener;

public class MainActivity extends AppCompatActivity {

    private TextView totalPedido;
    private TextView btPagar;
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
    private TextView btPonto;
    private ImageView btApagar;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //views da dessa atividade
        totalPedido = findViewById(R.id.total_pedido);
        btPagar = findViewById(R.id.pagar);
        progressBar = findViewById(R.id.progressBar);

        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#0c336a"), PorterDuff.Mode.SRC_IN);


        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                btPagar.setVisibility(View.INVISIBLE);

                String total = totalPedido.getText().toString();
                if (total.equals("") || total.equals(".") || total.equals("0") || !verificaFloat(total)){
                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                            .setMessage(MainActivity.this.getString(R.string.digite_umvalor))
                            .setPositiveButton("OK", null)
                            .create();
                    dialog.show();
                    progressBar.setVisibility(View.INVISIBLE);
                    btPagar.setVisibility(View.VISIBLE);
                }else {
                    Intent intent = new Intent(MainActivity.this, FormasPagActivity.class);
                    intent.putExtra("valor", total);
                    startActivity(intent);
                }
            }
        });

        configuraTeclado();
    }

    private boolean verificaFloat(String total) {
        try {
            float f = Float.parseFloat(total);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:
                return true;

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
        btPonto = findViewById(R.id.btponto);
        btApagar = findViewById(R.id.bt_apagar);

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt0.getText().toString());
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt1.getText().toString());
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt2.getText().toString());
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt3.getText().toString());
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt4.getText().toString());
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt5.getText().toString());
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt6.getText().toString());
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt7.getText().toString());
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt8.getText().toString());
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPedido.setText(totalPedido.getText().toString() + bt9.getText().toString());
            }
        });
        btPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPedido.getText().toString().contains(".")){

                }else {

                    totalPedido.setText(totalPedido.getText().toString() + btPonto.getText().toString());
                }
            }
        });

        btApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPedido.getText().length() == 0){
                    return;
                }
                String novoText = totalPedido.getText().toString().subSequence(0, totalPedido.getText().toString().length() - 1).toString();
                totalPedido.setText(novoText);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        progressBar.setVisibility(View.INVISIBLE);
        btPagar.setVisibility(View.VISIBLE);
    }
}
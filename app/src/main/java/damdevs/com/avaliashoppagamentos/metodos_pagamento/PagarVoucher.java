package damdevs.com.avaliashoppagamentos.metodos_pagamento;

import static damdevs.com.avaliashoppagamentos.util.Constantes.jáAvaliou;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cielo.orders.domain.CheckoutRequest;
import cielo.orders.domain.Credentials;
import cielo.orders.domain.Order;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.ServiceBindListener;
import cielo.sdk.order.payment.Payment;
import cielo.sdk.order.payment.PaymentCode;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;
import damdevs.com.avaliashoppagamentos.ActivityAvalienos;
import damdevs.com.avaliashoppagamentos.R;
import damdevs.com.avaliashoppagamentos.util.CriarId;

public class PagarVoucher {
    private Credentials credentials = new Credentials("x1Wcm79ZU8sR4VBxBcScL6PjjzXKVbwyURcJ6qsKcMERLscUnD", "WB37gAmGjVQ9yJxxs8lrYEFaYZX871GoAl4kPzpXqZktOPIDHy");

    public PagarVoucher(float valor, PaymentCode tipoVC,Context context) {

        /**O fluxo básico para utilização do SDK pode ser dividido em 7 etapas */

        //1 criar orderManager
        OrderManager orderManager = new OrderManager(credentials, context);

        //2 Vincular o contexto da aplicação ao SDK
        ServiceBindListener serviceBindListener = new ServiceBindListener() {

            @Override public void onServiceBoundError(Throwable throwable) {
                //Ocorreu um erro ao tentar se conectar com o serviço OrderManager
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setMessage(context.getString(R.string.algo_errado) + "\n"+
                                throwable.getMessage())
                        .setPositiveButton("OK", null)
                        .create();
                dialog.show();
                Log.e("Erro Cielo LIO: ", throwable.getMessage());
                Log.e("Erro Cielo LIO: ", "Ocorreu um erro ao tentar se conectar com o serviço OrderManager");
            }

            @Override
            public void onServiceBound() {
                //Você deve garantir que sua aplicação se conectou com a LIO a partir desse listener
                //A partir desse momento você pode utilizar as funções do OrderManager, caso contrário uma exceção será lançada.

                //3 criar um pedido
                Order order = orderManager.createDraftOrder("AvaliaShop");
                //4adicione items ao pedido
                order.addItem(CriarId.criar(context), "produto avaliashop", getCentavos(valor), 1, "UNIDADE");

                //5 LIBERAR O PEDIDO PARA PAGAMENTO
                orderManager.placeOrder(order);
                //você deverá utilizar o seguinte callback como parâmetro do método de checkoutOrder() para
                // receber os estados relacionados ao pagamento.

                PaymentListener paymentListener = new PaymentListener() {


                    @Override
                    public void onStart() {

                        Log.i("SDKClient", "O pagamento começou.");

                        //Intent intent = new Intent(context, ActivityAvalienos.class);
                        //context.startActivity(intent);

                    }

                    @Override
                    public void onPayment(Order order) {
                        if(!((AppCompatActivity) context).isFinishing()) {
                            //show dialog

                            Log.i("SDKClient", "Um pagamento foi realizado.");
                            AlertDialog dialog = new AlertDialog.Builder(context)
                                    .setMessage(context.getString(R.string.sucesso))
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ((AppCompatActivity) context).finish();
                                        }
                                    })
                                    .create();
                            dialog.show();
                        }
                    }

                    @Override public void onCancel() {
                        Log.i("SDKClient", "A operação foi cancelada.");
                        if(!((AppCompatActivity) context).isFinishing()) {
                            //show dialog

                            AlertDialog dialog = new AlertDialog.Builder(context)
                                    .setMessage(context.getString(R.string.cancelado))
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ((AppCompatActivity) context).finish();
                                        }
                                    })
                                    .create();
                            dialog.show();
                        }
                    }

                    @Override public void onError( PaymentError paymentError) {
                        Log.i("SDKClient", "Houve um erro no pagamento.");
                        if (!((AppCompatActivity) context).isFinishing()) {
                            //show dialog

                        AlertDialog dialog = new AlertDialog.Builder(context)
                                .setMessage(context.getString(R.string.ERRO) + "\n" + paymentError.toString())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ((AppCompatActivity) context).finish();
                                    }
                                })
                                .create();
                        dialog.show();
                        }
                    }
                };

                //6 REQUISIÇÃO DE PAGAMENTO
                CheckoutRequest request = new CheckoutRequest.Builder()
                        .orderId(order.getId()) /* Obrigatório */
                        .amount(getCentavos(valor)) /* Opcional */
                        //.ec("999999999999999") /* Opcional (precisa estar habilitado na LIO) */
                        //.installments(3) /* Opcional */
                        //.email("teste@email.com") /* Opcional */
                        .paymentCode(tipoVC) /* Opcional */
                        .build();
                try {
                    orderManager.checkoutOrder(request, paymentListener);
                }catch (Exception e){
                    AlertDialog dialog = new AlertDialog.Builder(context)
                            .setMessage(context.getString(R.string.ERRO) + "\n" +e.getMessage())
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ((AppCompatActivity) context).finish();
                                }
                            })
                            .create();
                    dialog.show();
                }

            }

            @Override
            public void onServiceUnbound() {
                // O serviço foi desvinculado
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setMessage(context.getString(R.string.informacao) + "\n"+
                                context.getString(R.string.servico_desvinculado))
                        .setPositiveButton("OK", null)
                        .create();
                dialog.show();

                Log.i("Informação Cielo LIO: ", "O serviço foi desvinculado");
            }
        };
        orderManager.bind(context, serviceBindListener);



        Log.i("SDKClient", "teste");
    }


    //transforma real pra centavos
    private long getCentavos(float valor){
        return (long) (valor*100);
    }
}

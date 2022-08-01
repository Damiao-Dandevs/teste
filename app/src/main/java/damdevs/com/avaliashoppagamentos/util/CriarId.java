package damdevs.com.avaliashoppagamentos.util;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//classe que cria um id com números e letras
public class CriarId {

    public static String criar(Context context){
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = new Date();

        return dateFormat.format(date);
    }
}

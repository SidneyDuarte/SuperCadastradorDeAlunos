package br.com.caelum.supercadastradordealunos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by android6920 on 25/07/17.
 */

public class SMSReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent data) {
        Object[] pdus = (Object[]) data.getExtras().get("pdus");

        byte[] pdu = (byte[])pdus[0];
        SmsMessage sms = SmsMessage.createFromPdu(pdu);
        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDao dao = new AlunoDao(context);

        if (dao.isAluno(telefone)) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();

            Toast.makeText(context, "Mensagem do aluno " + sms.getDisplayMessageBody(), Toast.LENGTH_SHORT).show();
        }

    }
}

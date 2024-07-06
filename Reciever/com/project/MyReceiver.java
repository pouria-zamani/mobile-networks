import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                final Object[] pduArray = (Object[]) bundle.get("pdus");

                for (Object pdu : pduArray) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String senderNumber = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Toast.makeText(context, "sender number: " + senderNumber
                            + "; message: " + message, Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "oops!", Toast.LENGTH_SHORT).show();
        }
    }
}

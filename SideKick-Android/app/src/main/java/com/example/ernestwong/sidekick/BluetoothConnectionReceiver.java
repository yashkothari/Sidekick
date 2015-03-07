package com.example.ernestwong.sidekick;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.bluetooth.*;


public class BluetoothConnectionReceiver extends BroadcastReceiver {
    public BluetoothConnectionReceiver(){
        //No initialisation code needed
    }

    @Override
    public void onReceive(Context context, Intent intent){
        /*if(BluetoothDevice.ACTION_ACL_CONNECTED.equals(action.getAction())){
            //Do something with bluetooth device connection
            log.d("ACTION_ACL_CONNECTED");
        } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action.getAction())){
            //Do something with bluetooth device disconnection
            log.d("ACTION_ACL_DISCONNECTED");
        } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action.getAction())){
        } else {
            log.d("else");
        }*/
    }
}

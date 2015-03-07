package com.example.ernestwong.sidekick;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothConnectionReceiver extends BroadcastReceiver {

    public BluetoothConnectionReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // Get the BluetoothDevice object from the Intent
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            // Add the name and address to an array adapter to show in a ListView
            MainActivity.mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            Log.d("mArrayAdapter", MainActivity.mArrayAdapter.getItem(MainActivity.mArrayAdapter.getCount() -1).toString());
        }
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

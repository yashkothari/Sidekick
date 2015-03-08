package com.example.ernestwong.sidekick;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;

class ConnectThread extends Thread {
    private final BluetoothSocket socket;
    private final BluetoothDevice device;

    public ConnectThread(BluetoothDevice device) {
        BluetoothSocket tmp = null;
        this.device = device;

        try {
            tmp = device.createRfcommSocketToServiceRecord(MainActivity.UUID_BT_SERIAL);
        } catch (IOException e) { }
        socket = tmp;
    }

    public void run() {
        try {
            socket.connect();
            Log.d("Bluetooth", "Success");
        } catch (IOException connectException) {
            Log.d("Bluetooth", "error: " + connectException.toString());
            try {
                socket.close();
            } catch (IOException closeException) { }
            return;
        }

        // Do work to manage the connection (in a separate thread)
        Log.d("Bluetooth", "sucesses");
    }

    /** Will cancel an in-progress connection, and close the socket */
    public void cancel() {
        try {
            socket.close();
        } catch (IOException e) { }
    }
}


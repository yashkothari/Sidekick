package com.example.ernestwong.sidekick;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends ActionBarActivity {

    BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;
    BluetoothConnectionReceiver mReceiver = new BluetoothConnectionReceiver();
    ListView pairedList;
    Button button;
    public static ArrayAdapter<BluetoothDevice> mArrayAdapter;
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

    public static final int REQUEST_ENABLE_BT = 1;
    public static final UUID UUID_BT_SERIAL = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    public static BluetoothArduino mBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pairedList = (ListView) findViewById(R.id.paired_devices);
        button = (Button) findViewById(R.id.bt_search_btn);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mArrayAdapter = new ArrayAdapter<BluetoothDevice>(this, R.layout.device_name);
        registerReceiver(mReceiver, filter);

        mBlue = BluetoothArduino.getInstance("Example");
        mBlue.Connect();
        /*
        pairedList.setAdapter(mArrayAdapter);

        pairedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("click", position + "");
                String address = mArrayAdapter.getItem(position).getAddress();
                mBluetoothAdapter.cancelDiscovery();
                ConnectThread thread = new ConnectThread(mBluetoothAdapter.getRemoteDevice(address));
                thread.start();
                if (connectToDevice(address)) {
                    Log.d("success", "connected");
                }
            }
        });
        */

    }

    public void searchDevices(View v) {
        mArrayAdapter.clear();
        mBluetoothAdapter.startDiscovery();
        mBlue = BluetoothArduino.getInstance("Example");
    }
    @Override
    protected void onResume() {
        super.onResume();
        handleBluetoothConnection();
        //checkPairedDevices();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private boolean connectToDevice(String macAddress) {
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(macAddress);
        try {
            mBluetoothAdapter.cancelDiscovery();
            BluetoothSocket socket = device.createRfcommSocketToServiceRecord(UUID_BT_SERIAL);
            socket.connect();
            Log.d("Connected", "sucess");
            return true;
        } catch(IOException e) {
           // socket.close();
            Log.e("Connect error", e.toString());
            return false;
        }
    }

    private void checkPairedDevices() {
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                mArrayAdapter.add(device);
                if(device.getName().equals("Example")) {
                    if(mBlue.Connect()) {
                        Log.d("Bluetooth", "Connected!");
                    } else {
                        Log.d("Bluetooth", "Failed");
                    }
                }
            }
        }
    }

    private void handleBluetoothConnection(){
        if(mBluetoothAdapter == null) {
            //handle
        }
        else if (!mBluetoothAdapter.isEnabled()) {
              Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
              startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

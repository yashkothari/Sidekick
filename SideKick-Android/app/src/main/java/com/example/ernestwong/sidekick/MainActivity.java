package com.example.ernestwong.sidekick;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
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

import java.util.Set;

public class MainActivity extends ActionBarActivity {

    BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;
    BluetoothConnectionReceiver mReceiver;
    ListView pairedList;
    public static ArrayAdapter<String> mArrayAdapter;
    Button button;
    public static final int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
        button = (Button) findViewById(R.id.bt_search_btn);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mReceiver = new BluetoothConnectionReceiver();
        registerReceiver(mReceiver, filter);
        pairedList = (ListView) findViewById(R.id.paired_devices);
        pairedList.setAdapter(mArrayAdapter);
    }

    public void searchDevices(View v) {
        mArrayAdapter.clear();
        mBluetoothAdapter.startDiscovery();
    }
    @Override
    protected void onResume() {
        super.onResume();
        handleBluetoothConnection();
        checkPairedDevices();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void checkPairedDevices() {
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                Log.d("mArrayAdapter", MainActivity.mArrayAdapter.getItem(MainActivity.mArrayAdapter.getCount() -1).toString());
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

package com.example.ernestwong.sidekick;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BluetoothArduino extends Thread {
    private BluetoothAdapter mBlueAdapter = null;
    private BluetoothSocket mBlueSocket = null;
    private BluetoothDevice mBlueRobo = null;
    OutputStream mOut;
    InputStream mIn;
    private boolean robotFound = false;
    private boolean connected = false;
    private int REQUEST_BLUE_ATIVAR = 10;
    private String robotName;
    private List<String> mMessages = new ArrayList<String>();
    private String TAG = "BluetoothConnector";
    private char DELIMITER = '#';

    private static BluetoothArduino __blue = null;

    public static BluetoothArduino getInstance(String n){
        return __blue == null ? new BluetoothArduino(n) : __blue;
    }

    public static BluetoothArduino getInstance(){
        return __blue == null ? new BluetoothArduino() : __blue;
    }


    private  BluetoothArduino(String Name){
        __blue = this;
        try {
            Log.d("Breakpoint", "getInstance");
            for(int i = 0; i < 2048; i++){
                mMessages.add("");
            }
            robotName = Name;
            mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBlueAdapter == null) {
                LogError("\t\t[#]Phone does not support bluetooth!!");
                return;
            }
            if (!isBluetoothEnabled()) {
              LogError("[#]Bluetooth is not activated!!");
            }

            Set<BluetoothDevice> paired = mBlueAdapter.getBondedDevices();
            if (paired.size() > 0) {
                for (BluetoothDevice d : paired) {
                    Log.d("Devices", d.getName() + "," + d.getName().equals(robotName));
                    if (d.getName().equals(robotName)) {
                        mBlueRobo = d;
                        robotFound = true;
                        break;
                    }
                }
            }
            
            if (!robotFound)
                LogError("\t\t[#]There is not robot paired!!");

        }catch (Exception e){
            LogError("\t\t[#]Erro creating Bluetooth! : " + e.getMessage());
        }

    }

    BluetoothArduino(){
        this("Arduino-Robot");
    }
    
    public boolean isBluetoothEnabled(){
      return mBlueAdapter.isEnabled();
    }

    public boolean Connect(){
        if(!robotFound)
            return false;
        try{
            LogMessage("\t\tConncting to the robot...");
            Log.d("Breakbpoint", "Connecting to robot");
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mBlueSocket = mBlueRobo.createRfcommSocketToServiceRecord(uuid);
            mOut = mBlueSocket.getOutputStream();
            mIn = mBlueSocket.getInputStream();
            connected = true;
            this.start();
            LogMessage("\t\t\t" + mBlueAdapter.getName());
            LogMessage("\t\tOk!!");
            SendMessage("r");
            return true;

        }catch (Exception e){
            LogError("\t\t[#]Error while conecting: " + e.getMessage());
            return false;
        }
    }

    public void run(){
        boolean run = false;
        try {
            mBlueSocket.connect();
        } catch(IOException e) {
            Log.e("error", e.toString());
        }

        while (run) {
            if(connected) {
                Log.d("breakpoint", "running thread");
                try {
                    byte ch, buffer[] = new byte[1024];
                    int i = 0;

                    String s = "";
                    while((ch=(byte)mIn.read()) != DELIMITER){
                        buffer[i++] = ch;
                    }
                    buffer[i] = '\0';

                    final String msg = new String(buffer);

                    MessageReceived(msg.trim());
                    LogMessage("[Blue]:" + msg);

                    run = false;
                } catch (IOException e) {
                    LogError("->[#]Failed to receive message: " + e.getMessage());
                    run = false;
                }
            }
        }
    }

    private void MessageReceived(String msg){
        try {

            Log.d("breakpoint", "Message received");
            mMessages.add(msg);
            try {
                this.notify();
            }catch (IllegalMonitorStateException e){
                //
            }
        } catch (Exception e){
            LogError("->[#] Failed to receive message: " + e.getMessage());
        }
    }

    public boolean hasMensagem(int i){
        try{
            String s = mMessages.get(i);
            if(s.length() > 0)
                return true;
            else
                return false;
        } catch (Exception e){
            return false;
        }
    }

    public String getMenssage(int i){
        return mMessages.get(i);
    }
    
    public void clearMessages(){
      mMessages.clear(); 
    }
    
   public int countMessages(){
     return mMessages.size();
   }

   public String getLastMessage(){
     if(countMessages() == 0)
       return "";
     return mMessages.get(countMessages()-1);
   }
    
    public void SendMessage(String msg){
        try {
            Log.d("breakpoint", "sending message, " + connected + " " + msg.getBytes());
            if(connected) {
                mOut.write(msg.getBytes());
                Log.d("breakpoint", "sending message" + msg.getBytes());
            }

        } catch (IOException e){
            LogError("->[#]Error while sending message: " + e.getMessage());
        }
    }
    
    private void LogMessage(String msg){
      Log.d(TAG, msg);
    }
    
    private void LogError(String msg){
      Log.e(TAG, msg);
    }
     
    public void setDelimiter(char d){
        DELIMITER = d;
    }
    public char getDelimiter(){
        return DELIMITER;
    }

}

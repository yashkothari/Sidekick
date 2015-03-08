#ifndef bluetooth_h
#define bluetooth_h

#include "Arduino.h"
#include <SoftwareSerial.h>


class Bluetooth{
private:
    int rxPin = 6;
    int txPin = 7;
    SoftwareSerial *btSerial = new SoftwareSerial(2, 3);
    SoftwareSerial *blueToothSerial = new SoftwareSerial(6,7);
    char msg[1024];
    void read();
    char Name[256];
    
public:
    int getrxPin();
    void setrxPin(int rx);
    int gettxPin();
    void settxPin(int tx);
    void setupBluetooth();
    Bluetooth(char name[]);
    Bluetooth(char name[], int r, int t);
    String Read();
    void Send(char c[]);
    char *getName();
    void setName(char c[]);
};


#endif
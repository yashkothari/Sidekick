
#include "bluetooth.h"

int Bluetooth::getrxPin(){
    return rxPin;
}

void Bluetooth::setrxPin(int rx){
    rxPin = rx;
}

int Bluetooth::gettxPin(){
    return txPin;
}

void Bluetooth::settxPin(int tx){
    txPin = tx;
}

void Bluetooth::setupBluetooth(){
    btSerial = new SoftwareSerial(rxPin, txPin);
    pinMode(txPin, OUTPUT);
    pinMode(rxPin, INPUT);

    blueToothSerial.begin(38400); //Set BluetoothBee BaudRate to default baud rate 38400
    blueToothSerial.print("\r\n+STWMOD=0\r\n"); //set the bluetooth work in slave mode
    blueToothSerial.print("\r\n+STNA=SideKick\r\n"); //set the bluetooth name as "SeeedBTSlave"
    blueToothSerial.print("\r\n+STOAUT=1\r\n"); // Permit Paired device to connect me
    blueToothSerial.print("\r\n+STAUTO=0\r\n"); // Auto-connection should be forbidden here
    blueToothSerial.print("\r\n+STPIN=7777\r\n");//Set SLAVE pincode"0000"
    delay(2000); // This delay is required.
    blueToothSerial.print("\r\n+INQ=1\r\n"); //make the slave bluetooth inquirable 
    
    Serial.println("The slave bluetooth is inquirable!");
    delay(4000); // This delay is required.
    blueToothSerial.flush();
    /*
    btSerial->begin(38400);

    Serial.println("Bluetooth with 9600");
    btSerial->print("AT+BAUD4");
    delay(1100);
    while (btSerial->available()) 
    Serial.print(btSerial->read());

    btSerial->print(Name);
    delay(1100);
    while (btSerial->available()) 
    Serial.print(btSerial->read());

    Serial.println("\nAjusting PIN - 6666");
    btSerial->print("AT+PIN6666");
    delay(1100);
    while (btSerial->available()) 
    Serial.print(btSerial->read());

    Serial.println("\nBluetooth version");
    btSerial->print("AT+VERSION");
    delay(1100);
    while (btSerial->available()) 
    Serial.print(btSerial->read());
    */
}

Bluetooth::Bluetooth(char name[]){
    setName(name);
    setrxPin(2);
    settxPin(3);
}

Bluetooth::Bluetooth(char name[], int r, int t){
    setName(name);
    setrxPin(r);
    settxPin(t);
    setupBluetooth();
}

void Bluetooth::setName(char c[]){
    strcpy(Name, "AT+NAME");
    strcat(Name, c);
}
char * Bluetooth::getName(){
    return &Name[0];
}


String Bluetooth::Read(){
    char c;
    String retorno = "";
    if(btSerial->available())
        while(1){
            c = btSerial->read();
            retorno += c;
            if(c == '#')
              break;
        }
    return retorno;
}

void Bluetooth::Send(char c[]){
    btSerial->print(c);
}
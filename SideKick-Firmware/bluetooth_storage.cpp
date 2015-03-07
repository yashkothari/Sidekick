 
 // This code will create random numbers between 0 and 255, store them in the EEPROM, then retrieve and display them on the serial monitor. 
#include <EEPROM.h>
#include <SoftwareSerial.h>   //Software Serial Port
#define RxD 6
#define TxD 7
 
#define DEBUG_ENABLED  1
 
SoftwareSerial blueToothSerial(RxD,TxD);

int zz;
int EEsize = 1024; // size in bytes of your board's EEPROM

 
void setup()
{
  Serial.begin(9600);
  randomSeed(analogRead(0));
  pinMode(RxD, INPUT);
  pinMode(TxD, OUTPUT);
  setupBlueToothConnection();
}

void loop()
{
  Serial.println("Writing random numbers...");
  //----------------WRITING DATA to EEPROM-----------------//
  for (int i = 0; i < EEsize; i++)
  {
    //Generates a number between 0 -255 because in EEPROM you are only able to store the integer 0 - 255
    zz=random(255);
    
    //i is the position in the EEPROM to store the integer (0~255) of data zz
    EEPROM.write(i, zz);
  }
  //----------------WRITING DATA to EEPRO<-----------------//
  Serial.println();

  //----------------READING DATA from EEPROM-----------------//
  for (int a=0; a<EEsize; a++)
  {
    //zz is an integer to store the data from the EEPROM position a
    zz = EEPROM.read(a);
    Serial.print("EEPROM position: ");
    Serial.print(a);
    Serial.print(" contains ");
    Serial.println(zz);
    delay(25);

  }
  //----------------READING DATA-----------------//
}



///////////------------////////////

void loop() 
{ 
  char recvChar;
  while(1){
    if(blueToothSerial.available()){//check if there's any data sent from the remote bluetooth shield
      recvChar = blueToothSerial.read();
      Serial.print(recvChar);
    }
    if(Serial.available()){//check if there's any data sent from the local serial terminal, you can add the other applications here
      recvChar  = Serial.read();
      blueToothSerial.print(recvChar);
    }
  }
} 
 
void setupBlueToothConnection()
{
  blueToothSerial.begin(38400); //Set BluetoothBee BaudRate to default baud rate 38400
  blueToothSerial.print("\r\n+STWMOD=0\r\n"); //set the bluetooth work in slave mode
  //MNS: Basically the bluetooth name
  blueToothSerial.print("\r\n+STNA=SeeedBTSlave\r\n"); //set the bluetooth name as "SeeedBTSlave"
  blueToothSerial.print("\r\n+STOAUT=1\r\n"); // Permit Paired device to connect me
  blueToothSerial.print("\r\n+STAUTO=0\r\n"); // Auto-connection should be forbidden here
  delay(2000); // This delay is required.
  blueToothSerial.print("\r\n+INQ=1\r\n"); //make the slave bluetooth inquirable 
  Serial.println("The slave bluetooth is inquirable!");
  delay(2000); // This delay is required.
  blueToothSerial.flush();
}




#include <EEPROMex.h>
#include <EEPROMVar.h>


int address = 0;
 
struct keyValuePair {
int key;
String value;
};
 
keyValuePair keyValueInput[2];
keyValuePair keyValueOutput[2];
 
void setup()
{
<<<<<<< Updated upstream
Serial.begin(9600);
=======
  Serial.begin(9600);
  randomSeed(analogRead(0));
  pinMode(RxD, INPUT);
  pinMode(TxD, OUTPUT);
  setupBlueToothConnection();
}
/*
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

*/

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
>>>>>>> Stashed changes
 
keyValueInput[0].key = 2; keyValueInput[0].value = "FirstName: Kartheek";
keyValueInput[1].key = 4; keyValueInput[1].value = "LastName: Gajjala";
 
EEPROM.writeBlock(address, keyValueInput,2);
EEPROM.readBlock(address, keyValueOutput,2);
 
Serial.print(keyValueOutput[0].key);
Serial.print("\t");
Serial.println(keyValueOutput[0].value);
 
Serial.print(keyValueOutput[1].key);
Serial.print("\t");
Serial.println(keyValueOutput[1].value);
}
<<<<<<< Updated upstream
 
void loop() { }
=======


void sendCommand(string command)
{
  if(bluetoothSerial.available()){
    blueToothSerial.print(command);
  }
  else
  {
    Serial.println("waiting");
  }

}
>>>>>>> Stashed changes

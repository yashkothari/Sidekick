#include <EEPROMex.h>
#include <EEPROMVar.h>


/* Upload this sketch into Crowduino and press reset*/
#include <SoftwareSerial.h>   //Software Serial Port
#define RxD 6
#define TxD 7


int address = 0;
 
struct keyValuePair {
int key;
String value;
};

 
keyValuePair keyValueInput[24];
keyValuePair keyValueOutput[24];
SoftwareSerial blueToothSerial(RxD,TxD);
 
void setup() 
{ 
  Serial.begin(9600);
  
  //Needs to write from the EEPROM library
  //Writing key patient data to EEPROM
  keyValueInput[0].key = 2; keyValueInput[0].value = "ID # : 1234";
  keyValueInput[1].key = 4; keyValueInput[1].value = "Full Name: Michael Scofield";
  keyValueInput[2].key = 4; keyValueInput[2].value = "Age/Gender: 78 years/Female";
  keyValueInput[3].key = 6; keyValueInput[3].value = "Weight: 65 kg";
  keyValueInput[4].key = 8; keyValueInput[4].value = "Language: English";
  keyValueInput[5].key = 10; keyValueInput[5].value = "Past Medical History";
  keyValueInput[6].key = 12; keyValueInput[6].value = "  1. Diabetes Type 2 (since '95)";
  keyValueInput[7].key = 14; keyValueInput[7].value = "  2. Congestive Heart Failure";
  keyValueInput[8].key = 16; keyValueInput[8].value = "  3. Myocardial Infarct('08)";
  keyValueInput[9].key = 18; keyValueInput[9].value = "  4. Osteoporosis";
  keyValueInput[10].key = 20; keyValueInput[10].value = "Previous Surgeries";
  keyValueInput[11].key = 22; keyValueInput[11].value = "  1. Right hip fracture repair('13)";
  keyValueInput[12].key = 24; keyValueInput[12].value = "  2. Coronary Artery Bypass Graft('08)";
  keyValueInput[13].key = 26; keyValueInput[13].value = "Allergies";
  keyValueInput[14].key = 28; keyValueInput[14].value = "  1. Penicillin";
  keyValueInput[15].key = 30; keyValueInput[15].value = "Medications";
  keyValueInput[16].key = 32; keyValueInput[16].value = " 1. Furosemide";
  keyValueInput[17].key = 34; keyValueInput[17].value = " 2. Bisoprolol";
  keyValueInput[18].key = 36; keyValueInput[18].value = " 3. Janumet";
  keyValueInput[19].key = 38; keyValueInput[19].value = " 4. Warfarin";
  keyValueInput[20].key = 40; keyValueInput[20].value = "Social History";
  keyValueInput[21].key = 42; keyValueInput[21].value = "- Lives in nursing home";
  keyValueInput[22].key = 44; keyValueInput[22].value = "Substance use";
  keyValueInput[23].key = 46; keyValueInput[23].value = " - No alcohol or smoking";
  EEPROM.writeBlock(address, keyValueInput,24);
  EEPROM.readBlock(address, keyValueOutput,24);
  
  pinMode(RxD, INPUT);
  pinMode(TxD, OUTPUT);
  setupBlueToothConnection(); 
} 
 
void loop() 
{ 
  char recvChar;
  while(1){
    if(blueToothSerial.available()){//check if there's any data sent from the remote bluetooth shield
      recvChar = blueToothSerial.read();
      Serial.print(recvChar);
      switch(recvChar)
      {
        case'r':
          Serial.println("Sending master the data");
          sendInfo();
          break;
        
        default:
          Serial.println("Command not valid");
      }
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
  blueToothSerial.print("STNA=Example"); //set the bluetooth name as "CrowBTSlave"
  //blueToothSerial.flush();
  blueToothSerial.print("\r\n+STPIN=0000\r\n");//Set SLAVE pincode"0000"
  blueToothSerial.print("\r\n+STOAUT=1\r\n"); // Permit Paired device to connect me
  blueToothSerial.print("\r\n+STAUTO=0\r\n"); // Auto-connection should be forbidden here
  delay(2000); // This delay is required.
  blueToothSerial.print("\r\n+INQ=1\r\n"); //make the slave bluetooth inquirable 
  Serial.println("The slave bluetooth is inquirable!");
  delay(2000); // This delay is required.
  blueToothSerial.flush();
}

void sendInfo()
{
  for(int i=0; i<= 23; i++)
  {
    //blueToothSerial.println(keyValueOutput[i].value);
  } 
  
}



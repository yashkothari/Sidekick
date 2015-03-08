#include <EEPROMex.h>
#include <EEPROMVar.h>


int address = 0;
int address1 = 1;
int address2 = 2;
int address3 = 3;

 
 
struct keyValuePair {
int key;
String value;
};

struct keyValPair {
int key1;
String value1;
};

struct keyValPair1 {
int key2;
String value2;
};

struct keyValPair2 {
int key3;
String value3;
};
 
keyValuePair keyValueInput[2];
keyValuePair keyValueOutput[2];

keyValPair keyValInput[2];
keyValPair keyValOutput[2];

keyValPair1 keyValInput1[2];
keyValPair1 keyValOutput1[2];

keyValPair2 keyValInput2[2];
keyValPair2 keyValOutput2[2];
 
void setup()
{
Serial.begin(9600);
 
keyValueInput[0].key = 2; keyValueInput[0].value = "ID # : 1234";
keyValueInput[1].key = 4; keyValueInput[1].value = "FirstName: Michael";

keyValInput[0].key1 = 2; keyValInput[0].value1 = "LastName: Scofield";
keyValInput[1].key1 = 4; keyValInput[1].value1 = "Age: 30";

keyValInput1[0].key2 = 10; keyValInput1[0].value2 = "Weight: 60 kg";
keyValInput1[1].key2 = 12; keyValInput1[1].value2 = "Language: English";

keyValInput2[0].key3 = 14; keyValInput2[0].value3 = "Serious Conditions: Heart Stroke(Last 20 Days)";
keyValInput2[1].key3 = 16; keyValInput2[1].value3 = "Allergies: Peanuts, Bees";

    
EEPROM.writeBlock(address, keyValueInput,2);
EEPROM.readBlock(address, keyValueOutput,2);

EEPROM.writeBlock(address1, keyValInput,2);
EEPROM.readBlock(address1, keyValOutput,2);

EEPROM.writeBlock(address2, keyValInput1,2);
EEPROM.readBlock(address2, keyValOutput1,2);

EEPROM.writeBlock(address3, keyValInput2,2);
EEPROM.readBlock(address3, keyValOutput2,2);

 
 
Serial.println(keyValueOutput[0].value);
 

Serial.println(keyValueOutput[1].value);


Serial.println(keyValOutput[0].value1);


Serial.println(keyValOutput[1].value1);

Serial.println(keyValOutput1[0].value2);


Serial.println(keyValOutput1[1].value2);

Serial.println(keyValOutput2[0].value3);

Serial.println(keyValOutput2[1].value3);



}
 
void loop() { }
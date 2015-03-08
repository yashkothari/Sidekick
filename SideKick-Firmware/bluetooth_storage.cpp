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
Serial.begin(9600);
 
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
 
void loop() { }
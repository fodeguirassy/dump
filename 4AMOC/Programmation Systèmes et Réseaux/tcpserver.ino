#include <ESP8266WiFi.h>

const char* ssid = "ESGI";
const char* password = "Reseau-GES";

// Le port du socket TCP (Ici 80 bien qu'il ne s'agisse pas de HTTP!! /!\)
WiFiServer server(80);

void setup()
{
  Serial.begin(115200);
  Serial.println();

  Serial.printf("Connecting to %s ", ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println(" connected");

  server.begin();
  Serial.printf("Server started, on %s\n", WiFi.localIP().toString().c_str());
}

void loop()
{ 
  // Récupérer les clients qui se connectent
  WiFiClient client = server.available();
  // wait for a client to connect
  if (client)
  {
    Serial.println("\n[Client connected]");
    while (client.connected())
    {
      // read line by line what the client is requesting
      if (client.available())
      { 
        // Read until character '\r', line break
        String line = client.readStringUntil('\r');
        Serial.print(line);
        // wait for end of client's request, that is marked with an empty line
        if (line == "Hello World!") {
          client.println("Bon Appetit !");
        } else {
          client.println("");
        }
        break;
      }
    }
    delay(1);

    // close the connection:
    client.stop();
    Serial.println("[Client disonnected]");
  }
}

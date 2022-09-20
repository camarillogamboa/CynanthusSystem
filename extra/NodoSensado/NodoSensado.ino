#include <LinkedList.h>
#include <DHT.h>
#include <HTTPClient.h>
#include <WiFi.h>

/**
 * @brief Nodo de sensado
 * El siguiente código define clases, variables y funciones globales que
 * aportan el funcionamiento de un nodo de sensado.
 * Los parámetros a sensar son:
 *  -temp: temperatura actual del ambiente (por medio de DHT).
 *  -hum: humedad actual en el ambiente (por medio de DHT).
 *  -co2: la concentración de co2 en el ambiente (implementación pendiente).
 *  -vueltas_norte,vueltas_este,vueltas_sur,vueltas_oeste:
 *      formas coloquiales de referirse al rpm obtenido a traves de los sensores
 *      de efecto hall dispuestos en 4 posiciones posibles, una por cada punto cardinal.
 *
 *  otros datos importantes:
 *  -mac: dirección física de la interface de red wifi utilizada como identificador del
 *      dispositivo
 *  -rssi: intensidad de la señal wifi captada por la interface de red expresada en db.
 *
 * Los datos descritos anteriormente son enviados a un servidor de flujo de datos en un paquete de datos
 *  o "muestra" en formato Json y utilizando el método POST del protocolo HTTP.
 *
 * El servidor de flujo de datos tiene la capacidad de enviar datos de configuración al nodo de sensado.
 * Para esta implementación, los datos de configuración para el nodo de sensado son enviados junto con la
 * respuesta de la petición HTTP si y solo si, el servidor de flujo detecta un cambio en las propiedades
 * destinadas para el nodo de sensado, por lo que no siempre se va a recibir una actualización de configuración.
 */

/**
 *@brief Representa una entrada de datos formada por una clave y valor ambos de tipo String.
 */
class Entry
{
private:
    /**
     *@brief Clave o llave de la entrada
     *
     */
    String key;
    /**
     * @brief valor de la entrada
     *
     */
    String value;

public:
    /**
     * @brief obtiene la llave de la entrada
     *
     * @return String
     */
    String getKey()
    {
        return key;
    }

    /**
     * @brief obtiene el valor de la entrada
     *
     * @return String
     */
    String getValue()
    {
        return value;
    }

    /**
     * @brief Construye un nuevo objeto de entrada
     *
     * @param key la clave de la entrada
     * @param value el valor de la entrada
     */
    Entry(String key, String value)
    {
        this->key = key;
        this->value = value;
    }
};

/**
 * @brief Identifica los tokens de una cadena que representa pares de clave valor
 *separados por comas, crea los pares y los añade a una lista de entradas.
 *
 */
class Parser
{
public:
    /**
     * @brief analiza caracter por caracter una cadena que posiblemente representa
     * pares de clave y valor separados por coma. El algoritmo es un pequeño autómata
     * de dos estados simples: KEY_ESTATE (estado de clave) y VALUE_STATE (estado de valor).
     * Se inicia el automata en el estado de clave, asumiendo que todos los caracteres antes
     * de un posible simbolo "=" forman la cadena correspondiente a la clave del par. Una vez
     * identificado el símbolo "=" se realiza el cambio de estado desde KEY_STATE A VALUE_STATE.
     * En el estado de valor, se asume que los siguientes caracteres conforman la cadena de valor
     * hasta que se encuentre el símbolo "," o se termine la cadena fuente. Si en el estado de valor se
     * encuentra el símbolo "," se crea el par identificado como un objeto Entry y se almacena en
     * la lista para despues realizar la transición desde el VALUE_STATE a KEY_STATE, repitiendo
     * todo lo anteriormente descrito. En caso de que se llegue a fin de la cadena fuente, se crea
     * el último par identificado y se agrega a la lista de entradas.
     *
     * @param source la cadena fuente
     * @param list  la lista de entradas (preferentemente vacía)
     */
    void parse(String *source, LinkedList<Entry *> *list)
    {
        const int KEY_STATE = 0;
        const int VALUE_STATE = 1;

        String key = "";
        String value = "";

        int length = strlen(source->c_str());
        int state = KEY_STATE;

        for (int i = 0; i < length; i++)
        {
            char code = source->charAt(i);
            switch (state)
            {
            case KEY_STATE:
                if (code != '=')
                    key += code;
                else
                    state = VALUE_STATE;
                break;
            case VALUE_STATE:
                if (code != ',')
                    value += code;

                if (code == ',' || i == length - 1)
                {
                    Entry *entry = new Entry(key, value);
                    key = "";
                    value = "";
                    list->add(entry);
                    state = KEY_STATE;
                }
                break;
            }
        }
    }
};

/*
Representación de la información realacionada con un sensor de efecto hall.
Es útil en la realización de conteo de rpm.
*/
class HallSensor
{

private:
    /**
     * @brief pin al que está conectado el sensor
     *
     */
    unsigned int pin;
    /**
     * @brief bandera booleana usada para evitar la repeticíon de un conteo
     * ocasionado por el sensado sostenido.
     *
     */
    bool captured;
    /**
     * @brief conteo de vueltas
     *
     */
    unsigned int laps;

public:
    /**
     * @brief Obtiene el número del pin utilizado por el sensor
     *
     * @return int
     */
    int getPin()
    {
        return pin;
    }

    /**
     * @brief Obtiene el conteo de vueltas actual
     *
     * @return int
     */
    int getLaps()
    {
        return laps;
    }

    /**
     * @brief reestablece el valor de la variable "captured" a false y laps a 0.
     *
     */
    void reset()
    {
        this->captured = false;
        this->laps = 0;
    }

    /**
     * @brief Realiza el conteo de vueltas.
     * Aumenta el valor de la variable "laps" en 1 si y solo si:
     * -El canal del sensor se encuentra en estado LOW .
     * -La variable "captured" se encuentra en false
     *
     * La bandera booleana "captured" ayuda a evitar el conteo repetitivo
     * a causa del sensado sostenido del sensor de efecto hall.
     */
    void countLaps()
    {
        if (digitalRead(this->pin) == LOW)
        {
            if (!this->captured)
            {
                this->laps++;
                this->captured = true;
            }
        }
        else
            this->captured = false;
    }

    /**
     * @brief Construye un nuevo objeto sensor de efecto hall
     *
     * @param pin
     */
    HallSensor(int pin)
    {
        this->pin = pin;
        reset();
    }
};

/**
 * @brief Número de sensores de efecto hall disponibles
 *
 */
const int NUM_HALL_SENSORS = 4;

/**
 * @brief arreglo con instancias de HallSensor
 *
 */
HallSensor *HALL_SENSORS[] = {
    new HallSensor(25),
    new HallSensor(27),
    new HallSensor(12),
    new HallSensor(4)};

/**
 * @brief inicialización de objeto DHT para el sensado de temperatura y humedad
 *
 */
DHT dht(32, DHT11);

/**
 * @brief dirección del servidor al que se le enviarán las muestras
 *
 */
const String SERVER_IP = "192.168.10.1";
/**
 * @brief puerto donde el servidor recibirá los datos enviados
 *
 */
const int PORT = 9000;
/**
 * @brief ruta del contexto http que gestiona las muestras en el servidor
 *
 */
const String CONTEXT = "/cynanthus/latiro/sample";

/**
 * @brief "tiempo muerto de sensado"
 * Es ocupado para producir un delay en la ejecución del programa, inhabilitando
 * cualquier operación por ese periodo
 *
 */
unsigned long timeOut = 500;
/**
 * @brief "tiempo efectivo de sensado"
 * Dato utilizado para la prolongación del periodo de sensado efectivo.
 * En este periodo se realiza el conteo de rpm de cada sensor de efecto hall,
 *
 */
unsigned long samplingTime = 3000;

/**
 * @brief dirección física de la interface de red wifi del dispositivo
 *
 */
String mac;

/**
 * @brief inicialización del nodo de sensado
 * -Asigna los pines para cada dispositivo conectado al nodo
 * -Inicia la conexión wifi dado un ssid y una contraseña
 * -Establece la dirección mac para el dispositivo
 */
void setup()
{
    Serial.begin(115200);
    for (int i = 0; i < NUM_HALL_SENSORS; i++)
        pinMode(HALL_SENSORS[i]->getPin(), INPUT);

    WiFi.mode(WIFI_STA);
    delay(3000);
    WiFi.begin("Raspilan", "raspados");
    while (WiFi.status() != WL_CONNECTED)
    {
        Serial.println("Conectando");
        delay(1000);
    }

    mac = WiFi.macAddress();
    dht.begin();
}

/**
 * @brief ciclo del proceso principal del nodo
 *
 */
void loop()
{
    // Tiempo muerto
    delay(timeOut);

    if (WiFi.status() != WL_CONNECTED)
        return;

    // Tarea de muestreo efectivo
    for (long time = 0, mark = millis(); time < samplingTime; time = millis() - mark)
        for (int i = 0; i < NUM_HALL_SENSORS; i++)
            HALL_SENSORS[i]->countLaps();

    // lectura del resto de parámetros
    long rssi = WiFi.RSSI();
    float temp = dht.readTemperature();
    float co2 = 0;
    float hum = dht.readHumidity();

    // Preparar url
    String url = "http://" + SERVER_IP + ":" + PORT + CONTEXT;

    // Preparar datos en formato json
    String json = "{\"node\":{\"mac\":\"" + mac + "\"," +
                  "\"rssi\":" + rssi + "" +
                  "}," +
                  "\"environment\":{" +
                  "\"temp\":" + temp + "," +
                  "\"northLaps\":" + HALL_SENSORS[0]->getLaps() + "," +
                  "\"eastLaps\":" + HALL_SENSORS[1]->getLaps() + "," +
                  "\"southLaps\":" + HALL_SENSORS[2]->getLaps() + "," +
                  "\"westLaps\":" + HALL_SENSORS[3]->getLaps() + "," +
                  "\"samplingTime\":" + samplingTime + "," +
                  "\"co2\":" + co2 + "," +
                  "\"hum\":" + hum + "" +
                  "}" +
                  "}";

    HTTPClient http;

    int httpCode;

    // Enviar datos
    // inicializar el ciente http
    http.begin(url);
    // preparar credenciales como cabeceras de la petición
    http.addHeader("Credentials", "agent 8wKYyvo9plF2diRtE0P");

    // realizar petición y obtener el códgio http
    httpCode = http.POST(json);

    // obtener el cuerpo de la repuesta http
    String res = http.getString();

    if (httpCode == HTTP_CODE_OK)
    {
        if (!res.equals("ok"))
        {
            // Preparar lista de entradas
            LinkedList<Entry *> *list = new LinkedList<Entry *>();

            // Crear instancia del parser
            Parser *parser = new Parser();

            // Parsear la respuesta http
            parser->parse(&res, list);

            // Asignar las entradas a sus respectivas variables
            int lenght = list->size();
            for (int i = 0; i < lenght; i++)
            {
                Entry *entry = list->get(i);

                if (entry->getKey().equals("timeOut"))
                {
                    timeOut = entry->getValue().toInt();
                }
                else if (entry->getKey().equals("samplingTime"))
                {
                    samplingTime = entry->getValue().toInt();
                }
            }

            // limpiar la lista de entradas
            list->clear();
        }
    }

    http.end();
    for (int i = 0; i < NUM_HALL_SENSORS; i++)
        HALL_SENSORS[i]->reset();
}

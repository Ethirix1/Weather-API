# Weather-API
 Prerequisites:
 1)Java JDK 8 or higher installed.
 2)An OpenWeatherMap API Key.
 3)Download JSON library.

  Step 1: Create a Folder
  WeatherApp/
├── WeatherSwingApp.java       ← your Java code
├── json-20240303.jar          ← the JSON parser JAR

 Step 2: Add Your API Key
 private final String apiKey = "YOUR_API_KEY_HERE";

 Step 3: Compile the Code
 javac -cp .;json-20250107.jar WeatherSwingApp.java

  Step 4: Run the App
  java -cp .;json-20250107.jar WeatherSwingApp

Step 5: Use the App
A small GUI window will open

Type your city name (e.g., Delhi)

Click "Get Weather"

You’ll see the temperature, humidity, and weather description

![image](https://github.com/user-attachments/assets/91934df6-6c12-43f6-b90f-292e81aa10b7)

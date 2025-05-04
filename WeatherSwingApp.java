import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class WeatherSwingApp extends JFrame {

    private JTextField cityField;
    private JButton getWeatherButton;
    private JTextArea weatherOutput;
    private final String apiKey = "API_KEY"; // Replace with your API key

    public WeatherSwingApp() {
        setTitle("Weather App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI components
        cityField = new JTextField(20);
        getWeatherButton = new JButton("Get Weather");
        weatherOutput = new JTextArea();
        weatherOutput.setEditable(false);

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter City:"));
        inputPanel.add(cityField);
        inputPanel.add(getWeatherButton);

        JScrollPane scrollPane = new JScrollPane(weatherOutput);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action
        getWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText().trim();
                if (!city.isEmpty()) {
                    fetchWeather(city);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a city name.");
                }
            }
        });
    }

    private void fetchWeather(String city) {
        try {
            String urlString = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
                city, apiKey
            );

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            // Parse and display
            JSONObject json = new JSONObject(content.toString());
            String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
            double temp = json.getJSONObject("main").getDouble("temp");
            double feels = json.getJSONObject("main").getDouble("feels_like");
            int humidity = json.getJSONObject("main").getInt("humidity");

            weatherOutput.setText(String.format(
                "City: %s\nDescription: %s\nTemperature: %.1f °C\nFeels Like: %.1f °C\nHumidity: %d%%",
                city, description, temp, feels, humidity
            ));

        } catch (Exception ex) {
            weatherOutput.setText("Error fetching weather data.\n" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WeatherSwingApp().setVisible(true);
        });
    }
}

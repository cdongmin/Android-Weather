import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main extends JFrame {
    public static void main(String[] args) throws IOException, JSONException {
        String apikey = "111fe28ffc24403ffb6b889bc8910af3";
        String houstonWeather = "https://api.openweathermap.org/data/2.5/weather?q=Houston,us&APPID=";
        String theURL = houstonWeather + apikey;
        StringBuilder sb = new StringBuilder();

        BufferedReader br = null;
        try {
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONObject another = jsonObject.getJSONObject("main");
        double s = (double) another.get("temp");
        double celsius = s - 273.15;

        JFrame mainFrame = new JFrame("Current Weather of Houston");
        mainFrame.setSize(2000, 450);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Press if you want to view the current weather of Houston");
        mainFrame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.remove(button);
                mainFrame.revalidate();
                mainFrame.repaint();
                JLabel label = new JLabel();
                label.setText("The current weather of Houston is " + celsius + " Celsius");
                mainFrame.add(label);
                label.paintImmediately(label.getVisibleRect());
            }
        });
        mainFrame.pack();
    }
}

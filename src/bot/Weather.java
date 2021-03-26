package bot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Weather {

    private String city;

    public Weather() {
    }

    /**
     * gets data from JSON object
     *
     * @param json     json file
     * @param dataType data type
     * @return list of all items of datatype
     */
    private List<String> getDataList(JSONObject json, String dataType) {

        List<String> dataList = new ArrayList<>();

        try {
            JSONArray array = json.getJSONArray("forecastTimestamps");
            for (int i = 0; i < array.length(); i++) {
                JSONObject singleObject = array.getJSONObject(i);
                dataList.add(singleObject.get(dataType).toString());
            }
        } catch (NullPointerException e) {
            System.err.println("City dosen't exist");
        }
        return dataList;
    }

    /**
     * gets JSON ojcect with all data about city wether
     *
     * @return
     * @throws IOException
     */
    private JSONObject getCityJSON(String city) throws IOException {

        HttpURLConnection connection = null;
        String request;
        URL url = null;

        //System.out.println(cityCheck(city));

        request = String.format("https://api.meteo.lt/v1/places/%s/forecasts/long-term", city);
        List<String> result = new ArrayList<>();

        try {
            url = new URL(request);
        } catch (MalformedURLException e) {
            System.err.println("Wrong url?");
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.err.println("connection failed to open");
        }

        if (connection instanceof HttpURLConnection) {

            try {
                if (connection.getResponseMessage().equals("OK")) {
                    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
                    BufferedReader buffer = new BufferedReader(inputStream);

                    String line;

                    if ((line = buffer.readLine()) != null) {

                        String requestData = UnicodeParser.unescape_perl_string(line);
                        JSONObject json = new JSONObject(requestData);
                        return json;
                    }
                }
            } catch (ConnectException e) {
                System.err.println("Connection timeout");
            }
        }
        return null;
    }

    /**
     * creates weather chart
     *
     * @param city
     * @return true if chart was created
     */
    public boolean makeChart(String city) {

        JSONObject cityJson = null;

        try {
            cityJson = getCityJSON(city);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cityJson != null) {
            List<String> tempList = new ArrayList<>();
            List<String> dateList = new ArrayList<>();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            tempList = getDataList(cityJson, "airTemperature");
            dateList = getDataList(cityJson, "forecastTimeUtc");

            for (int i = 0; i < tempList.size() / 2; i += 2) {
                String lazyShortDate = dateList.get(i).substring(5, 13) + "h";
                dataset.addValue(Double.parseDouble(tempList.get(i)), "data", lazyShortDate);
            }

            JFreeChart lineChart = ChartFactory.createLineChart
                    (String.format("%s %s", city, cityJson.get("forecastCreationTimeUtc")),
                            "Time",
                            "Temperature",
                            dataset,
                            PlotOrientation.VERTICAL,
                            false,
                            true,
                            false);

            CategoryAxis XAXIS = lineChart.getCategoryPlot().getDomainAxis();
            NumberAxis YAXIS = (NumberAxis) lineChart.getCategoryPlot().getRangeAxis();
            Plot plot = lineChart.getPlot();

            LineAndShapeRenderer rend = (LineAndShapeRenderer) lineChart.getCategoryPlot().getRenderer();

            lineChart.getTitle().setFont(new Font("Consolas", Font.PLAIN, 21));
            lineChart.getTitle().setPaint(Color.lightGray);
            rend.setBaseStroke(new BasicStroke(5.0f));
            rend.setAutoPopulateSeriesStroke(false);
            XAXIS.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
            YAXIS.setTickUnit(new NumberTickUnit(2));
            plot.setBackgroundPaint(Color.darkGray);
            lineChart.setBackgroundPaint(Color.darkGray);
            rend.setSeriesPaint(0, Color.CYAN);
            plot.setOutlineVisible(false);
            YAXIS.setTickMarkPaint(Color.WHITE);
            XAXIS.setTickMarkPaint(Color.WHITE);
            YAXIS.setTickLabelPaint(Color.lightGray);
            XAXIS.setTickLabelPaint(Color.lightGray);
            rend.setSeriesItemLabelPaint(0, Color.lightGray);
            lineChart.removeLegend();
            lineChart.getCategoryPlot().setDomainGridlinePaint(Color.GRAY);
            lineChart.getCategoryPlot().setRangeGridlinePaint(Color.GRAY);
            lineChart.getCategoryPlot().setDomainGridlinesVisible(false);
            lineChart.getCategoryPlot().setRangeGridlinesVisible(true);
            Stroke longerStroke = new BasicStroke(1.0f,
                    BasicStroke.CAP_SQUARE,
                    BasicStroke.JOIN_ROUND,
                    10.0f,
                    new float[]{10.0f, 20.0f},
                    4.0f);
            lineChart.getCategoryPlot().setRangeGridlineStroke(longerStroke);

            int width = 1280;
            int height = 720;

            File lineChartFile = new File("data/weatherChart.jpeg");
            try {
                ChartUtilities.saveChartAsJPEG(lineChartFile, lineChart, width, height);
            } catch (IOException e) {
                throw new bot.exceptions.IOException("Could not interpret command");
            }
            return true;
        }
        return false;
    }
}

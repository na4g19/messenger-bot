package bot.command.commands;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;
import bot.helper.Weather;

public class WeatherCommand extends Command {

    private Weather weather;
    public static String commandID = "!weather";
    private String city;

    public WeatherCommand() {
    }

    public WeatherCommand(String city) {
        this.city = city;
    }

    @Override
    public void execute() {
        weather = new Weather();
        if (weather.makeChart(city)) {
            System.out.println("Sending chart to messenger");
            Scheduler.scheduleSendMedia("weatherChart.jpeg");
        } else {
            Scheduler.scheduleSendText("Either city name typed incorectly or Meteo.lt does not have any weather data about it.");
        }
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID + " [city]")
                .summary("Creates weather graph");
    }

}

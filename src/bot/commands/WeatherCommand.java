package bot.commands;

import bot.*;

import java.util.LinkedList;

import bot.Tokenizer.Token;

public class WeatherCommand extends Command
{
    private Weather weather = new Weather();
    private LinkedList<Token> tokens;

    public WeatherCommand()
    {
        super("WEATHER");
    }
    public WeatherCommand(LinkedList<Token> tokens)
    {
        super("WEATHER");
        this.tokens = tokens;
    }

    @Override
    public void execute()
    {
        if(weather.makeChart(tokens.get(1).getSequence().toString()) == true)
        {
            Scheduler.scheduleSendMedia("weatherChart.jpeg");
        }
        else
        {
            Scheduler.scheduleSendText("Either city name typed incorectly or Meteo.lt does not have any weather data about it.");
        }
    }

    @Override
    public HelpDocsBuilder constructDocs()
    {
        return null;
    }

}

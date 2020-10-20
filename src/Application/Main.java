package Application;
import Lib.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws IOException
    {
        List<Integer> numbers = null;

        if (args.length == 0)
        {
            System.out.println("Specify a file path");
            return;
        }

        try
        {
            numbers = readFile(args[0]);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File isn't found");
            return;
        }
        catch (IOException ex)
        {
            System.out.println("File error");
            return;
        }

        Calculations signalCalculator = new Calculations();

        int dynamicRange = signalCalculator.DynamicRange(numbers);
        int Energy = signalCalculator.SignalEnergy(numbers);
        float avgPower = signalCalculator.AverageSignalPower(numbers);
        float avgSignalsValue = signalCalculator.AverageSignalValue(numbers);
        float signalDispersion = signalCalculator.SignalDispersion(numbers);
        float correlationInterval = signalCalculator.CorrelationInterval(numbers);

        System.out.println("Dynamic signal range: " + dynamicRange);
        System.out.println("Signal energy: " + Energy);
        System.out.println("Average signal power: " + avgPower);
        System.out.println("Average value of signals: " + avgSignalsValue);
        System.out.println("Dispersion of the signal values: " + signalDispersion);
        System.out.println("Correlation interval: " + correlationInterval);

        for(int tau = -10; tau <= 10; tau++)
        {
            float result = signalCalculator.Autocorrelation(numbers,tau);
            System.out.println("Autocorrelation function of discrete signal:(" + tau + "): " + result);
        }
    }
    public static List<Integer> readFile(String path) throws IOException
    {
        List<Integer> list = new ArrayList<Integer>();
        FileInputStream inputStream = new FileInputStream(path);

        int readResult;
        do
        {
            readResult = inputStream.read();

            if (readResult != -1)
            {
                list.add(readResult);
            }

        }
        while (readResult != -1);

        return list;
    }
}

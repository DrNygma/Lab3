package Lib;
import java.util.Collections;
import java.util.List;
public class Calculations {
    public int DynamicRange(List<Integer> numbers)
    {
        int min = Collections.min(numbers);
        int max = Collections.max(numbers);

        return max - min;
    }

    public int SignalEnergy(List<Integer> numbers)
    {
        int result = 0;

        for (Integer i : numbers)
        {
            result += (i * i);
        }

        return result;
    }

    public float AverageSignalPower(List<Integer> numbers)
    {
        float result = 0f;

        for (Integer i : numbers)
        {
            result += (i * i);
        }

        return result / numbers.size();
    }

    public float AverageSignalValue(List<Integer> numbers)
    {
        float result = 0f;

        for (Integer i : numbers)
        {
            result += i;
        }

        return result / numbers.size();
    }

    public float SignalDispersion(List<Integer> numbers)
    {
        float m = AverageSignalValue(numbers);
        float result = 0f;

        for (Integer i : numbers)
        {
            result += ((i - m) * (i - m));
        }

        return result / numbers.size();
    }

    public float Autocorrelation(List<Integer> numbers, int tau)
    {
        float m = AverageSignalValue(numbers);
        float result = 0f;

        tau = (tau > 0) ? tau : -tau;

        for(int i = 0; i < numbers.size() - tau; i++)
        {
            result += (numbers.get(i + tau) - m) * (numbers.get(i) - m);
        }

        return result / (numbers.size() - tau);
    }

    public float CorrelationInterval(List<Integer> numbers)
    {
        float result = 0f;

        for(int i = 0; i < numbers.size(); i++)
        {
            result += Autocorrelation(numbers, i);
        }

        return result / Autocorrelation(numbers, 0);
    }
}

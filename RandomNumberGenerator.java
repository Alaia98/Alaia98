public class RandomNumberGenerator
{   public int generateEvenRandomNumber (int max)

{
    int half = max / 2;
    int number = generateRandomNumber (half);
    number = number * 2;
    return number;

}
    public int generateOddRandomNumber (int max)
    {
        int half = max / 2;
        half = half - 1;
        int number = generateRandomNumber (0, half) ;
        number = number * 2 + 1;
        return number;
    }

    public int generateRandomNumber (int max)
    {
        return generateRandomNumber (1, max);
    }
    public int generateRandomNumber (int min, int max)
    {
        double randomNumber = Math.random();
        int range = max - min +1;
        int number = (int) (randomNumber * range) + min;
        return number;
    }

    public static void main (String[] args)
    {
        RandomNumberGenerator gen = new RandomNumberGenerator();
        for (int i = 0; i<10; i++)
        {
            int num = gen.generateEvenRandomNumber(100);
            System.out.println(num);
        }
    }
}
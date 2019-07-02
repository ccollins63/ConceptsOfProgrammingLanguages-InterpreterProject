
import java.util.List;

public class main
{
    public static void main(String args[])
    {
        LexicalAnalyzer scanner = new LexicalAnalyzer("/Users/cameron/OneDrive - Kennesaw State University/Cam/KSU/Spring 2019/CS4308Project/src/welcome2.scl");

        List<Token> tokens = scanner.getTokens();
        Executer execute = new Executer(tokens);

        System.out.println("Program Start");
        System.out.println("-------------------------");
        System.out.println(execute.execute());
        System.out.println("-------------------------");
    }
}

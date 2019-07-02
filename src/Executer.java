import java.util.ArrayList;
import java.util.List;

public class Executer
{
    Parser parse;
    List<Token> tokens;

    public Executer(List<Token> tokens)
    {
        parse = new Parser(tokens);
        this.tokens = tokens;

    }
    public int execute()
    {
        int product = 0;
        int variable = 0;
        ArrayList parseFunction = new ArrayList();
        for(int i = 0; i < tokens.get(tokens.size()-1).getRowNumber(); i++)
        {
            parseFunction.add(parse.ParseTokens(parse.getTokens()).get(i));
        }
        for(int i = 0; i < parseFunction.size(); i++)
        {
            switch(parseFunction.get(i).toString())
            {
                case "ASSIGNMENT_FUNCTION":
                {
                    variable = 12;
                }
                case "IF_CONDITION_FUNCTION":
                {
                    if (variable > 181)
                    {
                        variable = 32767;
                    }
                }
                case "MULTIPLICATION_FUNCTION":
                {
                    product = variable * variable;
                }
            }
        }
        return product;
    }
}

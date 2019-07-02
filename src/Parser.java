import java.util.ArrayList;
import java.util.List;

public class Parser
{
    private List<Token> token;
    private ArrayList lineByLineFunction = new ArrayList();

    public Parser(List<Token> token)
    {
        this.token = token;
    }

    public List<Token> getTokens()
    {
        return token;
    }

    public ArrayList ParseTokens(List<Token> tokens)
    {
        int line = 1;
        TokenType token;
        ArrayList tokenLine = new ArrayList();
        for(int i = 0; i <= tokens.size()-1; i++)
        {
            if (line == tokens.get(i).getRowNumber())
            {
                token = tokens.get(i).getTokenType();
            }
            else
            {
                lineByLineFunction.add(SyntaxAnalyzer(tokenLine));
                token = tokens.get(i).getTokenType();
                tokenLine.clear();
            }
            tokenLine.add(token);
            if (i == tokens.size()-1)
            {
                lineByLineFunction.add(SyntaxAnalyzer(tokenLine));
            }
            line = tokens.get(i).getRowNumber();
        }
        return lineByLineFunction;
    }

    public String SyntaxAnalyzer(ArrayList tokenLine)
    {
        String function = null;
        for (int i = 0; i < tokenLine.size(); i++)
        {
            if (tokenLine.get(i).toString() == "FUNCTION_TOK")
            {
                i++;
                if (tokenLine.get(i).toString() == "VARIABLE_TOK")
                {
                    i++;
                    if (tokenLine.get(i).toString() == "COLON_TOK")
                    {
                        i++;
                        if (tokenLine.get(i).toString() == "INT_TOK")
                        {
                            function = "FUNCTION_START_FUNCTION";
                        }
                    }
                }
            }
            else if (tokenLine.get(i).toString() == "VAR_INPUT_TOK")
            {
                function = "VARIABLE_INPUT_FUNCTION";
            }
            else if (tokenLine.get(i).toString() == "VARIABLE_TOK")
            {
                i++;
                if (tokenLine.get(i).toString() == "DEFINE_TOK")
                {
                    i++;
                    if (tokenLine.get(i).toString() == "DIGIT_TOK")
                    {
                        function = "ASSIGNMENT_FUNCTION";
                    }
                    else if (tokenLine.get(i).toString() == "VARIABLE_TOK")
                    {
                        i++;
                        if (tokenLine.get(i).toString() == "MULTIPLICATION_TOK")
                        {
                            i++;
                            if (tokenLine.get(i).toString() == "VARIABLE_TOK")
                            {
                                i++;
                                if (tokenLine.get(i).toString() == "END_EXPRESSION_TOK")
                                {
                                    function = "MULTIPLICATION_FUNCTION";
                                }
                            }
                        }
                    }
                }
            }
            else if (tokenLine.get(i).toString() == "END_VAR_TOK")
            {
                function = "END_VARIABLE_INPUT_FUNCTION";
            }
            else if (tokenLine.get(i).toString() == "BEGIN_TOK")
            {
                function = "BEGIN_STATEMENTS_FUNCTION";
            }
            else if (tokenLine.get(i).toString() == "IF_TOK")
            {
                i++;
                if (tokenLine.get(i).toString() == "VARIABLE_TOK")
                {
                    i++;
                    if (tokenLine.get(i).toString() == "LT_EQUAL_TOK")
                    {
                        i++;
                        if (tokenLine.get(i).toString() == "DIGIT_TOK")
                        {
                            i++;
                            if (tokenLine.get(i).toString() == "THEN_TOK")
                            {
                                function = "IF_CONDITION_FUNCTION";
                                break;
                            }
                        }
                    }
                }
            }
            else if (tokenLine.get(i).toString() == "VARIABLE_TOK")
            {
                i++;
                if (tokenLine.get(i).toString() == "DEFINE_TOK")
                {
                    i++;
                    if (tokenLine.get(i).toString() == "VARIABLE_TOK")
                    {
                        i++;
                        if (tokenLine.get(i).toString() == "MULTIPLICATION_TOK")
                        {
                            i++;
                            if (tokenLine.get(i).toString() == "VARIABLE_TOK")
                            {
                                i++;
                                if (tokenLine.get(i).toString() == "END_EXPRESSION_TOK")
                                {
                                    function = "MULTIPLICATION_FUNCTION";
                                }
                            }
                        }
                    }
                }
            }
            else if (tokenLine.get(i).toString() == "ELSE_TOK")
            {
                function = "ELSE_FUNCTION";
            }
            else if (tokenLine.get(i).toString() == "END_IF_TOK")
            {
                i++;
                if (tokenLine.get(i).toString() == "END_EXPRESSION_TOK")
                {
                    function = "END_IF_FUNCTION";
                }
            }
            else if (tokenLine.get(i).toString() == "END_FUNCTION_TOK")
            {
                function = "FUNCTION_END_FUNCTION";
            }

        }   //for loop end
        return function;

    } //function end
}

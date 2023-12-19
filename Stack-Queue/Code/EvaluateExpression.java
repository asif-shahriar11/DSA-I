import java.util.Scanner;

public class EvaluateExpression {

    public static double compute(char operator, double value1, double value2) {
        double result;
        if(operator=='+')   result = value1+value2;
        else if(operator=='-')   result =  value2-value1;
        else if(operator=='*')   result =  value1*value2;
        else {
            if(value1==0.0)   throw new ArithmeticException("Division by 0 not allowed.");
            else result = value2/value1;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<Double> valueStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        boolean isValid = true;
        boolean isUnaryMinus = false;

        System.out.println("Enter a mathematical expression: ");
        String expression = input.nextLine();
        char[] charArray = expression.toCharArray();

        for(int i=0; i<charArray.length; i++) {
            char c = charArray[i];
            if(c>='0' && c<='9') {
                // there may be more than 1 digit
                //StringBuilder s = new StringBuilder();
                StringBuilder whole = new StringBuilder();
                StringBuilder fraction = new StringBuilder();
                int j = i;
                /*while((j<charArray.length) &&(charArray[j]>='0') && (charArray[j]<='9')) {
                    s.append(charArray[j]);
                    j++;
                }*/
                while(j<charArray.length) {
                    if(charArray[j]>='0' && charArray[j]<='9') {
                        whole.append(charArray[j]);
                        j++;
                    }
                    else if(charArray[j]=='.') {
                        int k = j+1;
                        while(k<charArray.length &&(charArray[k]>='0') && (charArray[k]<='9') ) {
                            fraction.append(charArray[k]);
                            k++;
                        }
                        j = k;
                    } else break;
                }
                i = j-1;
                //double n = Double.parseDouble(String.valueOf(whole))+Double.parseDouble(String.valueOf(fraction));
                double n = 0.0;
                if(whole.length()>0) n += Double.parseDouble(String.valueOf(whole));
                if(fraction.length()>0) n += Double.parseDouble(String.valueOf(fraction))/Math.pow(10,fraction.length());
                if(isUnaryMinus)    n = -n;
                //System.out.println(n);
                valueStack.push(n);
            }
            else if(c=='(') operatorStack.push(c);
            else if(c==')') {
                if(isUnaryMinus)    isUnaryMinus = false;
                else {
                    while(operatorStack.peek()!='(')    {
                        try {
                            valueStack.push(compute(operatorStack.pop(),valueStack.pop(),valueStack.pop()));
                            //System.out.println(valueStack.peek());
                        } catch (Exception e) {
                            System.out.println(e);
                            isValid = false;
                            break;
                        }
                    }
                    operatorStack.pop();
                }
            }
            else if(c=='+'|| c=='-'|| c=='*'|| c=='/')  {
                if ((c=='-') && (!operatorStack.isEmpty())) {
                    if(charArray[i-1]=='(') {
                        isUnaryMinus = true;
                        operatorStack.pop(); // flashing out the '('
                        continue;
                    }
                }
                if(!(operatorStack.isEmpty()) && (operatorStack.peek()!='(')) {
                   char prevOp = operatorStack.peek();
                   if((prevOp=='+'||prevOp=='-') && (c=='*'||c=='/')) {} // nothing to do here
                   else {
                       try {
                           valueStack.push(compute(operatorStack.pop(),valueStack.pop(),valueStack.pop()));
                           //System.out.println(valueStack.peek());
                       } catch (Exception e) {
                           //System.out.println(e);
                           isValid = false;
                           break;
                       }
                   }
               }
               operatorStack.push(c);
            }
            else isValid = false;

            //System.out.println("k");
        }
        // if their are parenthesis at the beginning and end of the expression, the entire expression is now evaluated
        // otherwise, the evaluation is yet to be completed
        while(!operatorStack.isEmpty()) {
            try {
                valueStack.push(compute(operatorStack.pop(),valueStack.pop(),valueStack.pop()));
            } catch (Exception e) {
                //System.out.println(e);
                isValid = false;
                break;
            }
        }

        if(isValid) System.out.println("Valid expression, Computed value: "+valueStack.pop());
        else System.out.println("Not valid.");

        input.close();
    }
}
package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorListener {
    private long result;
    @JmsListener(destination = "calculatorQueue")
    public void receiveMessage(final String strInput) {
        String regex = "[\\+\\-\\*]";
        String[] splitStrs=strInput.split(regex);
        long number= Long.parseLong(splitStrs[1]);
        if(strInput.charAt(0)=='+'){
            result=result+number;
        }
        if(strInput.charAt(0)=='-'){
            result=result-number;

        }
        if(strInput.charAt(0)=='*'){
            result=result*number;

        }
        if(strInput.charAt(0)=='/'){
            result=result/number;
        }
        System.out.println("Result is: "+result);
    }
}

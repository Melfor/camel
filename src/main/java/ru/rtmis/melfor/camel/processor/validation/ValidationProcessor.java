package ru.rtmis.melfor.camel.processor.validation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ValidationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String mathOperation = (String) exchange.getIn().getHeader("mathOperation");
        if(!"SUBTRACTION".equals(mathOperation) && !"DIVISION".equals(mathOperation)){
            throw new IllegalArgumentException("Invalid Request");
        }
    }
}
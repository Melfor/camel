package ru.rtmis.melfor.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;

@Component
public class CalcDtoValidationProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        CalcDto calcDto = (CalcDto) exchange.getIn().getBody();
        if (calcDto.getFirst() == null || calcDto.getSecond() == null) {
            throw new IllegalArgumentException("Both arguments shouldn't be null");
        }
    }
}
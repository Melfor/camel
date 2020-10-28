package ru.rtmis.melfor.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import ru.rtmis.melfor.camel.dto.CalcDto;
import ru.rtmis.melfor.camel.dto.ResultDto;

@Component
public class DivisionProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        CalcDto calcDto = (CalcDto) exchange.getIn().getBody();
        int result = calcDto.getFirst() / calcDto.getSecond();
        ResultDto resultDto = new ResultDto(result);
        exchange.getIn().setBody(resultDto);
    }
}

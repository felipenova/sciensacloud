package br.com.sciensa.sciensacloud.helper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class JsonStdDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = jsonParser.getText();
        try {
            return format.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
	}
  
}
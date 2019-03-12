package org.github.davidcana.jcrud.core.JSON.serializers;

import java.io.IOException;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalTimeSerializer extends StdSerializer<LocalTime> {

	private static final long serialVersionUID = 1L;
	private static String template = "%02d:%02d";
    
	public LocalTimeSerializer() {
        this(null); 
    }
    
    public LocalTimeSerializer(Class<LocalTime> t) {
        super(t); 
    }
    
	@Override
	public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		
		String value = String.format( 
				template, 
				localTime.getHour(),
				localTime.getMinute()
		);
		jsonGenerator.writeString(value);
	}

}

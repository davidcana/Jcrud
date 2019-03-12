package org.github.davidcana.jcrud.core.JSON.deserializers;

import java.io.IOException;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalTimeDeserializer extends StdDeserializer<LocalTime> {

	private static final long serialVersionUID = 1L;

	public LocalTimeDeserializer() {
        this(null);
    }
 
    public LocalTimeDeserializer(Class<LocalTime> t) {
        super(t);
    }
 
    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException, JsonProcessingException {

        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
         
        String value = node.textValue();
        LocalTime localTime = LocalTime.parse(value);
        
        return localTime;
    }
}

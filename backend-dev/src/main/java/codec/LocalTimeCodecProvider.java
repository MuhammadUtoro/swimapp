package codec;

import java.time.LocalTime;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import org.bson.codecs.Codec;

public class LocalTimeCodecProvider implements CodecProvider{
    @SuppressWarnings("unchecked")
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if(clazz.equals(LocalTime.class)) {
            return (Codec<T>) new LocalTimeCodec();
        }
        return null;
    }
}

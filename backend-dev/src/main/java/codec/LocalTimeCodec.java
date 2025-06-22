package codec;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;

import org.bson.codecs.Codec;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;


public class LocalTimeCodec implements Codec<LocalTime>{
    @Override
    public void encode(BsonWriter writer, LocalTime value, EncoderContext encoderContext) {
        long millis = value.atDate(java.time.LocalDate.ofEpochDay(0))
        .atZone(ZoneOffset.UTC)
        .toInstant()
        .toEpochMilli();
        writer.writeDateTime(millis);
    }

    @Override
    public Class<LocalTime> getEncoderClass() {
        return LocalTime.class;
    }

    @Override
    public LocalTime decode(BsonReader reader, DecoderContext decoderContext) {
        long millis = reader.readDateTime();
        return Instant.ofEpochMilli(millis)
        .atZone(ZoneOffset.UTC)
        .toLocalTime();
    }
}


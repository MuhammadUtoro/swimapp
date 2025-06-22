package codec;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MongoClientProducer {

    @Produces
    @ApplicationScoped
    public MongoClient mongoClient() {
        CodecRegistry defaultCodecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        CodecRegistry customCodecs = CodecRegistries.fromProviders(new LocalTimeCodecProvider());

        CodecRegistry combinCodecRegistry = CodecRegistries.fromRegistries(customCodecs, defaultCodecRegistry);
        
        MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(combinCodecRegistry)
            .build();
    
    
        return MongoClients.create(settings);
    }

}

package com.myaseen.storage.redis.storage.serializer;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class FSTSerializer implements RedisSerializer<Object> {
    @Override
    public byte[] serialize(Object serializable) throws SerializationException {
        try(FSTObjectOutput output = new FSTObjectOutput()) {
            output.writeObject(serializable);
            return output.getBuffer();
        }catch (IOException e){
           throw new SerializationException(e.getMessage(),e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try(FSTObjectInput input = new FSTObjectInput()) {
            input.read(bytes);
            return input.readObject();
        } catch (IOException|ClassNotFoundException e) {
            throw new SerializationException(e.getMessage(),e);
        }
    }
}

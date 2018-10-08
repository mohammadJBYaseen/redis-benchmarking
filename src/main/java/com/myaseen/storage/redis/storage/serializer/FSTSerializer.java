package com.myaseen.storage.redis.storage.serializer;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.springframework.core.NestedIOException;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

public class FSTSerializer implements RedisSerializer<Object> {

    private static final FSTSerializer instance = new FSTSerializer();

    public static FSTSerializer getInstance() {
        return instance;
    }

    private FSTSerializer(){

    }


    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes == null || bytes.length ==0)
            return null;
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            return deserialize(byteStream);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public byte[] serialize(Object object) {
        if (object == null)
            return new byte[0];
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(256);
            serialize(object, byteStream);
            return byteStream.toByteArray();
        } catch (Exception ex) {
            throw new SerializationException("Cannot serialize", ex);
        }
    }

    public void serialize(Object object, OutputStream stream) throws IOException {
        if (!(object instanceof Serializable))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " is not serializable");
        try (FSTObjectOutput out = new FSTObjectOutput(stream)) {
            out.writeObject(object);
        }
    }

    public Object deserialize(InputStream stream) throws IOException {
        try (FSTObjectInput in = new FSTObjectInput(stream)) {
            return in.readObject();
        } catch (ClassNotFoundException e) {
            throw new NestedIOException("Failed to deserialize object", e);
        }
    }
}

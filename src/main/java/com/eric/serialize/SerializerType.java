package com.eric.serialize;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class SerializerType {
    public static byte JSON_TYPE = 0;

    public static Serializer findSerializerType(byte serializeAlgorithm) {
        if (serializeAlgorithm == JSON_TYPE) {
            return Serializer.DEFAULT;
        }
        return null;
    }
}

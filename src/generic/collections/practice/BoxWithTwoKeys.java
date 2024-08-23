package generic.collections.practice;

public class BoxWithTwoKeys<K,V> {
    K objectKey;
    V objectValue;

    public K getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(K objectKey) {
        this.objectKey = objectKey;
    }

    public V getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(V objectValue) {
        this.objectValue = objectValue;
    }

    @Override
    public String toString() {
        return "BoxWithTwoKeys{" +
                "objectKey=" + objectKey +
                ", objectValue=" + objectValue +
                '}';
    }
}

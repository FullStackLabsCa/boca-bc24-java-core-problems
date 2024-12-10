package io.reactivestax.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CacheEntryValue<V> {
    public V value;
    long ttl;
    LocalDateTime createdTime;
    LocalDateTime lastAccessTime;
    int count;

    public CacheEntryValue(V value, long ttl) {
        this.value = value;
        this.ttl = ttl;
        this.lastAccessTime = LocalDateTime.now();
    }

    public CacheEntryValue(V value) {
        this.value = value;
        this.createdTime = LocalDateTime.now();
        this.lastAccessTime = LocalDateTime.now();
    }

    public Boolean isExpired() {
        if (ttl == -1) {
            return false;
        }
        LocalDateTime expiredDuration = lastAccessTime.plusSeconds(ttl);
        return LocalDateTime.now().isAfter(expiredDuration);
    }

    public void updateLastAccessTime() {
        this.lastAccessTime = LocalDateTime.now();
    }

    public void increaseCount() {
        count = count + 1;
    }
}

package io.reactivestax.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CacheEntryValue<V> {
    public V value;
    public long ttl;
    public LocalDateTime createdTime;
    public LocalDateTime lastAccessTime;

    public CacheEntryValue(V value, long ttl) {
        this.value = value;
        this.ttl = ttl;
        this.createdTime = LocalDateTime.now();
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
}

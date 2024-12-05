package cache_lib;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TTL{
    private long ttlDuration; //In Seconds
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
}
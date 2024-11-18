package orkaTraks.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController

public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(8, 0);  // 8:00 AM
        LocalTime end = LocalTime.of(20, 0);   // 8:00 PM

        if (now.isAfter(start) && now.isBefore(end)) {
            return ResponseEntity.ok("Service is active!");
        } else {
            return ResponseEntity.status(503).body("Service is inactive!");
        }
    }
}

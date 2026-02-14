package com.luna.toggledemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/user/me")
    public Map<String, String> getMe(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return Map.of("username", "anonymous", "role", "NONE");
        return Map.of(
            "username", userDetails.getUsername(),
            "role", userDetails.getAuthorities().toString()
        );
    }

    @GetMapping("/status")
    public Map<String, Boolean> getFeatureStatus() {
        logger.info("Fetching all feature statuses for Togglz");
        Map<String, Boolean> status = new HashMap<>();
        for (MyFeatures feature : MyFeatures.values()) {
            status.put(feature.name(), feature.isActive());
        }
        return status;
    }

    @GetMapping("/compute")
    public Map<String, Object> doCompute() {
        Map<String, Object> result = new HashMap<>();
        if (MyFeatures.ADVANCED_LOGIC.isActive()) {
            logger.info("Executing ADVANCED_LOGIC path - Success");
            result.put("algorithm", "Togglz AI Engine");
            result.put("result", 9999);
        } else {
            logger.info("Executing standard logic path");
            result.put("algorithm", "Basic Adder");
            result.put("result", 1);
        }
        return result;
    }

    @GetMapping("/message")
    public Map<String, String> getMessage() {
        if (MyFeatures.EASTER_EGG.isActive()) {
            logger.info("User allowed to see EASTER_EGG");
            return Map.of("content", "🎉 Togglz Secret: 大大 is the Master of Config! 🌙✨");
        }
        logger.warn("EASTER_EGG access blocked or disabled");
        return Map.of("content", "Regular user update incoming.");
    }
}

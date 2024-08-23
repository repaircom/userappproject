package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    @Value("${otp.expiry.time}")
    private long otpExpiryTime;  // Time in milliseconds

    @Value("${otp.api.url}")
    private String otpApiUrl;

    @Value("${otp.api.key}")
    private String otpApiKey;

    private final Map<String, OtpDetails> otpStore = new ConcurrentHashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();

    // Generate and send OTP
    public boolean generateAndSendOtp(String phoneNumber) {
        String otp = generateOtp();
        OtpDetails otpDetails = new OtpDetails(otp, System.currentTimeMillis());

        otpStore.put(phoneNumber, otpDetails);

        // Send OTP via external OTP API
        return sendOtpToUser(phoneNumber, otp);
    }

    // Validate OTP
    public boolean validateOtp(String phoneNumber, String otp) {
        OtpDetails otpDetails = otpStore.get(phoneNumber);

        if (otpDetails == null) {
            return false;
        }

        // Check if OTP is expired
        if (System.currentTimeMillis() - otpDetails.getTimestamp() > otpExpiryTime) {
            otpStore.remove(phoneNumber);
            return false;
        }

        // Validate OTP
        if (otpDetails.getOtp().equals(otp)) {
            otpStore.remove(phoneNumber);
            return true;
        }

        return false;
    }

    // Generate a random OTP
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Send OTP to the user via API
    private boolean sendOtpToUser(String phoneNumber, String otp) {
        try {
            // Create a request payload
            Map<String, String> requestPayload = new HashMap<>();
            requestPayload.put("phoneNumber", phoneNumber);
            requestPayload.put("otp", otp);

            // Send OTP via external OTP API
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + otpApiKey);
            return restTemplate.postForObject(otpApiUrl, requestPayload, Boolean.class, headers);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Inner class to store OTP details
    private static class OtpDetails {
        private final String otp;
        private final long timestamp;

        public OtpDetails(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }

        public String getOtp() {
            return otp;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}

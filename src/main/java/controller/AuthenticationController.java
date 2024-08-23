package controller;

import service.OtpService;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String phoneNumber) {
        boolean isOtpSent = otpService.generateAndSendOtp(phoneNumber);

        if (isOtpSent) {
            return ResponseEntity.ok("OTP sent successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to send OTP.");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        boolean isOtpValid = otpService.validateOtp(phoneNumber, otp);

        if (isOtpValid) {
            // You may still want to save or update the user without generating a token
            String name = "defaultName";
            String location = "defaultLocation";
            userService.saveOrUpdateUser(phoneNumber, name, location);
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP.");
        }
    }
}

package model;

import lombok.Data;

@Data
public class JwtRequest {

    private String phoneNumber;

    public JwtRequest() {
    }

    public JwtRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

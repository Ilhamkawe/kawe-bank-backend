package com.example.kawebackend.dto.reqbody.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePinReqBody {
    String userId;
    String previousPin;
    String newPin;
}

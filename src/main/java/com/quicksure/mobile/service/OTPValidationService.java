package com.quicksure.mobile.service;

import com.quicksure.mobile.entity.OTPGeneration;

public interface OTPValidationService {
 public OTPGeneration templateSMS(String phoneNo,OTPGeneration otpGeneration);
}

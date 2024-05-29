package pl.dmcs.service;

public interface ReCaptchaService {
    boolean verify(String captcha);
}


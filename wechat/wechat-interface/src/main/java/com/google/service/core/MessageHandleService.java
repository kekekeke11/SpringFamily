package com.google.service.core;

import java.util.Map;

public interface MessageHandleService {

    String handleMessage(Map<String, String> params) throws Exception;
}

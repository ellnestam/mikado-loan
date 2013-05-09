package com.ssem.loan;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.server.Request;

public class StubbedRequest extends Request {

    Map<String, String> map = new HashMap<String, String>();

    public StubbedRequest() {
        map.put("apply", "");
    }

    @Override
    public String getParameter(String name) {
        return map.get(name);
    }

}

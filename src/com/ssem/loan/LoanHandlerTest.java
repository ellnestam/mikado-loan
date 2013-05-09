package com.ssem.loan;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoanHandlerTest {

    @Test
    public void test() throws Exception {
        LoanHandler loanHandler = new LoanHandler();
        StubbedServletRequest request = new StubbedServletRequest();
        StubbedResponse response = new StubbedResponse();
        loanHandler.handle(null, new StubbedRequest(), request, response);
        response.getWriter().flush();
        assertEquals("Incorrect parameters provided\n", response.responseAsText());
    }

}

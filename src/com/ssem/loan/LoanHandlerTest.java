package com.ssem.loan;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class LoanHandlerTest {

    LoanHandler loanHandler;
    StubbedRequest baseRequest;
    StubbedResponse response;

    @Before
    public void setUp() {
        loanHandler = new LoanHandler();
        baseRequest = new StubbedRequest();
        response = new StubbedResponse();
    }

    @Test
    public void enErrorMessageIsReturnedWheneverAnIncompleteRequestIsMade() throws Exception {
        StubbedServletRequest request = new StubbedServletRequest(Collections.<String, String> emptyMap());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        assertEquals("Incorrect parameters provided\n", response.responseAsText());
    }

    @Test
    public void givenThatAnApplicationContainsAllNecessaryInformationAnIdIsReturned() throws Exception {
        StubbedServletRequest request = new StubbedServletRequest(applyParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        String handlerResponse = response.responseAsText();
        assertEquals("{\"id\":1}\n", handlerResponse);
    }

    @Test
    public void givenAnIdTheStatusOfLoanIsReturned() throws Exception {
        StubbedServletRequest request = new StubbedServletRequest(fetchParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        assertEquals("{\"applicationNo\":4,\"amount\":100,\"contact\":\"a@ducks.burg\",\"approved\":true}\n",
                response.responseAsText());
    }

    @Test
    public void loanApplicationsCanBeApproved() throws Exception {
        StubbedServletRequest request = new StubbedServletRequest(approveParams());
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        assertEquals("{\"id\":3}\n", response.responseAsText());
    }

    private HashMap<String, String> approveParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.APPROVE);
        params.put("ticketId", "3");
        return params;
    }

    private HashMap<String, String> applyParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.APPLICATION);
        params.put("amount", "100");
        params.put("contact", "donald@ducks.burg");
        return params;
    }

    private HashMap<String, String> fetchParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.FETCH);
        params.put("ticketId", "4");
        return params;
    }
}

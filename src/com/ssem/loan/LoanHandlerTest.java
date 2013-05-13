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
    MemoryLoanRepository repository;

    @Before
    public void setUp() {
        repository = new MemoryLoanRepository();
        loanHandler = new LoanHandler(repository);
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
        int amount = 3000;
        String contact = "a@ducks.burg";
        long id = repository.apply(amount, contact);
        StubbedServletRequest request = new StubbedServletRequest(fetchParams(id + ""));
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        assertEquals("{\"applicationNo\":" + id + ",\"amount\":" + amount + ",\"contact\":\"" + contact
                + "\",\"approved\":false}\n", response.responseAsText());
    }

    @Test
    public void loanApplicationsCanBeApproved() throws Exception {
        long id = repository.apply(100, "a@a.com");
        StubbedServletRequest request = new StubbedServletRequest(approveParams(id + ""));
        loanHandler.handle(null, baseRequest, request, response);
        response.getWriter().flush();
        assertEquals("{\"id\":" + id + "}\n", response.responseAsText());
    }

    private HashMap<String, String> approveParams(String ticketId) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.APPROVE);
        params.put("ticketId", ticketId);
        return params;
    }

    private HashMap<String, String> applyParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.APPLICATION);
        params.put("amount", "100");
        params.put("contact", "donald@ducks.burg");
        return params;
    }

    private HashMap<String, String> fetchParams(String ticketId) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", LoanHandler.FETCH);
        params.put("ticketId", ticketId);
        return params;
    }
}

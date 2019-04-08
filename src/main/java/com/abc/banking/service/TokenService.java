package com.abc.banking.service;

import com.abc.banking.dto.TokenRequest;
import java.util.*;

/**
 * @author Srinivasa
 */
public interface TokenService {

    public Map<Integer, List<Integer>> getAllActiveTokens();

    public Integer createToken(TokenRequest tokenRequest);

    public void createTokenComment(int tokenNumber, String comments);

    public void markTokenAsCancel(int tokenNumber);

    public void markTokenAsComplete(int tokenNumber);

}

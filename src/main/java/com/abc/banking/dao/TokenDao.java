package com.abc.banking.dao;

import com.abc.banking.model.Token;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 *
 * @author Srinivasa
 */
@Transactional
public interface TokenDao extends CrudRepository<Token, Long> {

    Token findByNumber(int number);

    List<Token> findByStatusCode(Token.StatusCode statusCode);
}

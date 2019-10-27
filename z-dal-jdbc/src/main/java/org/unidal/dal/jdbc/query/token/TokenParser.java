package org.unidal.dal.jdbc.query.token;

import java.util.List;

public interface TokenParser {
    List<Token> parse(String pattern);
}

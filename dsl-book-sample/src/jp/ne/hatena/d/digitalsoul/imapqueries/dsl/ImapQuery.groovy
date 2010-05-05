package jp.ne.hatena.d.digitalsoul.imapqueries.dsl;


import jp.ne.hatena.d.digitalsoul.imapqueries.model.ImapChannel;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.builder.AstBuilder;
import jp.ne.hatena.d.digitalsoul.imapqueries.dsl.ConditionVisitor;
import org.codehaus.groovy.syntax.Types; 

public class ImapQuery implements IConditionHandler {

    ImapChannel channel

    StringBuilder queryStatement = new StringBuilder("SEARCH")

    static void condition(ImapQuery query, List<ASTNode> condition) {
        ConditionVisitor visitor = new ConditionVisitor(query:query)
        condition[0].visit visitor
    }

    void execute() {
        channel.send(queryStatement.toString())
    }

    void addCondition(String prop, int operator, String value) {
        switch(prop) {
        case "subject":
            handleSubject(operator, value)
            break
        case "date":
            handleDate(operator, value)
            break
        case "from":
            handleFrom(operator, value)
            break
        default:
            throw new IllegalArgumentException("illegal description")
        }
    }

    void handleSubject(int operator, String value) {
        switch(operator) {
        case Types.COMPARE_EQUAL:
            queryStatement.append " subject " + '"' + value + '"'
            break
        }
    }

    void handleDate(int operator, String value) {
        switch(operator) {
        case Types.COMPARE_GREATER_THAN_EQUAL:
            queryStatement.append " sentsince " + value
            break
        case Types.COMPARE_EQUAL:
            queryStatement.append " senton " + value
            break
        case Types.COMPARE_LESS_THAN_EQUAL:
            queryStatement.append " sentbefore " + value
            break
        }        
    }

    void handleFrom(int operator, String value) {
        switch(operator) {
        case Types.COMPARE_EQUAL:
            queryStatement.append " from " + '"' + value + '"'
            break
        case Types.COMPARE_NOT_EQUAL:
            queryStatement.append " not from " + '"' + value + '"'
            break
        }
    } 
}

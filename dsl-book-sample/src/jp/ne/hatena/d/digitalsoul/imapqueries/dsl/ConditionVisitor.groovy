package jp.ne.hatena.d.digitalsoul.imapqueries.dsl;

import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.control.SourceUnit;


public class ConditionVisitor extends ClassCodeVisitorSupport {

    IConditionHandler query

    void visitExpressionStatement(ExpressionStatement statement) {
        statement.expression.visit this
    }

    void visitClosureExpression(ClosureExpression expression) {
        expression.code.visit this
    }

    void visitBlockStatement(BlockStatement statement) {
        statement.statements.each { it.visit this }
    }

    void visitBinaryExpression(BinaryExpression expression) {
        if (expression.leftExpression instanceof PropertyExpression) {
          def prop = expression.leftExpression.property.text
          def operator = expression.operation.type
          def value = expression.rightExpression.text
          query.addCondition(prop, operator, value)
        } 
    }
    
    SourceUnit getSourceUnit(){ }

}

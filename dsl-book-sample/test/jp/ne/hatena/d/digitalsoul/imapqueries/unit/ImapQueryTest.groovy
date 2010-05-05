package jp.ne.hatena.d.digitalsoul.imapqueries.unit;

import static jp.ne.hatena.d.digitalsoul.imapqueries.dsl.ImapQuery.condition;
import static jp.ne.hatena.d.digitalsoul.imapqueries.dsl.ImapQuery.builder;
import static org.junit.Assert.*;

import jp.ne.hatena.d.digitalsoul.imapqueries.dsl.ImapQuery;
import jp.ne.hatena.d.digitalsoul.imapqueries.service.ImapQueryService;

import org.codehaus.groovy.ast.builder.AstBuilder;
import org.codehaus.groovy.control.CompilePhase;
import org.junit.Test;

public class ImapQueryTest {

    MockChannel mockChannel = new MockChannel()

    AstBuilder b = new AstBuilder()
    
    /* DSLのテスト */
    
    @Test
    public void shouldSendQueryBySubject() throws Exception {

        // setup
        ImapQuery query = new ImapQuery(channel:mockChannel)
        condition(query, b.buildFromCode { q ->
            q.subject == "SUBJECT"
        })

        // execute
        query.execute()

        // verify
        assertEquals  'SEARCH subject "SUBJECT"', query.channel.message
        
    }

    @Test
    public void shouldSendQueryBySentSince() throws Exception {

        // setup
        ImapQuery query = new ImapQuery(channel:mockChannel)
        condition(query, b.buildFromCode { q ->
            q.date >= "24-Jan-2000"
        })
        
        // execute
        query.execute()

        // verify
        assertEquals  'SEARCH sentsince 24-Jan-2000', query.channel.message
        
    }

    @Test
    public void shouldSendQueryBySentOn() throws Exception {

        // setup
        ImapQuery query = new ImapQuery(channel:mockChannel)
        condition(query, b.buildFromCode { q ->
            q.date == "24-Jan-2000"
        })
        
        // execute
        query.execute()

        // verify
        assertEquals  'SEARCH senton 24-Jan-2000', query.channel.message
        
    }

    @Test
    public void shouldSendQueryBySentBefore() throws Exception {

        // setup
        ImapQuery query = new ImapQuery(channel:mockChannel)
        condition(query, b.buildFromCode { q ->
            q.date <= "24-Jan-2000"
        })
        
        // execute
        query.execute()

        // verify
        assertEquals  'SEARCH sentbefore 24-Jan-2000', query.channel.message
        
    }

    @Test
    public void shouldSendQueryByFrom() throws Exception {

        // setup
        ImapQuery query = new ImapQuery(channel:mockChannel)
        condition(query, b.buildFromCode { q ->
            q.from == "@hoge"
        })
        
        // execute
        query.execute()

        // verify
        assertEquals  'SEARCH from "@hoge"', query.channel.message
    }

    @Test
    public void shouldSendQueryByNotFrom() throws Exception {

        // setup
        ImapQuery query = new ImapQuery(channel:mockChannel)
        condition(query, b.buildFromCode { q ->
            q.from != "@hoge"
        })
        
        // execute
        query.execute()

        // verify
        assertEquals  'SEARCH not from "@hoge"', query.channel.message
    }

    /** サービスのテスト */

    @Test
    public void shouldSendQueryWhenServiceExecuted() throws Exception {

        // setup
        MockChannel channel = new MockChannel()
        ImapQueryService service = new ImapQueryService(channel:channel)

        // execute
        service.doExecute()

        // verify
        assertEquals 'SEARCH subject "entity framework" sentsince 23-jun-2008 not from "@gmail.com"', channel.message
        
    }
}

package jp.ne.hatena.d.digitalsoul.imapqueries.service;

import static jp.ne.hatena.d.digitalsoul.imapqueries.dsl.ImapQuery.condition;

import jp.ne.hatena.d.digitalsoul.imapqueries.dsl.ImapQuery;
import jp.ne.hatena.d.digitalsoul.imapqueries.model.ImapChannel;
import org.codehaus.groovy.ast.builder.AstBuilder;

public class ImapQueryService {

    ImapChannel channel

    AstBuilder b = new AstBuilder()
    
    void doExecute() {

        ImapQuery query = new ImapQuery(channel:channel)
        condition(query, b.buildFromCode { q -> 
            q.subject == "entity framework"
            q.date >= "23-jun-2008" 
            q.from != "@gmail.com" 
         })
        query.execute()
        
    }

}

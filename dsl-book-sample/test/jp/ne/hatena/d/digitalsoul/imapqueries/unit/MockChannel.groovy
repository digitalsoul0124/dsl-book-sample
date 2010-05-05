package jp.ne.hatena.d.digitalsoul.imapqueries.unit;

import jp.ne.hatena.d.digitalsoul.imapqueries.model.ImapChannel;

public class MockChannel implements ImapChannel {

    String message
    
    void send(String message) {
        println message
        this.message = message
    }
}

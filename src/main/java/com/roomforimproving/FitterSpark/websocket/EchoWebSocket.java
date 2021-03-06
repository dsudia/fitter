package com.roomforimproving.FitterSpark.websocket;

import com.roomforimproving.FitterSpark.twitter.SampleStream;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


/*
 * Created by davidsudia on 5/2/16.
 */
@WebSocket
public class EchoWebSocket {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<Session>();

    private static final SampleStream SAMPLE_STREAM = new SampleStream(sessions);

    @OnWebSocketConnect
    public void connected(Session session) throws IOException {
        sessions.add(session);
        session.getRemote().sendString("{\"message\":\"You're connected!\"}");
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
        System.out.println("Connection closed");
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        System.out.println("Got: " + message);
        if (Filterer.addOrReplaceFilter(session, new Filters(message))) {
            session.getRemote().sendString("{\"message\":\"Your filter was added! Tweets incoming!\"}");
        } else {
            session.getRemote().sendString("{\"message\":\"Invalid API key, try again!\"}");
        }
    }
}

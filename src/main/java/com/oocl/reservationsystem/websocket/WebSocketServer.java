package com.oocl.reservationsystem.websocket;


import com.oocl.reservationsystem.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {
    private static final Map<String, Session> clients = new ConcurrentHashMap<>();
    private static PositionService positionService;

    @Autowired
    public void setPositionService(PositionService positionService){
        WebSocketServer.positionService = positionService;
    }
}

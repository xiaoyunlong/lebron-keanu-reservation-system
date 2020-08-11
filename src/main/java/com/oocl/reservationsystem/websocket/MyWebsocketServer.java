package com.oocl.reservationsystem.websocket;

import com.google.gson.Gson;
import com.oocl.reservationsystem.dto.parkingdto.WebSocketRequest;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
@Component
public class MyWebsocketServer {

  private static final Map<String, Session> clients = new ConcurrentHashMap<>();
  private final Logger log = LoggerFactory.getLogger(MyWebsocketServer.class);
  @Autowired
  private ParkingService parkingService;

  @OnOpen
  public void onOpen(Session session) {
    log.info("有新的客户端连接了: {}", session.getId());
    // 将新用户存入在线的组
    clients.put(session.getId(), session);
  }

  @OnClose
  public void onClose(Session session) {
    log.info("有用户断开了, id为:{}", session.getId());
    // 将掉线的用户移除在线的组里
    clients.remove(session.getId());
  }

  @OnError
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @OnMessage
  public void onMessage(String message) {
    log.info("服务端收到客户端发来的消息: {}", message);
    Gson gson = new Gson();
    String webSocketRequestJson = message;
    WebSocketRequest webSocketRequest = gson.fromJson(webSocketRequestJson, WebSocketRequest.class);
    ParkingLot parkingLot = parkingService.updateParkingLotByParkingLotIdAndStatus(webSocketRequest);
    this.sendAll(gson.toJson(parkingLot));
  }

  private void sendAll(String message) {
    for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
      sessionEntry.getValue().getAsyncRemote().sendText(message);
    }
  }
}

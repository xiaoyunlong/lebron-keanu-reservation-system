package com.oocl.reservationsystem.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.reservationsystem.dto.parkingdto.WebSocketRequest;
import com.oocl.reservationsystem.entity.parkingentity.ParkingLot;
import com.oocl.reservationsystem.service.parkingservice.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

  private static ParkingService parkingService;

  @Autowired
  public void setParkingService(ParkingService parkingService) {
    MyWebsocketServer.parkingService = parkingService;
  }

  @OnOpen
  public void onOpen(Session session) {
    log.info("New Connection: {}", session.getId());
    // 将新用户存入在线的组
    clients.put(session.getId(), session);
  }

  @OnClose
  public void onClose(Session session) {
    log.info("Connection Lost, id为:{}", session.getId());
    // 将掉线的用户移除在线的组里
    clients.remove(session.getId());
  }

  @OnError
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @OnMessage
  public void onMessage(String message) {
    log.info("Receive Message From Client: {}", message);
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    WebSocketRequest webSocketRequest = gson.fromJson(message, WebSocketRequest.class);
    ParkingLot parkingLot = parkingService.updateParkingLotByParkingLotIdAndStatus(webSocketRequest);
    this.sendAll(gson.toJson(parkingLot));
  }

  private void sendAll(String message) {
    for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
      sessionEntry.getValue().getAsyncRemote().sendText(message);
    }
  }
}

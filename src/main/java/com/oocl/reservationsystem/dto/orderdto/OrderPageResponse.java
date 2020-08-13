package com.oocl.reservationsystem.dto.orderdto;

import java.util.List;

public class OrderPageResponse {
  private Integer totalLength;
  private List<OrderResponse> orderResponse;

  public Integer getTotalLength() {
    return totalLength;
  }

  public void setTotalLength(Integer totalLength) {
    this.totalLength = totalLength;
  }

  public List<OrderResponse> getOrderResponse() {
    return orderResponse;
  }

  public void setOrderResponse(List<OrderResponse> orderResponse) {
    this.orderResponse = orderResponse;
  }
}

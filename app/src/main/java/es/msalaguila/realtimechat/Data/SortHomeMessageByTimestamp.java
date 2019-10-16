package es.msalaguila.realtimechat.Data;

import java.util.Comparator;

public class SortHomeMessageByTimestamp implements Comparator<HomeMessage> {

  @Override
  public int compare(HomeMessage m1, HomeMessage m2) {
    return m2.getTimestamp().compareTo(m1.getTimestamp());
  }
}

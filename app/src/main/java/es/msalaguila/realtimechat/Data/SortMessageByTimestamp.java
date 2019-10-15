package es.msalaguila.realtimechat.Data;

import java.util.Comparator;

public class SortMessageByTimestamp implements Comparator<Message> {

  @Override
  public int compare(Message m1, Message m2) {
    return m1.getTimestamp().compareTo(m2.getTimestamp());
  }
}

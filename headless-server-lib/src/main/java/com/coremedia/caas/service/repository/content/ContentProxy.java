package com.coremedia.caas.service.repository.content;

import java.time.ZonedDateTime;
import java.util.List;

public interface ContentProxy extends ProxyObject {

  boolean isSubtypeOf(String typeName);


  String getId();

  String getName();

  String getType();


  ZonedDateTime getCreationDate();

  ZonedDateTime getModificationDate();


  Object get(String propertyName);


  BlobProxy getBlob(String propertyName);

  Boolean getBoolean(String propertyName);

  ZonedDateTime getDate(String propertyName);

  Integer getInteger(String propertyName);

  ContentProxy getLink(String propertyName);

  List<ContentProxy> getLinks(String propertyName);

  MarkupProxy getMarkup(String propertyName);

  String getString(String propertyName);

  StructProxy getStruct(String propertyName);
}

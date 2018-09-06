package com.coremedia.caas.service.repository.content;

import com.coremedia.caas.service.repository.ProxyFactory;
import com.coremedia.cap.common.Blob;
import com.coremedia.cap.content.Content;
import com.coremedia.cap.struct.Struct;
import com.coremedia.xml.Markup;

import com.google.common.base.MoreObjects;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static com.coremedia.caas.service.repository.content.util.ContentUtil.getZonedDateTime;
import static com.coremedia.caas.service.repository.content.util.ContentUtil.toZonedDateTime;

public class ContentProxyImpl implements ContentProxy {

  public static final Class<ContentProxyImpl> TARGET_CLASS = ContentProxyImpl.class;
  public static final Class[] TARGET_CLASSES = new Class[]{TARGET_CLASS};


  private final Content content;
  private final ProxyFactory proxyFactory;


  public ContentProxyImpl(Content content, ProxyFactory proxyFactory) {
    this.content = content;
    this.proxyFactory = proxyFactory;
  }


  @Override
  public boolean isSubtypeOf(String typeName) {
    return content.getType().isSubtypeOf(typeName);
  }


  @Override
  public String getId() {
    return content.getId();
  }

  @Override
  public String getName() {
    return content.getName();
  }

  @Override
  public String getType() {
    return content.getType().getName();
  }


  @Override
  public ZonedDateTime getCreationDate() {
    return toZonedDateTime(content.getCreationDate());
  }

  @Override
  public ZonedDateTime getModificationDate() {
    return toZonedDateTime(content.getModificationDate());
  }


  @Override
  public Object get(String propertyName) {
    return proxyFactory.makeProxy(content.get(propertyName));
  }


  @Override
  public BlobProxy getBlob(String propertyName) {
    Blob source = content.getBlob(propertyName);
    if (source != null) {
      return proxyFactory.makeBlobProxy(source);
    }
    return null;
  }

  @Override
  public Boolean getBoolean(String propertyName) {
    return content.getBoolean(propertyName);
  }

  @Override
  public ZonedDateTime getDate(String propertyName) {
    return getZonedDateTime(content, propertyName);
  }

  @Override
  public Integer getInteger(String propertyName) {
    return content.getInteger(propertyName);
  }

  @Override
  public ContentProxy getLink(String propertyName) {
    List<ContentProxy> contentProxies = getLinks(propertyName);
    if (!contentProxies.isEmpty()) {
      return contentProxies.get(0);
    }
    return null;
  }

  @Override
  public List<ContentProxy> getLinks(String propertyName) {
    return proxyFactory.makeContentProxyList(content.getLinks(propertyName));
  }

  @Override
  public MarkupProxy getMarkup(String propertyName) {
    Markup source = content.getMarkup(propertyName);
    if (source != null) {
      return proxyFactory.makeMarkupProxy(source);
    }
    return null;
  }

  @Override
  public String getString(String propertyName) {
    return content.getString(propertyName);
  }

  @Override
  public StructProxy getStruct(String propertyName) {
    Struct source = content.getStruct(propertyName);
    if (source != null) {
      return proxyFactory.makeStructProxy(source);
    }
    return null;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContentProxyImpl that = (ContentProxyImpl) o;
    return Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("content", content)
            .toString();
  }


  /*
   * Package private area for model creation
   */

  Content getContent() {
    return content;
  }

  Object getModel(String modelName, Object... arguments) {
    return proxyFactory.getRootContext().getModelFactory().createModel(modelName, this, arguments);
  }
}

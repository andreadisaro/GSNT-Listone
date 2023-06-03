package it.giochisulnostrotavolo.listone.configuration;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateBean {

  @Bean
  public RestTemplate restTemplate()
    throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    SSLContextBuilder builder = new SSLContextBuilder();
    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
      builder.build()
    );
    List<Header> defaultHeaders = new ArrayList<Header>();
    final Header headerJson = new BasicHeader(
      HttpHeaders.CONTENT_TYPE,
      "application/json"
    );
    final Header headerAuth = new BasicHeader(
      HttpHeaders.AUTHORIZATION,
      "Basic RGlnaXRhbEludGFrZTpOTW1La1M4dDh1azZjWU1G"
    );
    defaultHeaders.add(headerJson);
    defaultHeaders.add(headerAuth);
    CloseableHttpClient httpclient = HttpClients
      .custom()
      .setSSLSocketFactory(sslsf)
      .setDefaultHeaders(defaultHeaders)
      .build();

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

    requestFactory.setHttpClient(httpclient);
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    return restTemplate;
  }
}

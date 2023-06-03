// package it.giochisulnostrotavolo.listone.configuration;

// import io.sentry.SamplingContext;
// import io.sentry.SentryOptions.TracesSamplerCallback;
// import javax.servlet.http.HttpServletRequest;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// @Component
// class CustomTracesSamplerCallback implements TracesSamplerCallback {

//   @Value("${sentry.traces-sample-rate}")
//   Double defaultSampleRate;

//   @Override
//   public Double sample(SamplingContext context) {
//     HttpServletRequest request = (HttpServletRequest) context
//       .getCustomSamplingContext()
//       .get("request");
//     String url = request.getRequestURI();
//     if ("/public/api/heartBeat".equals(url)) {
//       // The health check endpoint is just noise - drop all transactions
//       return 0d;
//     } else if (url.startsWith("/public/api")) {
//       // Default sample rate
//       return defaultSampleRate;
//     } else {
//       // Drop everything else
//       return 0d;
//     }
//   }
// }

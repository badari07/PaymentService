## PaymentSerive

Spring Boot demo application for generating payment links at runtime using multiple third-party payment gateways (Stripe, Razorpay, etc.).

### Features
- `/payments/` POST endpoint that accepts an order payload and returns a payment link.
- Adapter-based architecture (`PaymentGatewayAdoper`) allowing you to plug in multiple providers.
- Stripe integration creates prices and payment links dynamically, with redirect support.
- Razorpay adapter stub returning a mock link—replace with real API calls as needed.

### Getting Started
1. Install dependencies
   ```
   ./mvnw clean install
   ```
2. Configure environment variables in `application.properties` or via shell:
   ```
   stripe.api.key=sk_test_xxx
   ```
3. Run the application
   ```
   ./mvnw spring-boot:run
   ```
4. Call the endpoint (requires Basic Auth from Spring Security default credentials unless overridden):
   ```
   curl -u user:<password> \
        -H "Content-Type: application/json" \
        -d '{"orderId":123,"provider":"stripe"}' \
        http://localhost:8080/payments/
   ```

### Folder Structure
- `src/main/java/com/example/paymentserive/controler` — REST controllers.
- `src/main/java/com/example/paymentserive/adpoter` — Gateway adapters and strategy.
- `src/main/java/com/example/paymentserive/dto` — Request/response DTOs.
- `src/main/java/com/example/paymentserive/service` — Business logic orchestrating adapters.

### Notes
- Stripe integration requires valid API keys. Ensure `stripe.api.key` is set.
- The generated spring security password logs on startup; change it for production use.
- To add a new provider, implement `PaymentGatewayAdoper` and Spring will wire it in automatically.


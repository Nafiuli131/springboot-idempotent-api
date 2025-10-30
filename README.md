# springboot-idempotent-api

🧪 Testing the API
1️⃣ Create Order (first call)
POST /api/orders?customerId=1
Header: Idempotency-Key: order-12345
Body:
{
  "product": "Laptop",
  "quantity": 1,
  "price": 1200.0
}


✅ Response:
{
  "id": 1,
  "product": "Laptop",
  "quantity": 1,
  "price": 1200.0,
  "idempotencyKey": "order-12345",
  "customer": { "id": 1, "name": "Nafiul Islam" }
}

2️⃣ Retry Same Request (same key)
POST /api/orders?customerId=1
Header: Idempotency-Key: order-12345
Body:
{
  "product": "Laptop",
  "quantity": 1,
  "price": 1200.0
}


⚡ System detects duplicate → returns same order response, not new one.
✅ No duplicate orders created.

# React + Spring Boot

# API Documentation: (during development)
http://localhost:8080/swagger.html

# Run this in CLI to enable stripe's webhook
stripe listen --forward-to localhost:8080/api/payment/webhook

# Redeploy
git commit --allow-empty -m "redeploy"
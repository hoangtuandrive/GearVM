import { useEffect, useState } from "react";

import { Elements } from "@stripe/react-stripe-js";
import CheckoutForm from "./CheckoutForm";
import { loadStripe } from "@stripe/stripe-js";

const PaymentForm = () => {
  const [stripePromise, setStripePromise] = useState("");
  const [clientSecret, setClientSecret] = useState("");

  useEffect(() => {
    setStripePromise(
      loadStripe(
        "pk_test_51MiHQsJZOhhkwf3d9fG4tsJ91ovMdWpxXNz0kYIMB5UVevfRFpGoQcgjYxuKiLTxtuqDr0CKApXPbTfRfa8KHOcr00Dj1lCuVV"
      )
    );
    console.log(stripePromise);
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/api/payment/true", {
      method: "POST",
      body: JSON.stringify({}),
    }).then(async (result) => {
      var clientSecret = await result.text();
      setClientSecret(clientSecret);
    });
    console.log(clientSecret);
  }, []);

  return (
    <>
      <h1>React Stripe and the Payment Element</h1>
      {clientSecret && stripePromise && (
        <Elements stripe={stripePromise} options={{ clientSecret }}>
          <CheckoutForm />
        </Elements>
      )}
    </>
  );
};

export default PaymentForm;

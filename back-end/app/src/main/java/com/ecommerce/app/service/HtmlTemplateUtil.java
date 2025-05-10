package com.ecommerce.app.service;

public class HtmlTemplateUtil {
    private static String template = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8" />
              <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
              <title>Order Confirmation</title>
              <style>
                body {
                  font-family: Arial, sans-serif;
                  background: #f4f4f4;
                  color: #333;
                  text-align: center;
                  padding: 40px;
                }
                .container {
                  background: #fff;
                  padding: 30px;
                  border-radius: 10px;
                  display: inline-block;
                  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                }
                h1 {
                  color: #4CAF50;
                }
                .order-info {
                  margin-top: 20px;
                  font-size: 18px;
                }
                .order-info p {
                  margin: 10px 0;
                }
              </style>
            </head>
            <body>
            
              <div class="container">
                <h1>Hi %s!</h1>
                <h3>Thank You for Your Order!</h3>
                <div class="order-info">
                  <p><strong>Tracking Number:</strong> #%s</p>
                  <p><strong>Quantity:</strong> %d</p>
                  <p><strong>Total:</strong> $%f</p>
                </div>
              </div>
            </body>
            </html>
            """;
    public static String getHtmlTemplate(String name, String trackingNo, Integer quantity, Double total) {
        return String.format(template,name, trackingNo,quantity, total);
    }
}
